package com.temperature.swing.pojo;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/6 18:16
 */
public abstract class Info
{
	protected boolean isUpdate = false;

	public boolean isUpdate()
	{
		return isUpdate;
	}

	public void setUpdate(boolean update)
	{
		isUpdate = update;
	}
}
