package com.temperature.swing.wrapJComponent;

import com.temperature.swing.pojo.JPanelInfo;

import javax.swing.*;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/11 23:05
 */
public class FigureContain extends JPanel
{
	public FigureContain(JPanelInfo jPanelInfo)
	{
		super(new GridLayout(1, 1));
		this.add(new FigureJPanel(jPanelInfo));
		this.setBackground(Color.WHITE);
	}

}
