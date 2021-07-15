package com.temperature.swing.chart;

import com.temperature.swing.util.TimeType;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/23 17:45
 */
public class BarChart extends com.temperature.swing.chart.AbstractChart
{
	public String title="测试";
	public String subTitle="";
	public String xLabel="日温度变化范围";
	public String yLabel="出现频率(%)";
	public XYSeriesCollection dataset;

	public void defaultTheme()
	{
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		//设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("华文中宋", Font.BOLD, 24));
		//设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 18));
		//设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 18));


		ChartFactory.setChartTheme(standardChartTheme);
	}

	@Override
	public JFreeChart getFreeChart(TimeType timeType)
	{

		JFreeChart chart = ChartFactory.createXYBarChart(title, xLabel, false,yLabel, dataset);
//		chart.addSubtitle(new TextTitle("Time to generate 1000 charts in SVG "
//				+ "format (lower bars = better performance)"));
		return chart;
	}

	public BarChart(XYSeriesCollection dataset)
	{
		this.dataset = dataset;
		defaultTheme();
	}
}
