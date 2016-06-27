package com.fangcheng.service;

public interface ExecuteHqlService {
	
	/**
	 * 控制总体流程
	 * 程序运行时间
	 */
	public boolean execute(String run_time);
	/**
	 * 删除备份的表分区、清空中间表
	 */
	public void deletePartition();
	/**
	 * 获取要执行的sql
	 * 时间模板
	 * 月份
	 */
	public String[] getSql(String timeType,int num);
	/**
	 * 执行sql
	 * 需要执行的hql数组
	 */
	public void executeSql(String[] execHql);
	
}
