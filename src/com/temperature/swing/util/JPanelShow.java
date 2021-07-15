package com.temperature.swing.util;

import com.temperature.swing.wrapJComponent.CenterJPanel;
import com.temperature.swing.wrapJComponent.LoadingJPanel;
import com.temperature.swing.wrapJComponent.MyJFrame;
import com.temperature.swing.wrapJComponent.MyJPanel;
import org.apache.log4j.Logger;

import javax.swing.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/6 1:58
 */
public class JPanelShow
{
	private static final Logger logger = Logger.getLogger(JPanelShow.class);
	synchronized public static void jPanelShow(CenterJPanel centerJPanel)
	{
		MyJFrame.getInstance().centerJPanel.removeAll();
		MyJFrame.getInstance().centerJPanel.add(centerJPanel);
		MyJPanel.pageJPanel[centerJPanel.getPageID() - 1] = centerJPanel;
		MyJPanel.currentJPanel = centerJPanel;
		centerJPanel.revalidate();
		centerJPanel.repaint();
//
//		MyJPanel.currentJPanel = centerJPanel;
//		MyJPanel.currentJPanel.revalidate();//对panel1面板中的组件重新布局并绘制
//		MyJPanel.currentJPanel.repaint();//对panel1本身进行重绘
	}

	synchronized public static void bodyJPanelShow(JPanel jPanel, int pageId)
	{
		logger.debug("进入JPanelshow,开始更新bodyStyle");
		CenterJPanel centerJPanel = MyJPanel.pageJPanel[pageId - 1];
		restore(centerJPanel.bodyJPanel);
		centerJPanel.bodyJPanel.removeAll();
		centerJPanel.bodyJPanel.add(jPanel);
		centerJPanel.bodyJPanel.revalidate();//对panel1面板中的组件重新布局并绘制
		centerJPanel.bodyJPanel.repaint();//对panel1本身进行重绘
//		MyJPanel.currentJPanel.revalidate();//对panel1面板中的组件重新布局并绘制
//		MyJPanel.currentJPanel.repaint();//对panel1本身进行重绘
		logger.debug("bodyStyle页面更新");
	}
	synchronized public static void headJPanelShow(JPanel jPanel, int pageId)
	{
		logger.debug("进入JPanelshow,开始更新bodyStyle");
		CenterJPanel centerJPanel = MyJPanel.pageJPanel[pageId - 1];
		centerJPanel.headJPanel.removeAll();
		centerJPanel.headJPanel.add(jPanel);
		centerJPanel.headJPanel.revalidate();//对panel1面板中的组件重新布局并绘制
		centerJPanel.headJPanel.repaint();//对panel1本身进行重绘
//		MyJPanel.currentJPanel.revalidate();//对panel1面板中的组件重新布局并绘制
//		MyJPanel.currentJPanel.repaint();//对panel1本身进行重绘
		logger.debug("bodyStyle页面更新");
	}

	private static void restore(JPanel jPanel)
	{
		if (jPanel != null && jPanel.getComponentCount() > 0)
		{

			if (jPanel.getComponent(0) instanceof LoadingJPanel)
			{
				LoadingJPanel loadingJPanel = (LoadingJPanel) jPanel.getComponent(0);
				loadingJPanel.setComponentDisable(true);
				logger.debug("工具栏恢复");
			}
		}
	}
}
