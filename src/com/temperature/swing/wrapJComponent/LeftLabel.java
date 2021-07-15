package com.temperature.swing.wrapJComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @author Magic Book
 */
public class LeftLabel extends JLabel
{
	public static Font defaultFont=new Font("楷体", Font.PLAIN, 16);

	public LeftLabel(String string)
	{
		super(string);
		this.setFont(defaultFont);
		this.setBackground(Color.WHITE);
		this.setForeground(Color.WHITE);

	}
}
