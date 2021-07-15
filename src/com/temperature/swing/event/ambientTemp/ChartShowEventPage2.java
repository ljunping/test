package com.temperature.swing.event.ambientTemp;

import com.temperature.swing.event.ChartShowEvent;
import com.temperature.swing.util.ChartUtil;
import com.temperature.swing.pojo.DataInfo;
import com.temperature.swing.wrapJComponent.MyJPanel;

import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/25 21:18
 */
public abstract class ChartShowEventPage2 extends ChartShowEvent
{
	protected String sensorID;
	@Override
	public void init()
	{
		this.pageId = settingTreeNode.getPageId();
		this.time = MyJPanel.pageJPanel[pageId - 1].time;
		this.timeType = MyJPanel.pageJPanel[pageId - 1].timeType;
		this.sensorID = MyJPanel.pageJPanel[pageId - 1].sensorId;
		startTime = time;
		endTime = ChartUtil.caculateEndTime(timeType, startTime);
	}
	@Override
	protected void createDataInfo()
	{
		dataInfo = new DataInfo(tableNames,sensorID, startTime, endTime,timeType, chartInfo);
	}

	@Override
	public List<String> getTableName()
	{
		return ChartUtil.getTableNames(chartType, time, timeType, valueTypes);
	}
}
