package com.temperature.swing.pojo;

import sun.font.FontDesignMetrics;

import javax.xml.bind.annotation.XmlElement;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Magic Book
 */
public class TextInfo implements Cloneable
{
	private String text;
	private double x;
	private double y;
	private double width;
	private double height;
	private double size=20;
	private Font defaultFont = new Font("楷体", Font.PLAIN, 20);
	private int imageId = 0;
	private double scale = 1.0;
	private String color = "black";
	private RectangleInfos rectangleInfos = null;
	FontDesignMetrics metrics = FontDesignMetrics.getMetrics(defaultFont);

	public void setDefaultFont(int size)
	{
		this.defaultFont = new Font("楷体", Font.PLAIN, size);
		metrics = FontDesignMetrics.getMetrics(defaultFont);
	}

	public void setMetrics(int size)
	{

	}


	public TextInfo()
	{


	}

	public TextInfo(double x, double y, double width, double height, String text, int imageId, Font defaultFont, String color, double size)
	{
		this.color = color;
		this.size = size;
		this.text = "" + text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imageId = imageId;
		this.defaultFont = defaultFont;

	}

	@Override
	public TextInfo clone()
	{
		return new TextInfo(x, y, width, height, text, imageId, defaultFont,color,size);
	}

	public TextInfo(String text, double x, double y)
	{
		this.text = text;
		this.x = x;
		this.y = y;
	}

	public RectangleInfos getRecs()
	{
		getWidth();
		getHeight();
		if (rectangleInfos == null)
		{
			rectangleInfos = new RectangleInfos();
			rectangleInfos.setRectangleInfos(new ArrayList<>());
			rectangleInfos.getRectangleInfos().add(new RectangleInfo(x , y-height, width, height, imageId));
		}else
		{
			rectangleInfos.getRectangleInfos().get(0).setX(x);
			rectangleInfos.getRectangleInfos().get(0).setY(y-height);
			rectangleInfos.getRectangleInfos().get(0).setWidth(width+height);
			rectangleInfos.getRectangleInfos().get(0).setHeight(height);
		}

		return rectangleInfos;
	}

	public double getMidX()
	{
		getWidth();

		return x + width / 2.0;
	}

	public double getMidY()
	{
		getHeight();
		return y - height / 2.0;
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

	public String getColor()
	{
		return color;
	}

	@XmlElement(name = "color")
	public void setColor(String color)
	{
		this.color = color;
	}

	public Font getDefaultFont()
	{
		this.defaultFont = new Font("楷体", Font.PLAIN, (int) size);
		return defaultFont;
	}

	public void setDefaultFont(Font defaultFont)
	{
		this.defaultFont = defaultFont;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public double getWidth()
	{
		this.width = metrics.stringWidth(text)*scale;
		return width;
	}

	public double getHeight()
	{
		this.height = (metrics.getAscent() + metrics.getDescent())*scale;
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public int getImageId()
	{
		return imageId;
	}

	public void setImageId(int imageId)
	{
		this.imageId = imageId;
	}

	public double getSize()
	{
		return size;
	}
	@XmlElement(name = "size")
	public void setSize(double size)
	{
		this.size = size;
	}


	public double getScale()
	{
		return scale;
	}

	public void setScale(double scale)
	{
		this.scale = scale;
	}
}
