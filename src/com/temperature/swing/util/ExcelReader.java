package com.temperature.swing.util;

import com.monitorjbl.xlsx.StreamingReader;
import com.temperature.swing.event.Style;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/2 14:32
 */
public class ExcelReader
{
	public static SimpleDateFormat simpleDateFormat_Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String suffix = ".xlsx";
	public static String prefix = "Temp_t-";
	public static String root = "resource/Data_TMB/";
	private static final int maxSize = 10;
	public static LinkedHashMap<String, List<List<String>>> map = new LinkedHashMap<String, List<List<String>>>(maxSize, (float) 0.75, false)
	{
		@Override
		protected boolean removeEldestEntry(Map.Entry eldest)
		{
			return size() > maxSize;
		}
	};
	private static final Logger logger = Logger.getLogger(ExcelReader.class);
	private static Date time;

	public static List<List<String>> readXlsxByUrl(String url) throws SQLException
	{

		logger.debug("进入readXlsxByUrl");

		if (url != null)
		{
			if (map.containsKey(url))
			{
				logger.debug("数据已存入map,直接取出");
				return map.get(url);
			}
			List<List<String>> data = readXlsx(url);
			map.put(url, data);
			System.out.println(map.size());
			return data;
		}
		return null;
	}

	public static List<List<String>> readXlsx(ValueType valueType, TimeType timeType, Date time2) throws SQLException
	{

		logger.debug("进入readXlsx");
		time = time2;
		if (valueType != null && timeType != null)
		{
			String url = getUrl(valueType, timeType);

			List<List<String>> data = readXlsxByUrl(url);
			return refineAsTime(data);
		}
		return null;
	}

	private static List<List<String>> refineAsTime(List<List<String>> data)
	{
		List<List<String>> lists = new ArrayList<>();
		for (int i = 0; i < data.size(); i++)
		{
			List<String> list = data.get(i);
			List<String> list1 = new ArrayList<>();
			for (int j = 0; j < list.size(); j++)
			{
				list1.add(ValidUtil.validTimePoint(list.get(j)));
			}
			lists.add(list1);
		}
		return lists;
	}

	public static String getUrl(ValueType valueType, TimeType timeType)
	{
		String path = prefix;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		String year = "" + (calendar.get(Calendar.YEAR));
		String fileName;
		String url = "";

		switch (valueType)
		{
			case BAR:
				path = path + "bar";
				url = root + path + "/" + "Temp_daily_variation_" + year + suffix;
				break;
			default:
				switch (timeType)
				{
					case MONTH:
						path = path + "day";
						break;
					case DAY:
						path = path + "hour";
						break;
					case YEAR:
						path = path + "month";

				}
				fileName = path + "-" + year + "_" + valueType.getValue() + suffix;
				url = root + path + "/" + fileName;
		}
		return url;
	}

	synchronized public static List<List<String>> readXlsx(String url) throws SQLException
	{
		String[] strings = url.split("/");
		String tableName = strings[strings.length - 1].substring(0,strings[strings.length - 1].length()-5);

		return SQLiteJDBC.getInstance().selectAll(tableName);

	}
	synchronized public static List<List<String>> readXlsxOld(String url) throws IOException
	{
		List<List<String>> data = new ArrayList<>();
		List<String> rowData;
		logger.debug("excel相对路径：" + url);

		if (url != null)
		{
			URL file = Style.class.getResource(url);

			if (file != null)
			{
				logger.debug("excel绝对路径：" + file.getPath());
				InputStream in = file.openStream();
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
					logger.debug("第" + rowNum + "行excel显示长度：" + len);
					int sum = 0;

					rowData = new ArrayList<>();
					data.add(rowData);
					for (Cell cell : row)
					{
						sum++;
						String string = null;
						if (isValid(cell))
						{

							string = getCellValueByCell(cell);
						}
						rowData.add(string);
//						System.out.println(string);
					}

					logger.debug("第" + rowNum + "数据读取完成" + "    " + "数据实际个数：" + sum);

					rowNum++;
				}

			} else
			{
				logger.debug("excel路径为空");

			}

		}

		return data;
	}

	public static boolean isValid(Cell cell)
	{
		String string = cell.getStringCellValue();
		if ("999.00".equals(string) || "999".equals(string) || "999.0".equals(string))
		{
			return false;
		}
		return cell != null && !cell.toString().trim().equals("");
	}

	public static String getCellValueByCell(Cell cell)
	{
		//判断是否为null或空串

		String cellValue = "";
		CellType cellType = cell.getCellType();
		switch (cellType)
		{
			case NUMERIC: // 数字
				short format = cell.getCellStyle().getDataFormat();
				if (DateUtil.isCellDateFormatted(cell))
				{
					SimpleDateFormat sdf = null;
					//System.out.println("cell.getCellStyle().getDataFormat()="+cell.getCellStyle().getDataFormat());
					if (format == 21 || format == 20 || format == 32)
					{
						sdf = new SimpleDateFormat("HH:mm:ss");
					} else if (format == 15 || format == 14 || format == 31 || format == 57 || format == 58)
					{
						// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
						sdf = new SimpleDateFormat("yyyy-MM-dd");
						double value = cell.getNumericCellValue();
						Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
						cellValue = sdf.format(date);
					} else
					{// 日期
						sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					}
					try
					{
						cellValue = sdf.format(cell.getDateCellValue());// 日期
					} catch (Exception e)
					{
						try
						{
							throw new Exception("exception on get date data !".concat(e.toString()));
						} catch (Exception e1)
						{
							e1.printStackTrace();
						}
					} finally
					{
						sdf = null;
					}
				} else
				{
					BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
					cellValue = bd.toPlainString();// 数值 这种用BigDecimal包装再获取plainString，可以防止获取到科学计数值
				}
				break;
			case STRING: // 字符串
				cellValue = cell.getStringCellValue();
				break;
			case BOOLEAN: // Boolean
				cellValue = cell.getBooleanCellValue() + "";

				break;
			case FORMULA: // 公式
				//cellValue = cell.getCellFormula();
				cellValue = cell.getStringCellValue();

				break;
			case BLANK: // 空值
				cellValue = "";
				break;
			case ERROR: // 故障
				cellValue = "ERROR VALUE";
				break;
			default:
				cellValue = "UNKNOW VALUE";
				break;
		}
		return cellValue;
	}

}
