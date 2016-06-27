package com.fangcheng.filter;

import org.apache.log4j.Logger;

import com.fangcheng.database.MongoDb;
import com.fangcheng.database.MongoManager;
import com.fangcheng.tools.ClassConstants;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class MongFilter extends Filter {
	
	public static Logger logger = Logger.getLogger(MongFilter.class.getName());// 写控制台
	public MongoDb mo = MongoManager.getInstance();	
	
	@Override
	public boolean doFilter() {
		logger.info("查看mongdb中是否有数据........");
		boolean ret =false;
		String page=ClassConstants.getPageDatabase();
		String dp=ClassConstants.getDianpingDatabase();
		DB db1 = mo.getDb(page);
		DB db2 = mo.getDb(dp);
		
		DBCollection pageCollection = db1.getCollection(ClassConstants.getPage_collection());
		DBObject pageNum = pageCollection.findOne();
		
		DBCollection dpCollection =db2.getCollection(ClassConstants.getComment_collection());
		DBObject dpNum =dpCollection.findOne();
		
		if(pageNum.toString()!=null && dpNum.toString() != null)
			ret=true;		
		return ret;		
	}

}
