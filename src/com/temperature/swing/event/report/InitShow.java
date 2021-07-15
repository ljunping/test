package com.temperature.swing.event.report;

import com.temperature.swing.event.Event;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.util.PaddingUnit;
import com.temperature.swing.wrapJComponent.AbsoluteBodyContain;
import com.temperature.swing.wrapJComponent.CenterJPanel;
import com.temperature.swing.wrapJComponent.ComplexTable;

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
	int pageId = 9;
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
		JPanel panel = new JPanel();

		AbsoluteBodyContain panel0 = new AbsoluteBodyContain(new BorderLayout(), 1000, 350);


		JPanel panel1 = new JPanel(new BorderLayout());
		JLabel jLabel = new JLabel("数据简报", JLabel.CENTER);
		jLabel.setFont(new Font("楷体", Font.PLAIN, 40));
		PaddingUnit.setMargin(jLabel, new int[]{0, 0, 20, 0}, 0);
		panel0.add(jLabel, BorderLayout.NORTH);
		panel0.add(panel1, BorderLayout.CENTER);
		panel.add(panel0);


		// 表头（列名）
		Object[][] columnNames = {{"(℃)", ComplexTable.mergeCellX, "环境温度", "主梁有效温度", "主缆有效温度", "说明"}};

		// 表格所有行数据
		Object[][] rowData = {{"实测值", "最大值", "", "", "", ""},
				{ComplexTable.mergeCellY, "最小值", "", "", "", ""},
				{"设计值", "最大值", "", "", "", ""},
				{ComplexTable.mergeCellY, "最小值", "", "", "", ""},
				{"竖向温差", "", "", "", "", ""},
				{"竖向温差设计值", "", "", "", "", ""}};

		// 创建一个表格，指定 所有行数据 和 表头
		JTable table = new ComplexTable(columnNames, rowData);


		// 设置行高


		// 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
		panel1.add(table.getTableHeader(), BorderLayout.NORTH);
		panel1.add(table, BorderLayout.CENTER);

		// 把 表格内容 添加到容器中心


		CenterJPanel centerJPanel = new CenterJPanel();
//		PaddingUnit.setPadding(centerJPanel,new Pa);
		PaddingUnit.setPadding(centerJPanel, new int[]{0, 0, 200, 0}, 0);
		centerJPanel.setPageID(settingTreeNode.getPageId());
		centerJPanel.bodyJPanel.add(panel);
//		centerJPanel.headJPanel.add(headStyleToolBar.getJPanel());
		JPanelShow.jPanelShow(centerJPanel);

	}
}
