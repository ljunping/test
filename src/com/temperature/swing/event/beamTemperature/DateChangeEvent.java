package com.temperature.swing.event.beamTemperature;

import com.temperature.swing.event.Event;
import com.temperature.swing.pojo.SettingTreeNode;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 14:14
 */
public class DateChangeEvent implements Event
{

	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		event();
	}

	@Override
	public Object getImpObject()
	{
		return null;
	}

	private void event()
	{

		ChartShowEventIm1.isUpdate.set(true);
		ChartShowEventIm2.isUpdate.set(true);
		ChartShowEventIm3.isUpdate.set(true);
	}
}
