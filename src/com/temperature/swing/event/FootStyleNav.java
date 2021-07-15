package com.temperature.swing.event;

import com.temperature.swing.pojo.ButtonInfo;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.FootJPanel;
import com.temperature.swing.wrapJComponent.NavButtonBottom;

import javax.xml.bind.JAXBException;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/30 19:13
 */
public class FootStyleNav extends FootStyle
{
	private static SettingTreeNode originSettingTreeNode = null;
	private FootJPanel footJPanel;

	public FootStyleNav(SettingTreeNode settingTreeNode)
	{
		if (originSettingTreeNode == null)
		{
			originSettingTreeNode = settingTreeNode;
		}


		jPanel.setBackground(Color.GREEN);
	}

	@Override
	public void createFootStyle() throws ClassNotFoundException
	{

		footJPanel = new FootJPanel();
		NavButtonBottom lastPage = new NavButtonBottom(new ButtonInfo("上一页", 11, 2), "NavButtonBottom");
		lastPage.setSettingTreeNode(originSettingTreeNode.getChildren().get(11));
		footJPanel.add(lastPage);
		NavButtonBottom returnPage = new NavButtonBottom(new ButtonInfo("返回主页", 12, 2), "NavButtonBottom");
		footJPanel.add(returnPage);
		returnPage.setSettingTreeNode(originSettingTreeNode.getChildren().get(12));

		jPanel.add(footJPanel);
	}

	@Override
	public void update()
	{

	}

	@Override
	public void createStyle() throws JAXBException, ClassNotFoundException
	{
		createFootStyle();
	}
}
