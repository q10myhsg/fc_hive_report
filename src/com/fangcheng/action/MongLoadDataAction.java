package com.fangcheng.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fangcheng.mongo.loaddata.ExportDianpingData;
import com.fangcheng.mongo.loaddata.ExportPageData;
import com.fangcheng.tools.ClassConstants; 

public class MongLoadDataAction implements Action {
	private static final Logger logger = LoggerFactory.getLogger(MongLoadDataAction.class);

	@Override
	public void doAction() {
		logger.debug("=====load data from mongodb ========");
		
		ExportDianpingData dianping = new ExportDianpingData();				
		boolean ded = dianping.exportData(ClassConstants.getTime(),0);
		logger.info("--------------生成dianping数据："+ded);
		
		ExportPageData page = new ExportPageData();
		boolean ped = page.exportData(ClassConstants.getTime(),0);
		logger.info("--------------生成page数据:"+ped);
	}
}
