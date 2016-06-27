package com.fangcheng.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fangcheng.hive.runhql.DpPageHql;
import com.fangcheng.tools.ClassConstants;

public class HiveRunAction implements Action{
	private static final Logger logger = LoggerFactory.getLogger(HiveRunAction.class);
	@Override
	public void doAction() {
		logger.debug("执行hql语句=========");
		//加载数据到hive中
		DpPageHql ed = new DpPageHql();
		boolean cph=ed.execute(ClassConstants.getTime());
		logger.info("--------------执行hql语句："+cph);
		
	}

}
