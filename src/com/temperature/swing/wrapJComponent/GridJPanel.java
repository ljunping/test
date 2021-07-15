package com.temperature.swing.wrapJComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/11 18:05
 */
public class GridJPanel extends JPanel
{
	private final int[] an;
	private JPanel[] jPanels;

	public GridJPanel(int[] an)
	{
		super();
		this.an = an;
		jPanels = new JPanel[an.length];
		//this.setPreferredSize(new Dimension(10000, 10000));
		styleSet();
	}

	private void styleSet()
	{
		this.setLayout(new GridLayout(an.length, 1));
		int s = 0;
		while (s < an.length)
		{
			JPanel jPanel = new JPanel(new GridLayout(1, an[s]));
			this.add(jPanel);
			jPanels[s] = jPanel;
			jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			//jPanel.setPreferredSize(new Dimension(10000, 500));
			++s;
		}
	}

	@Override
	public void paint(Graphics g)
	{
//		if (isTransform())
//		{
//
//		}
		super.paint(g);
	}

//	private void transform()
//	{
//		this.setLayout(new GridLayout());
//		int s = 0;
//		while (s < an.length)
//		{
//			if (isTransform(getJPanel(s)))
//			{
//				if (s + 1 < an.length && isTransform(getJPanel(s + 1)))
//				{
//					getJPanel(s).add(this.getComponent(s + 1));
//					this.remove()
//
//				}
//
//			}
//		}
//	}

	private JPanel getJPanel(int id)
	{
		return (JPanel) this.getComponent(id);
	}

	private boolean isTransform()
	{

		for (int i = 0; i < an.length; i++)
		{
			if (an[i] != 1)
			{
				return false;
			}
		}
		JPanel jPanel = getJPanel(0);


		return jPanel.getWidth() * 1.0 > jPanel.getHeight() * 4.0;
	}

	public void setjPanels(JPanel[] jPanels)
	{
		this.jPanels = jPanels;
	}

	public JPanel[] getjPanels()
	{
		return jPanels;
	}
}
