package com.fangcheng.hive.runhql;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParaBeanTest {

	ParaBean pBean=new ParaBean();
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
	public void testGetCity() {

	}

	@Test
	public void testSetCity() {
		
	}

	@Test
	public void testGetShoptype() {
		String ret="10,15,20,30,45,50,55,60,65,70,80,90";
		System.out.println(ret);
		assertEquals(ret, pBean.getShoptype());
	}

	@Test
	public void testSetShoptype() {
		
	}

	@Test
	public void testGetRuntime() {
		pBean.setRuntime("201602");
		assertEquals("设置不匹配！","201602",pBean.getRuntime());
	}

	@Test
	public void testSetRuntime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetComparetime() {
		pBean.setComparetime("201602");
		assertEquals("设置不匹配！","201602",pBean.getComparetime());
	}

	@Test
	public void testSetComparetime() {
		fail("Not yet implemented");
	}
	@Test
	public void testSetAdddatetime() {
		fail("Not yet implemented");
	}

}
