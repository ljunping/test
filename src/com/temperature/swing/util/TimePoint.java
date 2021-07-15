package com.temperature.swing.util;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/25 20:23
 */
public class TimePoint extends XyPoint
{
	protected static String xLabel;
	protected static String yLabel;
	public TimePoint(String x, String y)
	{
		super(x, y);
	}
	@Override
	public void initLabel()
	{
		xLabel = "time";
		yLabel = "温度(℃)";
	}
}
