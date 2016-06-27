package com.fangcheng.service;

public interface LoadDataService {
	
	/**
	 * 控制总体流程
	 * 程序运行时间
	 */
	public boolean loadData(String run_time);
	/**
	 * 将数据移动到数据文件目录
	 * 原目录
	 * 目标目录
	 */
	public void moveFile(String from_path,String to_path,String time,String regular);
	/**
	 * 将数据加载到hive表中
	 * 文件路径
	 * hive表
	 * 表分区
	 */
	public boolean loadFileToHive(String files,String hive_table,String partition);
	/**
	 * 删除备份期外的数据文件
	 * 文件目录
	 * 删除的文件日期
	 */
	public void deleteFile(String path, String time);
	
}
