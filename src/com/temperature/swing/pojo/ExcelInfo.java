package com.temperature.swing.pojo;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/20 15:43
 */
public class ExcelInfo
{
	private int startRow;
	private int startClum;
	private int endRow;
	private int endClum;

	public ExcelInfo(int startRow, int startClum, int endRow, int endClum)
	{
		this.startClum = startClum;
		this.startRow = startRow;
		this.endClum = endClum;
		this.endRow = endRow;
	}

	public ExcelInfo()
	{

	}


	public int getStartRow()
	{
		return startRow;
	}

	public void setStartRow(int startRow)
	{
		this.startRow = startRow;
	}

	public int getStartClum()
	{
		return startClum;
	}

	public void setStartClum(int startClum)
	{
		this.startClum = startClum;
	}

	public int getEndRow()
	{
		return endRow;
	}

	public void setEndRow(int endRow)
	{
		this.endRow = endRow;
	}

	public int getEndClum()
	{
		return endClum;
	}

	public void setEndClum(int endClum)
	{
		this.endClum = endClum;
	}
}
