package com.fangcheng.service;

import com.fangcheng.hive.runhql.ParaBean;

public interface HqlInterface {
	/**
	 * 控制总体流程
	 * 程序运行时间
	 */
	public boolean execute(String run_time);
	/**
	 * 删除备份的表分区、清空中间表
	 */
	public boolean deletePartition();
	/**
	 * 执行sql
	 * 需要执行的hql数组
	 */	
    public void queryHql(ParaBean param);
    
}
