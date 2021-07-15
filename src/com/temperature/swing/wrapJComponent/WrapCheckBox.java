package com.temperature.swing.wrapJComponent;

import com.temperature.swing.pojo.ButtonInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/8 20:59
 */
public class WrapCheckBox extends JCheckBox
{

	private ButtonInfo buttonInfo;
	public static Font defaultFont=new Font("楷体", Font.PLAIN, 20);
	public WrapCheckBox(ButtonInfo buttonInfo)
	{
		super(buttonInfo.getText());
		this.buttonInfo = buttonInfo;
		styleSet();
	}


	private void styleSet()
	{
//		PaddingUnit.setMargin(this, new int[]{5, 10, 10, 10}, 0);
		this.setFont(defaultFont);
		this.setFocusable(false);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));

		this.setMargin(new Insets(5, 5, 5, 5));
		this.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				WrapCheckBox wrapCheckBox = (WrapCheckBox) e.getSource();
				MyJPanel.currentJPanel.isEffective = wrapCheckBox.isSelected();
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
