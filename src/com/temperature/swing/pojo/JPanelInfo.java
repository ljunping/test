package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Magic Book
 */
@XmlRootElement(name = "JPanelInfo")
public class JPanelInfo implements Cloneable
{
	private ImageInfos imageInfos;


	public ImageInfos getImageInfos()
	{
		return imageInfos;
	}

	@XmlElement(name = "imageInfos")
	public void setImageInfos(ImageInfos imageInfos)
	{
		this.imageInfos = imageInfos;
	}

	@Override
	public JPanelInfo clone()
	{
		JPanelInfo jPanelInfo = new JPanelInfo();
		if (imageInfos != null)
		{
			jPanelInfo.setImageInfos(imageInfos.clone());

		}
		return jPanelInfo;
	}


}


