package com.fangcheng.hive.sqoopsql;

import org.apache.log4j.Logger;
import com.fangcheng.service.ExportDataService;
import com.fangcheng.tools.ClassConstants;
import com.fangcheng.tools.TimePrint;

public class ExportDataImpl implements ExportDataService{
	
	public static Logger logger = Logger.getLogger(ExportDataImpl.class.getName());//写控制台
	/**
	 * 控制总体流程
	 */
	@Override
	public boolean execute(String run_time){
		boolean ret=true;
		String time = run_time; //判断是否是程序执行日期
		try{
			sqoopToMysql(time);
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
	
	@Override
	public void sqoopToMysql(String run_time) {
		// TODO Auto-generated method stub
		String[] sqoopsql=ClassConstants.getSQOOP_SQL();
		String sqoop = sqoopsql[0];
		//run_time=null, 为上个月的时间。
		if(null != run_time){
			sqoop = sqoop.replaceAll(sqoopsql[1], run_time);
		}else{
			sqoop = sqoop.replaceAll(sqoopsql[1], TimePrint.getTime(sqoopsql[1],Integer.parseInt(sqoopsql[2])));
		}
		sqoop = sqoop.replace("jdbc.url", ClassConstants.getJDBC_URL());
		sqoop = sqoop.replace("jdbc.username", ClassConstants.getJDBC_USERNAME());
		sqoop = sqoop.replace("jdbc.password", ClassConstants.getJDBC_PASSWORD());
		Export ep = new Export();
		ep.export(ClassConstants.getSHELL_PAGE(), sqoop);
	}

	@Override
	public void exportToFile() {
		// TODO Auto-generated method stub
		
	}
	 public static void main(String[] args) {		
			if(null != args && args.length==1){
				try {							
					//hive数据导入到sql
					ExportDataImpl exp = new ExportDataImpl();
					boolean edi=exp.execute(args[0]);
					logger.info("--------------导入数据到mysql:"+edi);
					
					} catch (Exception e) {
					StringBuffer error=new StringBuffer();
				   	StackTraceElement [] messages=e.getStackTrace();
				   	int length=messages.length;
				   	for(int i=0;i<length;i++){
				   		error.append(messages[i]);
				   	}
				   	logger.info("异常信息："+error);
				}
			}else{
				logger.info("...日期参数输入错误,请输入正确的日期格式：yyyyMM..........");
			}
			
		}
		
}
