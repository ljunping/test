package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class CycleInfos extends Info implements Cloneable
{
	private List<CycleInfo> cycleInfos;

	@Override
	public CycleInfos clone()
	{
		CycleInfos cycleInfos = new CycleInfos();
		if (this.getCycleInfos() != null)
		{
			List<CycleInfo> cycleInfoList = new ArrayList<>();
			cycleInfos.setCycleInfos(cycleInfoList);
			for (int i = 0; i < this.getCycleInfos().size(); i++)
			{
				cycleInfoList.add(this.getCycleInfos().get(i).clone());
			}

		}
		return cycleInfos;
	}

	public List<CycleInfo> getCycleInfos()
	{
		return cycleInfos;
	}
	@XmlElement(name = "cycleInfo")
	public void setCycleInfos(List<CycleInfo> cycleInfos)
	{
		this.cycleInfos = cycleInfos;
	}
}
