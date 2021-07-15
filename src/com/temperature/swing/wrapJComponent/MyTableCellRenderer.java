package com.temperature.swing.wrapJComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/12 12:10
 */
public  class MyTableCellRenderer extends DefaultTableCellRenderer
{
	/**
	 * 返回默认的表单元格渲染器，此方法在父类中已实现，直接调用父类方法返回，在返回前做相关参数的设置即可
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		// 偶数行背景设置为白色，奇数行背景设置为灰色
		if (row % 2 == 0) {
			setBackground(Color.WHITE);
		} else {
			setBackground(Color.LIGHT_GRAY);
		}



		setHorizontalAlignment(SwingConstants.CENTER);
		// 第一列的内容水平居中对齐，最后一列的内容水平右对齐，其他列的内容水平左对齐
//		if (column == 0) {
//			setHorizontalAlignment(SwingConstants.CENTER);
//		} else if (column == (table.getColumnCount() - 1)) {
//			setHorizontalAlignment(SwingConstants.RIGHT);
//		} else {
//			setHorizontalAlignment(SwingConstants.LEFT);
//		}

		// 设置提示文本，当鼠标移动到当前(row, column)所在单元格时显示的提示文本
		setToolTipText("提示的内容: " + row + ", " + column);

		// PS: 多个单元格使用同一渲染器时，需要自定义的属性，必须每次都设置，否则将自动沿用上一次的设置。

		/*
		 * 单元格渲染器为表格单元格提供具体的显示，实现了单元格渲染器的 DefaultTableCellRenderer 继承自
		 * 一个标准的组件类 JLabel，因此 JLabel 中相应的 API 在该渲染器实现类中都可以使用。
		 *
		 * super.getTableCellRendererComponent(...) 返回的实际上是当前对象（this），即 JLabel 实例，
		 * 也就是以 JLabel 的形式显示单元格。
		 *
		 * 如果需要自定义单元格的显示形式（比如显示成按钮、复选框、内嵌表格等），可以在此自己创建一个标准组件
		 * 实例返回。
		 */

		// 调用父类的该方法完成渲染器的其他设置
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//		Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
//		((JComponent) component).setBorder(border);
		return component;
	}
}
