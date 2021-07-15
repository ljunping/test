package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class DottedInfos extends Info implements Cloneable
{
	private List<DottedInfo> dottedInfos;
	@Override
	public DottedInfos clone()
	{
		DottedInfos dottedInfos = new DottedInfos();
		if (this.getDottedInfos() != null)
		{
			List<DottedInfo> dottedInfoList = new ArrayList<>();
			dottedInfos.setDottedInfos(dottedInfoList);
			for (int i = 0; i < this.getDottedInfos().size(); i++)
			{
				dottedInfoList.add(this.getDottedInfos().get(i).clone());
			}

		}
		return dottedInfos;
	}

	public List<DottedInfo> getDottedInfos()
	{
		return dottedInfos;
	}
	@XmlElement(name="dottedInfo")
	public void setDottedInfos(List<DottedInfo> dottedInfos)
	{
		this.dottedInfos = dottedInfos;
	}
}
