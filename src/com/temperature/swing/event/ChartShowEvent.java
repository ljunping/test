package com.temperature.swing.event;

import com.temperature.swing.pojo.ChartInfo;
import com.temperature.swing.pojo.DataInfo;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.util.*;
import com.temperature.swing.wrapJComponent.ChartCheckBox;
import com.temperature.swing.wrapJComponent.MyJPanel;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.LegendItemEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 14:30
 */
public abstract class ChartShowEvent implements Event
{
	public static Dimension dimension = new Dimension(1400, 600);
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	protected java.util.List<String> names;
	protected List<ValueType> valueTypes;
	protected TimeType timeType;
	protected String title;
	protected DataInfo dataInfo = null;
	protected ChartPanel chartPanel;
	protected JPanel jPanel;
	protected ChartType chartType = null;
	protected SettingTreeNode settingTreeNode;
	protected int pageId;
	protected Date time;
	protected List<String> tableNames;
	protected Date startTime;
	protected Date endTime;
	protected ChartInfo chartInfo;
	private static final Logger logger = Logger.getLogger(ChartShowEvent.class);

	public DataInfo getDataInfo()
	{
		return dataInfo;
	}

	public void setDataInfo(DataInfo dataInfo)
	{
		this.dataInfo = dataInfo;
	}

	public abstract ChartPanel createChartPanel(Dataset dataset,DataInfo dataInfo);

	public abstract void createJPanel() throws JAXBException, ClassNotFoundException;
	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
	{
		this.settingTreeNode = settingTreeNode;
		upDate();
	}

	public abstract void init();

	/**
	 * 图表更新
	 */
	public abstract void upDate() throws JAXBException, ClassNotFoundException;

	protected abstract List<String> getTableName();

	public void upDate(AtomicBoolean isUpdate)
	{
		if (isUpdate.get())
		{
			logger.debug("初始化图表信息");
			init();
			if (valueTypes == null)
			{
				JPanelShow.bodyJPanelShow(new JPanel(), pageId);
				return;
			}
			logger.debug("初始化成功");
			logger.debug("输出图表信息：");
			logger.debug("页面编号："+pageId);
			logger.debug("选择时间："+time.toString());
			logger.debug("表格类型：" + chartType.getLabel());
			logger.debug("时间类型：" + timeType.getLabel());
			for (int i = 0; i < valueTypes.size(); i++)
			{
				logger.debug("曲线" + i +"类型"+ valueTypes.get(i).getLabel());
			}
			for (int i = 0; i < names.size(); i++)
			{
				logger.debug("曲线" + i + "名称" + names.get(i));
			}
			logger.debug("图表标题" + title);

			logger.debug("图表更新");

			try
			{
				createJPanel();
			} catch (JAXBException e)
			{
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			logger.debug("创建基本容器JPanel成功");
			createDataInfo();

			Dataset dataset = null;
			try
			{
				logger.debug("开始创建dataset");
				dataset = GenerateDataSet.GenerateDateSet(dataInfo);
				logger.debug("数据dataset成功");
			} catch (IllegalAccessException illegalAccessException)
			{
				logger.debug(illegalAccessException);
				illegalAccessException.printStackTrace();
			} catch (InstantiationException instantiationException)
			{
				logger.debug(instantiationException);
				instantiationException.printStackTrace();
			} catch (IOException e)
			{
				logger.debug(e);
				e.printStackTrace();
			} catch (ParseException e)
			{	logger.debug(e);
				e.printStackTrace();
			} catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
			if (dataInfo.isEmpty())
			{
				JOptionPane.showMessageDialog(MyJPanel.currentJPanel.bodyJPanel,
						"当前时间段数据为空",
						"消息提示",
						JOptionPane.WARNING_MESSAGE
				);
			}

			logger.debug("开始创建chartJPanel");
			ChartPanel chartPanel = createChartPanel(dataset,dataInfo);
			chartPanel.addChartMouseListener(new ChartMouseListener() {
				@Override
				public void chartMouseClicked(ChartMouseEvent event) {
					ChartEntity entity = event.getEntity();
					if (entity instanceof ChartCheckBox) {
						//*
						ChartCheckBox chartCheckBox = (ChartCheckBox) entity;
						chartCheckBox.setSelect(!chartCheckBox.isSelect());
						LegendItemEntity itemEntity = chartCheckBox.getLegendItemEntity();
						itemEntity.setArea(new Rectangle(100, 100, 100, 100));
						XYDataset dataset = (XYDataset) itemEntity.getDataset();
						int index = dataset.indexOf(itemEntity.getSeriesKey());
						XYPlot plot = (XYPlot) event.getChart().getPlot();
						//set the renderer to hide the series
						XYItemRenderer renderer = plot.getRenderer();
						renderer.setSeriesVisible(index, !renderer.isSeriesVisible(index), false);

						renderer.setSeriesVisibleInLegend(index, true, false);
						plot.configureRangeAxes();
					}
				}

				@Override
				public void chartMouseMoved(ChartMouseEvent chartMouseEvent)
				{

				}
			});
			chartPanel.setPreferredSize(dimension);
			PaddingUnit.setPadding(jPanel, new int[]{100, 200, 100, 200}, 0);
			jPanel.setBackground(Color.WHITE);

			jPanel.add(chartPanel);
			isUpdate.set(false);

		}else
		{
			if (dataInfo != null)
			{
				if (dataInfo.isEmpty())
				{
					JOptionPane.showMessageDialog(MyJPanel.currentJPanel.bodyJPanel,
							"当前时间段数据为空",
							"消息提示",
							JOptionPane.WARNING_MESSAGE
					);
				}
			}
		}

		JPanelShow.bodyJPanelShow(jPanel, pageId);
	}

	protected abstract void createDataInfo();

	protected   String getTitle(TimeType timeType)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		String year = "" + calendar.get(Calendar.YEAR);
		String month = "" + (calendar.get(Calendar.MONTH) + 1);
		String day = "" + calendar.get(Calendar.DATE);
		String title = "";
		switch (timeType)
		{
			case YEAR:
				title = year + "年";
				break;
			case DAY:
				title = year + "年" + month + "月" + day + "日";
				break;
			case MONTH:
				title = year + "年" + month + "月";
				break;
		}
		return title;
	}
}
