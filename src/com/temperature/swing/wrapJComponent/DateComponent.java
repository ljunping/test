package com.temperature.swing.wrapJComponent;

import com.temperature.swing.App;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.util.TimeType;
import sun.font.FontDesignMetrics;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/4 12:02
 */

public class DateComponent
{
	private SelectComBox selectCombox;
	private YearComBox yearComBox;
	private MonthComBox monthComBox;
	private DayComBox dayComBox;
	private SettingTreeNode settingTreeNode;
	private UpLabel yearLabel = new UpLabel("年");
	private UpLabel monLabel = new UpLabel("月");
	private UpLabel dayLabel = new UpLabel("日");

	public void init()
	{
		monLabel.setVisible(false);
		dayLabel.setVisible(false);
		dayComBox.setVisible(false);
		monthComBox.setVisible(false);

	}


	public DateComponent(SettingTreeNode settingTreeNode)
	{
		this(settingTreeNode, 1997, 2005);
	}

	public DateComponent(SettingTreeNode settingTreeNode,int sYear,int eYear)
	{
		this.settingTreeNode = settingTreeNode;
		this.selectCombox = createSelectComBox();
		this.yearComBox = createYearComBox(sYear,eYear);
		this.monthComBox = createMonthComBox();
		this.dayComBox = createDayComBox(yearComBox, monthComBox);
	}

	public Date getDate()
	{
		Calendar calendar = Calendar.getInstance();
		int year, month, day;
		switch((String)selectCombox.getSelectedItem()){
			case "年":
				year = Integer.parseInt((String) yearComBox.getSelectedItem());
				calendar.set(year, 0, 1);
				break;
			case "月":
				year = Integer.parseInt((String) yearComBox.getSelectedItem());
				month = Integer.parseInt((String) monthComBox.getSelectedItem());
				calendar.set(year, month - 1, 1);
				break;
			case "日":
				year = Integer.parseInt((String) yearComBox.getSelectedItem());
				month = Integer.parseInt((String) monthComBox.getSelectedItem());
				day = Integer.parseInt((String) dayComBox.getSelectedItem());
				calendar.set(year, month - 1, day);
				break;
			default:
				return new Date();

		}
		calendar.add(Calendar.HOUR_OF_DAY, -calendar.get(Calendar.HOUR_OF_DAY));
		calendar.add(Calendar.MINUTE, -calendar.get(Calendar.MINUTE));
		calendar.add(Calendar.SECOND, -calendar.get(Calendar.SECOND));
		return calendar.getTime();
	}

