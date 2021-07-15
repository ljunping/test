package com.temperature.swing.event.boomTemperature;

import com.temperature.swing.event.temperatureFieldSimulation.ChartShowEventSimu;
import com.temperature.swing.pojo.ChartInfo;
import com.temperature.swing.pojo.DataInfo;
import com.temperature.swing.util.ChartFactory;
import com.temperature.swing.util.ChartType;
import com.temperature.swing.util.ValueType;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.Dataset;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/19 22:32
 */
public class ChartShowEventIm1 extends ChartShowEventSimu
{

	public static AtomicBoolean isUpdate = new AtomicBoolean(true);

	@Override
	public void init()
	{

		super.init();
		headSelectIndex = 3;
		chartType = ChartType.TimeSeriesChart;
		valueTypes = new ArrayList<>();
		valueTypes.add(ValueType.SIMU);
		title = "吊杆模拟温度";
		names = new ArrayList<>();
		names.add("East");
		title = getTitle(timeType)+title;
		chartInfo = new ChartInfo(chartType, names, valueTypes, title);
		tableNames = getTableName();

	}

	@Override
	public void upDate()
	{
		isUpdate.set(true);

		upDate(isUpdate);
	}

	@Override
	public ChartPanel createChartPanel(Dataset dataset, DataInfo dataInfo)
	{
		this.chartPanel = ChartFactory.getTimeChart((TimeSeriesCollection) dataset, dataInfo);
		return chartPanel;
	}

	@Override
	public void createJPanel() throws JAXBException, ClassNotFoundException
	{

		jPanel = new JPanel(new GridLayout(1, 1));


	}


	@Override
	public Object getImpObject() throws JAXBException, ClassNotFoundException
	{
		return null;
	}

}
