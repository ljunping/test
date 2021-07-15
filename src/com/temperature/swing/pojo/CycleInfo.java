package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Magic Book
 */
public class CycleInfo implements Cloneable
{
	private double x;
	private double y;
	private double r;
	private String color = "black";
	public String getColor()
	{
		return color;
	}
	@XmlElement(name = "color")
	public void setColor(String color)
	{
		this.color = color;
	}

	public CycleInfo()
	{

	}

	public CycleInfo(double x, double y, double r, String color)
	{
		this.color = color;
		this.x = x;
		this.y = y;
		this.r = r;
	}

	@Override
	public CycleInfo clone()
	{
		return new CycleInfo(x, y, r,color);
	}

	public double getX()
	{
		return x;
	}

	@XmlElement(name = "x")

	public void setX(double x)
	{
		this.x = x;
	}


	public double getY()
	{
		return y;
	}

	@XmlElement(name = "y")
	public void setY(double y)
	{
		this.y = y;
	}

	public double getR()
	{
		return r;
	}

	@XmlElement(name = "r")
	public void setR(double r)
	{
		this.r = r;
	}
}
