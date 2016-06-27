package com.fangcheng.service;

public interface ExportDataService {
	/**
	 * 控制总体流程
	 * 程序运行时间
	 */
	public boolean execute(String run_time);
	/**
	 * 最终数据输出到mysql表中
	 * 需要导的数据的分区时间
	 */
	public void sqoopToMysql(String run_time);
	/**
	 * 最终数据输出到文本
	 */
	public void exportToFile();
	
}
