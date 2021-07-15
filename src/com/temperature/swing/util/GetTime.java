package com.temperature.swing.util;


import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/2 19:48
 */
public class GetTime
{

	private static final Logger logger = Logger.getLogger(GetTime.class);
	public static String[] monthString = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
	public static Map<String, Integer> map = new HashMap<>();

	static
	{
		for (int i = 0; i < monthString.length; i++)
		{
			map.put(monthString[i], i);
		}
	}


	public static Date getTime(String year, String Month, String day, String hour, String minuter, String second)
	{
		int month = map.get(Month);
		Date date = new Date();
		date.setYear(Integer.parseInt(year));
		date.setMonth(month);
		date.setDate(Integer.parseInt(day));
		date.setHours(Integer.parseInt(hour));
		date.setMinutes(Integer.parseInt(minuter));
		date.setSeconds(Integer.parseInt(second));
		return date;
	}

	public static Date getTime(String year, String Month, String day)
	{
		int month = map.get(Month);
		Date date = new Date();
		date.setYear(Integer.parseInt(year));
		date.setMonth(month);
		date.setDate(Integer.parseInt(day));
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}

	public static Date getTime(String date1, String date2, Date time, TimeType timeType) throws ParseException
	{
		logger.debug("进入GetTime,开始进行excel上日期转换");
		Date date = null;
		if (date1 != null && date2 != null)
		{
			date = ExcelReader.simpleDateFormat_Date.parse(date1 + " " + date2);
		}else if (date1 != null)
		{
			date = ExcelReader.simpleDateFormat_Date.parse(date1 + " " + "00:00:00");
		}

		if (date != null)
		{
			date = revise(date, time, timeType);
		}

		return date;
	}

	private static Date revise(Date date, Date time, TimeType timeType)
	{
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(time);
		calendar2.setTime(date);
		switch (timeType)
		{
			case DAY:
			case MONTH:
				int mon1 = calendar1.get(Calendar.MONTH);

				int mon2 = calendar2.get(Calendar.MONTH);
				if (mon1 != mon2)
				{
					calendar2.add(Calendar.MONTH, mon1 - mon2);
				}
			case YEAR:
				int year = calendar1.get(Calendar.YEAR);
				int year2 = calendar2.get(Calendar.YEAR);
				if (year != year2)
				{
					calendar2.add(Calendar.YEAR, year - year2);
				}
				date = calendar2.getTime();
				break;
			default:

		}
		return date;

	}
}
