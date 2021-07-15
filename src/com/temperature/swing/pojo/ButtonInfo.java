package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 16:46
 */
public class ButtonInfo implements Cloneable
{
	private int type;
	private String text;
	private Items items;
	private int id;
	private int in;
	private int deep;

	public ButtonInfo(int type, int id, int deep, int in)
	{
		this.type = type;
		this.id = id;
		this.deep = deep;
		this.in = in;
	}

	@Override
	public ButtonInfo clone()
	{
		ButtonInfo buttonInfo = new ButtonInfo(type, id, deep, in);
		buttonInfo.text = "" + text;
		buttonInfo.items = items;
		return buttonInfo;
	}
	public ButtonInfo()
	{

	}
	public ButtonInfo(String text, int id)
	{
		this.id = id;
		this.text = text;

	}
	public ButtonInfo(String text, int id, int deep)
	{
		this.id = id;
		this.text = text;
		this.deep = deep;

	}
	public int getType()
	{
		return type;
	}
	@XmlElement(name = "type")
	public void setType(int type)
	{
		this.type = type;
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


	public Items getItems()
	{
		return items;
	}
	@XmlElement(name = "items")
	public void setItems(Items items)
	{
		this.items = items;
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

	public int getIn()
	{
		return in;
	}
	@XmlElement(name = "in")
	public void setIn(int in)
	{
		this.in = in;
	}

	public int getDeep()
	{
		return deep;
	}
	@XmlElement(name = "deep")
	public void setDeep(int deep)
	{
		this.deep = deep;
	}
}
