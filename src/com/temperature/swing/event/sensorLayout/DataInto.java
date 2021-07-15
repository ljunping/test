package com.temperature.swing.event.sensorLayout;

import com.temperature.swing.event.Event;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.MyJPanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/14 18:12
 */
public class DataInto implements Event
{
	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		JOptionPane.showMessageDialog(MyJPanel.currentJPanel.bodyJPanel,
				"导入数据完成",
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
