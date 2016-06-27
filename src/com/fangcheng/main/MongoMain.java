package com.fangcheng.main;

import org.apache.log4j.Logger;

import com.fangcheng.action.MongLoadDataAction;
import com.fangcheng.action.MongoAction;
import com.fangcheng.filter.Filter;
import com.fangcheng.filter.FilterChain;
import com.fangcheng.filter.MongFilter;
import com.fangcheng.tools.ClassConstants;
import com.fangcheng.tools.TimePrint;
public class MongoMain {
	
	public static final Logger logger = Logger.getLogger(MongoMain.class.getName());//写控制台	
	public static String time=null;
	public static String pagecoll=ClassConstants.getPageCollections();
	public static String commentcoll=ClassConstants.getDangpingCollections();
	public static long starTime;
	public static long endTime;
	public static long runTime;
	
	public static void main(String[] args) { 
		logger.info("begin importData.............");
		starTime=System.currentTimeMillis();
		if(args.length==1 && args[0]!=null){
		    time=args[0];
			boolean isDate=TimePrint.isValidDate(time,ClassConstants.getTimeAddDataPattern());
			if(isDate){
				
				ClassConstants.setTime(time);
				ClassConstants.setTime(time);
				ClassConstants.setComment_collection(time);
				ClassConstants.setPage_collection(time);
				
				FilterChain fc = new FilterChain();
				Filter f1 = new Filter();
				f1.appendAction(new MongoAction());
				f1.setPriority(1);
				fc.addFilter(f1);
				
				MongFilter f2 = new MongFilter();
				f2.appendAction(new MongLoadDataAction());
				f2.setPriority(2);
				fc.addFilter(f2);	
				fc.doAction();	
				
			}else{
				logger.info("...日期参数输入错误,请输入正确的日期格式：yyyyMM..........");
			}
			endTime=System.currentTimeMillis();
			runTime=endTime-starTime;
			logger.info("end mongo............."+runTime);
		}		
	}

}
