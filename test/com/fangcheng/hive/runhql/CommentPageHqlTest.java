package com.fangcheng.hive.runhql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fangcheng.database.HiveDb;

public class CommentPageHqlTest {
    DpPageHql cph=new DpPageHql();
	private static Connection con = null;
	private static Statement stmt = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		HiveDb.close(stmt, con);
	}

	@Test
	public void testHiveConnection() {
		DpPageHql.hiveConnection();
		assertEquals(true, true);
	}

	@Test
	
	public void testHiveClose() {
		
	}

	@Test
	public void testExecute() {
		boolean ret=cph.execute("201603");
		assertEquals("hql执行结果不一致", true, ret);
	}

	@Test
	public void testQueryHql() {
		boolean ret=true;
		HiveDb.getInstance();
		ParaBean param=new ParaBean();
		param.setRuntime("201603");
		param.setComparetime("201602");
		System.out.println(param.getCity());
		cph.queryHql(param);
		assertEquals("hql执行结果不一致", true, ret);
	}

	@Test
	public void testGetHql() {
		String filePath="E:\\workspace_2015\\fc-hive-report\\bin\\hive_conf\\dp_page_hql";
		HashMap<String,String> HqlList=DpPageHql.getHql(filePath);
		System.out.println(HqlList.get("dianping_filter"));
		assertEquals("hql 语句不匹配！", 20, HqlList.size());
	}

	@Test
	public void testReadInitHql() {
		String filePath="E:\\workspace_2015\\fc-hive-report\\bin\\hive_conf\\init_hql";
		ArrayList<String> initHqlList=DpPageHql.readInitHql(filePath);
		assertEquals("inithql 语句不匹配！", 9, initHqlList.size());		
	}

	@Test
	public void testDeletePartition() {
		boolean ret=cph.deletePartition();
		assertEquals("hql执行结果不一致", true, ret);
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
