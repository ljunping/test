package com.temperature.swing.event;

import javax.swing.*;
import javax.xml.bind.JAXBException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/30 14:28
 */
public abstract class HeadStyle extends Style
{
	protected JPanel jPanel;
	/**
	 * 創建headStyle实现
	 */
	public abstract void createHeadStyle( ) throws JAXBException, ClassNotFoundException;
	public abstract void update();

	public JPanel getJPanel()
	{
		return jPanel;
	}

	public void setJPanel(JPanel jPanel)
	{
		this.jPanel = jPanel;
	}
}
