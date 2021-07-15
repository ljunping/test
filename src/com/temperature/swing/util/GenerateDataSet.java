package com.temperature.swing.util;//package com.temperature.swing.util;


import com.temperature.swing.pojo.DataInfo;
import org.apache.log4j.Logger;
import org.jfree.data.general.Dataset;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/23 22:48
 */
public class GenerateDataSet
{

	private static final Logger logger = Logger.getLogger(GenerateDataSet.class);
	private static final Map<Integer, Map<Integer, Double>> map = new HashMap<>();
	private static DataInfo dataInfo;
	private static ValueType valueType;


	public static Dataset GenerateDateSet(DataInfo dataInfo2) throws IllegalAccessException, InstantiationException, IOException, ParseException, SQLException
	{
		dataInfo = dataInfo2;
		logger.debug("进入数据提取GernetateDataSet:");
		logger.debug("输出数据基本信息:");

		ChartType chartType = dataInfo.getChartInfo().getChartType();
		List<ValueType> valueTypes = dataInfo.getChartInfo().getValueTypes();
		TimeType timeType = dataInfo.getTimeType();
		List<String> names = dataInfo.getChartInfo().getCurveName();
		List<String> tableNames = dataInfo.getTableNames();

		Dataset dataset = null;
		switch (chartType)
		{
			case TimeSeriesChart:
				dataset = new TimeSeriesCollection();
				for (int j = 0; j < valueTypes.size(); j++)
				{
					String name = names.get(j);
					valueType = valueTypes.get(j);
					String tableName = tableNames.get(j);

					switch (valueType)
					{
						case MIN:
						case MAX:
						case MEAN:
						case DIFF:
							logger.debug("曲线名字" + valueType.getLabel());
							setTimeSeries(tableName, (TimeSeriesCollection) dataset, name);
							break;
						case DESIGNDOWN:
						case DESIGNUP:
						{
							setDESTimeSeries(valueType, (TimeSeriesCollection) dataset, name);
							break;
						}

						case EDIFF:
						case EMEAN:
						case EMIN:
						case EMAX:
							logger.debug("曲线名字" + valueType.getLabel());
							setETimeSeries(tableName,(TimeSeriesCollection) dataset, name);
							break;
						case SIMU:
							setSIMUTimeSeries(tableName, (TimeSeriesCollection) dataset);
							break;

					}
				} break;
			case BarChart:
				dataset = new XYSeriesCollection();
				String name = names.get(0);

				switch (valueTypes.get(0))
				{
					case EBAR:
						switch (timeType)
						{
							case YEAR:
								setEBar(tableNames.get(0),(XYSeriesCollection) dataset, name);
								break;
							case MONTH:
							case DAY:
						}
						break;

					case BAR:
						switch (timeType)
						{
							case YEAR:
								setBar(tableNames.get(0), (XYSeriesCollection) dataset, name);
								break;
							case DAY:
							case MONTH:
						}
						break;
				}
//				valueTypes的size为1
		}

		return dataset;
	}

	private static void setETimeSeries(String tableName,TimeSeriesCollection dataset, String name) throws IOException, ParseException, SQLException
	{

		Date startTime = dataInfo.getStartTime();
		Date endTime = dataInfo.getEndTime();
		Class clazz = TF.TimeTypeToClass(dataInfo.getTimeType(),valueType);

		List<XyPoint> eData = CaculateEffeTem.caculateEffective(tableName,dataInfo,startTime, endTime);

		if (eData == null || eData.size() == 0)
		{
			return;
		}
		TimeSeries timeSeries = new TimeSeries(name);

		for (int i = 0; i <eData.size(); i++)
		{

			Date t = TF.StringToDate(eData.get(i).getX());
			System.out.println(t);
			Double value = TF.StringToDouble(eData.get(i).getY());
			timeSeries.add(new TimeSeriesDataItem(RegularTimePeriod.createInstance(clazz, t, TimeZone.getDefault()), value));
			if (value != null)
			{

				dataInfo.setEmpty(false);
			}

		}
		dataset.addSeries(timeSeries);
	}


