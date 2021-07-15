package com.temperature.swing.event.beamTemperature;

import com.temperature.swing.event.Event;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.util.ReadInfo;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.FigureContain;
import com.temperature.swing.wrapJComponent.GridJPanel;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/12 3:24
 */
public class Temp2 implements Event

{
	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		GridJPanel gridJPanel = new GridJPanel(new int[]{1});
		gridJPanel.getjPanels()[0].add(new FigureContain(ReadInfo.readFigureInfo(4, 4)));

		JPanelShow.bodyJPanelShow(gridJPanel, 4);
	}

	@Override
	public Object getImpObject() throws JAXBException, ClassNotFoundException
	{
		return null;
	}
}
