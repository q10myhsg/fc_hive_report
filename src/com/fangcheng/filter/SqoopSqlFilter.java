package com.fangcheng.filter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.fangcheng.database.HiveDb;
import com.fangcheng.tools.ClassConstants;

public class SqoopSqlFilter extends Filter{

	public static Logger logger = Logger.getLogger(SqoopSqlFilter.class.getName());// 写控制台
	private static Connection con = null;
	private static Statement stmt = null;	

	@Override
	public boolean doFilter() {
		logger.info("查看数据报告是否有生成........");		
		boolean ret =false;
	
		try {
			con=HiveDb.getConn();
			stmt= con.createStatement();
			
			String dpres=ClassConstants.HIVE_DP_FINAL;		
			String dpHql = "select count(*) from "+dpres+" where dt='"+ClassConstants.getTime()+"'";
			
			ResultSet dpSet=stmt.executeQuery(dpHql);
			ret =dpSet.next();
			
			logger.info("查看数据报告是否有生成："+ret);	
		} catch (SQLException e) {
			ret=false;
			e.printStackTrace();
			logger.info(e.toString());
		}		
		return ret;			
	}
}
