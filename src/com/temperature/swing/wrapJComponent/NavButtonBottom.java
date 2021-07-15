package com.temperature.swing.wrapJComponent;

import com.temperature.swing.beautyeye.ch3_button.BEButtonUI;
import com.temperature.swing.pojo.ButtonInfo;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 17:55
 */
public class NavButtonBottom extends Button
{
	private ButtonInfo buttonInfo;

	public static Color originColor = new Color(80, 183, 193, 255);
	public static Color activeColor = new Color(196, 171, 177, 255);
	public static Font defaultFont = new Font("楷体", Font.PLAIN, 20);

	public NavButtonBottom(ButtonInfo buttonInfo,String className) throws ClassNotFoundException
	{
		super();
		this.buttonInfo = buttonInfo;
		this.setText(buttonInfo.getText());
		setClazz(className);
		styleSet();
	}

	@Override
	public void ActivePerform() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, JAXBException
	{
		ActiveCase();
	}

	@Override
	public void styleSet()
	{
		this.setBackground(originColor);
		this.setFont(defaultFont);
		this.setFocusPainted(false);
		this.setMargin(new Insets(10, 10, 10, 10));
		this.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightGreen_1));

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
				} catch (InstantiationException | JAXBException instantiationException)
				{
					instantiationException.printStackTrace();
				}
			}
		});
	}

	public ButtonInfo getButtonInfo()
	{
		return buttonInfo;
	}

	public void setButtonInfo(ButtonInfo buttonInfo)
	{
		this.buttonInfo = buttonInfo;
	}
}
