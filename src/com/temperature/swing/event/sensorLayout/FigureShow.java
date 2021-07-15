package com.temperature.swing.event.sensorLayout;

import com.temperature.swing.event.Event;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.event.StyleImp;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.CenterJPanel;
import com.temperature.swing.wrapJComponent.MyJFrame;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/4 11:35
 */
public class FigureShow implements Event
{
	private int headId;
	private int bodyId;
	private int pageId;
	private StyleImp styleImp;
	private SettingTreeNode settingTreeNode;

	public int getHeadId()
	{
		return headId;
	}

	public void setHeadId(int headId)
	{
		this.headId = headId;
	}

	public int getBodyId()
	{
		return bodyId;
	}

	public void setBodyId(int bodyId)
	{
		this.bodyId = bodyId;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public SettingTreeNode getSettingTreeNode()
	{
		return settingTreeNode;
	}

	public void setSettingTreeNode(SettingTreeNode settingTreeNode)
	{
		this.settingTreeNode = settingTreeNode;
	}

	public FigureShow(List<String> list)
	{
		this.headId = Integer.parseInt(list.get(0));
		this.bodyId = Integer.parseInt(list.get(1));
		this.pageId = Integer.parseInt(list.get(2));

	}

	public void createStyleImp() throws JAXBException, ClassNotFoundException
	{
		StyleImp styleImp = new StyleImp(headId, bodyId, pageId);
		this.styleImp = styleImp;
		styleImp.setSettingTreeNode(settingTreeNode);
		styleImp.createStyle();
	}

	public void figureShow(SettingTreeNode settingTreeNode) throws JAXBException, ClassNotFoundException
	{
		this.settingTreeNode = settingTreeNode;
		createStyleImp();
		CenterJPanel centerJPanel = styleImp.getCenterJPanel();
		centerJPanel.setPageID(settingTreeNode.getPageId());
		JPanelShow.jPanelShow(centerJPanel);

	}



	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		figureShow(settingTreeNode);
		MyJFrame.settingTreeNodes[settingTreeNode.getPageId() - 2] = settingTreeNode;

	}

	@Override
	public Object getImpObject() throws JAXBException, ClassNotFoundException
	{
		return this;
	}
}
