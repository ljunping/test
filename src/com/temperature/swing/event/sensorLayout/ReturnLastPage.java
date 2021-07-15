package com.temperature.swing.event.sensorLayout;

import com.temperature.swing.event.Event;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.MyJFrame;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/4 11:13
 */
public class ReturnLastPage implements Event
{
	private final int pageId=0;
	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		returnLastPage(settingTreeNode);
	}

	@Override
	public Object getImpObject()
	{
		return null;
	}

	public void returnLastPage(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JAXBException
	{
		int pageId = settingTreeNode.getPageId();

		SettingTreeNode parent = MyJFrame.settingTreeNodes[pageId - 2].getParent();
		if (parent.getPageId() == pageId)
		{
			parent.getEventObj().eventActive(parent);
		}


	}
}
