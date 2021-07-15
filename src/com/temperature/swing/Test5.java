package com.temperature.swing;

import com.temperature.swing.wrapJComponent.MyTableCellRenderer;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class Test5
{

	public static void main(String[] args) {
		JFrame jf = new JFrame("测试窗口");
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// 创建内容面板，使用边界布局
		JPanel panel = new JPanel(new BorderLayout());

		// 表头（列名）
		Object[] columnNames = {"姓名", "语文", "数学", "英语", "总分"};

		// 表格所有行数据
		Object[][] rowData = {
				{"张三", 80, 80, 80, 240},
				{"John", 70, 80, 90, 240},
				{"Sue", 70, 70, 70, 210},
				{"Jane", 80, 70, 60, 210},
				{"Joe_01", 80, 70, 60, 210},
				{"Joe_02", 80, 70, 60, 210},
				{"Joe_03", 80, 70, 60, 210},
				{"Joe_04", 80, 70, 60, 210},
				{"Joe_05", 80, 70, 60, 210}
		};

		// 创建一个表格，指定 所有行数据 和 表头
		JTable table = new JTable(rowData, columnNames);

		// 创建单元格渲染器
		MyTableCellRenderer renderer = new MyTableCellRenderer();

		// 遍历表格的每一列，分别给每一列设置单元格渲染器
		for (int i = 0; i < columnNames.length; i++) {
			// 根据 列名 获取 表格列
			TableColumn tableColumn = table.getColumn(columnNames[i]);
			// 设置 表格列 的 单元格渲染器
			tableColumn.setCellRenderer(renderer);
		}

		// 如果需要自定义表头样式，也可以给表头设置一个自定义渲染器
		table.getTableHeader().setDefaultRenderer(renderer);

		// 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		// 把 表格内容 添加到容器中心
		panel.add(table, BorderLayout.CENTER);
		jf.setContentPane(panel);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

	/**
	 * 单元格渲染器，继承已实现渲染器接口的默认渲染器 DefaultTableCellRenderer
	 */


}

