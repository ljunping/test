package com.temperature.swing.event.exit;

import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.MyJFrame;
import com.temperature.swing.event.Event;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 15:54
 */
public class ExitEvent implements Event
{
	@Override
	public void eventActive(SettingTreeNode settingTreeNode)
	{
		MyJFrame.getInstance().dispose();
	}

	@Override
	public Object getImpObject()
	{
		return null;
	}
}
