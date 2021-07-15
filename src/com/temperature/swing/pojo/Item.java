package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Magic Book
 */
public class Item
{
	private String text;
	private int id;
	public Item()
	{

	}
	public Item(String text, int id)
	{
		this.text = text;
		this.id = id;
	}

	@Override
	public String toString()
	{
		return text;
	}

	public int getId()
	{
		return id;
	}
	@XmlElement(name = "id")
	public void setId(int id)
	{
		this.id = id;
	}

	public String getText()
	{
		return text;
	}
	@XmlElement(name = "text")

	public void setText(String text)
	{
		this.text = text;
	}
}
