package com.temperature.swing.wrapJComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @author Magic Book
 */
public class MyJPanel extends JPanel
{
	public static CardLayout defaultLayout = new CardLayout();
	public static int pageId = -1;
	public static CenterJPanel currentJPanel;
	public static int height = 711;
	public static int width = MyJFrame.width - MyJFrame.leftWidth;
	public static CenterJPanel[] pageJPanel = new CenterJPanel[9];
	public MyJPanel()
	{
		super(defaultLayout);
	}
}
