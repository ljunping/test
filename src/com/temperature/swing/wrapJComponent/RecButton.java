package com.temperature.swing.wrapJComponent;

import com.temperature.swing.beautyeye.ch3_button.BEButtonUI;
import com.temperature.swing.pojo.RectangleInfo;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 20:52
 */
public class RecButton extends Button
{
	private RectangleInfo rectangleInfo;
	private boolean isUpdate = false;
	public static Color originColor =new Color(80,183,193, 255);
	public static Font defaultFont=new Font("楷体", Font.PLAIN, 20);

	public RecButton(RectangleInfo rectangleInfo,String className) throws ClassNotFoundException
	{
		super(rectangleInfo.getText());
		this.rectangleInfo = rectangleInfo;
		this.setOpaque(true);

		setClazz(className);
		styleSet();

	}
	@Override
	public void ActivePerform() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, JAXBException
	{
		ActiveCase();

	}

	public void update()
	{
		this.setBounds(rectangleInfo.getRectangle());
	}

	@Override
	public void styleSet()
	{
		this.setBackground(originColor);
		this.setFont(defaultFont);
		this.setFocusPainted(false);
		this.setMargin(new Insets(10, 10, 10, 10));
		this.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightGreen));
		this.setBorder(BorderFactory.createLineBorder(new Color(49, 127, 170), 1));
		this.setBounds(rectangleInfo.getRectangle());
		this.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					ActivePerform();
				} catch (ClassNotFoundException classNotFoundException)
				{
					classNotFoundException.printStackTrace();
				} catch (NoSuchMethodException noSuchMethodException)
				{
					noSuchMethodException.printStackTrace();
				} catch (IllegalAccessException illegalAccessException)
				{
					illegalAccessException.printStackTrace();
				} catch (InvocationTargetException invocationTargetException)
				{
					invocationTargetException.printStackTrace();
				} catch (InstantiationException instantiationException)
				{
					instantiationException.printStackTrace();
				} catch (JAXBException jaxbException)
				{
					jaxbException.printStackTrace();
				}
			}
		});
	}

	public RectangleInfo getRectangleInfo()
	{
		return rectangleInfo;
	}

	public void setRectangleInfo(RectangleInfo rectangleInfo)
	{
		this.rectangleInfo = rectangleInfo;
	}

	public boolean isUpdate()
	{
		return isUpdate;
	}

	public void setUpdate(boolean update)
	{
		isUpdate = update;
	}
}
