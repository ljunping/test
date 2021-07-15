package com.temperature.swing.event.cableTemperature;

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
public abstract class ChartShowEventPage4 extends ChartShowEvent
{
	@Override
	public void init()
	{
		pageId = settingTreeNode.getPageId();
		this.time = MyJPanel.pageJPanel[pageId - 1].time;
		this.timeType = MyJPanel.pageJPanel[pageId - 1].timeType;
		startTime = time;
		endTime = ChartUtil.caculateEndTime(timeType, startTime);
	}
	@Override
	protected void createDataInfo()
	{
		dataInfo = new DataInfo(pageId, tableNames, startTime, endTime, timeType,chartInfo);
	}

	@Override
	public List<String> getTableName()
	{
		return ChartUtil.getTableNames(chartType, time, timeType, valueTypes);
	}
}
