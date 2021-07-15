package com.temperature.swing.wrapJComponent;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/12 13:30
 */
public class WrapTable extends JTable
{
	Object[] head;
	Object[][] body;

	@Override
	public void paintComponents(Graphics g)
	{
		super.paintComponents(g);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
	}

	public WrapTable(Object[][] body, Object[] head)
	{
		super(body, head);
		this.body = body;
		this.head = head;
		styleSet();

	}

	private void styleSet()
	{
		MyTableCellRenderer renderer = new MyTableCellRenderer();



		this.setGridColor(Color.BLACK);

		this.setShowVerticalLines(true);
		this.setShowHorizontalLines(true);
		this.getTableHeader().setPreferredSize(new Dimension(tableHeader.getWidth(), 40));

		this.setRowHeight(40);
		// 遍历表格的每一列，分别给每一列设置单元格渲染器
		for (int i = 0; i < body[0].length; i++)
		{
			// 根据 列名 获取 表格列
			//i是表头的列
			TableColumn column = this.getTableHeader().getColumnModel().getColumn(i);
			column.setHeaderRenderer(renderer);

			//表头文字居中
			TableColumn tableColumn = this.getColumn(head[i]);
			// 设置 表格列 的 单元格渲染器
			tableColumn.setCellRenderer(renderer);
		}
		this.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 25));
		this.setFont(new Font("楷体", Font.PLAIN, 25));
	}
}
