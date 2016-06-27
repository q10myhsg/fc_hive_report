package com.fangcheng.setjob;

import java.io.File;
import java.util.logging.Logger;

import com.fangcheng.tools.ClassConstants;

public class Set_Job {

	private static boolean isJob = true;
	private static Logger logger = Logger.getLogger(Set_Job.class.getName());//写控制台
	
	public static void main(String[] args) {
		try {
			ClassConstants.getDangpingCollections();
			JobManager jm = new JobManager();
			jm.start();
			while(isJob){
				Thread.sleep(60000l);
				File[] ff =new File(ClassConstants.getSTOP_PATH()).listFiles();
				if(ff.length>0){
					logger.info("手动停止程序.................");
					isJob = false;
					jm.stop();
					break;
				}
			}
		} catch (Exception e) {
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	logger.info("异常信息："+error);
		}
	}
}
