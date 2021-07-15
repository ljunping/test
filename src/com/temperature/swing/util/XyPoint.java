package com.temperature.swing.util;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/25 20:18
 */
public abstract class XyPoint
{

	protected String xValue;
	protected String yValue;
	public XyPoint(String x, String y)
	{
		this.xValue = x;
		this.yValue = y;
	}
	public abstract void initLabel();

	public String getX()
	{
		return xValue;
	}

	public String getY()
	{
		return yValue;
	}
}
