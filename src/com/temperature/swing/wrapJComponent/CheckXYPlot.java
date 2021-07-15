package com.temperature.swing.wrapJComponent;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.LegendItemEntity;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/8 1:06
 */
public class CheckXYPlot extends XYPlot
{
	private EntityCollection _entityCollection = null;

	public CheckXYPlot(XYDataset dataset, ValueAxis timeAxis, NumberAxis valueAxis, XYItemRenderer xyItemRenderer)
	{

		super(dataset, timeAxis, valueAxis, null);

	}


	@Override
	public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info)
	{
		super.draw(g2, area, anchor, parentState, info);
		drawPlus(g2, info);

	}

	public void drawPlus(Graphics2D g2, PlotRenderingInfo info)
	{

		EntityCollection collection1 = new StandardEntityCollection();
		EntityCollection entityCollection = info.getOwner().getEntityCollection();
		Iterator iterator = entityCollection.iterator();
		int id = 0;
		while (iterator.hasNext())
		{
			ChartEntity chartEntity = (ChartEntity) iterator.next();
			if (chartEntity instanceof LegendItemEntity)
			{
				ChartCheckBox chartCheckBox = null;
				if (_entityCollection == null)
				{
					Shape shape = chartEntity.getArea();
					chartCheckBox = new ChartCheckBox(revise(shape.getBounds()), (LegendItemEntity) chartEntity, id);
				}else
				{

					chartCheckBox = (ChartCheckBox) _entityCollection.getEntity(id);
					chartCheckBox.setBounds(revise(chartEntity.getArea().getBounds()));
					chartCheckBox.setArea(revise(chartEntity.getArea().getBounds()));

				}

				collection1.add(chartCheckBox);
				chartCheckBox.draw(g2, chartCheckBox.getBounds());
				id++;
			}
		}
		_entityCollection = new StandardEntityCollection();
		entityCollection.addAll(collection1);
		_entityCollection.addAll(collection1);
		this.getRangeAxis().setAutoRange(true);
	}


	private Rectangle revise(Rectangle rectangle)
	{
		Rectangle rectangle1 = new Rectangle();

		rectangle1.x = rectangle.x + 10;
		rectangle1.y = rectangle.y + 5;
		rectangle1.width = 20;
		rectangle1.height = 20;
		return rectangle1;
	}


}
