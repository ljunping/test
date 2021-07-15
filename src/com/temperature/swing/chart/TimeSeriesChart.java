package com.temperature.swing.chart;

import com.temperature.swing.util.TimeType;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.xy.XYDataset;

import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/23 17:30
 */
public class TimeSeriesChart extends AbstractChart
{
	public String title = "测试";
	public String subTitle = "";
	public String xLabel = "时间";
	public String yLabel = "温度(℃)";
	public XYDataset dataset;
	boolean legend = true;
	boolean toolTip = true;
	boolean urlIs = false;

	public void defaultTheme()
	{
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		//设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("华文中宋", Font.BOLD, 24));
		//设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 18));
		//设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 18));
		com.temperature.swing.util.ChartFactory.setCurrentTheme(standardChartTheme);
	}

	@Override
	public JFreeChart getFreeChart(TimeType timeType)
	{

		switch (timeType)
		{
			case DAY:
				xLabel = "时间(时)";
				break;
			case YEAR:
				xLabel = "时间(月)";
				break;
			case MONTH:
				xLabel = "时间(日)";
				break;
		}
		JFreeChart chart =  com.temperature.swing.util.ChartFactory.createTimeSeriesChart(title, xLabel, yLabel, dataset,
				// create legend?
				true,
				// generate tooltips?
				true,
				// generate URLs?
				false);


		return chart;
	}

	public TimeSeriesChart(XYDataset dataset)
	{
		this.dataset = dataset;
		defaultTheme();
	}
}
