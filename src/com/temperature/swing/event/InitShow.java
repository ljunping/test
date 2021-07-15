package com.temperature.swing.event;

import com.temperature.swing.App;
import com.temperature.swing.pojo.ButtonInfo;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.util.PaddingUnit;
import com.temperature.swing.wrapJComponent.MyJFrame;
import com.temperature.swing.wrapJComponent.NavButtonLeft;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 16:03
 */
public class InitShow implements Event
{
	public MyJFrame jFrame = MyJFrame.getInstance();
	public SettingTreeNode settingTreeNode;



	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, JAXBException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		this.settingTreeNode = settingTreeNode;
		createNorth();
		createCenter();
		createWest(settingTreeNode);
	}

	@Override
	public Object getImpObject()
	{
		return null;
	}

	private void createCenter() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JAXBException
	{
		SettingTreeNode settingTreeNode1 = settingTreeNode.getChildren().get(0);

		Event event = settingTreeNode1.getEventObj();
		event.eventActive(settingTreeNode1);

	}

	private void createNorth()
	{
		JLayeredPane jLayeredPane = new JLayeredPane();
		JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT))
		{
			@Override
			public void paintComponent(Graphics g)
			{
				JComponent component = (JComponent) this.getParent();
				this.setBounds(0, 0, component.getWidth(), component.getHeight());
				this.validate();
				super.paintComponent(g);
			}
		};
		JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER))
		{
			@Override
			public void paintComponent(Graphics g)
			{
				JComponent component = (JComponent) this.getParent();
				this.setBounds(0, 0, component.getWidth(), component.getHeight());
				this.validate();
				super.paintComponent(g);
			}
		};
		JLabel titleText = new JLabel("大跨度桥梁温度分析与温度场模拟软件平台");
		titleText.setForeground(Color.WHITE);
		ImageIcon icon = new ImageIcon(MyJFrame.class.getResource("resource/main-logo-1x.png"));
		icon.setImage(icon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		titleText.setFont(new Font("楷体", Font.PLAIN, 28));
		PaddingUnit.setPadding(jPanel1, new int[]{10, 0, 10, 0}, 0);
		PaddingUnit.setPadding(jPanel, new int[]{15, 0, 20, 0}, 0);
		JLabel imageLabel = new JLabel(icon);
		imageLabel.setPreferredSize(new Dimension(25, 25));
		JLabel label = new JLabel("香港理工大学");
		label.setFont(App.defaultFont);
		label.setForeground(Color.WHITE);
		jPanel.add(imageLabel);
		jPanel.add(label);
		jPanel.setBackground(Color.red);
		jPanel.setOpaque(false);
		jPanel1.add(titleText);
		jPanel1.setBackground(Color.PINK);
		jPanel1.setOpaque(false);
		jPanel.setBounds(0, 0, MyJFrame.width, 60);
		jPanel1.setBounds(0, 0, MyJFrame.width, 60);
		jLayeredPane.add(jPanel, 1);
		jLayeredPane.add(jPanel1, 0);
		jLayeredPane.setBackground(Color.YELLOW);
		jLayeredPane.setPreferredSize(new Dimension(100, 60));
		jLayeredPane.setOpaque(false);

		jFrame.northPanel.setBackground(Color.YELLOW);

		jFrame.northPanel.add(jLayeredPane);

	}

	private void createWest(SettingTreeNode settingTreeNode) throws ClassNotFoundException
	{


		int deep = 1;
		List<SettingTreeNode> nodeList = settingTreeNode.getChildren();
		jFrame.westPanel.setLayout(new GridLayout(nodeList.size(), 1, 0, 0));
		for (int i = 0; i < nodeList.size(); i++)
		{
			JComponent jc;
			ButtonInfo buttonInfo;
			SettingTreeNode childNode;
			childNode = nodeList.get(i);
			buttonInfo = new ButtonInfo(nodeList.get(i).getText(), i, deep);
			NavButtonLeft buttonLeft = new NavButtonLeft(buttonInfo, "NavButtonLeft");
			buttonLeft.setSettingTreeNode(childNode);
			childNode.setButton(buttonLeft);
			jc = buttonLeft;
			MyJFrame.settingTreeNodes[i] = childNode;
			jFrame.westPanel.add(jc);
		}


		jFrame.westPanel.setBackground(new Color(18, 72, 96));



	}

}



