package com.temperature.swing.event.appIntroduction;

import com.temperature.swing.event.BodyStyleFigure;
import com.temperature.swing.event.Event;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.pojo.SettingTreeNode;

import javax.xml.bind.JAXBException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 15:40
 */
public class appDescribe implements Event
{
	int pageId = 10;
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
		BodyStyleFigure bodyStyleFigure = new BodyStyleFigure(bodyId, pageId);
		bodyStyleFigure.createStyle();
//		bodyStyleFigure.getFigureJPanel().setDefaultHeight(500);
//		bodyStyleFigure.getFigureJPanel().setDefaultWidth(750);

		bodyStyleFigure.getFigureJPanel().setIsText(true);

		bodyStyleFigure.getFigureJPanel().setUpDateSwitch(false);
		JPanelShow.bodyJPanelShow(bodyStyleFigure.getJPanel(),settingTreeNode.getPageId());

	}
}
