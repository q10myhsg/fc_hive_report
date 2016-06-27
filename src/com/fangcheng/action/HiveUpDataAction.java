package com.fangcheng.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fangcheng.hive.uploaddata.LoadDataDianpingImpl;
import com.fangcheng.hive.uploaddata.LoadDataPageImpl;
import com.fangcheng.tools.ClassConstants;

public class HiveUpDataAction implements Action {
	private static final Logger logger = LoggerFactory.getLogger(HiveUpDataAction.class);

	@Override
	public void doAction() {
		logger.debug("将数据上传到hive数据库中=========");
		//加载数据到hive中
		LoadDataDianpingImpl loaddp = new LoadDataDianpingImpl();
		boolean ldap=loaddp.loadData(ClassConstants.getTime());
		logger.info("dianping数据加载:"+ldap);
				
		LoadDataPageImpl loadpg = new LoadDataPageImpl();
		boolean ldp=loadpg.loadData(ClassConstants.getTime());
		logger.info("page数据加载:"+ldp);
		
//		LoadDataAddDataImpl loada=new LoadDataAddDataImpl();
//		boolean ldad=loada.loadData(ClassConstants.getTime());
//		logger.info("page数据加载:"+ldad);
	}
}
