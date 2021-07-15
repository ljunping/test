package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Magic Book
 */
public class DottedInfo implements Cloneable
{
	private double x1;
	private double x2;
	private double y1;
	private double y2;
	public DottedInfo()
	{

	}
	@Override
	public DottedInfo clone()
	{
		return new DottedInfo(x1, y1, x2, y2);
	}

	public DottedInfo(double x1, double y1, double x2, double y2)
	{

		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
	}

	public double getX1()
	{
		return x1;
	}
	@XmlElement(name = "x1")
	public void setX1(double x1)
	{
		this.x1 = x1;
	}

	public double getX2()
	{
		return x2;
	}
	@XmlElement(name = "x2")
	public void setX2(double x2)
	{
		this.x2 = x2;
	}

	public double getY1()
	{
		return y1;
	}
	@XmlElement(name = "y1")
	public void setY1(double y1)
	{
		this.y1 = y1;
	}

	public double getY2()
	{
		return y2;
	}
	@XmlElement(name = "y2")
	public void setY2(double y2)
	{
		this.y2 = y2;
	}
}
