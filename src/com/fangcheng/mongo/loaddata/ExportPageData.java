package com.fangcheng.mongo.loaddata;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fangcheng.database.MongoManager;
import com.fangcheng.tools.ClassConstants;
import com.fangcheng.tools.TimePrint;
import com.fangcheng.utils.FCStringUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ExportPageData extends BasicMongoData {

	public static Logger logger = Logger.getLogger(ExportPageData.class.getName());// 写控制台

	/**
	 * 控制总体流程
	 */
	@Override
	public boolean exportData(String run_time, int num) { // num=0 或 num=300
		boolean ret = true;
		try {
			String dbstr = ClassConstants.getPageDatabase();
			time = run_time;
			DB db = mo.getDb(dbstr);
			mo.db = db;
			filepath = ClassConstants.getSOURCE_FILE();
			DBCursor obj = basicDataService(ClassConstants.getPageCollections(), null, num);
			// 判断要生成的文件是否存在，如果存在则删除 city_\\d{1}\\_page_\\d{6}\\.txt
			ifExistsFile(filepath + "city_page_" + time + ".txt");
			downLoadDataFile(obj, filepath + "city_page_" + time + ".txt"); // 将page_parsed_201604d文件按地市+时间写入txt文件。

		} catch (Exception e) {
			ret = false;
			e.printStackTrace();
			StringBuffer error = new StringBuffer();
			StackTraceElement[] messages = e.getStackTrace();
			int length = messages.length;
			for (int i = 0; i < length; i++) {
				error.append(messages[i]);
			}
			logger.debug("异常信息：" + error);
		}
		return ret;
	}

	/*
	 * 从mongdb中筛选符合条件的数据集合
	 */
	@Override
	public DBCursor basicDataService(String collection, String cityId, int num) {

		DBCollection coll = mo.db.getCollection(collection);

		DBObject dbo = new BasicDBObject();
		dbo.put("closed", false);

		if (cityId != null) {
			dbo.put("cityId", Integer.parseInt(cityId));
		}

		if (num == 0)
			return coll.find(dbo);
		else
			return coll.find(dbo).limit(num);
	}

	@Override
	public String planData(DBObject data, String split) {
		StringBuffer sb = new StringBuffer();
		try {
			JSONObject jobj = new JSONObject(data.toString());
			if (null == jobj.getJSONObject("cat").toString())
				return null;
			sb.append(jobj.getInt("shopId"));
			sb.append(split);
			sb.append(jobj.isNull("cityId") ? 0 : jobj.getInt("cityId"));
			sb.append(split);
			
			JSONArray star = jobj.getJSONArray("star");
			if(star!=null&&star.length()==5){
				sb.append(star.get(0)==null ? 0 : star.get(0));
				sb.append(split);
				sb.append(star.get(1)==null ? 0 : star.get(1));
				sb.append(split);
				sb.append(star.get(2)==null ? 0 : star.get(2));
				sb.append(split);
				sb.append(star.get(3)==null ? 0 : star.get(3));
				sb.append(split);
				sb.append(star.get(4)==null ? 0 : star.get(4));
				sb.append(split);
			}else{
				sb.append(0);
				sb.append(split);
				sb.append(0);
				sb.append(split);
				sb.append(0);
				sb.append(split);
				sb.append(0);
				sb.append(split);
				sb.append(0);
				sb.append(split);
			}
			JSONObject cat=jobj.getJSONObject("cat");
			if(cat!=null){
				sb.append(cat.isNull("businessarea") ? "":FCStringUtils.cleanLineFlag(cat.getString("businessarea")));
				sb.append(split);
				sb.append(cat.isNull("cat_1") ? "":FCStringUtils.cleanLineFlag(cat.getString("cat_1")));
				sb.append(split);
				sb.append(cat.isNull("cat_2") ? "":FCStringUtils.cleanLineFlag(cat.getString("cat_2")));
				sb.append(split);
				sb.append(cat.isNull("cat_3") ? "":FCStringUtils.cleanLineFlag(cat.getString("cat_3")));
				sb.append(split);
				sb.append(cat.isNull("district") ? "":FCStringUtils.cleanLineFlag(cat.getString("district")));
				sb.append(split);
			}else{
				sb.append("");
				sb.append(split);
				sb.append("");
				sb.append(split);
				sb.append("");
				sb.append(split);
				sb.append("");
				sb.append(split);
				sb.append("");
				sb.append(split);
			}
			sb.append(jobj.isNull("comments") ? 0:jobj.getInt("comments"));
			sb.append(split);
			sb.append(jobj.isNull("picCount")? 0:jobj.getInt("picCount"));
			sb.append(split);
			sb.append(FCStringUtils.cleanLineFlag(jobj.getString("shops")) == null
					|| FCStringUtils.cleanLineFlag(jobj.getString("shops")).equals("null") ? ""
							: FCStringUtils.cleanLineFlag(jobj.getString("shops")));
			sb.append(split);
			sb.append(TimePrint.getTime("yyyy-MM-dd", 0));
		} catch (JSONException e) {
			e.printStackTrace();
			StringBuffer error = new StringBuffer();
			StackTraceElement[] messages = e.getStackTrace();
			int length = messages.length;
			for (int i = 0; i < length; i++) {
				error.append(messages[i]);
			}
			logger.info("jsondata：" + sb.toString());
			logger.info("异常信息：" + error);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		if (args.length == 1 && args[0].trim() != null) {
			ExportPageData lp = new ExportPageData();
			String date = args[0].trim();
			boolean isDate = TimePrint.isValidDate(date, ClassConstants.getTimePagePattern());
			if (isDate)
				lp.exportData(date, 0);
		} else {
			logger.info("...日期参数输入错误,请输入正确的日期格式：yyyyMM..........");
		}
		MongoManager.exit();
	}

}
