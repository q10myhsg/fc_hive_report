package com.fangcheng.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fangcheng.hive.sqoopsql.ScpData;
import com.fangcheng.tools.ClassConstants;

public class CopyDataAction implements Action{
	private static final Logger logger = LoggerFactory.getLogger(CopyDataAction.class);
	@Override
	public void doAction() {
		logger.info("copy data from 152 to 64=========");
		//加载数据到hive中
		boolean sdData=ScpData.copyData(ClassConstants.getTime());		
		logger.info("--------------执行hql语句："+sdData);
		
	}

}
