package com.temperature.swing.wrapJComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ComplexTable extends JTable
{
	public final static Object mergeCellX = "mergeCellX";//标识单元格是否要被横向合并 
	public final static Object mergeCellY = "mergeCellY";//标识单元格是否要被纵向合并
	private final Object[][] head;
	private final Object[][] body;

	public ComplexTable(Object[][] headerRows, Object[][] body)
	{
		super(new DefaultTableModel(body, headerRows[0]));
		this.head = headerRows;

		this.body = body;

//		super( 0 , headerRows[0].length );
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//设置table内容居中
//		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
//		tcr.setHorizontalAlignment(JLabel.CENTER);// 这句和上句作用一样
//		this.setDefaultRenderer(Object.class, tcr);
		//设置鼠标点击选中单元格，而不是选中整行
//		getColumnModel().setColumnSelectionAllowed(true);
		styleSet();
		//TODO: 扩展body的合并
/*		this.setUI(newUI);*/

		//设置表头UI
		this.getTableHeader().setUI(new ComplexHeaderUI(headerRows, this));
		this.setUI(new ComplexBodyUI(body, this));


	}
	private void styleSet()
	{
		MyTableCellRenderer renderer = new MyTableCellRenderer();

		this.setShowVerticalLines(true);
		this.setShowHorizontalLines(true);
		this.getTableHeader().setPreferredSize(new Dimension(tableHeader.getWidth(), 40));

		this.setRowHeight(40);
		// 遍历表格的每一列，分别给每一列设置单元格渲染器
		for (int i = 0; i < head[0].length; i++)
		{
			// 根据 列名 获取 表格列
			//i是表头的列
			TableColumn column = this.getTableHeader().getColumnModel().getColumn(i);
			column.setHeaderRenderer(renderer);

			//表头文字居中
			TableColumn tableColumn = this.getColumn(head[0][i]);
			// 设置 表格列 的 单元格渲染器
			tableColumn.setCellRenderer(renderer);
		}
	}
}