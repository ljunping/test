package com.temperature.swing.wrapJComponent;

import com.temperature.swing.pojo.SettingTreeNode;

import javax.swing.*;
import java.awt.*;

/**
 * @author Magic Book
 */
public class MyJFrame extends JFrame
{
	static String title = "大跨度桥梁温度分析与温度场模拟软件平台";
	public JPanel mainPanel;
	public GradientPanel northPanel;
	public JPanel westPanel;
	public MyJPanel centerJPanel;
	public static SettingTreeNode[] settingTreeNodes = new SettingTreeNode[10];
	public static int minWidth = 600;
	public static int minHeight = 300;
	public static int leftWidth = 102;
	public static int upHeight = 42;
	public static int width = 1400;
	public static int height = 750;
	private static class SingletonHolder
	{
		private static final MyJFrame instance = new MyJFrame();
	}

	public static MyJFrame getInstance()
	{
		return SingletonHolder.instance;
	}

	private MyJFrame()
	{
		super(title);
		this.setSize(width, height);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainPanel = new JPanel(new BorderLayout());
		northPanel = new GradientPanel(new GridLayout(1, 1));
		westPanel = new JPanel();

		centerJPanel = new MyJPanel();
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(westPanel, BorderLayout.WEST);
		mainPanel.add(centerJPanel, BorderLayout.CENTER);
		this.setContentPane(mainPanel);
		this.setMinimumSize(new Dimension(minWidth, minHeight));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.isPreferredSizeSet();

		ImageIcon icon=new ImageIcon(MyJFrame.class.getResource("resource/main-logo-1x.png"));
		this.setIconImage(icon.getImage());
	}


}
