package com.fangcheng.mongo.dealdata;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.fangcheng.database.MongoDb;
import com.fangcheng.database.MongoManager;
import com.fangcheng.tools.ClassConstants;
import com.fangcheng.tools.TimePrint;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class DealAddDate{
	
	public static Logger logger = Logger.getLogger(DealAddDate.class.getName());//写控制台
	public MongoDb mo = MongoManager.getInstance();
	public DB db = mo.getDb(ClassConstants.getAddDataDatabase());
	public String time = null;
	public static final int MAX_LINE=10000;

	/**
	 * 控制总体流程
	 */
	public boolean deal(String run_time, int num){
		boolean ret=true;
		try{
			time=run_time;
			
			//获得数据库链接配置参数
//			DB db = mo.getDb(ClassConstants.getAddDataDatabase());
//			
			String[] collections=ClassConstants.getAPI_COLLECTION();
			String history_add = ClassConstants.getAPI_HISTORY_ADD();  //api_history_add
			String history_close =ClassConstants.getAPI_HISTORY_CLOSE(); //api_history_clos
						
			//把最新数据插入中间集合
			DBCursor temp = null;
			for(int i=0;i<collections.length;i++){
				logger.info("将集合中数据插入临时表.............."+collections.length+":"+collections[i]);
				temp = getBasicData(collections[i],num);
				if(null != temp)
					insertColl(temp ,history_add+"_temp",history_close+"_temp");
			}
			//把历史add集合数据覆盖到中间表
			temp = getBasicData(history_add,num);
			if(null != temp)
				insertColl(temp,history_add+"_temp",null);
			//把历史close集合数据覆盖到中间表
			temp = getBasicData(history_close,num);
			if(null != temp)
				insertColl(temp,history_close+"_temp",null);
			//将中间表更名为历史表
			if(db.collectionExists(history_add)){
				db.getCollection(history_add).rename(history_add+time);
			}
			db.getCollection(history_add+"_temp").rename(history_add);
			if(db.collectionExists(history_close)){
				db.getCollection(history_close).rename(history_close+time);
			}
			db.getCollection(history_close+"_temp").rename(history_close);
		} catch (Exception e) {
			ret=false;
			e.printStackTrace();
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	logger.info("异常信息："+error);
		}
		return ret;
	}
	

	/**
	 * 依据条件从mongdb数据库中获取数据
	 */
	public DBCursor getBasicData(String collection, int num) {		
		if(collection==null || mo==null){
			logger.info("......mo,db,collectlion空指针.........");
		    System.exit(-1);
		}

		DBCollection coll = db.getCollection(collection);
		DBObject dbo = new BasicDBObject();
		if(coll ==null){
			logger.info("......mongo中没有该collection.........");
		    System.exit(-1);
		}
		dbo.put("shopId", 1);
		dbo.put("shopType", 1);
		dbo.put("cityId", 1);
		dbo.put("addDate", 1);
		dbo.put("power", 1);
		if(num == 0)
			return coll.find(null, dbo);
		else
			return coll.find(null, dbo).limit(num);
	}
	/**
	 * 将数据插入历史表
	 * 
	 * insertColl(temp ,api_history_add_temp,api_history_clos_temp);
	 */
	public boolean insertColl(DBCursor obj, String history_add, String history_close) {
		boolean ret= true;
		DBCollection add = db.getCollection(history_add);
		DBCollection close = history_close==null?null:db.getCollection(history_close);
		
		DBCursor dbc = obj;
		dbc.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT);
		int objNumber = 0;
		JSONObject jobj = null;
		DBObject dbo = null;
		try {
			while(dbc.hasNext()){
				objNumber ++;
				if(objNumber%MAX_LINE == 0){
					logger.info("--------------------------"+objNumber+"-----------------------------");
				}
				dbo = new BasicDBObject();
				jobj = new JSONObject(dbc.next().toString());
				dbo.put("_id", jobj.getInt("shopId"));
				dbo.put("shopId", jobj.getInt("shopId"));
				dbo.put("shopType", jobj.getInt("shopType"));
				dbo.put("cityId", jobj.getInt("cityId"));
				dbo.put("addDate", jobj.getLong("addDate"));
				add.save(dbo);
				if(null != history_close && jobj.getInt("power")<5){
					close.save(dbo);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret=false;
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	logger.info("异常信息："+error);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		DealAddDate lp = new DealAddDate();	
//		MongoDb mo = MongoManager.getInstance();
		if (args.length==1 && args[0].trim()!=null){
			String  date = args[0].trim();
			boolean isDate=TimePrint.isValidDate(date,ClassConstants.getTimeAddDataPattern());
			if(isDate)		
				lp.deal(date,0);	
		}else{
			logger.info("...日期参数输入错误,请输入正确的日期格式：yyyyMM..........");
		}
		MongoManager.exit();
	}
	
}
