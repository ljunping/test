package com.temperature.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ProgressMonitorTest
{
	public static void main(String[] args)
	{
		// 创建一个包含“Click me”的窗口
		final JFrame f =
				new JFrame("ProgressMonitor Sample");
		f.getContentPane().setLayout(new FlowLayout());
		JButton b = new JButton("Click me");
		f.getContentPane().add(b);
		f.pack();

		// 设置按钮的动作事件
		b.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new Thread(() ->
				{
					try {
						// 打开文件输出流，
						//在当前目录中需要放置一个大文件，建议超过50M
						File file = new File("D:\\workspace\\idea\\temperatureAnalysis_new\\src\\com\\temperature\\swing\\event\\resource\\Data_TMB\\Temp_t-hour\\Temp_t-hour-1997_mean.xlsx");
						InputStream in = new FileInputStream(file);
						ProgressMonitorInputStream pm =
								new ProgressMonitorInputStream(f,"Reading a big file",in);
						// 读取文件，如果总耗时超过2秒，
						//   显示已读取的百分比。
						pm.getProgressMonitor().setMillisToDecideToPopup(1);

						byte[] bytes = new byte[1024];

						while((pm.read(bytes)) != -1)
						{
							long i=0;
							Thread.sleep(1);
							System.out.println(i++);

//							System.out.println(new String(bytes));
							// 处理代码
						}
						long i=0;
							while (i < 1000000000000L)
							{
								i++;
							}
						pm.close();
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}).start();
			}});

		// 设置缺省的窗口关闭行为，并显示窗口。
		f.setDefaultCloseOperation
				(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}