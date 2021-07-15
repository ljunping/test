package com.temperature.swing.util;

import com.temperature.swing.pojo.DataInfo;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import static com.temperature.swing.util.CaculateEffeTem.util.gd;
import static com.temperature.swing.util.CaculateEffeTem.util.gds;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/8 22:37
 */
public class CaculateEffeTem
{

	private static final int maxSize = 10;
	public static LinkedHashMap<String, List<XyPoint>> map = new LinkedHashMap<String, List<XyPoint>>()
	{
		@Override
		protected boolean removeEldestEntry(Map.Entry eldest)
		{
			return size() > maxSize;
		}
	};

	public static Map caculateEHz(String table,DataInfo dataInfo) throws ParseException, SQLException
	{
		Date startTime = dataInfo.getStartTime();
		Date endTime = dataInfo.getEndTime();
		List<XyPoint> eData = CaculateEffeTem.caculateEffective(table,dataInfo, startTime, endTime);
		Map<Integer, Double> map = new HashMap<>();
		int sum = 0;
		for (int i = 0; i < eData.size(); i++)
		{
			Integer key = null;
			if (eData.get(i) != null)
			{
				key = TF.round(TF.StringToDouble(eData.get(i).getY()));
				if (key != null)
				{
					map.putIfAbsent(key, 0.0);
					map.computeIfPresent(key, (k, v) -> v + 1.0);
					sum++;
				}
			}
		}
		for (int d : map.keySet())
		{
			int finalSum = sum;
			map.computeIfPresent(d, (Key, value) -> value / finalSum);
		}
		return map;
	}


	public static List<XyPoint> caculateEffective(String table,DataInfo dataInfo, Date startTime, Date endTime) throws ParseException, SQLException
	{
		List<XyPoint> eData = null;
		if (dataInfo.getPageID() == 4)
		{
			eData= caculateBeamEffective(table);
		}
		else if (dataInfo.getPageID() == 5)
		{
			eData= caculateCableEffective(table);
		} else
		{
			return null;

		}
		List<XyPoint> selectSegment = new ArrayList<>();
		long s = startTime.getTime()/1000;
		long t = endTime.getTime()/1000;
		for (int i = 0; i < eData.size(); i++)
		{
			if (eData.get(i) != null)
			{

				long timeMil = TF.StringToDate(eData.get(i).getX()).getTime()/1000;
				if (timeMil >= s && timeMil < t)
				{
					selectSegment.add(eData.get(i));
				}
			}

		}
		return selectSegment;

	}
	public static List<XyPoint> caculateCableEffective(String table) throws SQLException
	{

		String key = table;

		if (map.containsKey(key))
		{
			return map.get(key);
		}
		List<List<String>> data = SQLiteJDBC.getInstance().selectAll(key);



		List<XyPoint> result = new ArrayList<>();
		for (int i = 0; i < data.size(); i++)
		{
			List<String> temDate = data.get(i);
			String time = temDate.get(0);
			if (isValidCable(temDate))
			{
				util.data = temDate;
//				double T_E = ((gd(102) + gd(103) + gd(104) + gd(105) + gd(106) + gd(107) + gd(108) + gd(109) + gd(110)) / 9);
				double T_E = ((gd(110) + gd(111) + gd(112) + gd(113) + gd(114) + gd(115)) / 6);

				result.add(new TimePoint(time, ""+T_E));
			} else
			{
				result.add(new TimePoint(time, ""+null));
			}
		}
		map.putIfAbsent(key, result);

		return result;
	}

	private static boolean isBeamValid(List<String> temDate)
	{
		util.data = temDate;
		for (int i = 53; i <= 80; i++)
		{
			if (!ValidUtil.isTimePoint(gds(i)))
			{
				return false;
			}
		}
		return true;

	}

	private static boolean isValidCable(List<String> temDate)
	{
		util.data = temDate;
		for (int i = 110; i <= 115; i++)
		{

			if (!ValidUtil.isTimePoint(gds(i)))
			{

				return false;
			}
		}
		return true;

	}


	public static List<XyPoint> caculateBeamEffective(String table) throws SQLException
	{


		String key = table;
		if (map.containsKey(key))
		{
			return map.get(key);
		}
		List<List<String>> data = SQLiteJDBC.getInstance().selectAll(key);

		List<XyPoint> result = new ArrayList<>();
		for (int i = 0; i < data.size(); i++)
		{
			List<String> temDate = data.get(i);
			String time = temDate.get(0);
			if (isBeamValid(temDate))
			{
				util.data = temDate;
				double s = 2.29;
				double A_b = 0.7;
				double A_w = 0.95;
				double T_D1, T_D2, T_D3, T_D4, T_D5;
//      Calculate mean temp.

				T_D1 = (gd(56) * s + (gd(53) + gd(55)) * A_w + gd(54) * A_b) / (s + 2 * A_w + A_b);
				T_D2 = (gd(60) * s + (gd(57) + gd(59)) * A_w + gd(38) * A_b) / (s + 2 * A_w + A_b);
				T_D3 = (gd(64) * s + (gd(61) + gd(63)) * A_w + gd(62) * A_b) / (s + 2 * A_w + A_b);
				T_D4 = (gd(68) * s + (gd(65) + gd(67)) * A_w + gd(66) * A_b) / (s + 2 * A_w + A_b);
				T_D5 = (gd(80) * s + (gd(77) + gd(79)) * A_w + gd(78) * A_b) / (s + 2 * A_w + A_b);
//				sensor nos. 69-72
				double s_ct = 3.5;
				double A_b1 = 2.3;
				double A_w1 = 2.3;
				double T_C1;

				T_C1 = (gd(72) * s_ct + (gd(69) + gd(71)) * A_w1 + gd(70) * A_b1) / (s_ct + 2 * A_w1 + A_b1);
//				 sensor nos. 73-76
				double s_cb = 4.3;
				double A_b2 = 2.4;
				double A_w2 = 2.4;

				double T_C2 = (gd(72) * s_cb + (gd(73) + gd(75)) * A_w2 + gd(74) * A_b2) / (s_cb + 2 * A_w2 + A_b2);
//      Calculate effective temp.
				double S_chord = 0.2;
				double W_B = 2.55;
				double W_T = 4.95;
				double S_cb = 0.2745;
				double S_ct = 0.2670;

				double T_E = (T_D1 * W_T + T_D2 * W_B + (T_D3 + T_D4) / 2 * (W_T - S_ct) + T_D5 * (W_B - S_cb) + (T_C1 + T_C2) * S_chord) / (W_T + W_B + (W_T - S_ct) + (W_B - S_cb) + 2 * S_chord);
				result.add(new TimePoint(time, T_E + ""));
			} else
			{
				result.add(new TimePoint(time, ""+null));
			}

		}
		map.putIfAbsent(key, result);
		return result;


	}


	static class util
	{
		static List<String> data;

		public static Double gd(int sensorId)
		{
			String value = ValidUtil.validTimePoint(data.get(sensorId));
			return Double.parseDouble(value);
		}

		public static String gds(int sensorId)
		{
			if (sensorId >= data.size())
			{
				return null;
			}
			return data.get(sensorId);
		}
	}
}
