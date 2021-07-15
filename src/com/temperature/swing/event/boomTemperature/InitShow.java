package com.temperature.swing.event.boomTemperature;

import com.temperature.swing.event.Event;
import com.temperature.swing.event.HeadStyleToolBar;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.CenterJPanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 15:40
 */
public class InitShow implements Event
{
	int pageId = 6;
	int headId = 2;
	int bodyId=2;

	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws JAXBException, ClassNotFoundException
	{
		event(settingTreeNode);

	}

	@Override
	public Object getImpObject()
	{
		return this;
	}


	public void event(SettingTreeNode settingTreeNode) throws JAXBException, ClassNotFoundException
	{
		HeadStyleToolBar headStyleToolBar = new HeadStyleToolBar(headId, pageId, settingTreeNode);
		headStyleToolBar.createStyle();
		CenterJPanel centerJPanel = new CenterJPanel();
		centerJPanel.setPageID(settingTreeNode.getPageId());
		centerJPanel.bodyJPanel.add(new JPanel());

		centerJPanel.headJPanel.add(headStyleToolBar.getJPanel());
		JPanelShow.jPanelShow(centerJPanel);
		try
		{
			settingTreeNode.getChildren().get(0).getEventObj().eventActive(settingTreeNode.getChildren().get(0));
		} catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}

	}
}
