package com.temperature.swing.event.temperatureFieldSimulation;

import com.temperature.swing.util.ReadInfo;
import com.temperature.swing.pojo.ChartInfo;
import com.temperature.swing.pojo.DataInfo;
import com.temperature.swing.util.ChartFactory;
import com.temperature.swing.util.ChartType;
import com.temperature.swing.util.ValueType;
import com.temperature.swing.wrapJComponent.FigureContain;
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
		chartType = ChartType.TimeSeriesChart;

		valueTypes = new ArrayList<>();
		valueTypes.add(ValueType.SIMU);
		tableNames = new ArrayList<>();
		switch (headSelectIndex)
		{
			case 0:
			{
				title = getTitle(timeType)+tailSelect + "测量温度与模拟温度比较";
				names = new ArrayList<>();
				names.add(tailSelect+"：测量值");
				names.add(tailSelect+"：边界条件1");
				names.add(tailSelect+"：边界条件2");
				for (int i = 0; i < names.size(); i++)
				{

					tableNames.add("Simulation_result_crossframe");
				}
				break;
			}
			case 1:
				switch (tailSelectIndex)
				{
					case 0:
					case 1:
					case 2:
					{
						title = getTitle(timeType)+tailSelect + "测量温度与模拟温度比较";
						names = new ArrayList<>();
						names.add(tailSelect+"：测量值");
						names.add(tailSelect+"：模拟值");
						for (int i = 0; i < names.size(); i++)
						{

							tableNames.add("Simulation_result_deck_plate");
						}
						break;
					}
					case 3:
					{
						title = getTitle(timeType)+tailSelect + "模拟温度";
						names = new ArrayList<>();
						names.add(tailSelect+"：桥面板");
						for (int i = 0; i < names.size(); i++)
						{
							tableNames.add("Simulation_result_deck_plate");
						}
						break;
					}
					case 4:
					{
						title = getTitle(timeType)+tailSelect + "模拟温度";
						names = new ArrayList<>();
						names.add(tailSelect+"：U肋中部");
						for (int i = 0; i < names.size(); i++)
						{
							tableNames.add("Simulation_result_deck_plate");
						}
						break;
					}
				}
				break;
			case 2:
			{
				switch (tailSelectIndex)
				{
					case 0:
					{
						title =getTitle(timeType)+tailSelect + "温度变化";
						names = new ArrayList<>();
						names.add("西1");
						names.add("西2");
						names.add("西3");
						names.add("西4");
						names.add("西5");
						names.add("西6");
						for (int i = 0; i < names.size(); i++)
						{

							tableNames.add("Simulation_result_tower");
						}
						break;
					}
					case 1:
					{
						title = getTitle(timeType)+tailSelect + "温度变化";
						names = new ArrayList<>();
						names.add("东1");
						names.add("东2");
						names.add("东3");
						names.add("东4");
						names.add("东5");
						names.add("东6");
						for (int i = 0; i < names.size(); i++)
						{
							tableNames.add("Simulation_result_tower");
						}
						break;
					}


				}
				break;

			}
			case 3:
			{
				title = getTitle(timeType)+"吊杆模拟温度";
				names = new ArrayList<>();
				names.add("East");
				names.add("West");
				names.add("South");
				names.add("North");
				for (int i = 0; i < names.size(); i++)
				{
					tableNames.add("Simulation_result_hanger");
				}
				break;
			}

		}
		chartInfo = new ChartInfo(chartType, names, valueTypes, title);


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
		switch (headSelectIndex)
		{
			case 2:
			{
				jPanel = new JPanel(new GridLayout(1, 2));
				jPanel.setBackground(Color.blue);

				switch (tailSelectIndex)
				{
					case 0:
					{

						jPanel.add(new FigureContain(ReadInfo.readFigureInfo(6, pageId)));
						break;
					}

					case 1:
					{
						jPanel.add(new FigureContain(ReadInfo.readFigureInfo(7, pageId)));
						break;
					}
				}
				break;

			}
			default:
			{
				jPanel = new JPanel(new GridLayout(1, 1));
			}

		}

	}


	@Override
	public Object getImpObject() throws JAXBException, ClassNotFoundException
	{
		return null;
	}


}
