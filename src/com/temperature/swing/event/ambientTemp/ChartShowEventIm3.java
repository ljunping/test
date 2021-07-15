package com.temperature.swing.event.ambientTemp;

import com.temperature.swing.pojo.ChartInfo;
import com.temperature.swing.pojo.DataInfo;
import com.temperature.swing.util.ChartFactory;
import com.temperature.swing.util.ChartType;
import com.temperature.swing.util.ValueType;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 14:25
 */
public class ChartShowEventIm3 extends ChartShowEventPage2
{
	public static AtomicBoolean isUpdate = new AtomicBoolean(true);

	/**
	 * 后续需要根据文件名进行数据导入
	 */



	@Override
	public Object getImpObject()
	{
		return null;
	}

	@Override
	public ChartPanel createChartPanel(Dataset dataset, DataInfo dataInfo)
	{

		ChartPanel chartPanel = ChartFactory.getBarChart((XYSeriesCollection) dataset, dataInfo);
		return chartPanel;
	}

	@Override
	public void createJPanel()
	{
		this.jPanel=new JPanel(new GridLayout(1, 1));
	}

	@Override
	public void init()
	{
		super.init();
		chartType = ChartType.BarChart;
		title = "日温度变化范围直方图";
		names = new ArrayList<>();
		names.add("频率");
		valueTypes = new ArrayList<>();
		valueTypes.add(ValueType.BAR);
		title = getTitle(timeType)+title;
		chartInfo = new ChartInfo(chartType, names, valueTypes, title);
		tableNames = getTableName();
	}

	@Override
	public void upDate()
	{

		upDate(isUpdate);

	}


	@Override
	public DataInfo getDataInfo()
	{
		return dataInfo;
	}

	@Override
	public void setDataInfo(DataInfo dataInfo)
	{
		this.dataInfo = dataInfo;
	}
}
