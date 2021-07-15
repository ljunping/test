package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Magic Book
 */
public class RectangleInfos extends Info implements Cloneable
{
	private List<RectangleInfo> rectangleInfos;


	@Override
	public RectangleInfos clone()
	{
		RectangleInfos rectangleInfos = new RectangleInfos();
		if (this.getRectangleInfos() != null)
		{
			List<RectangleInfo> rectangleIno1 = new ArrayList<>();
			rectangleInfos.setRectangleInfos(rectangleIno1);
			for (int i = 0; i < this.getRectangleInfos().size(); i++)
			{
				rectangleIno1.add(this.getRectangleInfos().get(i).clone());
			}

		}
		return rectangleInfos;
	}

	public List<RectangleInfo> getRectangleInfos()
	{
		return rectangleInfos;
	}
	@XmlElement(name="rectangleInfo")
	public void setRectangleInfos(List<RectangleInfo> rectangleInfos)
	{
		this.rectangleInfos = rectangleInfos;
	}
}
