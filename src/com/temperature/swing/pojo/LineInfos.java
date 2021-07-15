package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Magic Book
 */
public class LineInfos extends Info implements Cloneable
{
	private List<LineInfo> lineInfos;

	@Override
	public LineInfos clone()
	{
		LineInfos lineInfos = new LineInfos();
		if (this.getLineInfos() != null)
		{
			List<LineInfo> lineInfos1 = new ArrayList<>();
			lineInfos.setLineInfos(lineInfos1);
			for (int i = 0; i < this.getLineInfos().size(); i++)
			{
				lineInfos1.add(this.getLineInfos().get(i).clone());
			}

		}
		return lineInfos;
	}

	public List<LineInfo> getLineInfos()
	{
		return lineInfos;
	}
	@XmlElement(name="lineInfo")
	public void setLineInfos(List<LineInfo> lineInfos)
	{
		this.lineInfos = lineInfos;
	}
}
