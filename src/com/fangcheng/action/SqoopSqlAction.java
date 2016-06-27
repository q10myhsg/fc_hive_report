package com.fangcheng.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fangcheng.hive.sqoopsql.ExportDataImpl;
import com.fangcheng.tools.ClassConstants;

public class SqoopSqlAction implements Action{
	private static final Logger logger = LoggerFactory.getLogger(SqoopSqlAction.class);
	@Override
	public void doAction() {
		logger.debug("将数据报告导入Mysql数据库=========");
		
		ExportDataImpl exp = new ExportDataImpl();
		boolean edi=exp.execute(ClassConstants.getTime());
		logger.info("--------------导入数据到mysql:"+edi);	
		
	}

}
