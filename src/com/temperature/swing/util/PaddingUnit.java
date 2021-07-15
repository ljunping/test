package com.temperature.swing.util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/23 19:33
 */
public class PaddingUnit
{
	public static void setPadding(JComponent component, int[] padding, int thick)
	{

		Border paddingBorder = BorderFactory.createEmptyBorder(padding[0], padding[1], padding[2], padding[3]);
		Border lineBorder = BorderFactory.createLineBorder(Color.black, thick);
		Border border = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);
		component.setBorder(border);

	}

	public static void setMargin(JComponent component,int[] padding, int thick)
	{
		Border paddingBorder = BorderFactory.createEmptyBorder(padding[0], padding[1], padding[2], padding[3]);
		Border lineBorder = BorderFactory.createLineBorder(Color.black, thick);
		Border border = BorderFactory.createCompoundBorder(paddingBorder,lineBorder);
		component.setBorder(border);

	}
}
