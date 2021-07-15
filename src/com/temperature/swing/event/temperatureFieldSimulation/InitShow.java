package com.temperature.swing.event.temperatureFieldSimulation;

import com.temperature.swing.event.BodyStyleFigure;
import com.temperature.swing.event.Event;
import com.temperature.swing.event.HeadStyleToolBar;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.util.PaddingUnit;
import com.temperature.swing.wrapJComponent.CenterJPanel;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 15:40
 */
public class InitShow implements Event
{
	int pageId = 8;
	int headId = 2;
	int bodyId = 2;

	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		BodyStyleFigure bodyStyleFigure = new BodyStyleFigure(bodyId, pageId);
		bodyStyleFigure.createStyle();
		bodyStyleFigure.getFigureJPanel().setIsText(true);
		HeadStyleToolBar headStyleToolBar = new HeadStyleToolBar(headId, pageId, settingTreeNode);
		headStyleToolBar.createStyle();
		CenterJPanel centerJPanel = new CenterJPanel();
		centerJPanel.setPageID(settingTreeNode.getPageId());
		centerJPanel.bodyJPanel.add(bodyStyleFigure.getJPanel());
		centerJPanel.headJPanel.add(headStyleToolBar.getJPanel());
		PaddingUnit.setMargin(headStyleToolBar.toolJPanel.leftToolJPanel, new int[]{5, 0, 0, 0}, 0);
		JPanelShow.jPanelShow(centerJPanel);

	}

	@Override
	public Object getImpObject()
	{
		return null;
	}
}
