package com.temperature.swing.wrapJComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/23 2:26
 */
public class FootJPanel extends JPanel
{
	public FootJPanel()
	{
		super(new FlowLayout(FlowLayout.RIGHT));

		defaultSetting();
	}
	private void defaultSetting()
	{
		this.setBackground(new Color(14, 37, 82));
	}
}
