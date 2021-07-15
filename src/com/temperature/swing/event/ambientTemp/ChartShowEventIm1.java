package com.temperature.swing.event.ambientTemp;

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
public class ChartShowEventIm1 extends ChartShowEventPage2
{
//
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

		this.chartPanel = ChartFactory.getTimeChart((TimeSeriesCollection) dataset, dataInfo);
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


		names = new ArrayList<>();
		names.add("最小值");
		names.add("平均值");
		names.add("最大值");
		names.add("设计值上限");
		names.add("设计值下限");
		valueTypes = new ArrayList<>();
		valueTypes.add(ValueType.MIN);
		valueTypes.add(ValueType.MEAN);
		valueTypes.add(ValueType.MAX);
		valueTypes.add(ValueType.DESIGNUP);
		valueTypes.add(ValueType.DESIGNDOWN);
		title = getTitle(timeType)+"环境" + "温度变化";
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
