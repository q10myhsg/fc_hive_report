package com.fangcheng.hive.uploaddata;

import org.apache.log4j.Logger;

import com.fangcheng.database.MongoManager;
import com.fangcheng.mongo.loaddata.ExportDianpingData;
import com.fangcheng.tools.ClassConstants;
import com.fangcheng.tools.TimePrint;

public class LoadDataAddDataImpl extends BasicLoadDataImpl{
	
	public static Logger logger = Logger.getLogger(ExportDianpingData.class.getName());//写控制台
	
	/**
	 * 控制总体流程
	 */
	@Override
	public boolean loadData(String run_time){
		String time = run_time;
		boolean ret=true;
		try{
			//将数据文件转移到page文件夹,以便于数据加载到hive中
			String source_path = ClassConstants.getSOURCE_FILE();			
			String run_path = ClassConstants.getTARGET_FILE()+"adddate/";
			String regular=ClassConstants.getADDDATA_REGULAR_EXPRESSION()[0];
			logger.info("将文件转移到数据入库目录。。。。。。。。。。。。");
			moveFile(source_path,run_path,time,regular);
			
			//将数据加载到hive表中:api_history_add_\\d{6}\\.txt,(dt='2008-08-08')
			logger.info("数据入库。。。。。。。。。。。。");
			String filename = "api_history_add_"+time+".txt";
			String adddatatable=ClassConstants.getHIVE_ADDDATE_TABLE();
			loadFileToHive(run_path+filename,adddatatable,"dt='"+time+"'");
			
			//数据加载到hive后把文件转移到备份目录
			logger.info("将文件转移到数据备份目录。。。。。。。。。。。。");
			String back_path = ClassConstants.getBACKUP_FILE()+"adddate_back/";			
			moveFile(run_path,back_path,null,regular);
			
			//删除备份期外的数据
			String timeformat=ClassConstants.getADDDATA_REGULAR_EXPRESSION()[1];
			int deletbeforetime=Integer.parseInt(ClassConstants.getADDDATA_REGULAR_EXPRESSION()[2]);
			deleteFile(back_path,TimePrint.getTime(timeformat, deletbeforetime));
			
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
	public static void main(String[] args) {
		if(args.length==1){
			LoadDataAddDataImpl lp = new LoadDataAddDataImpl();		
			String  date = args[0].trim();
			boolean isDate=TimePrint.isValidDate(date,ClassConstants.getTimePagePattern());
			if(isDate)				
				lp.loadData(date);
		}else{
			logger.info("...日期格式输入错误,请输入正确格式：yyyyMM..........");
		}
		MongoManager.exit();
	}
	
}
