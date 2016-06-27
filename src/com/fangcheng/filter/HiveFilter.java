package com.fangcheng.filter;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.fangcheng.database.HiveDb;
import com.fangcheng.tools.ClassConstants;

public class HiveFilter extends Filter{
	
	public static Logger logger = Logger.getLogger(HiveFilter.class.getName());// 写控制台
    private static Connection con = null;
    private static Statement stmt = null;
	
	@Override
	public boolean doFilter() {
		logger.info("查看hive中是否有上传........");	
		boolean ret =false;
		String page=ClassConstants.getHIVE_PAGE_TABLE();
		String dp=ClassConstants.getHIVE_COMMENT_TABLE();

		String pageHql = "select count(*) from "+page+" where dt='"+ClassConstants.getTime()+"'";
		String dpHql = "select count(*) from "+dp+" where dt='"+ClassConstants.getTime()+"'";
		
		try {
			con=HiveDb.getConn();
			stmt= con.createStatement();
			
			ResultSet pageSet=stmt.executeQuery(pageHql);
			ret=pageSet.next();
			
			ResultSet dpSet=stmt.executeQuery(dpHql);
			ret=dpSet.next();
				
			logger.info("hivedb 数据上传完毕："+ret);
		} catch (SQLException e) {
			ret=false;
			e.printStackTrace();
			logger.info(e.toString());
		}finally{
			HiveDb.close(stmt, con);
		}
		return ret;			
	}
	
}
