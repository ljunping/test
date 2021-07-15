package com.temperature.swing.wrapJComponent;

import com.temperature.swing.util.PaddingUnit;

import javax.swing.*;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/4 11:56
 */
public class UpTextArea extends JTextArea
{
	public static Font defaultFont=new Font("楷体", Font.PLAIN, 20);
	public UpTextArea(String s)
	{
		super(s);
		PaddingUnit.setMargin(this, new int[]{10, 10, 10, 10}, 0);
		this.setFont(defaultFont);
	}
}
