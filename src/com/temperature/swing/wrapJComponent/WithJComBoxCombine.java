package com.temperature.swing.wrapJComponent;

import com.temperature.swing.pojo.ButtonInfo;
import com.temperature.swing.pojo.Item;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.util.PaddingUnit;
import sun.font.FontDesignMetrics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/20 2:27
 */
public class WithJComBoxCombine
{
	SettingTreeNode settingTreeNode1;
	SettingTreeNode settingTreeNode2;
	Vector<Item> vector1;
	Vector<Item> vector2;
	String label;
	public JComponent headJComponent;
	public JComponent tailLabel;
	public JComponent tailJComponent;

	public WithJComBoxCombine(SettingTreeNode settingTreeNode1, SettingTreeNode settingTreeNode2, Vector<Item> vector)
	{

		super();
		this.settingTreeNode1 = settingTreeNode1;
		this.settingTreeNode2 = settingTreeNode2;
		this.vector1 = vector;
		this.vector2 = map.get(0).vector;
		this.label = map.get(0).getText();
		createHead();
		createTail();
	}

	private void createTail()
	{
		headJComponent = new HeadJComBox(settingTreeNode1, vector1);
	}

	private void createHead()
	{
		tailLabel = new UpLabel(label);
		tailJComponent = new TailJComBox(settingTreeNode2, vector2);
	}

	class TailButton extends ToolBarButton
	{

		public TailButton(ButtonInfo buttonInfo, String className,SettingTreeNode settingTreeNode) throws ClassNotFoundException
		{
			super(buttonInfo, className,settingTreeNode);
		}

		@Override
		public void plus(ActionEvent event)
		{
			MyJPanel.pageJPanel[settingTreeNode.getPageId() - 1].TailSelectIndex = 0;
		}

	}


	class HeadJComBox extends BasicJComBox
	{

		public HeadJComBox(SettingTreeNode settingTreeNode, Vector<Item> vector)
		{
			super(settingTreeNode, vector);
			this.lastSelect = null;
		}

		@Override
		protected void uniqueAction(ActionEvent e) throws ClassNotFoundException
		{
			JComboBox wrapJComBox = (JComboBox) e.getSource();
			int index = wrapJComBox.getSelectedIndex();
			String text = wrapJComBox.getSelectedItem().toString();
			MyJPanel.pageJPanel[settingTreeNode.getPageId() - 1].HeadSelectIndex = index;
			MyJPanel.pageJPanel[settingTreeNode.getPageId() - 1].headSelect = text;

			CenterJPanel centerJPanel = MyJPanel.pageJPanel[settingTreeNode.getPageId() - 1];
			JPanel jPanel = (JPanel) centerJPanel.headJPanel.getComponent(0);
			ToolJPanel toolJPanel = (ToolJPanel) jPanel.getComponent(0);
			JPanel leftJPanel = (JPanel) toolJPanel.getComponent(0);
			Box hBox = (Box) leftJPanel.getComponent(1);
			for (int i = hBox.getComponentCount() - 1; i > 1; i--)
			{
				hBox.remove(i);
			}
			switch (index)
			{
				case 0:
				case 1:
				case 2:
				{
					PaddingUnit.setMargin(leftJPanel, new int[]{5, 0, 0, 0}, 0);
					Type type = map.get(index);
					tailLabel = new UpLabel(type.text);
					tailJComponent = new TailJComBox(settingTreeNode2, type.getVector());
					hBox.add(tailLabel);
					hBox.add(Box.createHorizontalStrut(10));
					hBox.add(tailJComponent);
					break;
				}

				case 3:
				{
					PaddingUnit.setMargin(leftJPanel, new int[]{5, 0, 0, 0}, 0);
					Type type = map.get(index);
					tailJComponent = new TailButton(new ButtonInfo(type.getText(), 0), "ToolBarButton", settingTreeNode2);
					PaddingUnit.setPadding(tailJComponent, new int[]{8, 0, 8, 0}, 0);
					hBox.add(tailJComponent);
					break;
				}
			}
			centerJPanel.headJPanel.validate();
			lastSelect = text;
			execute();


		}

		@Override
		protected int getLen()
		{
			FontDesignMetrics metrics = FontDesignMetrics.getMetrics(defaultFont);
			int len = metrics.stringWidth(vector.get(1).toString());
			return len;
		}

	}

	class TailJComBox extends BasicJComBox
	{


		public TailJComBox(SettingTreeNode settingTreeNode, Vector<Item> vector)
		{

			super(settingTreeNode, vector);
			this.lastSelect = null;
		}

		@Override
		protected void uniqueAction(ActionEvent e)
		{

			JComboBox JComboBox = (JComboBox) e.getSource();
			int index = JComboBox.getSelectedIndex();
			String text = JComboBox.getSelectedItem().toString();
			MyJPanel.pageJPanel[settingTreeNode.getPageId() - 1].TailSelectIndex = index;
			MyJPanel.pageJPanel[settingTreeNode.getPageId() - 1].tailSelect = text;


			lastSelect = text;
			execute();


		}

		@Override
		protected int getLen()
		{
			FontDesignMetrics metrics = FontDesignMetrics.getMetrics(defaultFont);
			int len = metrics.stringWidth(vector.get(1).toString());
			return len;
		}
	}

	public static Map<Integer, Type> map = new HashMap<>();

	{
		Vector<Item> vector1 = new Vector<Item>();
		vector1.add(new Item("通道20", 0));
		vector1.add(new Item("通道22", 1));
		vector1.add(new Item("通道25", 2));
		map.putIfAbsent(0, new Type("显示测量与模拟结果比较", 0, vector1));
		Vector<Item> vector2 = new Vector<Item>();
		vector2.add(new Item("通道66", 0));
		vector2.add(new Item("通道67", 1));
		vector2.add(new Item("通道68", 2));
		vector2.add(new Item("A点", 3));
		vector2.add(new Item("B点", 4));
		map.putIfAbsent(1, new Type("显示测量与模拟结果比较", 0, vector2));
		Vector<Item> vector3 = new Vector<>();
		vector3.add(new Item("塔断面1", 0));
		vector3.add(new Item("塔断面2", 1));
		map.putIfAbsent(2, new Type("显示塔温度变化", 0, vector3));
		map.putIfAbsent(3, new Type("显示吊杆温度变化", 1));
	}

	class Type
	{
		private String text;
		private int type = 0;
		private Vector<Item> vector;

		public Type(String text, int type)
		{
			this.type = type;
			this.text = text;
		}

		public Type(String text, int type, Vector<Item> vector)
		{
			this.vector = vector;
			this.type = type;
			this.text = text;
		}

		public String getText()
		{
			return text;
		}

		public void setText(String text)
		{
			this.text = text;
		}

		public int getType()
		{
			return type;
		}

		public void setType(int type)
		{
			this.type = type;
		}

		public Vector<Item> getVector()
		{
			return vector;
		}

		public void setVector(Vector<Item> vector)
		{
			this.vector = vector;
		}
	}


}
