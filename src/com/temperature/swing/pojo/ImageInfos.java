package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Magic Book
 */
public class ImageInfos extends Info implements Cloneable
{
	private List<ImageInfo> imageInfos;

	public ImageInfos()
	{

	}
	@Override
	public ImageInfos clone()
	{
		ImageInfos imageInfos = new ImageInfos();
		imageInfos.setUpdate(isUpdate);
		if (this.getImageInfos() != null)
		{
			List<ImageInfo> imageInfo1 = new ArrayList<>();
			imageInfos.setImageInfos(imageInfo1);
			for (int i = 0; i < this.getImageInfos().size(); i++)
			{
				imageInfo1.add(this.getImageInfos().get(i).clone());
			}

		}
		return imageInfos;
	}
	@Override
	public boolean isUpdate()
	{
		return isUpdate;
	}

	@Override
	public void setUpdate(boolean update)
	{
		isUpdate = update;
	}

	public List<ImageInfo> getImageInfos()
	{
		return imageInfos;
	}
	@XmlElement(name = "imageInfo")
	public void setImageInfos(List<ImageInfo> imageInfos)
	{
		this.imageInfos = imageInfos;
	}
}
