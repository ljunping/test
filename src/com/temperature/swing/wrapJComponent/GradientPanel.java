package com.temperature.swing.wrapJComponent;

import com.temperature.swing.App;

import javax.swing.*;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/13 21:29
 */
public class GradientPanel extends JPanel
{
	public GradientPanel(LayoutManager lm) {
		super(lm);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!isOpaque()) {
			return;
		}

		int width = getWidth();
		int height = getHeight();
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint gradientPaint =new GradientPaint(width/2, height/2, App.defaultColor, width/2, height, new Color(47, 124, 134),true);
		g2.setPaint(gradientPaint);
		g2.fillRect(0, 0, width, height);

	}
	public static void main(String[] args){
		JFrame jf=new JFrame("GradientPanelTest");
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		GradientPanel gp=new GradientPanel(new BorderLayout());
		jf.getContentPane().add(gp);
		jf.setSize(500,400);
		jf.setVisible(true);
	}
}
