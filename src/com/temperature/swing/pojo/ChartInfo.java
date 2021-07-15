package com.temperature.swing.pojo;

import com.temperature.swing.util.ChartType;
import com.temperature.swing.util.ValueType;

import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/25 21:00
 */
public class ChartInfo
{
	private List<String> curveName;
	private List<ValueType> valueTypes;
	private String title;
	private ChartType chartType;

	public ChartInfo(ChartType chartType, List<String> curveName, List<ValueType> valueTypes, String title)
	{
		this.chartType = chartType;
		this.curveName = curveName;
		this.valueTypes = valueTypes;
		this.title = title;
	}

	public ChartType getChartType()
	{
		return chartType;
	}

	public void setChartType(ChartType chartType)
	{
		this.chartType = chartType;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public List<ValueType> getValueTypes()
	{
		return valueTypes;
	}

	public void setValueTypes(List<ValueType> valueTypes)
	{
		this.valueTypes = valueTypes;
	}

	public List<String> getCurveName()
	{
		return curveName;
	}

	public void setCurveName(List<String> curveName)
	{
		this.curveName = curveName;
	}
}
