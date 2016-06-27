package com.fangcheng.main;

import java.util.logging.Logger;

import com.fangcheng.hive.runhql.DpPageHql;
import com.fangcheng.hive.sqoopsql.ExportDataImpl;
import com.fangcheng.hive.uploaddata.LoadDataAddDataImpl;
import com.fangcheng.hive.uploaddata.LoadDataDianpingImpl;
import com.fangcheng.hive.uploaddata.LoadDataPageImpl;

public class HiveAction {

	public static Logger logger = Logger.getLogger(HiveAction.class.getName());//写控制台
	String time=null;
	private static String dptime = null;
	
    public static void main(String[] args) {		
		if(null != args && args.length==1){
			dptime = args[0];
			try {
				logger.info("project begin:--------------");	
				//加载数据到hive中
				LoadDataDianpingImpl loaddp = new LoadDataDianpingImpl();
				boolean ldap=loaddp.loadData(dptime);
				logger.info("dianping数据加载:"+ldap);
						
				LoadDataPageImpl loadpg = new LoadDataPageImpl();
				boolean ldp=loadpg.loadData(dptime);
				logger.info("page数据加载:"+ldp);
				
				LoadDataAddDataImpl loada=new LoadDataAddDataImpl();
				boolean ldad=loada.loadData(dptime);
				logger.info("page数据加载:"+ldad);
			    //执行hql语句
				DpPageHql ed = new DpPageHql();
				boolean cph=ed.execute(dptime);
				logger.info("--------------执行hql语句："+cph);
				
				//hive数据导入到sql
				ExportDataImpl exp = new ExportDataImpl();
				boolean edi=exp.execute(dptime);
				logger.info("--------------导入数据到mysql:"+edi);
				
				logger.info("project end:--------------");
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
