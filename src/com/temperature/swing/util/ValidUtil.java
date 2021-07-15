package com.temperature.swing.util;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/25 16:24
 */
public class ValidUtil
{

	public static String validTempPoint(String text)
	{
		if (!isValid(text)||text == null )
		{
			return null;
		}
		Double d = Double.parseDouble(text);
		if (d < 0.000001)
		{
			d = 0.0;
		}
		return d+"";
	}

	public static boolean isTimePoint(String text)
	{
		return validTimePoint(text) != null;
	}

	public static String validTimePoint(String text)
	{

		if (!isValid(text)||text == null || "999".equals(text) || "999.0".equals(text))
		{
			return null;
		}
		return text;
	}

	public static boolean isValid(String text)
	{
		if (text == null || text.length() == 0)
		{
			return false;
		}
		int c = text.charAt(0) - '0';
		return c >= 0 && c <= 9;
	}
}
