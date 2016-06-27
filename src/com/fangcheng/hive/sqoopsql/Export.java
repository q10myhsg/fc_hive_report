package com.fangcheng.hive.sqoopsql;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.logging.Logger;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;

public class Export {

	private static final Logger logger = Logger.getLogger(Export.class.getName());
	
	public void export(String shell_path,String sqoopsql) {
		//sqoop导表
		BufferedWriter bf;	
		String file_path = shell_path;
		try {			
			File dbtable = new File(file_path + "sqoop.sh");
			logger.info("------------------开始执行sqoop导表-------------------:"+file_path + "sqoop.sh");
			if (dbtable.exists()) {
				dbtable.delete();
			}
			dbtable.createNewFile();
			bf = new BufferedWriter(new FileWriter(dbtable));
			bf.write(sqoopsql);
			bf.write(" > " + file_path + "sqoop.out &");
			bf.newLine();
			bf.flush();
			bf.close();
			Process pp=Runtime.getRuntime().exec("chmod 777 " + file_path + "sqoop.sh");
			pp.waitFor();
			pp.destroy();
			runexec(file_path + "sqoop.sh");
			Thread.sleep(180000);
			logger.info("------------------sqoop导表结束-------------------:"+sqoopsql);
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
	}
	
	public static synchronized void runexec(String strsh){
		CommandLine cmdLine = CommandLine.parse(strsh);
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		Executor executor = new DefaultExecutor();
		executor.setExitValue(1);
		executor.setStreamHandler(new PumpStreamHandler());
		try {
			executor.execute(cmdLine, resultHandler);
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
	}
}
