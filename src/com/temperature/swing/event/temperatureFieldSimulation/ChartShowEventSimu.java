package com.temperature.swing.event.temperatureFieldSimulation;

import com.temperature.swing.event.ChartShowEvent;
import com.temperature.swing.util.ChartUtil;
import com.temperature.swing.pojo.DataInfo;
import com.temperature.swing.util.TimeType;
import com.temperature.swing.wrapJComponent.MyJPanel;

import java.util.Calendar;
import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/25 21:18
 */
public abstract class ChartShowEventSimu extends ChartShowEvent
{
	protected int headSelectIndex;
	protected int tailSelectIndex;
	protected String headSelect;
	protected String tailSelect;
	@Override
	public void init()
	{
		pageId = settingTreeNode.getPageId();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2005);
		calendar.set(Calendar.MONTH, 6);
		calendar.set(Calendar.DATE, 17);
		calendar.add(Calendar.HOUR_OF_DAY, -calendar.get(Calendar.HOUR_OF_DAY));
		calendar.add(Calendar.MONTH, -calendar.get(Calendar.MONTH));
		calendar.add(Calendar.SECOND, -calendar.get(Calendar.SECOND));
		time = calendar.getTime();
		timeType = TimeType.DAY;
		startTime = time;
		endTime = ChartUtil.caculateEndTime(timeType, startTime);
		headSelectIndex = MyJPanel.pageJPanel[pageId - 1].HeadSelectIndex;
		tailSelectIndex = MyJPanel.pageJPanel[pageId - 1].TailSelectIndex;
		tailSelect = MyJPanel.pageJPanel[pageId - 1].tailSelect;
		headSelect = MyJPanel.pageJPanel[pageId - 1].tailSelect;

	}
	@Override
	protected void createDataInfo()
	{
		dataInfo = new DataInfo(tableNames, startTime, endTime,timeType, chartInfo);
	}

	@Override
	public List<String> getTableName()
	{
		return ChartUtil.getTableNameSIMU(headSelectIndex);
	}

}
