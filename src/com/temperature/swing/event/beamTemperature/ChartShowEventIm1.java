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
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 14:25
 */
public class ChartShowEventIm1 extends ChartShowEventPage3
{
	//
	public static AtomicBoolean isUpdate = new AtomicBoolean(true);


	/**
	 * 后续需要根据文件名进行数据导入
	 */


	private void addEffective()
	{

	}



	@Override
	public Object getImpObject()
	{
		return null;
	}

	@Override
	public ChartPanel createChartPanel(Dataset dataset, DataInfo dataInfo)
	{

		this.chartPanel = ChartFactory.getTimeChart((TimeSeriesCollection) dataset,dataInfo);
		return chartPanel;
	}

	@Override
	public void createJPanel()
	{
		this.jPanel = new JPanel(new GridLayout(1, 1));
	}

	@Override
	public void init()
	{
		super.init();
		chartType = ChartType.TimeSeriesChart;
		title = "桥板" + "温度变化";
		names = new ArrayList<>();
		valueTypes = new ArrayList<>();
		names.add("最小值(有效)");
		names.add("平均值(有效)");
		names.add("最大值(有效)");
		names.add("设计值上限");
		names.add("设计值下限");
		valueTypes.add(ValueType.EMIN);
		valueTypes.add(ValueType.EMEAN);
		valueTypes.add(ValueType.EMAX);
		valueTypes.add(ValueType.DESIGNUP);
		valueTypes.add(ValueType.DESIGNDOWN);
		title = getTitle(timeType)+title;
		chartInfo = new ChartInfo(chartType, names, valueTypes, title);
		tableNames = getTableName();
	}

	@Override
	public void upDate() throws JAXBException, ClassNotFoundException
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
