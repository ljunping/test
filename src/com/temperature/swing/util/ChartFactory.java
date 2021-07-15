package com.temperature.swing.util;

import com.temperature.swing.chart.BarChart;
import com.temperature.swing.chart.TimeSeriesChart;
import com.temperature.swing.pojo.DataInfo;
import com.temperature.swing.wrapJComponent.CheckXYPlot;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.*;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.Range;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/7 0:45
 */
public class ChartFactory extends org.jfree.chart.ChartFactory
{
	private static StandardChartTheme currentTheme = new StandardChartTheme("JFree");
	public static List<Color> colors = new ArrayList<>();
	public static TimeType timeType;
	public static Date time;
	static String title;
	private static final Logger logger = Logger.getLogger(ChartFactory.class);

	public static ChartPanel getTimeChart(TimeSeriesCollection timeSeriesCollection, DataInfo dataInfo)
	{
		logger.debug("进入ChartFactory");
		TimeSeriesChart chartUtil;
		timeType = dataInfo.getTimeType();
		time = dataInfo.getTime();
		title = dataInfo.getChartInfo().getTitle();

		chartUtil = new TimeSeriesChart(timeSeriesCollection);
		chartUtil.title = title;
		JFreeChart chart = chartUtil.getFreeChart(timeType);
		timeChartSet(chart);

		ChartPanel chartPanel = new ChartPanel(chart);
		logger.debug("创建chartJPanel成功");
		return chartPanel;
	}

//	public static JPanel getCheckChartJPanel(JFreeChart chart)
//	{
//		LegendTitle legendTitle = chart.getLegend();
//		BlockContainer blockContainer = legendTitle.getItemContainer();
//		blockContainer.add(new ChartCheckBox(new Rectangle(10, 10, 10, 10)));
//
//		LegendItemSource[] sources = legendTitle.getSources();
//		for (int i = 0; i < sources.length; i++)
//		{
//			LegendItemSource legendItemSource = sources[i];
////			if (legendItemSource != null)
////			{
////				legendItemSource.
////			}
//		}
//	}

	public static ChartPanel getBarChart(XYSeriesCollection dataset, DataInfo dataInfo)
	{
		BarChart chartUtil;
		timeType = dataInfo.getTimeType();
		title = dataInfo.getChartInfo().getTitle();
		time = dataInfo.getStartTime();
		chartUtil = new BarChart(dataset);
		chartUtil.title =  title;
		JFreeChart chart = chartUtil.getFreeChart(timeType);
		ChartPanel chartPanel = new ChartPanel(chart);

		barChartSet(chart);
		return chartPanel;
	}

	public static JFreeChart createTimeSeriesChart(String title, String timeAxisLabel, String valueAxisLabel, XYDataset dataset, boolean legend, boolean tooltips, boolean urls)
	{
		ValueAxis timeAxis = new DateAxis(timeAxisLabel);
		timeAxis.setLowerMargin(0.02D);
		timeAxis.setUpperMargin(0.02D);
		NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
		valueAxis.setAutoRangeIncludesZero(false);
		XYPlot plot = new CheckXYPlot(dataset, timeAxis, valueAxis, null);
		XYToolTipGenerator toolTipGenerator = null;
		if (tooltips)
		{
			toolTipGenerator = StandardXYToolTipGenerator.getTimeSeriesInstance();
		}

		XYURLGenerator urlGenerator = null;
		if (urls)
		{
			urlGenerator = new StandardXYURLGenerator();
		}

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
		renderer.setBaseToolTipGenerator(toolTipGenerator);
		renderer.setURLGenerator(urlGenerator);
		renderer.setBaseShapesFilled(false);
//		renderer.setSeriesShapesFilled(0,false);
		renderer.setUseFillPaint(false);
		plot.setRenderer(renderer);
		JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);

		currentTheme.apply(chart);
		return chart;
	}
//

	public static void barChartSet(JFreeChart chart)
	{
		chart.setBackgroundPaint(Color.white);
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setDomainGridlinePaint(Color.white);
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
		plot.setRangeGridlinePaint(Color.white);
		plot.getDomainAxis().setTickMarkOutsideLength(5);
		plot.getRangeAxis().setTickMarkOutsideLength(5);
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
		XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);
		renderer.setMargin(0.5);

		renderer.setSeriesPaint(0, new Color(0, 0, 143));
		//取消柱子的阴影效果
		renderer.setShadowVisible(false);
		//取消渐变色
		renderer.setBarPainter(new StandardXYBarPainter());

		NumberAxis XAxis = (NumberAxis) plot.getDomainAxis();

		XAxis.setTickUnit(getTickUnit(XAxis.getRange()));


