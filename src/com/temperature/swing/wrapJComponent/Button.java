package com.temperature.swing.wrapJComponent;

import com.temperature.swing.pojo.SettingTreeNode;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 18:16
 */
public abstract class Button extends JButton
{
	protected Class clazz;
	protected SettingTreeNode settingTreeNode;
	protected String prefix = this.getClass().getPackage().getName();
	public static Button lastActive;
	public boolean flag = false;

	@Override
	public void paint(Graphics g)
	{
//		if (!flag)
//		{
//			if (this instanceof ToolBarButton)
//			{
//				ToolBarButton toolBarButton = (ToolBarButton) this;
//				JComponent component = (JComponent) this.getParent();
//				if ("计算有效温度".equals(toolBarButton.getButtonInfo().getText()))
//				{
//					System.out.println(toolBarButton.getButtonInfo().getText());
//					System.out.println(component.getHeight());
//					System.out.println(this.getHeight());
//					System.out.println(this.getY());
//
//					System.out.println(component.getInsets().top);
//					System.out.println(component.getInsets().bottom);
//					this.setMargin(new Insets(0, 0, 0, 0));
//					this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
//					int paddingTop = (component.getHeight() - this.getHeight()) / 2;
//					System.out.println(paddingTop);
//
//					PaddingUnit.setMargin(this, new int[]{0, 0, paddingTop, 0}, 0);
//				}
//				flag = true;
//
//
//			}
//		}

		super.paint(g);

	}

	public Button()
	{
		super();

	}

	public Button(String text)
	{
		super(text);
	}

	public abstract void ActivePerform() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, JAXBException, InterruptedException;

	public abstract void styleSet();

	public void ActiveCase() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, JAXBException
	{
		if (settingTreeNode != null)
		{
			if (settingTreeNode.getEventObj() != null)
			{
				settingTreeNode.getEventObj().eventActive(settingTreeNode);
			}
		} else
		{
			System.out.println("settingTreeNode為null");
		}
	}


	public <T> T getImObj(Class<T> T)
	{
		return (T) (this);
	}

	public Class getClazz()
	{
		return clazz;
	}

	public void setClazz(String clazz) throws ClassNotFoundException
	{
		this.clazz = Class.forName(prefix + "." + clazz);
	}

	public void setClazz(Class clazz)
	{
		this.clazz = clazz;
	}

	public SettingTreeNode getSettingTreeNode()
	{
		return settingTreeNode;
	}

	public void setSettingTreeNode(SettingTreeNode settingTreeNode)
	{
		this.settingTreeNode = settingTreeNode;
	}
}
