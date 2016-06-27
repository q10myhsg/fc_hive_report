package com.fangcheng.hive.uploaddata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.fangcheng.database.HiveDb;
import com.fangcheng.mongo.loaddata.ExportDianpingData;
import com.fangcheng.service.LoadDataService;
import com.fangcheng.tools.ClassConstants;
import com.fangcheng.tools.FileNameFilter;
import com.fangcheng.utils.FCFileUtils;

public abstract class BasicLoadDataImpl implements LoadDataService{
	
	public static Logger logger = Logger.getLogger(ExportDianpingData.class.getName());//写控制台
	
	@Override
	public abstract boolean  loadData(String run_time);
	
	 
	@Override
	public void moveFile(String from_path, String to_path, String time, String regular) {
		// TODO Auto-generated method stub
        if(!new File(to_path).exists() && !new File(to_path).isDirectory())
        	new File(to_path).mkdirs();
        
		//获取源文件夹当前下的文件或目录
		FilenameFilter filter=new FileNameFilter(regular, time);
		File[] files= new File(from_path).listFiles(filter);		
		for(int i=0;i<files.length;i++){
			files[i].renameTo(new File(to_path+files[i].getName()));
		}
	}

	@Override
	public boolean loadFileToHive(String files, String hive_table, String partition) {
		// TODO Auto-generated method stub
		boolean retString=true;
		Connection con = null;
		Statement stmt = null;
		try {
			
			//链接hive数据库
			con = HiveDb.getConn();
			stmt = con.createStatement();
			String ret=FCFileUtils.readFile(ClassConstants.getHQL_LOADDATA());

			String[] hql=ret.split(";");
			String tables="SET hive_table="+hive_table;
			String dtString="SET partition="+partition;
			String dataPath="SET files="+files;
			stmt.executeQuery(tables);
			stmt.executeQuery(dtString);
			stmt.executeQuery(dataPath);

			for(int i=0;i<hql.length;i++){
				if(!hql[i].trim().equals("") && !hql[i].trim().substring(0, 1).equals("#")){
					stmt.executeQuery(hql[i]);
				}				
			}
//	 		stmt.executeBatch();


		} catch (Exception e) {
			retString=false;
			e.printStackTrace();
		    logger.info(e.getMessage());
		} finally{
			HiveDb.close(stmt, con);
		}
		return retString;
		
	}
	@Override
	public void deleteFile(String path, String time) {
		// TODO Auto-generated method stub
		FilenameFilter filter=new FileNameFilter(time);
		File[] file= new File(path).listFiles(filter);
		for(int i=0;i<file.length;i++){
			file[i].delete();
		}		
	}
	
	/**
	 * 压缩备份
	 * */
		public static boolean compressBak(String fileName, String source_path){
			boolean resBool = true;
			StringBuffer cmdStr = new StringBuffer();
			cmdStr.append(ClassConstants.getRAR_FILE());
			//tar -zcvf 文件名.tar.gz 目录名 --remove-files，文件名按日期
			cmdStr.append("tar -zcvf ");//压缩，排除源目录，并删除源文件
			cmdStr.append(fileName);
			cmdStr.append(".tar.gz ");
			cmdStr.append(source_path);
			cmdStr.append(" --remove-files");
			logger.info(cmdStr.toString());
			try {
				Process process = Runtime.getRuntime().exec(cmdStr.toString());
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				BufferedReader readerErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				String line = null;
				while((line = reader.readLine())!= null) {
					logger.info(line);
				}//必须在reader后面
				while ((line = readerErr.readLine())!= null) {
					logger.info("错误信息："+ line);
				}
				if (process.waitFor()!= 0) {
					logger.info("退出值 = "+ process.exitValue());
					resBool = false;
				}
				readerErr.close();
				reader.close();
				process.destroy();//关闭的意义不大
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resBool = false;
				e.printStackTrace();
			}
			return resBool;
		}
}
