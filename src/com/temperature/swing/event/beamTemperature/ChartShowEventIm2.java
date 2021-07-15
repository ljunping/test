package com.temperature.swing.event.beamTemperature;

import com.temperature.swing.pojo.ChartInfo;
import com.temperature.swing.pojo.DataInfo;
import com.temperature.swing.util.ChartFactory;
import com.temperature.swing.util.ChartType;
import com.temperature.swing.util.ValueType;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.Dataset;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 14:25
 */
public class ChartShowEventIm2 extends ChartShowEventPage3
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
		this.jPanel=new JPanel(new GridLayout(1, 1));
		this.chartPanel = ChartFactory.getTimeChart( (TimeSeriesCollection) dataset, dataInfo);
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
		chartType = ChartType.TimeSeriesChart;
		title = "昼夜最大温差曲线";
		names = new ArrayList<>();
		valueTypes = new ArrayList<>();
		names.add("最大温差(有效)");
		valueTypes.add(ValueType.EDIFF);
		title = getTitle(timeType)+title;
		chartInfo = new ChartInfo(chartType, names, valueTypes, title);
		tableNames = getTableName();
	}



	@Override
	public void upDate()
	{
		upDate(isUpdate);
	}


	public ChartPanel getChartPanel()
	{
		return chartPanel;
	}

	public void setChartPanel(ChartPanel chartPanel)
	{
		this.chartPanel = chartPanel;
	}
}
