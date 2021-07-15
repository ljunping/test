package com.temperature.swing.wrapJComponent;

import com.temperature.swing.pojo.SettingTreeNode;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/23 1:24
 */
public class ToolJPanel extends JPanel
{
	public JPanel leftToolJPanel;
	public JPanel rightToolJPanel;
	Set<JComponent> set;
	boolean tag = true;
	boolean first = true;
	int pageId;

	public ToolJPanel()
	{
		super(new BorderLayout());

		this.leftToolJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT))
		{
			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
			}
		};
		this.rightToolJPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.add(leftToolJPanel, BorderLayout.WEST);
		this.add(rightToolJPanel, BorderLayout.CENTER);
		defaultSetting();
	}

	private void defaultSetting()
	{
		leftToolJPanel.setBackground(new Color(18, 72, 96));
		rightToolJPanel.setBackground(new Color(18, 72, 96));
	}

	public void setPartVisible(boolean visible)
	{
		if (tag != visible)
		{
			if (!visible)
			{
				set = new HashSet<>();
			}
			setVisible(leftToolJPanel, visible);
			setVisible(rightToolJPanel, visible);
			tag = visible;
		}
	}

	private void setVisible(JPanel leftToolJPanel, boolean visible)
	{
		for (int i = 0; i < leftToolJPanel.getComponentCount(); i++)
		{
			JComponent jComponent = (JComponent) leftToolJPanel.getComponent(i);
			if (jComponent instanceof UpLabel && "传感器编号".equals(((UpLabel) jComponent).getText()))
			{
				UpLabel upLabel = (UpLabel) jComponent;

				if (pageId == 4 || pageId == 5)
				{
					upLabel.setVisible(!visible);
				}
			} else if ((jComponent instanceof WrapJComBox))
			{
				WrapJComBox wrapJComBox = (WrapJComBox) jComponent;
				if (pageId == 4 || pageId == 5)
				{
					wrapJComBox.setVisible(!visible);
				}

			} else if (((jComponent instanceof ToolBarButton)) && ("计算有效温度".equals(((ToolBarButton) jComponent).getButtonInfo().getText())))
			{

				ToolBarButton toolBarButton = (ToolBarButton) jComponent;
				if ("计算有效温度".equals(toolBarButton.getButtonInfo().getText()))
				{
					if (!visible&&toolBarButton.isSelected())
					{
						toolBarButton.setSelected(false);
					}
				}

			} else if (!set.contains(jComponent))
			{

				if (!visible && !jComponent.isVisible())
				{
					set.add(jComponent);
				}
				jComponent.setVisible(visible);
				if (jComponent instanceof ToolBarButton)
				{
					ToolBarButton toolBarButton = (ToolBarButton) jComponent;
					if (toolBarButton.isSelected())
					{
						toolBarButton.setSelected(false);
					}
				}
			}
		}
	}

	public void restore(SettingTreeNode settingTreeNode)
	{
		this.pageId = settingTreeNode.getPageId();
		restore(leftToolJPanel);
		restore(rightToolJPanel);
		first = false;

	}

	private void restore(JPanel rightToolJPanel)
	{
		for (int i = 0; i < rightToolJPanel.getComponents().length; i++)
		{
			if (rightToolJPanel.getComponent(i) instanceof JComboBox)
			{
				if (!first)
				{
					JComboBox jComboBox = (JComboBox) rightToolJPanel.getComponent(i);
					jComboBox.setSelectedIndex(0);
				}
			}
		}

	}
}

