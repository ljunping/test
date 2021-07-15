package com.temperature.swing.event.ambientTemp;

import com.temperature.swing.event.JComBoxEvent;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 14:20
 */
public class JComBoxEvent1 extends JComBoxEvent
{


	@Override
	public void chartUpdate()
	{
		ChartShowEventIm1.isUpdate.set(true);
		ChartShowEventIm2.isUpdate.set(true);
		ChartShowEventIm3.isUpdate.set(true);
	}
}
