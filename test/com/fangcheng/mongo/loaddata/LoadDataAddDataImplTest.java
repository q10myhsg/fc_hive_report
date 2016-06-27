package com.fangcheng.mongo.loaddata;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fangcheng.hive.uploaddata.LoadDataAddDataImpl;
import com.fangcheng.tools.ClassConstants;

public class LoadDataAddDataImplTest {
	
	LoadDataAddDataImpl ldad=new LoadDataAddDataImpl();

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
        boolean ret=ldad.loadData("201605");	 
		assertEquals("结果不匹配", true, ret);
	}
	
	@Test
	public void testMoveFile() {
		String from="E:\\workspace_2015\\run_path\\adddate\\";
		String to="E:\\workspace_2015\\backpath\\adddate_back\\";
		String regular=ClassConstants.getADDDATA_REGULAR_EXPRESSION()[0];
		ldad.moveFile(from,to,"201603", regular);
		assertEquals("结果不匹配", true, true);
	}

	@Test
	public void testLoadFileToHive() {
		String filepath = "/home/hduser/project/run_path/adddate/api_history_add.txt";
		System.out.println(filepath);
		ldad.loadFileToHive(filepath,ClassConstants.getHIVE_PAGE_TABLE(),"dt=201604");
		assertEquals("结果不匹配", true, true);
	}

	@Test
	public void testDeleteFile() {
		String from="E:\\workspace_2015\\run_path\\adddate\\";
		ldad.deleteFile(from, "201603");
		assertEquals("结果不匹配", true, true);
	}

	@Test
	public void testCompressBak() {
		fail("Not yet implemented");
	}

}
