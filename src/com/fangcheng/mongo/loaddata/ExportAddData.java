package com.fangcheng.mongo.loaddata;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.fangcheng.database.MongoManager;
import com.fangcheng.tools.ClassConstants;
import com.fangcheng.tools.TimePrint;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class ExportAddData extends BasicMongoData{
	
	public static Logger logger = Logger.getLogger(ExportAddData.class.getName());//写控制台
	/**
	 * 控制总体流程
	 */
	@Override
	public boolean exportData(String run_time, int num){
		boolean ret=true;
		try{
			time=run_time;
			DB db = mo.getDb(ClassConstants.getAddDataDatabase());
			mo.db=db;
            filepath=ClassConstants.getSOURCE_FILE();			
     
			DBCursor obj = basicDataService(ClassConstants.getAdddataCollections(), null, num);

			//判断要生成的文件是否存在，如果存在则删除   api_history_add_\\d{6}\\.txt
			ifExistsFile(filepath+"api_history_add_"+time+".txt");
			if(null != obj)
				downLoadDataFile(obj,filepath+"api_history_add_"+time+".txt");
		} catch (Exception e) {
			ret=false;
			e.printStackTrace();
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	logger.debug("异常信息："+error);
		} 
		
		return ret;
	}
	
	

	@Override
	public String planData(DBObject data,String split) {
		StringBuffer sb = new StringBuffer();
		try {
			JSONObject jobj = new JSONObject(data.toString());
			sb.append(jobj.getInt("shopId"));
			sb.append(split);
			sb.append(jobj.getInt("shopType"));
			sb.append(split);
			sb.append(jobj.getInt("cityId"));
			sb.append(split);
			sb.append(jobj.getString("addDate"));
			sb.append(split);
			sb.append(time);
		} catch (JSONException e) {
			e.printStackTrace();
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	logger.info("异常信息："+error);
		}
		return sb.toString();
	}


	
	
	public static void main(String[] args){			
		if (args.length==1 && args[0].trim()!=null){
			ExportAddData lp = new ExportAddData();
			String  date = args[0].trim();
			boolean isDate=TimePrint.isValidDate(date,ClassConstants.getTimeAddDataPattern());
			if(isDate)		
				lp.exportData(date,0);	
		}else{
			logger.info("...日期参数输入错误,请输入正确的日期格式：yyyyMM..........");
		}
		MongoManager.exit();
	}
}
