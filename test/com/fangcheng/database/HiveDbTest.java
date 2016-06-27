package com.fangcheng.database;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HiveDbTest {
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
	public void testHiveConn() throws SQLException {
		
	}

	@Test
	public void testGetInstance() {
		boolean ret=false;
		HiveDb h = HiveDb.getInstance();
		if(h!=null) ret=true;
        System.out.println(h);
		assertEquals("连接hive数据库失败", true, ret);
	}

	@Test
	public void testGetConn() {
		boolean ret=false;
		Connection con = HiveDb.getConn();
		if(con!=null) ret=true;
        System.out.println(con);
		assertEquals("连接hive数据库失败", true, ret);
	}

	@Test
	public void testCloseConnection() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloseStatementConnection() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloseResultSetStatementConnection() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloseResultSet() {
		fail("Not yet implemented");
	}

}
