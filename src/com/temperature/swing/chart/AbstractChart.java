package com.temperature.swing.chart;

import com.temperature.swing.util.TimeType;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/24 17:09
 */
public abstract class AbstractChart
{

	public Dataset dataset;

	public abstract JFreeChart getFreeChart(TimeType timeType);
}
