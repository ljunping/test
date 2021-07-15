package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author Magic Book
 */
public class Items
{
	private List<Item>item;

	public List<Item> getItem()
	{
		return item;
	}

	@XmlElement(name = "item")
	public void setItem(List<Item> items)
	{
		this.item = items;
	}
}
