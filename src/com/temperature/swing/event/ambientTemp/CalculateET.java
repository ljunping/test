package com.temperature.swing.event.ambientTemp;

import com.temperature.swing.event.Event;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.MyJPanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/14 19:22
 */
public class CalculateET implements Event
{
	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		JOptionPane.showMessageDialog(MyJPanel.currentJPanel.bodyJPanel,
				"有效温度计算完成",
				"消息提示",
				JOptionPane.INFORMATION_MESSAGE
		);
	}

	@Override
	public Object getImpObject() throws JAXBException, ClassNotFoundException
	{
		return null;
	}
}
