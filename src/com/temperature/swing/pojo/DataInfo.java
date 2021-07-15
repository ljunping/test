package com.temperature.swing.pojo;

import com.temperature.swing.util.TimeType;

import java.util.Date;
import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/23 23:40
 */
public class DataInfo
{

	private Date startTime;
	private Date endTime;
	private Date time;
	private TimeType timeType;
	private int pageID;
	private String title;
	private String sensorId;
	private boolean isEmpty=true;
	private String url;
	private List<String> tableNames;
	private ChartInfo chartInfo;

	public DataInfo(List<String> tableNames,  Date startTime, Date endTime, TimeType timeType, ChartInfo chartInfo)
	{
		this.timeType = timeType;
		this.chartInfo = chartInfo;
		this.tableNames = tableNames;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public DataInfo(List<String> tableNames, String sensorId, Date startTime, Date endTime, TimeType timeType, ChartInfo chartInfo)
	{
		this.timeType = timeType;
		this.chartInfo = chartInfo;
		this.sensorId = sensorId;
		this.tableNames = tableNames;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public DataInfo(List<String> tableNames,  Date startTime, Date endTime, ChartInfo chartInfo)
	{
		this.chartInfo = chartInfo;
		this.tableNames = tableNames;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public DataInfo(int pageId, List<String> tableNames, Date startTime, Date endTime, TimeType timeType, ChartInfo chartInfo)
	{
		this.pageID = pageId;
		this.timeType = timeType;
		this.chartInfo = chartInfo;
		this.tableNames = tableNames;
		this.startTime = startTime;
		this.endTime = endTime;
	}


	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}



	public TimeType getTimeType()
	{
		return timeType;
	}



	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

	public int getPageID()
	{
		return pageID;
	}

	public void setPageID(int pageID)
	{
		this.pageID = pageID;
	}

	public String getSensorId()
	{
		return sensorId;
	}

	public void setSensorId(String sensorId)
	{
		this.sensorId = sensorId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public boolean isEmpty()
	{
		return isEmpty;
	}

	public void setEmpty(boolean empty)
	{
		isEmpty = empty;
	}




	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}



	public ChartInfo getChartInfo()
	{
		return chartInfo;
	}

	public void setChartInfo(ChartInfo chartInfo)
	{
		this.chartInfo = chartInfo;
	}

	public List<String> getTableNames()
	{
		return tableNames;
	}

	public void setTableNames(List<String> tableNames)
	{
		this.tableNames = tableNames;
	}


}
