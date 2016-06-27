package com.fangcheng.mongo.loaddata;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fangcheng.hive.uploaddata.LoadDataPageImpl;
import com.fangcheng.tools.ClassConstants;

public class LoadDataPageImplTest {
	
	LoadDataPageImpl ldp=new LoadDataPageImpl();

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
		 boolean ret=ldp.loadData("201605");
		 
		 assertEquals("结果不匹配", true, ret);
	}
	
	@Test
	public void testMoveFile() {
		String from="E:\\workspace_2015\\run_path\\page\\";
		String to="E:\\workspace_2015\\backpath\\page_back\\";
		String regular=ClassConstants.getPAGE_REGULAR_EXPRESSION()[0];
		ldp.moveFile(from,to,"201603", regular);
		assertEquals("结果不匹配", true, true);
	}

	@Test
	public void testLoadFileToHive() {
		String filepath = "/home/hduser/project/run_path/page/city_page_201604.txt";
		System.out.println(filepath);
		ldp.loadFileToHive(filepath,ClassConstants.getHIVE_PAGE_TABLE(),"dt=201604");
		assertEquals("结果不匹配", true, true);
	}

	@Test
	public void testDeleteFile() {
		String from="E:\\workspace_2015\\run_path\\page\\";
		ldp.deleteFile(from, "201604");
		assertEquals("结果不匹配", true, true);
	}

	@Test
	public void testCompressBak() {
//		fail("Not yet implemented");
	}
}
