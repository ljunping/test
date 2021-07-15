package com.temperature.swing.event.beamTemperature;

import com.temperature.swing.event.JComBoxEvent;

import javax.xml.bind.JAXBException;


/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 14:20
 */
public class JComBoxEvent2 extends JComBoxEvent
{


	@Override
	public void chartUpdate()
	{
		ChartShowEventIm1.isUpdate.set(true);
		ChartShowEventIm2.isUpdate.set(true);
		ChartShowEventIm3.isUpdate.set(true);
	}


}
