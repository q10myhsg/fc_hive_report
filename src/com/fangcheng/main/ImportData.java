package com.fangcheng.main;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.fangcheng.database.MongoManager;

import com.fangcheng.tools.ClassConstants;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class ImportData {
	
	public static Mongo mongoTarget = null;
	public static DB dbTarget = null;
	public static Mongo mongoSource = null;
	public static DB dbSource = null;
	public static final Logger logger = Logger.getLogger(ImportData.class.getName());//写控制台
	public static final int MAX_LINE=10000;
	public static long starTime;
	public static long endTime;
	public static long runTime;
	
	/*
	 * mongodb数据库配置  123.57.4.152(内网id 10.172.216.138)  233(10.172.228.111)
	 * mongoip152=10.172.228.111
	 * mongoport=27017
	 * 
	 * */
	public static void getDataBase(String dbstr){
		starTime=System.currentTimeMillis();
		logger.info("........get database........."+dbstr);
		try {
			mongoTarget = new Mongo(ClassConstants.getMongodb(),ClassConstants.getMongoport());
			dbTarget = mongoTarget.getDB(dbstr);
			mongoSource = new Mongo(ClassConstants.MONGODB_SOURCE,ClassConstants.getMongoport());
			dbSource = mongoSource.getDB(dbstr);
		} catch (UnknownHostException e) {
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
	
	public static void insertData(String collectionName) throws JSONException{
		logger.info("................."+collectionName);
		DBCollection coll1 = dbTarget.getCollection(collectionName);
		DBCollection coll2 = dbSource.getCollection(collectionName);
		
		DBCursor dbc = coll2.find();
		dbc.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT);
		int i=0;
		//逐条
		while(dbc.hasNext()){  
			DBObject dbo = dbc.next();
			dbo.removeField("_id");
			dbo.put("_id", Integer.parseInt(dbo.get("shopId").toString()));
			coll1.save(dbo);
			i++;
			if(i%MAX_LINE==0)
				logger.info("................."+i);
		}
	}
	
	public static void main(String[] args) throws Exception {	
		logger.info("begin importData.............");
		getDataBase(ClassConstants.API_DATABASE);
		if(args!=null && args.length == 1){
			String pagecoll="page_parsed_"+args[0]+"d";
//			String commentcoll="api_"+args[0]+"d";
			insertData(pagecoll);
//			insertData(commentcoll);
		}else{
			insertData(ClassConstants.getPageCollections());
//			insertData(ClassConstants.getDangpingCollections());
		}

		MongoManager.exit();
		endTime=System.currentTimeMillis();
		runTime=endTime-starTime;
		logger.info("end mongo............."+runTime);
	}
	

}
