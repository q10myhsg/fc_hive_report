package com.fangcheng.hive.sqoopsql;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExportDataImplTest {
	ExportDataImpl eda=new ExportDataImpl();

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
	public void testExecute() {
		boolean ret=eda.execute("201603");
		assertEquals("结果不匹配", true, ret);
	}

	@Test
	public void testExportToMysql() {
		eda.sqoopToMysql("201603");
		assertEquals("结果不匹配", true, true);
	}

	@Test
	public void testExportToFile() {
		fail("Not yet implemented");
	}

}
