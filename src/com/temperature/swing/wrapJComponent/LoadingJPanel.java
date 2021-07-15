package com.temperature.swing.wrapJComponent;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXBusyLabel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/9 16:30
 */
public class LoadingJPanel extends JPanel
{
	private int pageID;
	List<Component> componentList ;
	private static final Logger logger = Logger.getLogger(LoadingJPanel.class);

	public LoadingJPanel(int pageId)
	{
		super(new BorderLayout());

		this.pageID = pageId;
		JPanel jPanel = new JPanel();

		JXBusyLabel label = new JXBusyLabel(new Dimension(100, 100));

		jPanel.add(label);

		label.setBusy(true);
		setComponentDisable(false);
		logger.debug("工具栏开始遮盖");

		this.add(jPanel, BorderLayout.CENTER);
	}


	public void setComponentDisable(boolean componentDisable)
	{
		CenterJPanel centerJPanel = MyJPanel.pageJPanel[pageID - 1];
//		Component[] components = centerJPanel.headJPanel.getComponents();
		dfs(centerJPanel,componentDisable);

	}

	private void dfs(Component component,boolean componentDisable)
	{
		if (component!=null&&component instanceof JComponent)
		{
			JComponent jComponent = (JComponent) component;
			setted(component, componentDisable);
			for (int i = 0; i < jComponent.getComponentCount(); i++)
			{
				dfs( jComponent.getComponent(i), componentDisable);
			}
		}
	}

	private void setted(Component component,boolean componentDisable)
	{
		if (component instanceof WrapCheckBox)
		{
			WrapCheckBox wrapCheckBox = (WrapCheckBox) component;
			wrapCheckBox.setEnabled(componentDisable);
		}
		if (component instanceof ToolBarButton)
		{
			ToolBarButton wrapCheckBox = (ToolBarButton) component;
			wrapCheckBox.setEnabled(componentDisable);
		}

		if (component instanceof WrapJComBox)
		{
			WrapJComBox wrapJComBox = (WrapJComBox) component;
			wrapJComBox.setEnabled(componentDisable);
		}
//		if (component instanceof WrapDatePick)
//		{
//			WrapDatePick wrapDatePick = (WrapDatePick) component;
//			wrapDatePick.setEnabled(componentDisable);
//		}
//		if (component instanceof WrapDatePick.WithCombox)
//		{
//			WrapDatePick.WithCombox wrapDatePick = (WrapDatePick.WithCombox) component;
//			wrapDatePick.setEnabled(componentDisable);
//		}
	}

	public int getPageID()
	{
		return pageID;
	}

	public void setPageID(int pageID)
	{
		this.pageID = pageID;
	}
}
