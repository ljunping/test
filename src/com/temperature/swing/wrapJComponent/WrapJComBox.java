package com.temperature.swing.wrapJComponent;

import com.temperature.swing.pojo.Item;
import com.temperature.swing.pojo.SettingTreeNode;
import sun.font.FontDesignMetrics;

import java.awt.event.ActionEvent;
import java.util.Vector;

/**
 * @author Magic Book
 */
public class WrapJComBox extends BasicJComBox
{
	public WrapJComBox(SettingTreeNode settingTreeNode, Vector<Item> vec)
	{
		super(settingTreeNode, vec);
	}

	@Override
	protected void uniqueAction(ActionEvent e)
	{
		WrapJComBox wrapJComBox = (WrapJComBox) e.getSource();
		String text = wrapJComBox.getSelectedItem().toString();
		MyJPanel.pageJPanel[settingTreeNode.getPageId() - 1].sensorId = text;
		if (wrapJComBox.getSelectedIndex() != 0)
		{
			lastSelect = text;
			execute();

		}
	}

	@Override
	protected int getLen()
	{
		FontDesignMetrics metrics = FontDesignMetrics.getMetrics(defaultFont);
		int len = metrics.stringWidth(vector.get(0).toString());
		return len;
	}
}
