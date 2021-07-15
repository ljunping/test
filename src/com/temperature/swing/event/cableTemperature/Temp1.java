package com.temperature.swing.event.cableTemperature;

import com.temperature.swing.event.Event;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.util.PaddingUnit;
import com.temperature.swing.wrapJComponent.AbsoluteBodyContain;
import com.temperature.swing.wrapJComponent.WrapTable;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/12 3:20
 */
public class Temp1 implements Event
{
	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		JPanel panel = new JPanel();
		AbsoluteBodyContain panel0 = new AbsoluteBodyContain(new BorderLayout(), 400, 170);
		JPanel panel1 = new JPanel(new BorderLayout());
		JLabel jLabel = new JLabel("极值有效主缆温度", JLabel.CENTER);
		jLabel.setFont(new Font("楷体", Font.PLAIN, 40));
		PaddingUnit.setMargin(jLabel, new int[]{0, 0, 50, 0}, 0);
		panel0.add(jLabel, BorderLayout.NORTH);
		panel0.add(panel1, BorderLayout.CENTER);
		panel.add(panel0);

		// 表头（列名）
		Object[] columnNames = {"最大值", "最小值"};

		// 表格所有行数据
		Object[][] rowData = {{37.84, 1.06}};

		// 创建一个表格，指定 所有行数据 和 表头
		JTable table = new WrapTable(rowData, columnNames);


		// 设置行高


		// 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
		panel1.add(table.getTableHeader(), BorderLayout.NORTH);
		panel1.add(table, BorderLayout.CENTER);

		// 把 表格内容 添加到容器中心
		JPanelShow.bodyJPanelShow(panel, 5);
	}

	@Override
	public Object getImpObject() throws JAXBException, ClassNotFoundException
	{
		return null;
	}
}
