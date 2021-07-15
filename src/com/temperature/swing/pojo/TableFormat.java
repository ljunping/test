package com.temperature.swing.pojo;

import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/23 15:59
 */
public class TableFormat
{
	private String tableName;
	private List<String> clumName;
	private List<String> clumFormat;

	public TableFormat(String tableName,List<String> clumName, List<String> clumFormat)
	{
		this.tableName = tableName;
		this.clumFormat = clumFormat;
		this.clumName = clumName;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public List<String> getClumName()
	{
		return clumName;
	}

	public void setClumName(List<String> clumName)
	{
		this.clumName = clumName;
	}

	public List<String> getClumFormat()
	{
		return clumFormat;
	}

	public void setClumFormat(List<String> clumFormat)
	{
		this.clumFormat = clumFormat;
	}
}
