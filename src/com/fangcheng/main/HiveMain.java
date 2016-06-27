package com.fangcheng.main;

import org.apache.log4j.Logger;




import com.fangcheng.action.CopyDataAction;
//import com.fangcheng.action.CopyDataAction;
import com.fangcheng.action.HiveRunAction;
import com.fangcheng.action.HiveUpDataAction;
import com.fangcheng.action.SqoopSqlAction;
import com.fangcheng.filter.Filter;
import com.fangcheng.filter.FilterChain;
import com.fangcheng.filter.HiveFilter;
import com.fangcheng.filter.SqoopSqlFilter;
import com.fangcheng.tools.ClassConstants;
import com.fangcheng.tools.TimePrint;

public class HiveMain {
	public static final Logger logger = Logger.getLogger(HiveMain.class.getName());//写控制台
	public static String time=null;
	public static long starTime;
	public static long endTime;
	public static long runTime;
	
	public static void main(String[] args) {
		ClassConstants.getTimeAddDataPattern();
		starTime=System.currentTimeMillis();
		logger.debug("project run.............");
		if(args.length==1 && args[0]!=null){
			logger.debug("project run.............");
		    time=args[0];
			boolean isDate=TimePrint.isValidDate(time,ClassConstants.getTimeAddDataPattern());
			if(isDate){				
				ClassConstants.setTime(time);
				
				FilterChain fc = new FilterChain();
				Filter f1 = new Filter();
				f1.appendAction(new HiveUpDataAction());
				f1.setPriority(2);
				fc.addFilter(f1);
				
				logger.info("Chain f1.............");
				
				HiveFilter f2=new HiveFilter();
			    f2.appendAction(new HiveRunAction());
				f2.setPriority(3);
				fc.addFilter(f2);
				
				logger.info("Chain f2.............");
				SqoopSqlFilter f3=new SqoopSqlFilter();
				f3.appendAction(new SqoopSqlAction() );
				f3.setPriority(4);
				fc.addFilter(f3);
				
				logger.info("Chain f3.............");
				
//				Filter f4 = new Filter();
//				f4.appendAction(new CopyDataAction());
//				f4.setPriority(1);
//				fc.addFilter(f4);	
//				logger.info("Chain f3.............");
				
				fc.doAction();
//			
			}else{
				logger.info("...日期参数输入错误,请输入正确的日期格式：yyyyMM..........");
			}
			endTime=System.currentTimeMillis();
			runTime=endTime-starTime;
			logger.info("end hiveAction............."+runTime);
		}

	}

}
