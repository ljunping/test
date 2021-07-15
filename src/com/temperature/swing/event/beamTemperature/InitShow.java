package com.temperature.swing.event.beamTemperature;

import com.temperature.swing.event.Event;
import com.temperature.swing.event.HeadStyleToolBar;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.util.ReadInfo;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.CenterJPanel;
import com.temperature.swing.wrapJComponent.FigureContain;
import com.temperature.swing.wrapJComponent.GridJPanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 15:40
 */
public class InitShow implements Event
{
	int pageId = 4;
	int headId = 2;
	int bodyId=2;
	public static GridJPanel gridJPanel0;

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

		GridJPanel gridJPanel = new GridJPanel(new int[]{1, 1, 1, 1});
		gridJPanel0=gridJPanel;

		gridJPanel.getjPanels()[0].setPreferredSize(new Dimension(1000, 400));
		gridJPanel.getjPanels()[0].add(new FigureContain(ReadInfo.readFigureInfo(bodyId, pageId)));


		gridJPanel.getjPanels()[1].setPreferredSize(new Dimension(1000, 400));
		gridJPanel.getjPanels()[1].add(new FigureContain(ReadInfo.readFigureInfo(5, 2)));

		gridJPanel.getjPanels()[2].setPreferredSize(new Dimension(1000, 400));
		gridJPanel.getjPanels()[2].add(new FigureContain(ReadInfo.readFigureInfo(17, 2)));

		gridJPanel.getjPanels()[3].setPreferredSize(new Dimension(1000, 400));
		gridJPanel.getjPanels()[3].add(new FigureContain(ReadInfo.readFigureInfo(25, 2)));

		HeadStyleToolBar headStyleToolBar = new HeadStyleToolBar(headId, pageId, settingTreeNode);
		headStyleToolBar.createStyle();
//		进行组件隐藏
		headStyleToolBar.toolJPanel.restore(settingTreeNode);
		headStyleToolBar.toolJPanel.setPartVisible(false);
//		PaddingUnit.setPadding(headStyleToolBar.toolJPanel.leftToolJPanel, new int[]{0, 0, 0, 0}, 0);
		JScrollPane scrollPane = new JScrollPane(gridJPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(Color.WHITE);
		JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
		scrollBar.setUnitIncrement(25);
		centerJPanel.bodyJPanel.add(scrollPane);
		centerJPanel.headJPanel.add(headStyleToolBar.getJPanel());


		JPanelShow.jPanelShow(centerJPanel);

	}
}
