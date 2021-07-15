package com.temperature.swing.util;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/25 20:25
 */
public class TempPoint extends XyPoint
{
	public TempPoint(String x, String y)
	{
		super(x, y);
	}

	protected static String xLabel;
	protected static String yLabel;
	@Override
	public void initLabel()
	{
		xLabel = "温度";
		yLabel = "出现频率(%)";
	}
}
