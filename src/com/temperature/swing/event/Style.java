package com.temperature.swing.event;

import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.CenterJPanel;

import javax.xml.bind.JAXBException;
import java.util.Map;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/30 14:24
 */
public abstract class Style
{
	protected HeadStyle headStyle;
	protected BodyStyle bodyStyle;
	protected FootStyle footStyle;
	protected CenterJPanel centerJPanel;
	protected SettingTreeNode settingTreeNode;
	protected Map<Integer, Style> map;
	protected int headId;
	protected int bodyId;
	protected int pageId;

	/**
	 * 利用不同的实现方式进行创建
	 */
	public  abstract void createStyle() throws JAXBException, ClassNotFoundException;
	public  void updateAll()
	{
		updateHead();
		updateFoot();
		updateBody();

	}
	public  void updateHead()
	{
		this.headStyle.update();

	}
	public void updateFoot()
	{
		this.footStyle.update();

	}

	public void updateBody()
	{
		this.bodyStyle.update();
	}

	public void assembleStyle()
	{
		centerJPanel.headJPanel.add(headStyle.getJPanel());
		centerJPanel.bodyJPanel.add(bodyStyle.getJPanel());
		centerJPanel.footJPanel.add(footStyle.getJPanel());
	}




	public CenterJPanel getCenterJPanel() throws JAXBException, ClassNotFoundException
	{
		if (centerJPanel == null)
		{
			createStyle();
		}
		return centerJPanel;
	}

	public void setCenterJPanel(CenterJPanel centerJPanel)
	{
		this.centerJPanel = centerJPanel;
	}


	public void setHeadStyle(HeadStyle headStyle)
	{
		this.headStyle = headStyle;
	}

	public BodyStyle getBodyStyle()
	{
		return bodyStyle;
	}

	public void setBodyStyle(BodyStyle bodyStyle)
	{
		this.bodyStyle = bodyStyle;
	}

	public FootStyle getFootStyle()
	{
		return footStyle;
	}

	public void setFootStyle(FootStyle footStyle)
	{
		this.footStyle = footStyle;
	}

	public Map<Integer, Style> getMap()
	{
		return map;
	}

	public void setMap(Map<Integer, Style> map)
	{
		this.map = map;
	}
}
