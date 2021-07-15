package com.temperature.swing.wrapJComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/12 12:58
 */
public class AbsoluteBodyContain extends JPanel
{
	private int width;
	private int height;
	public AbsoluteBodyContain(LayoutManager layoutManager)
	{
		super(layoutManager);
	}

	public AbsoluteBodyContain(LayoutManager layoutManager, int width, int height)
	{
		this(layoutManager);
		this.height = height;
		this.width = width;
		this.setPreferredSize(new Dimension(width, height));

	}
	@Override
	public void paint(Graphics g)
	{
		updateLoc();
		super.paint(g);
	}

	private void updateLoc()
	{
		JPanel bodyJPanel = MyJPanel.currentJPanel.bodyJPanel;
		Insets insets = bodyJPanel.getInsets();
		double width0 = bodyJPanel.getWidth() - insets.left - insets.right;
		double height0 = bodyJPanel.getHeight() - insets.top - insets.bottom;
		int x = (int) ((width0 - width) / 2);
		int y = (int) ((height0 - height) / 2);
		this.setBounds(x, y, width, height);
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}
}
