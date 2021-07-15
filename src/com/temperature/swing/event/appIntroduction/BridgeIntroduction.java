package com.temperature.swing.event.appIntroduction;

import com.temperature.swing.event.Event;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.pojo.SettingTreeNode;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/19 17:58
 */
public class BridgeIntroduction implements Event
{
	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		JPanel jPanel = new JPanel();
		JPanelShow.bodyJPanelShow(jPanel, settingTreeNode.getPageId());
	}

	@Override
	public Object getImpObject() throws JAXBException, ClassNotFoundException
	{
		return null;
	}
}
