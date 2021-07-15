package com.temperature.swing.util;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/23 15:33
 */

import com.temperature.swing.event.Style;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SQLiteJDBC
{

	private Connection connection = null;
	private final Class clazz = Style.class;
	private final String prefix = "resource/dataDB/";
	private static final Logger logger = Logger.getLogger(SQLiteJDBC.class);
	private static class SingletonHolder
	{
		private static final SQLiteJDBC instance = new SQLiteJDBC();
	}
	public static SQLiteJDBC getInstance()
	{

		return SingletonHolder.instance;
	}

	private SQLiteJDBC()
	{
		connection = createConnection();
	}


	private Connection createConnection()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite::resource:com/temperature/swing/event/resource/dataDB/data.db");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		System.out.println("Opened database successfully");
		return connection;
	}

	public List<List<String>> selectAll(final String tableName) throws SQLException
	{
		List<List<String>> lists = new ArrayList<>();
		String sql = "select * from '" + tableName+"'";
		Statement statement = null;
		ResultSet rs = null;
		try
		{
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			while (rs.next())
			{
				List<String> list = new ArrayList<>();
				for (int i = 1; i <=numberOfColumns; i++)
				{
					list.add(rs.getString(i));
				}
				lists.add(list);
			}

		} catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}finally
		{
			if (rs != null)
			{
				rs.close();
			}

		}
		return lists;
	}



	public List<XyPoint> selectSIMU(String tableName, Date startTime, Date endTime, String clumName) throws SQLException, ParseException
	{
		List<XyPoint> list = new ArrayList<>();
		String sql = "select [" + clumName + "] from " + tableName ;
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		while (rs.next())
		{
			String time = TF.DateToString(calendar.getTime());
			String value = ValidUtil.validTimePoint(rs.getString(clumName));
			list.add(new TimePoint(time, value));
			calendar.add(Calendar.HOUR_OF_DAY, 1);
		}
		return list;
	}
	public List<XyPoint> selectByTime(String tableName, Date startTime, Date endTime, String clumName) throws ParseException, SQLException
	{
		List<XyPoint> list = new ArrayList<>();
		String sql = "select [time],[" + clumName + "] from '" + tableName + "' where time>='" + TF.DateToString(startTime)+ "' and time<'" + TF.DateToString(endTime)+"'";
		logger.error(sql);
		ResultSet rs = null;
		Statement statement = null;
		try
		{
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next())
			{
				String time = rs.getString("time");
				String value = ValidUtil.validTimePoint(rs.getString(clumName));
				list.add(new TimePoint(time, value));
			}
		} catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}finally
		{
			if (rs != null)
			{
				rs.close();
			}
		}



		return list;
	}

	public List<XyPoint> selectByTemp(String tableName,String clumName) throws SQLException
	{
		List<XyPoint> list = new ArrayList<>();
		String sql = "select [温度],[" + clumName + "] from " + tableName;
		Statement statement = null;
		ResultSet rs = null;
		try
		{
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next())
			{
				String temp = rs.getString("温度");
				String value = ValidUtil.validTempPoint(rs.getString(clumName));
				list.add(new TempPoint(temp, value));
			}
		} catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}finally
		{
			if (rs != null)
			{
				rs.close();

			}
		}

		return list;
	}




}
