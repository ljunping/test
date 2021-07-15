package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Magic Book
 */
public class TextInfos extends Info implements Cloneable
{
	private List<TextInfo> textInfos;


	@Override
	public TextInfos clone()
	{
		TextInfos textInfos = new TextInfos();
		if (this.getTextInfos() != null)
		{
			List<TextInfo> textInfos1 = new ArrayList<>();
			textInfos.setTextInfos(textInfos1);
			for (int i = 0; i < this.getTextInfos().size(); i++)
			{
				textInfos1.add(this.getTextInfos().get(i).clone());
			}
		}
		return textInfos;
	}

	public List<TextInfo> getTextInfos()
	{
		return textInfos;
	}
	@XmlElement(name = "textInfo")
	public void setTextInfos(List<TextInfo> textInfos)
	{
		this.textInfos = textInfos;
	}
}
