package com.temperature.swing.event.beamTemperature;

import com.temperature.swing.event.JComBoxEvent;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.wrapJComponent.LoadingJPanel;
import com.temperature.swing.wrapJComponent.MyJPanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/14 19:22
 */
public class CalculateET extends JComBoxEvent
{
	@Override
	public void chartUpdate()
	{
		LoadingJPanel jPanel = new LoadingJPanel(settingTreeNode.getPageId());
		jPanel.setPreferredSize(new Dimension(100, 100));
		JPanelShow.bodyJPanelShow(jPanel, settingTreeNode.getPageId());
		new Thread(() ->
		{
			synchronized (this)
			{
				try
				{
					Thread.sleep(500);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				JPanelShow.bodyJPanelShow(new JPanel(), settingTreeNode.getPageId());

				JOptionPane.showMessageDialog(MyJPanel.currentJPanel.bodyJPanel,
						"有效温度计算完成",
						"消息提示",
						JOptionPane.INFORMATION_MESSAGE
				);
			}
		}).start();


	}

//	@Override
//	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
//	{
//
//
//
//	}

	@Override
	public Object getImpObject() throws JAXBException, ClassNotFoundException
	{
		return null;
	}
}
