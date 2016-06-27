package com.fangcheng.hive.runhql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcheng.database.HiveDb;
import com.fangcheng.tools.ClassConstants;

public class HiveData {

	private static final Logger logger = Logger.getLogger(HiveData.class.getName());//写控制台
	private static final Log log = LogFactory.getLog(HiveData.class);//写日志文件
	private static Connection con = null;
	private static Statement stmt = null;
	
	/*
	 * 链接hive数据库
	 * */	
	public static void HiveConnection(){
		try {
			//链接hive数据库
			con = HiveDb.getConn();
			stmt = con.createStatement();
			//设定reduce任务的个数
			logger.info(".....hive 初始化设置....");
			String MRtaskNum="set taskNum="+ClassConstants.TASK_NUM;
			stmt.executeQuery(MRtaskNum);
//			//hive 初始化设置
			ArrayList<String> initHqlArrayList=DpPageHql.readInitHql(ClassConstants.INIT_HQL_PATH);
			for(int i=0;i<initHqlArrayList.size();i++)
				stmt.executeQuery(initHqlArrayList.get(i));
			
		} catch (SQLException e) {
			e.printStackTrace();
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	logger.info("异常信息："+error);
		}
	}
	
	public void hiveClose(){
		HiveData.close(stmt, con);
	}
	
	
	/*
	 * 合并方法2，反射
	 * 
	 * */
	public static void query(String subStr){
		try {
			HiveConnection();
			//建立函数,函数是否需要回收？,必须有个字段作为参数，要不函数只执行一次
			//HQL语句最好全小写，函数尽量使用系统的
			logger.info(subStr);
			stmt.executeQuery(subStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("丢失的语句"+ subStr);
			e.printStackTrace();
			log.info(e.getCause()+ subStr, e);
		} finally{
			HiveData.close(stmt, con);
		}
	}
	/*
	 * 合并方法2，反射;show partitions ticket_ggsn;有自动排序功能和建立先后无关
	 * */
	public static List<String> queryInfo(String subStr){
		List<String> reList = new ArrayList<String>();
		ResultSet res = null;
		try {
			HiveConnection();
			//建立函数,函数是否需要回收？,必须有个字段作为参数，要不函数只执行一次
			//HQL语句最好全小写，函数尽量使用系统的
			logger.info(subStr);
			res = stmt.executeQuery(subStr);
			while (res.next()) {
				logger.info(res.getString(1));
				reList.add(res.getString(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("丢失的语句"+ subStr);
			e.printStackTrace();
			log.info(e.getCause()+ subStr, e);
		} finally{
			HiveData.close(res, stmt, con);
		}
		
		return reList;
	}

	
	//执行多个动态语句
	public static void queryHql(String[] param, int taskNum){
		Connection con = null;
		Statement stmt = null;
		try {
			HiveConnection();
			for(int i=0; i<param.length; i++){
				logger.info(param[i]);
				try{
					stmt.executeQuery(param[i]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info(e.getCause(), e);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(e.getCause(), e);
		} finally{
			HiveData.close(stmt, con);
		}
	}
	/**
	 * 关闭数据库连接
	 * 
	 * @param con
	 */
	public static void close(Connection con)
	{
		close(null, null, con);
	}
	/**
	 * 关闭数据库连接
	 * 
	 * @param stmt
	 * @param con
	 */
	public static void close(Statement stmt, Connection con)
	{
		close(null, stmt, con);
	}
	/**
	 * 关闭数据库连接:这是最安全的关闭连接方法.
	 * 
	 * @param rs
	 *            ResultSet
	 * @param stmt
	 *            Statement
	 * @param con
	 *            Connection
	 */
	public static void close(ResultSet rs, Statement stmt, Connection con)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
				rs = null;
			}
			catch (SQLException e)
			{
			    e.printStackTrace();
			    log.info(e.getCause(), e);
			}
		}
		if (stmt != null)
		{
			try
			{
				stmt.close();
				stmt = null;
			}
			catch (SQLException e1)
			{
			    e1.printStackTrace();
			    log.info(e1.getCause(), e1);
			}
		}
		if (con != null)
		{
			try
			{
				con.close();
				con = null;
			}
			catch (SQLException e2)
			{
			    e2.printStackTrace();
			    log.info(e2.getCause(), e2);
			}
		}
	}
	
	public static void close(ResultSet rs)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
				rs = null;
			}
			catch (SQLException e)
			{
			    e.printStackTrace();
			    log.info(e.getCause(), e);
			}
		}
	}
}
