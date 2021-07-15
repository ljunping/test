package com.temperature.swing.event.pavementTemperature;

import com.temperature.swing.event.temperatureFieldSimulation.ChartShowEventSimu;
import com.temperature.swing.pojo.ChartInfo;
import com.temperature.swing.pojo.DataInfo;
import com.temperature.swing.pojo.ExcelInfo;
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
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/19 22:32
 */
public class ChartShowEventIm1 extends ChartShowEventSimu
{

	private List<ExcelInfo> excelInfos;
	private String url;
	public static AtomicBoolean isUpdate = new AtomicBoolean(true);

	@Override
	public void init()
	{
		super.init();
		chartType = ChartType.TimeSeriesChart;
		valueTypes = new ArrayList<>();
		valueTypes.add(ValueType.SIMU);
		title = "钢桥板和路面模拟温度变化";
		names = new ArrayList<>();
		names.add("沥青层顶部");
		names.add("沥青层中部");
		names.add("沥青层与钢板交界面");
		names.add("钢板中部");
		names.add("钢板底部");
		title = getTitle(timeType)+title;
		chartInfo = new ChartInfo(chartType, names, valueTypes, title);
		tableNames = new ArrayList<>();
		for (int i = 0; i < 5; i++)
		{
			tableNames.add("Simulation_result_pavement");
		}


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

	public String getUrl()
	{
		return null;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
}
