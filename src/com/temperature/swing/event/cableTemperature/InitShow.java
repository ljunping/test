package com.temperature.swing.event.cableTemperature;

import com.temperature.swing.event.Event;
import com.temperature.swing.event.HeadStyleToolBar;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.util.ReadInfo;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.CenterJPanel;
import com.temperature.swing.wrapJComponent.FigureContain;
import com.temperature.swing.wrapJComponent.GridJPanel;

import javax.xml.bind.JAXBException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 15:40
 */
public class InitShow implements Event
{
	int pageId = 5;
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
		CenterJPanel centerJPanel = new CenterJPanel();
		centerJPanel.setPageID(settingTreeNode.getPageId());

		GridJPanel gridJPanel = new GridJPanel(new int[]{1, 3});
		gridJPanel.getjPanels()[0].add(new FigureContain(ReadInfo.readFigureInfo(bodyId, pageId)));
		gridJPanel.getjPanels()[1].add(new FigureContain(ReadInfo.readFigureInfo(30, 2)));
		gridJPanel.getjPanels()[1].add(new FigureContain(ReadInfo.readFigureInfo(31, 2)));
		gridJPanel.getjPanels()[1].add(new FigureContain(ReadInfo.readFigureInfo(32, 2)));

		HeadStyleToolBar headStyleToolBar = new HeadStyleToolBar(headId, pageId, settingTreeNode);
		headStyleToolBar.createStyle();
//		进行组件隐藏
		headStyleToolBar.toolJPanel.restore(settingTreeNode);
		headStyleToolBar.toolJPanel.setPartVisible(false);

		centerJPanel.bodyJPanel.add(gridJPanel);
		centerJPanel.headJPanel.add(headStyleToolBar.getJPanel());
		JPanelShow.jPanelShow(centerJPanel);

	}
}
