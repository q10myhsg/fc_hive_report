package com.fangcheng.hive.sqoopsql;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.apache.log4j.Logger;

import com.fangcheng.tools.ClassConstants;
import com.fangcheng.tools.TimePrint;

public class ScpData {
	public static Logger logger = Logger.getLogger(ScpData.class.getName());//写控制台
	public static String time=null;
	
	public static boolean copyData(String timeStr){
		boolean ret=true;
		String cpyString="scp root@"+ClassConstants.SOURCE_IP+":/home/hduser/project/source_path/*"+timeStr+"*.txt /home/hduser/project/source_path/";
		BufferedWriter bf;	
		String file_path = ClassConstants.getSHELL_PAGE();
		try {			
			File dbtable = new File(file_path + "copy.sh");
			logger.info("------------------开始执行复制数据到64中-------------------:"+file_path + "copy.sh");
			if (dbtable.exists()) {
				dbtable.delete();
			}
			dbtable.createNewFile();
			bf = new BufferedWriter(new FileWriter(dbtable));
			bf.write(cpyString);
			bf.write(" > " + file_path + "copy.out &");
			bf.newLine();
			bf.flush();
			bf.close();
			Process pp=Runtime.getRuntime().exec("chmod 777 " + file_path + "copy.sh");
			pp.waitFor();
			pp.destroy();
			logger.info("------------------copy data-------------------:");
			Export.runexec(file_path + "copy.sh");
			Thread.sleep(10800000);    //1hour=3600000
			logger.info("------------------复制数据结束-------------------:");
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
		if(args.length==1 && args[0]!=null){
			boolean isData=TimePrint.isValidDate(args[0], ClassConstants.DATE_FORMAT);
			if(isData){
				time=args[0];
				copyData(time);
			}
		}else{
			logger.info("...日期参数输入错误,请输入正确的日期格式：yyyyMM..........");
		}
	}

}
