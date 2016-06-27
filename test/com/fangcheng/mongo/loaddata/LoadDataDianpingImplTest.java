package com.fangcheng.mongo.loaddata;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fangcheng.hive.uploaddata.LoadDataDianpingImpl;
import com.fangcheng.mongo.loaddata.ExportDianpingData;
import com.fangcheng.tools.ClassConstants;

public class LoadDataDianpingImplTest {
	
	LoadDataDianpingImpl lddp=new LoadDataDianpingImpl();
	ExportDianpingData  ldtf = new ExportDianpingData();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadData() {
	//	boolean ret1=ldtf.exportData("201603", 10);
		boolean ret=lddp.loadData("201604");
		assertEquals("结果不匹配", true, ret);
	}
	
	@Test
	public void testMoveFile() {
		String from="E:\\home\\hduser\\project\\run_path\\dianping\\";
		String to="E:\\workspace_2015\\backpath\\dianping_back\\";
		String regular=ClassConstants.getCOMMENT_REGULAR_EXPRESSION()[0];
		lddp.moveFile(from,to, "201604", regular);
		assertEquals("结果不匹配", true, true);
	}

	@Test
	public void testLoadFileToHive() {
		String filepath = "/home/hduser/project/run_path/dianping/city_2_dynamic_201604.txt";
		System.out.println(filepath);
		lddp.loadFileToHive(filepath,ClassConstants.getHIVE_COMMENT_TABLE(),"dt=201604,city=1");
		assertEquals("结果不匹配", true, true);
	}

	@Test
	public void testDeleteFile() {
		String from="E:\\home\\hduser\\project\\run_path\\dianping\\";
		lddp.deleteFile(from, "201605");
		assertEquals("结果不匹配", true, true);
	}

	@Test
	public void testCompressBak() {
		String from="E:\\home\\hduser\\project\\run_path\\dianping\\city_3_dynamic_201604.txt";
		String to="E:\\workspace_2015\\backpath\\dianping_back\\";
		boolean ret=LoadDataDianpingImpl.compressBak(from, to);
		assertEquals("结果不匹配", true, ret);
		
	}

}
