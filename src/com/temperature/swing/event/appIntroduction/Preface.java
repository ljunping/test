package com.temperature.swing.event.appIntroduction;

import com.temperature.swing.App;
import com.temperature.swing.event.Event;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.pojo.SettingTreeNode;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/19 17:58
 */
public class Preface implements Event
{
	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		JPanel initJPanel = new JPanel()
		{

			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				if (!isOpaque())
				{
					return;
				}
				int width = getWidth();
				int height = getHeight();
				Graphics2D g2 = (Graphics2D) g;
				GradientPaint gradientPaint = new GradientPaint(width / 2, 0, new Color(17, 64, 86), width / 2, height, new Color(33, 125, 165), false);
				g2.setPaint(gradientPaint);
				g2.fillRect(0, 0, width, height);
			}

		};

		JLabel label = new JLabel("<html>"
				+ "<body>" +
				"<p align='center' style='font-size:30px;font-family:楷体;'>" + "序言</p>"
				+ "<br>" +
				"<span style='font-size:24px;font-family:楷体;'>"
				+ "本软件基于各类温度传感器所采集的数据对大跨度桥梁的主梁、主缆、吊<br>"
				+ "杆、路面以及环境作温度分析，得到它们的日、" + "月与季节的温度变化规律，" +
				"<br>" + "并且与设计值作比较。本软件也能基于各类温度传感器与测量其它环境因" +
				"<br>" + "素（例如太阳幅射）的传感器所采集的数据通過热传导分析来模拟整桥的" +
				"<br>" + "温度场," + "从而进行由温度变化引起的结构响应分析。" +
				"<br><br>本软件以香港青马大桥为背景作演示，稍作修改后即可提供其它大桥使用。" +
				"</span>" + "</body>" + "</html>");
		label.setForeground(Color.WHITE);
		label.setFont(App.defaultFont);
		initJPanel.add(label);
		JPanelShow.bodyJPanelShow(initJPanel, settingTreeNode.getPageId());

	}

	@Override
	public Object getImpObject() throws JAXBException, ClassNotFoundException
	{
		return null;
	}
}
