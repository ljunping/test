package com.temperature.swing.event;

import javax.swing.*;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/30 14:28
 */
public abstract class FootStyle extends Style
{
	protected  JPanel jPanel = new JPanel(new GridLayout(1, 1));
	/**
	 * 創建FootStyle实现
	 */
	public abstract void createFootStyle() throws ClassNotFoundException;
	public abstract void update();

	public JPanel getJPanel()
	{
		return jPanel;
	}

}