	public static int getDayNum(int year, int month)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		long time1 = calendar.getTimeInMillis();
		calendar.add(Calendar.MONTH, 1);
		long time2 = calendar.getTimeInMillis();
		int dayNum = (int) ((time2 - time1) / (24 * 3600 * 1000));
		return dayNum;
	}

	private DayComBox createDayComBox(YearComBox yearComBox, MonthComBox monthComBox)
	{
		int year = Integer.parseInt((String) yearComBox.getSelectedItem());
		int month = Integer.parseInt((String) monthComBox.getSelectedItem());
		Vector<String> vector = new Vector<>();
		int dayNum = getDayNum(year, month);
		for (int i = 0; i < dayNum; i++)
		{
			vector.add("" + (i + 1));
		}
		DayComBox dayComBox = new DayComBox(vector);
		return dayComBox;
	}


	private MonthComBox createMonthComBox()
	{
		Vector<String> vector = new Vector<>();
		for (int i = 0; i < 12; i++)
		{
			vector.add("" + (i + 1));
		}
		MonthComBox monthComBox = new MonthComBox(vector);
		return monthComBox;
	}

	private YearComBox createYearComBox(int sYear, int eYear)
	{
		Vector<String> vector = new Vector<>();
		for (int i = sYear; i <= eYear; i++)
		{
			vector.add("" + i);
		}
		return new YearComBox(vector);
	}

	private SelectComBox createSelectComBox()
	{
		Vector<String> vector = new Vector<>();
		vector.add("年");
		vector.add("月");
		vector.add("日");
		return new SelectComBox(vector);
	}

	public UpLabel getYearLabel()
	{
		return yearLabel;
	}

	public void setYearLabel(UpLabel yearLabel)
	{
		this.yearLabel = yearLabel;
	}

	public UpLabel getMonLabel()
	{
		return monLabel;
	}

	public void setMonLabel(UpLabel monLabel)
	{
		this.monLabel = monLabel;
	}

	public UpLabel getDayLabel()
	{
		return dayLabel;
	}

	public void setDayLabel(UpLabel dayLabel)
	{
		this.dayLabel = dayLabel;
	}

	class SelectComBox extends DateComBox
	{
		public SelectComBox(Vector vector)
		{
			super(vector);
		}
		@Override
		protected void plusAction()
		{
			switch((String)selectCombox.getSelectedItem()){
				case "年":
					monthComBox.setVisible(false);
					monLabel.setVisible(false);

					dayComBox.setVisible(false);
					dayLabel.setVisible(false);

					MyJPanel.pageJPanel[settingTreeNode.getPageId() - 1].timeType = TimeType.YEAR;
					break;
				case "月":
					monthComBox.setVisible(true);
					monLabel.setVisible(true);
					dayComBox.setVisible(false);
					dayLabel.setVisible(false);
					MyJPanel.pageJPanel[settingTreeNode.getPageId() - 1].timeType = TimeType.MONTH;
					break;
				case "日":
					monthComBox.setVisible(true);
					monLabel.setVisible(true);

					dayComBox.setVisible(true);
					dayLabel.setVisible(true);

					MyJPanel.pageJPanel[settingTreeNode.getPageId() - 1].timeType = TimeType.DAY;
					break;
				default:
			}
		}
	}

	abstract class DateComBox extends JComboBox
	{
		String lastSelect = null;

		public DateComBox(Vector<String> vector)
		{

			super(vector);
			styleSet();

			this.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					DateComBox dateComBox = (DateComBox) e.getSource();
					if (lastSelect == null || dateComBox.getSelectedItem().equals(lastSelect))
					{
						MyJPanel.pageJPanel[settingTreeNode.getPageId() - 1].time = getDate();
						plusAction();
						executeCase();
					}

				}
			});
		}

		public void styleSet()
		{
			FontDesignMetrics metrics = FontDesignMetrics.getMetrics(App.defaultFont);
			int len = metrics.stringWidth((String) this.getItemAt(this.getItemCount()-1));
			this.setPreferredSize(new Dimension(len + 70, 40));
			this.setFont(App.defaultFont);
		}
		protected abstract void plusAction();
	}

	class YearComBox extends DateComBox
	{
		public YearComBox(Vector vector)
		{
			super(vector);
		}

		@Override
		protected void plusAction()
		{
			int year = Integer.parseInt((String) yearComBox.getSelectedItem());
			int month = Integer.parseInt((String) monthComBox.getSelectedItem());
			int dayNum = getDayNum(year, month);
			Vector<String> vector = new Vector<>();
			for (int i = 0; i < dayNum; i++)
			{
				vector.add("" + (i + 1));
			}
			dayComBox.setModel(new DefaultComboBoxModel(vector));
		}
	}

	class MonthComBox extends DateComBox
	{

		public MonthComBox(Vector vector)
		{
			super(vector);
		}

		@Override
		protected void plusAction()
		{
			int year = Integer.parseInt((String) yearComBox.getSelectedItem());
			int month = Integer.parseInt((String) monthComBox.getSelectedItem());
			int dayNum = getDayNum(year, month);
			Vector<String> vector = new Vector<>();
			for (int i = 0; i < dayNum; i++)
			{
				vector.add("" + (i + 1));
			}
			dayComBox.setModel(new DefaultComboBoxModel(vector));
		}
	}

	class DayComBox extends DateComBox
	{

		public DayComBox(Vector vector)
		{
			super(vector);
		}

		@Override
		protected void plusAction()
		{

		}
	}

	public void setSettingTreeNode(SettingTreeNode settingTreeNode)
	{
		this.settingTreeNode = settingTreeNode;
	}

	public DayComBox getDayComBox()
	{
		return dayComBox;
	}

	public void setDayComBox(DayComBox dayComBox)
	{
		this.dayComBox = dayComBox;
	}

	public MonthComBox getMonthComBox()
	{
		return monthComBox;
	}

	public void setMonthComBox(MonthComBox monthComBox)
	{
		this.monthComBox = monthComBox;
	}

	public YearComBox getYearComBox()
	{
		return yearComBox;
	}

	public void setYearComBox(YearComBox yearComBox)
	{
		this.yearComBox = yearComBox;
	}

	public SelectComBox getSelectCombox()
	{
		return selectCombox;
	}

	public void setSelectCombox(SelectComBox selectCombox)
	{
		this.selectCombox = selectCombox;
	}

	private void executeCase()
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

	public void ActiveCase() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, JAXBException
	{
		if (settingTreeNode != null)
		{
			settingTreeNode.getEventObj().eventActive(settingTreeNode);
		} else
		{
			System.out.println("settingTreeNode為null");
		}
	}



}


