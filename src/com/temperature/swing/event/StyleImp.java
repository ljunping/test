package com.temperature.swing.event;

import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.util.GenerateKey;
import com.temperature.swing.wrapJComponent.CenterJPanel;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/30 19:52
 */
public class StyleImp extends Style
{
	public static Map<Integer, CenterJPanel> map = new HashMap<>();

	private final int key;

	public StyleImp(int headId, int bodyId, int pageId)
	{
		this.headId = headId;
		this.bodyId = bodyId;
		this.pageId = pageId;
		this.key = GenerateKey.generateKey(headId, bodyId, pageId);
	}
	@Override
	public void createStyle() throws JAXBException, ClassNotFoundException
	{
		if (map.containsKey(key))
		{
			this.centerJPanel = map.get(key);

		}else
		{
			this.centerJPanel = new CenterJPanel();
			this.bodyStyle = new BodyStyleFigure(bodyId,pageId);

			this.bodyStyle.createStyle();
//			注意这里顺序不能变
			this.headStyle = new HeadStyleToolBar(headId, pageId,settingTreeNode);
			headStyle.setBodyStyle(bodyStyle);
			this.headStyle.createStyle();
			footStyle = new FootStyleNav(settingTreeNode);
			this.footStyle.createStyle();
			assembleStyle();
			map.put(key, this.centerJPanel);
		}
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
}
