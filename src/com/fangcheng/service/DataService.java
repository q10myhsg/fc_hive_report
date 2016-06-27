package com.fangcheng.service;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public interface DataService {
	
	/**
	 * 控制程序总体流程
	 * 手动运行输入的时间
	 * 获取数据的数量,0为全部查询,否则获取指定数据量
	 */
	public boolean exportData(String run_time, int num);
	/**
	 * 从mongo获取数据
	 * 集合名字
	 * 地市id
	 * 获取数据的数量,0为全部查询,否则获取指定数据量
	 */
	public DBCursor basicDataService(String table, String cityId, int num);
	

	/**
	 * 将数据生成文件
	 */
	public boolean downLoadDataFile(DBCursor obj,String filepath);
	/**
	 * 数据文件上传到数据服务器
	 */
//	public void upLoadDataFile();
	/**
	 * 数据文件下载到数据服务器
	 */
//	public void downLoadDataFile();
	/**
	 * 整理数据,生成写入文本的数据
	 * 数据字符串
	 * 整理之后的数据字段分隔符
	 */
	public String planData(DBObject data,String split);
	/**
	 * 判断文件是否已经存在,如果存在则删掉
	 */
	public void ifExistsFile(String filepath);
	
}
