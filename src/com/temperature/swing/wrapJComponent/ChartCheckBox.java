package com.temperature.swing.wrapJComponent;

import com.temperature.swing.util.ChartFactory;
import org.jfree.chart.block.Block;
import org.jfree.chart.block.EntityBlockParams;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.LegendItemEntity;
import org.jfree.ui.Size2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/7 21:31
 */
public class ChartCheckBox extends ChartEntity implements Block
{
	private Rectangle2D rectangle2d = null;
	private final int inset = 4;
	private final Stroke lineStroke = new BasicStroke(2.0f);
	private LegendItemEntity legendItemEntity;
	private final Color selectColor ;
	private final Color lineColor ;
	private int id;
	private boolean isSelect = true;


	public ChartCheckBox(Shape area, LegendItemEntity legendItemEntity, int id)
	{

		super(area);

		this.rectangle2d = (Rectangle2D) area;
		this.legendItemEntity = legendItemEntity;
		selectColor = ChartFactory.colors.get(id);
		lineColor = ChartFactory.colors.get(id);
		this.id = id;
	}


	@Override
	public String getID()
	{
		return null;
	}

	@Override
	public void setID(String s)
	{

	}

	@Override
	public Size2D arrange(Graphics2D graphics2D)
	{
		return null;
	}

	@Override
	public Size2D arrange(Graphics2D graphics2D, RectangleConstraint rectangleConstraint)
	{
		return null;
	}

	@Override
	public Rectangle2D getBounds()
	{
		return rectangle2d;
	}

	@Override
	public void setBounds(Rectangle2D rectangle2D)
	{
		this.rectangle2d = rectangle2D;
	}

	@Override
	public Object draw(Graphics2D graphics2D, Rectangle2D rectangle2D, Object o)
	{
		if (isSelect)
		{

			graphics2D.setStroke(lineStroke);
			graphics2D.setColor(lineColor);
			graphics2D.drawRect((int) rectangle2D.getX(), (int) rectangle2D.getY(), (int) rectangle2D.getWidth(), (int) rectangle2D.getHeight());

			graphics2D.setColor(selectColor);
			graphics2D.drawRect((int) rectangle2D.getX() + inset, (int) rectangle2D.getY() + inset, (int) rectangle2D.getWidth() - 2 * inset, (int) rectangle2D.getHeight() - 2 * inset);
			graphics2D.fillRect((int) rectangle2D.getX() + inset, (int) rectangle2D.getY() + inset, (int) rectangle2D.getWidth() - 2 * inset, (int) rectangle2D.getHeight() - 2 * inset);
		} else
		{
			graphics2D.setStroke(lineStroke);
			graphics2D.setColor(lineColor);
			graphics2D.drawRect((int) rectangle2D.getX(), (int) rectangle2D.getY(), (int) rectangle2D.getWidth(), (int) rectangle2D.getHeight());
		}
		return null;
	}

	@Override
	public void draw(Graphics2D graphics2D, Rectangle2D rectangle2D)
	{

		draw(graphics2D, rectangle2D, null);

	}


	public LegendItemEntity getLegendItemEntity()
	{
		return legendItemEntity;
	}

	public void setLegendItemEntity(LegendItemEntity legendItemEntity)
	{
		this.legendItemEntity = legendItemEntity;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public boolean isSelect()
	{
		return isSelect;
	}

	public void setSelect(boolean select)
	{
		isSelect = select;
	}
}
