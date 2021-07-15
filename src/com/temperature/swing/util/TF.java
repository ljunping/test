package com.temperature.swing.util;

import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Month;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/26 16:47
 */
public class TF
{
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Date StringToDate(String date) throws ParseException
	{
		return sdf.parse(date);
	}
	public static String DateToString(Date date) throws ParseException
	{
		return sdf.format(date);
	}

	public static Double StringToDouble(String value)
	{
		if (value == null || !ValidUtil.isValid(value))
		{
			return null;
		}else
		{
			return Double.parseDouble(value);
		}
	}
	public static Integer round(Double num)
	{
		if (num == null)
		{
			return null;
		}

		double a = Math.signum(num); //判断是正数负数还是0，负数返回-1.0，正数返回1.0
		if (a < 0.0)
		{
			return (int) (0.0 - Math.round(Math.abs(num)));
		}
		return (int) Math.round(num);
	}

	public static Class TimeTypeToClass(TimeType timeType,ValueType valueType)
	{
		switch (timeType)
		{
			case MONTH:
				return Day.class;
			case DAY:
				return Hour.class;
			case YEAR:
				switch (valueType)
				{
					case DIFF:
					case EDIFF:
					{
						return Day.class;
					}
					default:
						return Month.class;
				}


		}
		return null;
	}

	public static Date toEndInside(Date endTime, TimeType timeType)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endTime);

		switch (timeType)
		{
			case YEAR:
				calendar.add(Calendar.MONTH, -1);
				break;
			case MONTH:
				calendar.add(Calendar.DATE, -1);
				break;
			case DAY:
				calendar.add(Calendar.HOUR_OF_DAY, -1);
				break;

		}
		return calendar.getTime();
	}


}
