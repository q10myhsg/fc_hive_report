package com.fangcheng.hive.sqoopsql;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExportTest {
	Export e=new Export();
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
	public void testExport() {
		String str="sqoop export --input-fields-terminated-by '\\001' --export-dir /home/hduser/hive_tmp/warehouse/hive_dianping_sort/dt=201603 --connect jdbc:mysql://192.168.1.134:3306/test --username fangcheng_admin --password fc1234 --table comment_table ";
		e.export("E:\\workspace_2015\\shell\\", str);
		System.out.println("E:\\shell\\");
		assertEquals("结果不匹配", true, true);
	}

	@Test
	public void testRunexec() {

	}

}
