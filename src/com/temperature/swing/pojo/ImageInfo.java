package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Magic Book
 */
public class ImageInfo extends Info implements Cloneable
{
	private String imageUrl;

	private double x;
	private double y;
	private double width;
	private double height;
	private LineInfos lineInfos;
	private TextInfos textInfos;
	private ArrowLineInfos arrowLineInfos;
	private CycleInfos cycleInfos;
	private DottedInfos dottedInfos;
	private RectangleInfos rectangleInfos;
	private static final Map<String, Color> colorMap = new HashMap<>();

	static
	{
		colorMap.putIfAbsent("red", Color.RED);
		colorMap.putIfAbsent("black", Color.BLACK);
		colorMap.putIfAbsent("yellow", Color.YELLOW);
	}
	public static Map<String, Color> getColorMap()
	{
		return colorMap;
	}


	@Override
	public ImageInfo clone()
	{
		ImageInfo imageInfo = new ImageInfo(imageUrl, x, y, width, height);
		if (lineInfos != null)
		{
			imageInfo.setLineInfos(lineInfos.clone());
		}
		if (rectangleInfos != null)
		{
			imageInfo.setRectangleInfos(rectangleInfos.clone());
		}
		if (arrowLineInfos != null)
		{
			imageInfo.setArrowLineInfos(arrowLineInfos.clone());
		}
		if (dottedInfos != null)
		{
			imageInfo.setDottedInfos(dottedInfos.clone());
		}
		if (cycleInfos != null)
		{
			imageInfo.setCycleInfos(cycleInfos.clone());
		}
		if (textInfos != null)
		{
			imageInfo.setTextInfos(textInfos.clone());
		}
		return imageInfo;
	}

	public TextInfos getTextInfos()
	{
		return textInfos;
	}

	@XmlElement(name = "textInfos")
	public void setTextInfos(TextInfos textInfos)
	{
		this.textInfos = textInfos;
	}

//	public RectangleInfos getRectangleInfos()
//	{
//		return rectangleInfos;
//	}
//	@XmlElement(name = "rectangleInfos")
//	public void setRectangleInfos(RectangleInfos rectangleInfos)
//	{
//		this.rectangleInfos = rectangleInfos;
//	}


	public LineInfos getLineInfos()
	{
		return lineInfos;
	}

	@XmlElement(name = "lineInfos")
	public void setLineInfos(LineInfos lineInfos)
	{
		this.lineInfos = lineInfos;
	}

	public ArrowLineInfos getArrowLineInfos()
	{
		return arrowLineInfos;
	}

	@XmlElement(name = "arrowLineInfos")
	public void setArrowLineInfos(ArrowLineInfos arrowLineInfos)
	{
		this.arrowLineInfos = arrowLineInfos;
	}

	public CycleInfos getCycleInfos()
	{
		return cycleInfos;
	}

	@XmlElement(name = "cycleInfos")
	public void setCycleInfos(CycleInfos cycleInfos)
	{
		this.cycleInfos = cycleInfos;
	}

	public DottedInfos getDottedInfos()
	{
		return dottedInfos;
	}

	@XmlElement(name = "dottedInfos")
	public void setDottedInfos(DottedInfos dottedInfos)
	{
		this.dottedInfos = dottedInfos;
	}

	public ImageInfo(String imageUrl, double x, double y, double width, double height)
	{
		this.imageUrl = imageUrl;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void setUpdateAll(boolean flag)
	{
		this.setUpdate(flag);
		if (lineInfos != null)
		{
			lineInfos.setUpdate(flag);

		}
		if (textInfos != null)
		{
			textInfos.setUpdate(flag);
		}
		if (cycleInfos != null)
		{
			cycleInfos.setUpdate(flag);
		}
		if (dottedInfos != null)
		{
			dottedInfos.setUpdate(flag);
		}
		if (rectangleInfos != null)
		{
			rectangleInfos.setUpdate(flag);
		}
		if (textInfos != null)
		{
			textInfos.setUpdate(flag);
		}
		if (arrowLineInfos != null)
		{
			arrowLineInfos.setUpdate(flag);
		}
	}
	public ImageInfo()
	{

	}

	public ImageInfo(String imageUrl, int x, int y)
	{
		this.imageUrl = imageUrl;
		this.x = x;
		this.y = y;
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

	public String getImageUrl()
	{
		return imageUrl;
	}

	@XmlElement(name = "imageUrl")
	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public double getWidth()
	{
		return width;
	}

	@XmlElement(name = "width")
	public void setWidth(double width)
	{
		this.width = width;
	}

	@XmlElement(name = "height")
	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public RectangleInfos getRectangleInfos()
	{
		return rectangleInfos;
	}

	public void setRectangleInfos(RectangleInfos rectangleInfos)
	{
		this.rectangleInfos = rectangleInfos;
	}
}
