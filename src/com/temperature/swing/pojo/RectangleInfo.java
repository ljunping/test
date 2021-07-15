package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.awt.*;

/**
 * @author Magic Book
 */
public class RectangleInfo implements Cloneable
{

	private double x;
	private double y;
	private double width;
	private double height;
	private int id=-1;
	private String text;
	private Color color=new Color(40, 184, 184);
	private int imageId;


	public RectangleInfo()
	{

	}
	public RectangleInfo(double x,double y,double width,double height)
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}

	public RectangleInfo(double x, double y, double width, double height, int imageId, String text, int id, Color color)
	{
		this.imageId = imageId;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.id = id;
		this.text = text;
	}

	@Override
	public RectangleInfo clone()
	{
		return new RectangleInfo(x, y, width, height, imageId, text, id, color);
	}

	public RectangleInfo(double x, double y, double width, double height, int imageId)
	{
		this.imageId = imageId;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public double getMidX()
	{
		return x + width / 2.0;
	}
	public double getMidY()
	{
		return y + height / 2.0;
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

	public double getWidth()
	{
		return width;
	}
	@XmlElement(name = "height")
	public void setWidth(double width)
	{
		this.width = width;
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

	public double getHeight()
	{
		return height;
	}

	@XmlElement(name = "width")
	public void setHeight(double height)
	{
		this.height = height;
	}

	public int getId()
	{
		return id;
	}
	@XmlElement(name = "id")
	public void setId(int id)
	{
		this.id = id;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public Rectangle getRectangle()
	{
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}

	public String getText()
	{
		return text;
	}
	@XmlElement(name = "text")
	public void setText(String text)
	{
		this.text = text;
	}

	public int getImageId()
	{
		return imageId;
	}

	public void setImageId(int imageId)
	{
		this.imageId = imageId;
	}
}
