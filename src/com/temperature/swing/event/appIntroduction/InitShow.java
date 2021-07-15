package com.temperature.swing.event.appIntroduction;

import com.temperature.swing.event.Event;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.pojo.ButtonInfo;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.CenterJPanel;
import com.temperature.swing.wrapJComponent.ToolBarButton;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/19 17:57
 */
public class InitShow implements Event

{
	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		CenterJPanel centerJPanel = new CenterJPanel();
		JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Box hBox = Box.createHorizontalBox();
		hBox.add(Box.createHorizontalStrut(10));
		hBox.add(new ToolBarButton(new ButtonInfo("序言", 0), "ToolBarButton",settingTreeNode.getChildren().get(0)));
		hBox.add(Box.createHorizontalStrut(20));
		hBox.add(new ToolBarButton(new ButtonInfo("桥梁介绍", 1), "ToolBarButton",settingTreeNode.getChildren().get(1)));
		hBox.add(Box.createHorizontalStrut(20));
		hBox.add(new ToolBarButton(new ButtonInfo("软件说明", 2), "ToolBarButton",settingTreeNode.getChildren().get(2)));
		jPanel.add(hBox);
		jPanel.setBackground(new Color(17, 64, 86));
		centerJPanel.setPageID(settingTreeNode.getPageId());
		centerJPanel.headJPanel.add(jPanel);
		JPanelShow.jPanelShow(centerJPanel);
		settingTreeNode.getChildren().get(0).getEventObj().eventActive(settingTreeNode.getChildren().get(0));
	}


	@Override
	public Object getImpObject() throws JAXBException, ClassNotFoundException
	{
		return null;
	}
}