	private static void setDESTimeSeries(ValueType valueType, TimeSeriesCollection dataset, String name)
	{
		TimeSeries timeSeries = new TimeSeries(name);
		Date startTime = dataInfo.getStartTime();
		Date endTime = dataInfo.getEndTime();
		endTime = TF.toEndInside(endTime,dataInfo.getTimeType());
		Class clazz = TF.TimeTypeToClass(dataInfo.getTimeType(),valueType);
		timeSeries.add(new TimeSeriesDataItem(RegularTimePeriod.createInstance(clazz, startTime, TimeZone.getDefault()), Double.parseDouble(valueType.getValue())));
		timeSeries.add(new TimeSeriesDataItem(RegularTimePeriod.createInstance(clazz, endTime, TimeZone.getDefault()), Double.parseDouble(valueType.getValue())));
		dataset.addSeries(timeSeries);
		logger.debug("创建数据dataset:  " + valueType.getLabel() + "成功");
	}

	private static void setTimeSeries(String tableName, TimeSeriesCollection dataset, String name) throws IOException, ParseException, SQLException
	{
		TimeSeries timeSeries = new TimeSeries(name);
		Date startTime = dataInfo.getStartTime();
		Date endTime = dataInfo.getEndTime();
		String sensorId = dataInfo.getSensorId();
		if (!ValidUtil.isValid(sensorId))
		{
			logger.debug("传感器编号不合法");
			return;
		}
		List<XyPoint> data = SQLiteJDBC.getInstance().selectByTime(tableName, startTime, endTime, sensorId);
		Class clazz = TF.TimeTypeToClass(dataInfo.getTimeType(), valueType);



		if (data != null)
		{

			for (int i = 0; i < data.size(); i++)
			{
				Date t = TF.StringToDate(data.get(i).getX());
				Double value = TF.StringToDouble(data.get(i).getY());
				timeSeries.add(new TimeSeriesDataItem(RegularTimePeriod.createInstance(clazz, t, TimeZone.getDefault()), value));
				if (value != null)
				{
					dataInfo.setEmpty(false);
				}
			}
		}
		dataset.addSeries(timeSeries);


	}


	private static void setBar(String tableName, XYSeriesCollection dataset, String name) throws IOException, SQLException
	{


		String sensorId = dataInfo.getSensorId();
		if (!ValidUtil.isValid(sensorId))
		{
			return;
		}
		List<XyPoint> data = SQLiteJDBC.getInstance().selectByTemp(tableName, sensorId);
		XYSeries series = new XYSeries(name);
		if (data != null)
		{

			int end = data.size() - 1;
			Double value = TF.StringToDouble(data.get(end).getY());
			while (value == null || value < 0.00001)
			{
				end--;
				value = TF.StringToDouble(data.get(end).getY());
			}
			for (int i = 0; i < end + 1; i++)
			{
				String label = data.get(i).getX();
				value = TF.StringToDouble(data.get(i).getY());

				if (ValidUtil.isValid(label))
				{
					series.add(Double.parseDouble(label), value * 100);
					if (value != null)
					{
						dataInfo.setEmpty(false);
					}
				}
			}
		}
		dataset.addSeries(series);
	}

	private static void setSIMUTimeSeries(String tableName, TimeSeriesCollection dataset) throws SQLException, ParseException
	{

		List<String> names = dataInfo.getChartInfo().getCurveName();
		Date startTime = dataInfo.getStartTime();
		Date endTime = dataInfo.getEndTime();
		List<XyPoint> data = null;
		List<String> tableNames = dataInfo.getTableNames();
		for (int i = 0; i < names.size(); i++)
		{

			TimeSeries timeSeries = new TimeSeries(names.get(i));
			data = SQLiteJDBC.getInstance().selectSIMU(tableNames.get(i), startTime, endTime, names.get(i));
			if (data != null)
			{

				for (int j = 0; j < data.size(); j++)
				{
					Date t = TF.StringToDate(data.get(j).getX());

					Double value = TF.StringToDouble(data.get(j).getY());
					timeSeries.add(new TimeSeriesDataItem(RegularTimePeriod.createInstance(Hour.class, t, TimeZone.getDefault()), value));
					if (value != null)
					{
						dataInfo.setEmpty(false);
					}
				}
			}
			dataset.addSeries(timeSeries);
		}
	}

