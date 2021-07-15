package com.temperature.swing.event;

import javax.swing.*;
import javax.xml.bind.JAXBException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/30 14:28
 */
public abstract class BodyStyle extends Style
{
	protected JPanel jPanel;

	/**
	 * 創建BodyStyle实现
	 */
	public abstract void createBodyStyle() throws JAXBException, ClassNotFoundException;

	public JPanel getJPanel()
	{
		return jPanel;
	}

	public void setJPanel(JPanel jPanel)
	{
		this.jPanel = jPanel;
	}

	public abstract void update();
}
