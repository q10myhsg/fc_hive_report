package com.fangcheng.mongo.loaddata;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.fangcheng.database.MongoManager;
import com.fangcheng.tools.ClassConstants;
import com.fangcheng.tools.TimePrint;
import com.fangcheng.utils.FCStringUtils;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class ExportDianpingData extends BasicMongoData {
	

	public static Logger logger = Logger.getLogger(ExportDianpingData.class.getName());//写控制台
	/**
	 * 从mongdb中导出数据到本地文件中
	 */
	@Override
	public boolean exportData(String run_time, int num){
		boolean ret=true;
		try{
			time=run_time;
			DB db = mo.getDb(ClassConstants.getDianpingDatabase());
			mo.db=db;
			city=ClassConstants.getCity();			
			filepath=ClassConstants.getSOURCE_FILE();
			
			for(int i=0;i<city.size();i++){
				logger.info("生成................."+city.get(i)+".................文件");
				DBCursor obj = basicDataService(ClassConstants.getDangpingCollections(),city.get(i),num);       //查询地市为city[i]的数据集合
				
				//判断要生成的文件是否存在，如果存在则删除   city_\\d{1}\\_dynamic_\\d{6}\\.txt
				ifExistsFile(filepath+"city_"+city.get(i)+"_dynamic_"+time+".txt");
				downLoadDataFile(obj,filepath +"city_"+city.get(i)+"_dynamic_"+time+".txt");
			}
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
			sb.append(jobj.isNull("shopName")?"":FCStringUtils.cleanLineFlag(jobj.getString("shopName")));
			sb.append(split);
			sb.append(jobj.isNull("shopType") ?0:jobj.getInt("shopType"));
			sb.append(split);
			sb.append(jobj.isNull("glng") ?0.0:jobj.getDouble("glng"));
			sb.append(split);
			sb.append(jobj.isNull("glat") ?0.0:jobj.getDouble("glat"));
			sb.append(split);
			sb.append(jobj.isNull("address") ? "":FCStringUtils.cleanLineFlag(jobj.getString("address")));
			sb.append(split);
			sb.append(jobj.isNull("cityId") ?0:jobj.getInt("cityId"));
			sb.append(split);
			sb.append(jobj.isNull("phoneNo") ? "":FCStringUtils.cleanLineFlag(jobj.getString("phoneNo")));
			sb.append(split);
			sb.append(jobj.isNull("addDate") ? "":FCStringUtils.cleanLineFlag(jobj.getString("addDate")));
			sb.append(split);
			sb.append(jobj.isNull("avgPrice") ?0:jobj.getInt("avgPrice"));
			sb.append(split);
			sb.append(jobj.isNull("monthlyHits") ?0:jobj.getInt("monthlyHits"));
			sb.append(split);
			sb.append(jobj.isNull("popularity") ?0:jobj.getInt("popularity"));
			sb.append(split);
			sb.append(jobj.isNull("wishTotal") ?0:jobj.getInt("wishTotal"));
			sb.append(split);
			sb.append(jobj.isNull("score") ?0:jobj.getInt("score"));
			sb.append(split);
			sb.append(jobj.isNull("score1") ?0:jobj.getInt("score1"));
			sb.append(split);
			sb.append(jobj.isNull("score2") ?0:jobj.getInt("score2"));
			sb.append(split);
			sb.append(jobj.isNull("score3")?0:jobj.getInt("score3"));
			sb.append(split);
			sb.append(jobj.isNull("score4") ?0:jobj.getInt("score4"));
			sb.append(split);
			sb.append(jobj.isNull("shopPower") ?0:jobj.getInt("shopPower"));
			sb.append(split);
			sb.append(jobj.isNull("promoId") ?0:jobj.getInt("promoId"));
			sb.append(split);
			sb.append(jobj.isNull("picTotal") ?0:jobj.getInt("picTotal"));
			sb.append(split);
			sb.append(jobj.isNull("priceInfo")  ? "":FCStringUtils.cleanLineFlag(jobj.getString("priceInfo")));
			sb.append(split);
			sb.append(jobj.isNull("businessHours") ? "":FCStringUtils.cleanLineFlag(jobj.getString("businessHours")));
			sb.append(split);
			sb.append(jobj.isNull("branchTotal") ?0:jobj.getInt("branchTotal"));
			sb.append(split);
			sb.append(jobj.isNull("shopTags") ? "":FCStringUtils.cleanLineFlag(jobj.getString("shopTags")));
			sb.append(split);
			sb.append(jobj.isNull("phoneNo2") ? "":FCStringUtils.cleanLineFlag(jobj.getString("phoneNo2")));
			sb.append(split);
			sb.append(jobj.isNull("voteTotal") ?0:jobj.getInt("voteTotal"));
			sb.append(split);
			sb.append(jobj.isNull("nearByTags") ? "":FCStringUtils.cleanLineFlag(jobj.getString("nearByTags")));
			sb.append(split);
			sb.append(jobj.isNull("priceLevel") ?0:jobj.getInt("priceLevel"));
			sb.append(split);			
			sb.append(jobj.isNull("primaryTag") ? "":FCStringUtils.cleanLineFlag(jobj.getString("primaryTag")));
			sb.append(split);
			sb.append(jobj.isNull("webSite") ? "":FCStringUtils.cleanLineFlag(jobj.getString("webSite")));
			sb.append(split);
			sb.append(jobj.isNull("searchKeyWord")  ? "":FCStringUtils.cleanLineFlag(jobj.getString("searchKeyWord")));
			sb.append(split);
			sb.append(jobj.isNull("shopPowerTitle")  ? "":FCStringUtils.cleanLineFlag(jobj.getString("shopPowerTitle")));
			sb.append(split);
			sb.append(jobj.isNull("dishTags")  ? "":FCStringUtils.cleanLineFlag(jobj.getString("dishTags")));
			sb.append(split);
			sb.append(jobj.isNull("branchName")  ? "":FCStringUtils.cleanLineFlag(jobj.getString("branchName")));
			sb.append(split);
			sb.append(jobj.isNull("newShopType") ? "":FCStringUtils.cleanLineFlag(jobj.getString("newShopType")));
			sb.append(split);
			sb.append(jobj.isNull("newDistrict") ? "":FCStringUtils.cleanLineFlag(jobj.getString("newDistrict")));
			sb.append(split);
			sb.append(jobj.isNull("displayStatus") ? "":FCStringUtils.cleanLineFlag(jobj.getString("displayStatus")));
			sb.append(split);
			sb.append(jobj.isNull("clientType") ? "":FCStringUtils.cleanLineFlag(jobj.getString("clientType")));
			sb.append(split);		
			sb.append(jobj.isNull("oldChainId") ? "":FCStringUtils.cleanLineFlag(jobj.getString("oldChainId")));
			sb.append(split);
			sb.append(TimePrint.getTime("yyyy-MM-dd",0));
		} catch (Exception e) {
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
	

	public static void main(String[] args) {
		if(args.length==1 && args[0].trim()!=null){
			ExportDianpingData lp = new ExportDianpingData();			
			String  date = args[0].trim();
			boolean isDate=TimePrint.isValidDate(date,ClassConstants.getTimeDianpingPattern());
			if(isDate)		
				lp.exportData(date, 0);
		}else{
			logger.info("...日期参数输入错误,请输入正确的日期格式：yyyyMM..........");
		}
		MongoManager.exit();
	}	
}
