package com.temperature.swing.event.temperatureFieldSimulation;

import com.temperature.swing.event.BodyStyleFigure;
import com.temperature.swing.event.Event;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.CenterJPanel;
import com.temperature.swing.wrapJComponent.MyJPanel;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 1:58
 */
public class FigureChange implements Event
{

	int pageId;
	int bodyId;
	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		pageId = settingTreeNode.getPageId();
		CenterJPanel centerJPanel = MyJPanel.pageJPanel[settingTreeNode.getPageId() - 1];
		bodyId = centerJPanel.HeadSelectIndex + 2;
		BodyStyleFigure bodyStyleFigure = new BodyStyleFigure(bodyId, pageId);
		bodyStyleFigure.createStyle();
		JPanelShow.bodyJPanelShow(bodyStyleFigure.getJPanel(), pageId);
	}

	@Override
	public Object getImpObject() throws JAXBException, ClassNotFoundException
	{
		return null;
	}
}
