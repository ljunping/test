package com.temperature.swing.util;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/2 14:43
 */
public enum ValueType
{
	MIN("MIN", "min"),
	MAX("MAX", "max"),
	MEAN("MEAN", "mean"),
	DIFF("DIFF", "diff"),
	DESIGNUP("DESU", "46"),
	DESIGNDOWN("DESD", "-2"),
	BAR("BAR", "daily_variation"),
	EMIN("EMIN", "min"),
	EMAX("EMAX", "max"),
	EMEAN("EMEAN", "mean"),
	EDIFF("EDIFF", "diff"),
	EBAR("EBAR", "EBAR"),
	SIMU("simulation", "simu");

	private String label;
	private String value;

	ValueType(String label, String value)
	{
		this.label = label;
		this.value = value;
	}


	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
	}
