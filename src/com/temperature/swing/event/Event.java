package com.temperature.swing.event;

import com.temperature.swing.pojo.SettingTreeNode;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/30 23:17
 */
public interface Event
{
	void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException;

	Object getImpObject() throws JAXBException, ClassNotFoundException;
}
