package com.temperature.swing.util;

import java.text.SimpleDateFormat;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/23 22:55
 */
public enum TimeType
{
	YEAR("一年",new SimpleDateFormat("yyyy")),MONTH("一月",new SimpleDateFormat("yyyy-MM")),
	DAY("24小时",new SimpleDateFormat("yyyy-MM-dd"));

	private String label;
	private SimpleDateFormat value;

	TimeType(String s, SimpleDateFormat simpleDateFormat)
	{
		this.label=s;
		this.value = simpleDateFormat;


	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public SimpleDateFormat getValue()
	{
		return value;
	}

	public void setValue(SimpleDateFormat value)
	{
		this.value = value;
	}

}