//		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		if (plot.getLegendItems().getItemCount() == 1)
		{
			chart.getLegend().setVisible(false);

		}
		chart.getLegend().setFrame(BlockBorder.NONE);
	}


	public static void timeChartSet(JFreeChart chart)
	{
		chart.setBackgroundPaint(Color.white);
		//legend设置
		XYPlot plot = (XYPlot) chart.getPlot();
		XYLineAndShapeRenderer xyLinerenderer = (XYLineAndShapeRenderer) plot.getRenderer();
		Color color1 = new Color(0, 0, 255);
		xyLinerenderer.setSeriesPaint(0, color1);
		colors.add(color1);
		Color color2 = new Color(13, 210, 56);
		colors.add(color2);
		xyLinerenderer.setSeriesPaint(1, color2);
		Color color3 = new Color(239, 11, 190);
		colors.add(color3);
		xyLinerenderer.setSeriesPaint(2, color3);
		Color color4 = new Color(238, 17, 17);
		colors.add(color4);
		xyLinerenderer.setSeriesPaint(3, color4);
		Color color5 = new Color(1, 248, 211);
		colors.add(color5);
		xyLinerenderer.setSeriesPaint(4, color5);

		Color color6 = new Color(120, 5, 252, 141);
		colors.add(color6);
		xyLinerenderer.setSeriesPaint(5, color6);
		Color color7 = new Color(0, 0, 0);
		colors.add(color7);
		xyLinerenderer.setSeriesPaint(6, color7);
		Color color8 = new Color(241, 203, 8, 255);
		colors.add(color8);
		xyLinerenderer.setSeriesPaint(7, color8);
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		LegendTitle sources = chart.getLegend();
		sources.setItemLabelPadding(new RectangleInsets(5, 0, 5, 0));
		sources.setPosition(RectangleEdge.BOTTOM);
		sources.setLegendItemGraphicPadding(new RectangleInsets(5, 40, 5, 0));
		plot.setFixedLegendItems(plot.getLegendItems());
		plot.setAxisOffset(new RectangleInsets(0, 0, 0, 0));

		for (int i = 0; i < plot.getLegendItems().getItemCount(); i++)
		{

			plot.getRenderer().setSeriesStroke(i, new BasicStroke(1F));
			if (plot.getLegendItems().get(i).getLabel().startsWith("设计值"))
			{
				plot.getRenderer().setSeriesStroke(i, new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[]{6.0f, 6.0f}, 0.0f));
			}
			((XYLineAndShapeRenderer) plot.getRenderer()).setSeriesShapesFilled(i, false);

		}
		if (plot.getLegendItems().getItemCount() == 1)
		{
			chart.getLegend().setVisible(false);
		}

		plot.setRangeCrosshairVisible(false);
		plot.setDomainCrosshairVisible(false);
		plot.setRangeMinorGridlinesVisible(false);
		plot.setDomainGridlinesVisible(true);
		plot.setDomainMinorGridlinesVisible(false);
		plot.setRangeGridlinesVisible(true);
		plot.setOutlineVisible(true);
		plot.setDomainZeroBaselineVisible(false);
		plot.setRangeZeroBaselineVisible(false);


		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
//		yAxis.setAutoTickUnitSelection(true);
//		yAxis.setAutoRange(false);
//		Range range = yAxis.getRange();
//		NumberTickUnit tickUint = getTickUnit(range);
//		yAxis.setTickUnit(tickUint);
////		yAxis.setRange(getRange(range, tickUint));
////		yAxis.configure();
//		yAxis.setRange(0, 20000);
//		yAxis.setRangeWithMargins(getRange(range, tickUint));

		plot.getDomainAxis().setLowerMargin(0);
		plot.getDomainAxis().setUpperMargin(0.000001);
		plot.getDomainAxis().setTickMarkOutsideLength(5);

		plot.getRangeAxis().setTickMarkOutsideLength(5);
		plot.getRangeAxis().setMinorTickMarksVisible(false);

		XYItemRenderer r = plot.getRenderer();

		if (r instanceof XYLineAndShapeRenderer)
		{
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(true);
			renderer.setBaseShapesFilled(true);
			renderer.setDrawSeriesLineAsPath(true);
		}


		DateAxis axis = (DateAxis) plot.getDomainAxis();


		String pattern = null;
		int unit = DateTickUnit.DAY;

		switch (timeType)
		{
			case DAY:
				unit = DateTickUnit.HOUR;
				pattern = "HH";
				break;
			case YEAR:
				unit = DateTickUnit.MONTH;
				pattern = "MM";
				break;
			case MONTH:
				unit = DateTickUnit.DAY;
				pattern = "dd";
				break;
		}
		axis.setDateFormatOverride(new SimpleDateFormat(pattern));

		axis.setTickUnit(new DateTickUnit(unit, 1, new SimpleDateFormat(pattern)));
	}

	private static Range getRange(Range range, NumberTickUnit tickUint)
	{
		double uint = tickUint.getSize();
		double up = range.getUpperBound();
		double low = range.getLowerBound();
		double nLow = (int) ((low / uint)-1) * uint-10;
		double nUp = (int) ((up / uint) + 1) * uint;
		return new Range(nLow, nUp);
	}

	private static NumberTickUnit getTickUnit(Range range)
	{
//		double up = range.getUpperBound();
//		double down = range.getLowerBound();
		double length = range.getLength();
		if (length > 100)
		{
			return new NumberTickUnit((((int) (length + 1) / 10) / 10 + 1) * 10);
		} else if (length > 20 && length <= 100)
		{
			return new NumberTickUnit(5.0);
		} else if (length <= 20 && length > 10)
		{
			return new NumberTickUnit(2.0);
		} else if (length <= 10 && length > 1)
		{
			return new NumberTickUnit(1.0);
		} else if (length <= 1 && length > 0.1)
		{
			return new NumberTickUnit(0.1);
		} else
		{
			return new NumberTickUnit(length / 10);
		}
	}


	private static String getTitle(TimeType timeType)
	{

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);

		String year = "" + calendar.get(Calendar.YEAR);
		String month = "" + (calendar.get(Calendar.MONTH) + 1);
		String day = "" + calendar.get(Calendar.DATE);
		String title = "";
		switch (timeType)
		{
			case YEAR:
				title = year + "年";
				break;
			case DAY:
				title = year + "年" + month + "月" + day + "日";
				break;
			case MONTH:
				title = year + "年" + month + "月";
				break;
		}
		return title;
	}


	public static void setCurrentTheme(StandardChartTheme currentTheme)
	{
		ChartFactory.currentTheme = currentTheme;
	}
}


