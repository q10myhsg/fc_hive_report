package com.fangcheng.main;

import java.util.logging.Logger;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.fangcheng.mongo.loaddata.ExportDianpingData;
import com.fangcheng.mongo.loaddata.ExportPageData;

public class ProjectAction implements StatefulJob {
	
	public static Logger logger = Logger.getLogger(ProjectAction.class.getName());//写控制台
	String time=null;
	private static String dptime = null;	
	public static long starTime;
	public static long endTime;
	public static long runTime;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		starTime=System.currentTimeMillis();
		if(null != args && args.length==1){
			dptime = args[0];
			try {
				logger.info("project begin:--------------");		
				
			    //将152中mongo数据导出
				ExportDianpingData dianping = new ExportDianpingData();				
				boolean ded = dianping.exportData(dptime,0);
				logger.info("--------------生成dianping数据："+ded);
				
				ExportPageData page = new ExportPageData();
				boolean ped = page.exportData(dptime,0);
				logger.info("--------------生成page数据:"+ped);
				
//				ExportAddData add = new ExportAddData();
//				boolean aed = add.exportData(dptime,0);
//				logger.info("--------------生成adddate数据:"+aed);
			} catch (Exception e) {
				StringBuffer error=new StringBuffer();
			   	StackTraceElement [] messages=e.getStackTrace();
			   	int length=messages.length;
			   	for(int i=0;i<length;i++){
			   		error.append(messages[i]);
			   	}
			   	logger.info("异常信息："+error);
			}
		}else {
			logger.info("...日期参数输入错误,请输入正确的日期格式：yyyyMM..........");
		}
		endTime=System.currentTimeMillis();
		runTime=endTime-starTime;
		logger.info("end load data from mongodb............."+runTime);
	}

}
