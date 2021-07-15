package com.temperature.swing.event.sensorLayout;

import com.temperature.swing.event.Event;
import com.temperature.swing.pojo.SettingTreeNode;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/4 11:17
 */
public class ReturnMainPage implements Event
{
	private final int pageId=2;
	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		returnMainPage(settingTreeNode);
	}

	@Override
	public Object getImpObject()
	{
		return null;
	}

	public void returnMainPage(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JAXBException
	{
		while (settingTreeNode.getParent() != null)
		{
			settingTreeNode = settingTreeNode.getParent();
		}

		SettingTreeNode mainNode = settingTreeNode.getChildren().get(pageId-1);
		mainNode.getEventObj().eventActive(mainNode);

	}
}
