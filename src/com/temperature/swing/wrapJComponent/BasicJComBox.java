package com.temperature.swing.wrapJComponent;

import com.temperature.swing.App;
import com.temperature.swing.pojo.Item;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.util.PaddingUnit;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/20 12:18
 */
public abstract class BasicJComBox extends JComboBox
{
	Vector<Item> vector;
	SettingTreeNode settingTreeNode;
	String lastSelect;
	public static Font defaultFont = App.defaultFont;

	public BasicJComBox(SettingTreeNode settingTreeNode,Vector<Item> vector)
	{
		super(vector);
		this.vector = vector;
		this.settingTreeNode = settingTreeNode;
		lastSelect = vector.get(0).toString();
		styleSet();
	}

	protected abstract void uniqueAction(ActionEvent e) throws ClassNotFoundException;

	protected abstract int getLen();
	protected void styleSet()
	{
		PaddingUnit.setMargin(this, new int[]{0, 0, 0, 0}, 0);
		int len = getLen();
		this.setPreferredSize(new Dimension(len + 60, 40));
		this.setFont(defaultFont);
		this.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					uniqueAction(e);
				} catch (ClassNotFoundException classNotFoundException)
				{
					classNotFoundException.printStackTrace();
				}
			}
		});
	}
	protected void ActiveCase() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JAXBException
	{
		if (settingTreeNode != null&&settingTreeNode.getEvent()!=null)
		{
			settingTreeNode.getEventObj().eventActive(settingTreeNode);
		}

	}
	protected void execute()
	{
		try
		{
			ActiveCase();
		} catch (ClassNotFoundException classNotFoundException)
		{
			classNotFoundException.printStackTrace();
		} catch (NoSuchMethodException noSuchMethodException)
		{
			noSuchMethodException.printStackTrace();
		} catch (InvocationTargetException invocationTargetException)
		{
			invocationTargetException.printStackTrace();
		} catch (InstantiationException instantiationException)
		{
			instantiationException.printStackTrace();
		} catch (IllegalAccessException | JAXBException illegalAccessException)
		{
			illegalAccessException.printStackTrace();
		}
	}
}
