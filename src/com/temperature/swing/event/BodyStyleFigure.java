package com.temperature.swing.event;

import com.temperature.swing.pojo.JPanelInfo;
import com.temperature.swing.util.GenerateKey;
import com.temperature.swing.util.ReadInfo;
import com.temperature.swing.wrapJComponent.FigureJPanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/30 15:15
 */
public class BodyStyleFigure extends BodyStyle
{
	private FigureJPanel figureJPanel;
	private JLayeredPane jLayeredPane;
	public static Map<Integer, FigureJPanel> map = new HashMap<>();
	private final int bodyId;
	private final int pageId;
	private final int key;

	public void isText(boolean tag)
	{
		if (figureJPanel != null)
		{
			figureJPanel.setIsText(tag);
		}

	}

	public BodyStyleFigure(int bodyId, int pageId)
	{
		this.bodyId = bodyId;
		this.pageId = pageId;
		this.jPanel = new JPanel(new GridLayout(1, 1));
		this.key = GenerateKey.generateKey(bodyId, pageId);
//		jPanel.setBackground(Color.RED);
		jLayeredPane = new JLayeredPane();
//		jLayeredPane.setBackground(Color.PINK);

	}


	@Override
	public void createBodyStyle() throws JAXBException, ClassNotFoundException
	{

	}

	@Override
	public void update()
	{


	}


	public FigureJPanel getFigureJPanel()
	{
		return figureJPanel;
	}

	public void setFigureJPanel(FigureJPanel figureJPanel)
	{
		this.figureJPanel = figureJPanel;
	}


	@Override
	public void createStyle() throws JAXBException, ClassNotFoundException
	{
		if (map.containsKey(key))
		{
			figureJPanel = map.get(key);
		}else
		{

			JPanelInfo jPanelInfo = ReadInfo.readFigureInfo(bodyId, pageId);
			if (jPanelInfo != null)
			{
				figureJPanel = new FigureJPanel();
				figureJPanel.imageInfos=jPanelInfo.getImageInfos();
				map.put(key, figureJPanel);
			}else
			{
				figureJPanel = null;
			}

		}
		if (figureJPanel != null)
		{
			figureJPanel.setOpaque(true);

			figureJPanel.setBounds(0, 0, 10000, 10000);
			jLayeredPane.add(figureJPanel, 1);
		}
//		figureJPanel.setBackground(Color.YELLOW);


		this.jPanel.add(jLayeredPane);
	}

	public JLayeredPane getjLayeredPane()
	{
		return jLayeredPane;
	}

	public void setjLayeredPane(JLayeredPane jLayeredPane)
	{
		this.jLayeredPane = jLayeredPane;
	}
}