//
//public class WrapDatePick extends JXDatePicker
//{
//	public WithCombox withCombox;
//	public Date nowTime = null;
//	private SettingTreeNode settingTreeNode;
//
//	public WrapDatePick(SettingTreeNode settingTreeNode)
//	{
//		super();
//		this.setDate(new Date(97, 0, 1));
//
//
//		this.settingTreeNode = settingTreeNode;
//		styleSet();
//	}
//

//
//	public void styleSet()
//	{
//		PaddingUnit.setMargin(this, new int[]{0, 0, 0, 10}, 0);
//		this.setFont(App.defaultFont);
//		this.setPreferredSize(new Dimension(160, 40));
//		this.setFormats("yyyy");
//		this.setFocusable(true);
//		this.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(ActionEvent e)
//			{
//				WrapDatePick datePick = (WrapDatePick) e.getSource();
//				if (nowTime != datePick.getDate())
//				{
//					nowTime = datePick.getDate();
//					MyJPanel.pageJPanel[settingTreeNode.getPageId() - 2].time = nowTime;
//					executeCase();
//				}
//			}
//		});
//		createWithJCom();
//
//	}
//
//	private void createWithJCom()
//	{
//		Vector<String> vector = new Vector<String>();
//		vector.add("年");
//		vector.add("月");
//		vector.add("日");
//		withCombox = new WithCombox(vector, this);
//		int len = FontUtil.getStringLength(App.defaultFont, vector.get(0).toString());
//		withCombox.setPreferredSize(new Dimension(len + 60, 40));
//		withCombox.setFont(App.defaultFont);
//		withCombox.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(ActionEvent e)
//			{
//				WithCombox jComboBox = (WithCombox) e.getSource();
//				if (jComboBox.getSelectedItem() != null)
//				{
//					String type = (String) jComboBox.getSelectedItem();
//					if ("年".equals(type))
//					{
//						jComboBox.datePick.setFormats("yyyy");
//						MyJPanel.pageJPanel[settingTreeNode.getPageId() - 2].timeType = TimeType.YEAR;
//					}
//					if ("月".equals(type))
//					{
//						jComboBox.datePick.setFormats("yyyy-MM");
//						MyJPanel.pageJPanel[settingTreeNode.getPageId() - 2].timeType = TimeType.MONTH;
//					}
//					if ("日".equals(type))
//					{
//						jComboBox.datePick.setFormats("yyyy-MM-dd");
//						MyJPanel.pageJPanel[settingTreeNode.getPageId() - 2].timeType = TimeType.DAY;
//					}
//					executeCase();
//
//				}
//			}
//		});
//	}

//}
