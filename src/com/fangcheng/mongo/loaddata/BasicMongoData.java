package com.fangcheng.mongo.loaddata;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.fangcheng.database.MongoDb;
import com.fangcheng.database.MongoManager;
import com.fangcheng.service.DataService;
import com.fangcheng.tools.ClassConstants;
import com.fangcheng.tools.Project_Tool;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public abstract class BasicMongoData implements DataService{
	
	public static Logger logger = Logger.getLogger(BasicMongoData.class.getName());//写控制台
	public static final int MAX_LINE=10000;  //写入文件的最大行数
	public MongoDb mo = MongoManager.getInstance();	
	public String time=null;
	public ArrayList<String> city=null;
	public String filepath=null;

	@Override
	public abstract boolean  exportData(String run_time, int num);

	@Override
	public DBCursor basicDataService(String collection, String cityId, int num) {
		if(collection==null || mo==null|| mo.db==null){
			logger.info("......mo,db,collectlion空指针.........");
		    System.exit(-1);
		}

		//查询符合条件的数据集，并返回
		DBObject dbo = new BasicDBObject();
		DBCollection coll = mo.db.getCollection(collection);
		if(coll ==null){
			logger.info("......mongo中没有该collection.........");
		    System.exit(-1);
		}
		if(cityId!=null){
			dbo.put("cityId", Integer.parseInt(cityId));
		}
		if(num == 0)
			return coll.find(dbo);
		else			
			return coll.find(dbo).limit(num);
				
	}

	@Override
	public boolean downLoadDataFile(DBCursor obj, String filepath) {
		String file_split =ClassConstants.getFILE_SPLIT();
		obj.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT);
		int tmpline = 0;
		String str = null;
		boolean flag=false;
		try{
			//append==true不新建文件，继续写到文件尾,如果没有第二个参数或者第二个参数为false则新建文件
			FileWriter fw = new FileWriter(filepath,true);
			//创建字符输出流对象 
			BufferedWriter bf = new BufferedWriter(fw);
			while(obj.hasNext()){
				str = planData(obj.next(),file_split);
				if(null != str){
					bf.append(str);
					if(obj.hasNext())
						bf.newLine();
					tmpline ++;
					if(tmpline%MAX_LINE == 0){
						bf.flush();
						tmpline = 0;
					}
				}
			}
			flag=true;
			bf.flush();
			bf.close();
		}catch (IOException e){
			e.printStackTrace();
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	logger.info("异常信息："+error);
		}		
		return flag;
	}

	@Override
	public abstract String planData(DBObject data, String split) ;

	
	@Override
	public void ifExistsFile(String filepath) {
		Project_Tool.ifExistsFile(filepath);
	}
	
	
	
}
