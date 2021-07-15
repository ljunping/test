package com.temperature.swing.wrapJComponent;

import com.temperature.swing.beautyeye.ch3_button.BEButtonUI;
import com.temperature.swing.event.Event;
import com.temperature.swing.pojo.ButtonInfo;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 17:54
 */
public class NavButtonLeft extends Button
{
	private ButtonInfo buttonInfo;
	private static NavButtonLeft lastActive;
	private static final Logger logger = Logger.getLogger(NavButtonLeft.class);

	public static Color originColor = new Color(80, 183, 193, 255);
	public static Font defaultFont = new Font("楷体", Font.PLAIN, 20);

	public NavButtonLeft(ButtonInfo buttonInfo, String className) throws ClassNotFoundException
	{
		super(buttonInfo.getText());
		this.buttonInfo = buttonInfo;
		setClazz(className);
		styleSet();
	}

	@Override
	public void ActivePerform() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, JAXBException
	{


//		if (settingTreeNode.eventIsExist() && MyJPanel.pageJPanel[settingTreeNode.getPageId() - 2] != null)
//		{
//			logger.debug("eventObj已存在，进入激活");
//			int pageId = settingTreeNode.getPageId();
//
//			CenterJPanel centerJPanel = MyJPanel.pageJPanel[pageId - 2];
//			JPanelShow.jPanelShow(centerJPanel);
//		} else
		{
			logger.debug("eventObj不存在，进入激活");
			Event event = settingTreeNode.getEventObj();
			event.eventActive(settingTreeNode);
		}

	}

	@Override
	public void styleSet()
	{
		this.setBackground(originColor);
		this.setFont(defaultFont);
		this.setFocusPainted(false);
		this.setMargin(new Insets(10, 20, 10, 20));
		this.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightGreen));
		this.setBorder(BorderFactory.createLineBorder(new Color(49, 127, 170), 1));
		this.setForeground(Color.WHITE);

		this.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				logger.debug("进入" + settingTreeNode.getText());

				NavButtonLeft jButton = (NavButtonLeft) e.getSource();
				if (lastActive != null)
				{
					lastActive.setSelected(false);

				}
				jButton.setSelected(true);
				lastActive = jButton;
				try
				{
					ActivePerform();
				} catch (ClassNotFoundException classNotFoundException)
				{
					logger.debug(classNotFoundException);
					classNotFoundException.printStackTrace();
				} catch (NoSuchMethodException noSuchMethodException)
				{
					logger.debug(noSuchMethodException);
					noSuchMethodException.printStackTrace();
				} catch (IllegalAccessException illegalAccessException)
				{
					logger.debug(illegalAccessException);
					illegalAccessException.printStackTrace();
				} catch (InvocationTargetException invocationTargetException)
				{
					logger.debug(invocationTargetException);
					invocationTargetException.printStackTrace();
				} catch (InstantiationException | JAXBException instantiationException)
				{
					logger.debug(instantiationException);
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
