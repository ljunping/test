package com.temperature.swing;


import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/2 14:32
 */
public class Test4
{







	public static void main(String[] args) throws FileNotFoundException
	{
		String t = "D:/workspace/Temp_t-hour-1997_";
		String[] strings = {"mean.xlsx", "min.xlsx", "max.xlsx"};
		List<List<String>> data = new ArrayList<>();
		List<String> rowData;
		for (int i = 0; i < 3; i++)
		{
			String url = t + strings[i];
			if (url != null)
			{
				File file = new File(url);
				if (file != null)
				{

					InputStream in = new FileInputStream(file);

					Workbook wk = StreamingReader.builder().rowCacheSize(100)  //缓存到内存中的行数，默认是10
							.bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
							.open(in);  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
					Sheet sheet = wk.getSheetAt(0);
					//遍历所有的行int c;
					int rowNum = 0;
					boolean flag = false;
					for (Row row : sheet)
					{
						if (rowNum == 0)
						{
							boolean tag = false;
							for (Cell cell : row)
							{
								if (cell.getStringCellValue() != null)
								{
									tag = true;
									break;

								}
							}
							if (!tag)
							{
								continue;

							}
							if (!flag)
							{
								flag = true;
								continue;
							}

						}
						//遍历所有的列
						int len = row.getLastCellNum() - row.getFirstCellNum();
						if (len >= 116)
						{
							rowData = new ArrayList<>();
							data.add(rowData);
							for (Cell cell : row)
							{
								String string = cell.getStringCellValue();
								if ("999.00".equals(string) || "999".equals(string) || "999.0".equals(string))
								{
									string = null;
								}
								System.out.println(string);

								rowData.add(string);
							}
						}

						rowNum++;
					}

				}


			}
		}


	}

}

