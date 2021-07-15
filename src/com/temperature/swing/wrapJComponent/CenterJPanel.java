package com.temperature.swing.wrapJComponent;

import com.temperature.swing.util.TimeType;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * @author Magic Book
 */
public class CenterJPanel extends JPanel
{

	public JPanel headJPanel;
	public JPanel bodyJPanel;
	public JPanel footJPanel;
	public CardLayout headCard;
	public CardLayout bodyCard;
	public CardLayout footCard;
	public static int headHeight = 58;
	public static int footHeight = 58;
	public static int bodyWidth = MyJPanel.width - MyJFrame.leftWidth;
	public static int bodyHeight = MyJPanel.height - footHeight - headHeight;
//	选择时间
	public TimeType timeType = TimeType.YEAR;
	public Date time = new Date(97, 0, 1);
//	温度时程曲线
	public String sensorId = "all";
	public boolean isEffective = false;
//	温度场模拟数据
	public int HeadSelectIndex = 0;
	public int TailSelectIndex = 0;
	public String headSelect="主梁桁架";
	public String tailSelect="通道20";
	private int pageID;

	public CenterJPanel()
	{
		super(new BorderLayout());
		this.headCard = new CardLayout();
		this.bodyCard = new CardLayout();
		this.footCard = new CardLayout();

		this.footJPanel = new JPanel(footCard);
		this.bodyJPanel = new JPanel(bodyCard);
		headJPanel = new JPanel(headCard);
		this.headJPanel.setPreferredSize(new Dimension(1000, headHeight));
		this.add(headJPanel, BorderLayout.NORTH);
		this.add(bodyJPanel, BorderLayout.CENTER);
		this.add(footJPanel, BorderLayout.SOUTH);
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
