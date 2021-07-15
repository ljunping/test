package com.temperature.swing.util;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/23 23:08
 */
public enum ChartType
{
	TimeSeriesChart(com.temperature.swing.chart.TimeSeriesChart.class),
	BarChart(com.temperature.swing.chart.BarChart.class);
	Class aclass;

	public String getLabel()
	{
		return  aclass.getName();

	}
	ChartType(Class c)
	{
		aclass=c;
	}
}
