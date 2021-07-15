package com.temperature.swing.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/25 21:55
 */
public class ChartUtil
{
	public static List<String> getTableNameSIMU(int headSelectIndex)
	{
		List<String> list = new ArrayList<>();
		switch (headSelectIndex)
		{
			case 0:
			{
				list.add("Simulation_result_crossframe") ;

				break;
			}
			case 1:
				list.add("Simulation_result_deck_plate");

				break;
			case 2:
			{
				list.add("Simulation_result_tower");

				break;

			}
			case 3:
			{
				list.add("Simulation_result_hanger");
				break;
			}

		}
		return list;
	}
	public static Date caculateEndTime(TimeType timeType,Date startTime)
	{
		Date endTime = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		switch (timeType)
		{
			case DAY:
				calendar.add(Calendar.DATE, 1);
				endTime = calendar.getTime();
				break;
			case MONTH:
				calendar.add(Calendar.MONTH, 1);
				endTime = calendar.getTime();
				break;
			case YEAR:
				calendar.add(Calendar.YEAR, 1);
				endTime = calendar.getTime();
				break;
		}
		return endTime;
	}
	public static List<String> getTableNames(ChartType chartType, Date time, TimeType timeType, List<ValueType> valueTypes)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		List<String> tableNames = new ArrayList<>();
		String tableName = "";
		String date = "";
		String year = "" + calendar.get(Calendar.YEAR);
		switch (timeType)
		{
			case YEAR:
				date = "month-" + year;
				break;
			case MONTH:
				date = "day-" + year;
				break;
			case DAY:
				date = "hour-" + year;
				break;
		}
		switch (chartType)
		{
			case TimeSeriesChart:
			{
				for (ValueType valueType : valueTypes)
				{
					switch (valueType)
					{
						case MAX:
						case MEAN:
						case MIN:


						case EMIN:
						case EMAX:
						case EMEAN:
							tableName = "Temp_t-" + date + "_" + valueType.getValue();
							break;
						case EDIFF:
						case DIFF:
							switch (timeType)
							{
								case YEAR:
									tableName = "Temp_t-" + "day-"+year + "_" + valueType.getValue();
									break;
								case MONTH:
									tableName = "Temp_t-" + date + "_" + valueType.getValue();
									break;
							}
							break;
						default:
							tableName = "";
					}
					tableNames.add(tableName);

				}
				break;
			}
			case BarChart:
			{
				ValueType valueType = valueTypes.get(0);
				switch (valueType)
				{
					case BAR:
					{
						tableName = "Temp_daily_variation_" + year;
						tableNames.add(tableName);
						break;
					}
					case EBAR:
					{
						switch (timeType)
						{
							case YEAR:
								tableName = "Temp_t-hour-" +year+"_mean";
								tableNames.add(tableName);
								break;
						}
						break;
					}
				}

			}
		}
		return tableNames;
	}
}
