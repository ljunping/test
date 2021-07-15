package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Magic Book
 */
public class ArrowLineInfos extends Info implements Cloneable
{
	private List<ArrowLineInfo>arrowLineInfos;

	@Override
	public ArrowLineInfos clone()
	{
		ArrowLineInfos arrowLineInfos = new ArrowLineInfos();
		if (this.getArrowLineInfos() != null)
		{
			List<ArrowLineInfo> arrowLineInfoList = new ArrayList<>();
			arrowLineInfos.setArrowLineInfos(arrowLineInfoList);
			for (int i = 0; i < this.getArrowLineInfos().size(); i++)
			{
				arrowLineInfoList.add(this.getArrowLineInfos().get(i).clone());
			}

		}
		return arrowLineInfos;
	}


	public ArrowLineInfos()
	{

	}
	public List<ArrowLineInfo> getArrowLineInfos()
	{
		return arrowLineInfos;
	}
	@XmlElement(name = "arrowLineInfo")
	public void setArrowLineInfos(List<ArrowLineInfo> arrowLineInfos)
	{
		this.arrowLineInfos = arrowLineInfos;
	}
}