	private static void setEBar(String table,XYSeriesCollection dataset, String name) throws ParseException, SQLException
	{
		Map<Integer, Double> dataMap = null;

		dataMap = CaculateEffeTem.caculateEHz(table,dataInfo);

		XYSeries series = new XYSeries(name);

		if (dataMap.size() > 0)
		{
			dataInfo.setEmpty(false);
		}


		for (Map.Entry entry:dataMap.entrySet())
		{
			series.add(new XYDataItem((int)entry.getKey(), ((Double)(entry.getValue())) * 100));

		}



		dataset.addSeries(series);

	}


//	private static void setEDiffYear(TimeSeriesCollection dataset, String name) throws ParseException, SQLException
//	{
//		TimeSeries timeSeries = new TimeSeries(name);
//		Date startTime = dataInfo.getStartTime();
//		Date endTime = dataInfo.getEndTime();
//		List<TimePoint> eData = CaculateEffeTem.caculateEffective(dataInfo, startTime, endTime);
//
//			if (eData == null || eData.size() == 0)
//			{
//				return;
//			}
//			for (int i = 0; i <= eData.size(); i++)
//			{
//
//				Date t = sdf.parse(plotInfo.data.get(i).get(0));
////				?Date t = GetTime.getTime(date1, data2, GenerateDataSet.time, timeType);
//				Double value = eData.get(i);
//				if (value == null)
//				{
//					timeSeries.add(new TimeSeriesDataItem(RegularTimePeriod.createInstance(plotInfo.clazz, t, TimeZone.getDefault()), null));
//				} else
//				{
//					dataInfos.setEmpty(false);
//					timeSeries.add(new TimeSeriesDataItem(RegularTimePeriod.createInstance(plotInfo.clazz, t, TimeZone.getDefault()), value));
//				}
//
//			}
//			calendar.add(Calendar.MONTH, 1);
//
//		}
//		dataset.addSeries(timeSeries);
//	}

//	private static void setDiffYear(String tableName,TimeSeriesCollection dataset, String name) throws IOException, ParseException, SQLException
//	{
//		TimeSeries timeSeries = new TimeSeries(name);
//		Date startTime = dataInfo.getStartTime();
//		Date endTime = dataInfo.getEndTime();
//		String sensorId = dataInfo.getSensorId();
//
//		if (!ValidUtil.isValid(sensorId))
//		{
//			logger.debug("传感器编号不合法");
//			return;
//		}
//		List<XyPoint> data = SQLiteJDBC.getInstance().selectByTime(tableName, startTime, endTime, sensorId);
//		for (int s = 0; s < 12; s++)
//		{
//			PlotInfo plotInfo = getPlotInfo(calendar.getTime(), valueType, timeType1);
//
//			if (plotInfo.data == null || plotInfo.data.size() == 0)
//			{
//				continue;
//			}
//			if (!ValidUtil.isValid(sensorId))
//			{
//				logger.debug("传感器编号不合法");
//				logger.debug("创建" + valueType.getLabel() + "曲线失败");
//				return;
//			}
//			Integer id = Integer.parseInt(sensorId);
//
//			id = id + plotInfo.moveRight;
//			if (id != null && plotInfo.data != null && plotInfo.data.size() > 0)
//			{
//				for (int i = plotInfo.startRow; i <= plotInfo.endRow; i++)
//				{
//					String date1 = null, data2 = null;
//					if (timeType.equals(TimeType.DAY))
//					{
//						data2 = plotInfo.data.get(i).get(1);
//					}
//					Date t = sdf.parse(plotInfo.data.get(i).get(0));
//
//					String value = plotInfo.data.get(i).get(id);
//					if (value == null || !ValidUtil.isValid(value))
//					{
//						timeSeries.add(new TimeSeriesDataItem(RegularTimePeriod.createInstance(plotInfo.clazz, t, TimeZone.getDefault()), null));
//					} else
//					{
//						dataInfos.setEmpty(false);
//						timeSeries.add(new TimeSeriesDataItem(RegularTimePeriod.createInstance(plotInfo.clazz, t, TimeZone.getDefault()), Double.parseDouble(value)));
//					}
//				}
//			}
//			calendar.add(Calendar.MONTH, 1);
//
//		}
//		dataset.addSeries(timeSeries);
//	}



//	private static PlotInfo getPlotInfo(Date time, ValueType valueType, TimeType timeType) throws IOException, SQLException
//	{
//		int startRow;
//		int endRow;
//		time.setHours(0);
//		time.setMinutes(0);
//		time.setSeconds(0);
//		Date startTime = new Date(time.getYear(), 0, 1);
//		Date endTime = null;
//		Class clazz;
//		int moveRight = 0;
//		Calendar calendar = Calendar.getInstance();
//		logger.debug("excel格式");
//
//		switch (timeType)
//		{
//			case YEAR:
//				startRow = 0;
//				endRow = 11;
//				clazz = Month.class;
//				time.setMonth(0);
//				calendar.setTime((Date) startTime.clone());
//				calendar.add(Calendar.YEAR, 1);
//				calendar.add(Calendar.MONTH, -1);
//				endTime = calendar.getTime();
//				logger.debug("年  " + valueType.getLabel());
//				logger.debug("起始行  " + startRow);
//				logger.debug("终止行  " + endRow);
//				logger.debug("excel读取首列偏移  " + moveRight);
//
//
//				break;
//			case MONTH:
//				time.setDate(1);
//				calendar.setTime((Date) time.clone());
//				calendar.add(Calendar.MONTH, 1);
//				calendar.add(Calendar.DATE, -1);
//				endTime = calendar.getTime();
//				startRow = (int) ((time.getTime() - startTime.getTime()) / (24 * 3600 * 1000));
//				endRow = (int) ((endTime.getTime() - startTime.getTime()) / (24 * 3600 * 1000));
//				clazz = Day.class;
//				logger.debug("年  " + valueType.getLabel());
//				logger.debug("起始行  " + startRow);
//				logger.debug("终止行  " + endRow);
//				logger.debug("excel读取首列偏移  " + moveRight);
//
//				break;
//			case DAY:
//				time.setHours(0);
//				startRow = (int) ((time.getTime() - startTime.getTime()) / (24 * 3600 * 1000)) * 24;
//				endRow = startRow + 23;
//				clazz = Hour.class;
//				calendar.setTime(time);
//				calendar.add(Calendar.HOUR_OF_DAY, 23);
//				endTime = calendar.getTime();
//				moveRight = 0;
//				logger.debug("年  " + valueType.getLabel());
//				logger.debug("起始行  " + startRow);
//				logger.debug("终止行  " + endRow);
//				logger.debug("excel读取首列偏移  " + moveRight);
//				break;
//			default:
//				logger.debug(new IllegalStateException("Unexpected value: " + timeType));
//				throw new IllegalStateException("Unexpected value: " + timeType);
//		}
//		logger.debug("是否读取excel: " + moveRight);
//		switch (valueType)
//		{
//			case DESIGNDOWN:
//			case DESIGNUP:
//				logger.debug("是否读取excel:否");
//				return new PlotInfo(startRow, endRow, moveRight, startTime, endTime, clazz, null);
//			default:
//				break;
//		}
//		logger.debug("是否读取excel:是");
//
//		List<List<String>> data = ExcelReader.readXlsx(valueType, timeType, time);
//		return new PlotInfo(startRow, endRow, moveRight, startTime, endTime, clazz, data);
//	}




}

