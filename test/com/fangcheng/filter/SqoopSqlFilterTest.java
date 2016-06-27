package com.fangcheng.filter;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fangcheng.tools.ClassConstants;

public class SqoopSqlFilterTest {
	SqoopSqlFilter ssfFilter=new SqoopSqlFilter();

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
	public void testDoFilter() {
		ClassConstants.setTime("201604");
		System.out.println(ClassConstants.getTime());
		boolean ret=ssfFilter.doFilter();
		assertEquals("result in not right!", true, ret);
	}

}
