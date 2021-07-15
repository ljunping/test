package com.temperature.swing.wrapJComponent;

import javax.swing.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/30 15:42
 */
public abstract class WrapJComponent
{
	protected JComponent jComponent;

	public abstract <T> T getJComponent(Class JButton);





	public abstract void wrapJComponent();

}
