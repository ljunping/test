package com.temperature.swing.wrapJComponent;

import com.temperature.swing.beautyeye.ch3_button.BEButtonUI;
import com.temperature.swing.event.ChartShowEvent;
import com.temperature.swing.util.JPanelShow;
import com.temperature.swing.pojo.ButtonInfo;
import com.temperature.swing.pojo.SettingTreeNode;
import org.apache.log4j.Logger;

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
public class ToolBarButton extends Button
{
	private ButtonInfo buttonInfo;
	private static ToolBarButton lastActive = null;

	public static Color defaultColor = new Color(153, 204, 213, 255);
	public static Font defaultFont = new Font("楷体", Font.PLAIN, 20);

	private static final Logger logger = Logger.getLogger(ToolBarButton.class);

	public ToolBarButton(ButtonInfo buttonInfo, String className) throws ClassNotFoundException
	{
//		super(("<html>显示温度变<br>化时间历程 </html>"));
		super(buttonInfo.getText());
		this.buttonInfo = buttonInfo;
		this.setVerticalTextPosition(0);
		setClazz(className);
		styleSet();

	}
	public ToolBarButton(ButtonInfo buttonInfo, String className, SettingTreeNode settingTreeNode) throws ClassNotFoundException
	{
		this(buttonInfo, className);
		this.settingTreeNode = settingTreeNode;

	}
//	@Override
//	public void ActivePerform() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, JAXBException;
//	{
//
//	}

	@Override
	public void ActivePerform() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, JAXBException, InterruptedException
	{
		if (settingTreeNode == null || settingTreeNode.getEvent() == null)
		{
			return;
		}
		logger.debug("进入ToolBarButton");
		logger.debug("点击了" + settingTreeNode.getText() + "按钮");
		logger.debug("页面" + settingTreeNode.getPageId());

		logger.debug("启动加载页");
		loading();
		mainPerform();
	}

	protected void loading() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
	{
		if (settingTreeNode.getEventObj() instanceof ChartShowEvent)
		{
			LoadingJPanel jPanel = new LoadingJPanel(settingTreeNode.getPageId());
			jPanel.setPreferredSize(new Dimension(100, 100));
			JPanelShow.bodyJPanelShow(jPanel, settingTreeNode.getPageId());
		}
	}

	protected void mainPerform()
	{
		new Thread(() ->
		{
			synchronized (this)
			{
				try
				{
					logger.debug("启动线程");
					if (settingTreeNode != null && settingTreeNode.getEventObj() != null)
					{
						try
						{
							logger.debug("开始触发事件");
							settingTreeNode.getEventObj().eventActive(settingTreeNode);
//							System.out.println("完成");
						} catch (ClassNotFoundException e)
						{
							logger.debug(e);
							e.printStackTrace();
						} catch (NoSuchMethodException e)
						{
							logger.debug(e);
							e.printStackTrace();
						} catch (InstantiationException e)
						{
							logger.debug(e);
							e.printStackTrace();
						} catch (IllegalAccessException e)
						{
							logger.debug(e);
							e.printStackTrace();
						} catch (InvocationTargetException e)
						{
							logger.debug(e);
							e.printStackTrace();
						} catch (JAXBException e)
						{
							logger.debug(e);
							e.printStackTrace();
						}
					}
				} catch (ClassNotFoundException e)
				{
					logger.debug(e);
					e.printStackTrace();
				} catch (NoSuchMethodException e)
				{
					logger.debug(e);
					e.printStackTrace();
				} catch (InstantiationException e)
				{
					logger.debug(e);
					e.printStackTrace();
				} catch (IllegalAccessException e)
				{
					logger.debug(e);
					e.printStackTrace();
				} catch (InvocationTargetException e)
				{
					logger.debug(e);
					e.printStackTrace();
				} finally
				{
//						glasspane.stop();
				}
			}
		}).start();
	}

	public void plus(ActionEvent e)
	{
		ToolBarButton jButton = (ToolBarButton) e.getSource();
		if (lastActive != null)
		{
			lastActive.setSelected(false);

		}
		jButton.setSelected(true);
		lastActive = jButton;
	}


	@Override
	public void styleSet()
	{
		this.setBackground(defaultColor);
		this.setFont(defaultFont);
		this.setFocusPainted(false);
		if (buttonInfo.getText().startsWith("<html>"))
		{
			this.setMargin(new Insets(0, 10, 0, 10));
		} else
		{
			this.setMargin(new Insets(10, 10, 10, 10));
		}

		this.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightGreen_1));

		this.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				plus(e);
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
				} catch (InterruptedException interruptedException)
				{
					interruptedException.printStackTrace();
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
