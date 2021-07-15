package com.temperature.swing.wrapJComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @author Magic Book
 */
public class UpLabel extends JLabel
{
	public static Font defaultFont=new Font("楷体", Font.PLAIN, 20);

	public UpLabel(String string)
	{
		super(string);
		this.setFont(defaultFont);
		this.setBackground(Color.WHITE);
//		PaddingUnit.setMargin(this, new int[]{5, 0, 0, 0}, 0);
		this.setFont(defaultFont);
		this.setForeground(Color.WHITE);
	}
}
