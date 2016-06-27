package com.fangcheng.filter;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.fangcheng.database.MongoManager;
import com.fangcheng.tools.ClassConstants;

public class MongFilterTest {
	MongFilter mfFilter=new MongFilter();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		MongoManager.exit();
	}

	@Test
	public void testDoFilter() {
		ClassConstants.setTime("201604");
		ClassConstants.setComment_collection("201604");
		ClassConstants.setPage_collection("201604");
		System.out.println(ClassConstants.getTime()+" "+ClassConstants.getMongodb());
		boolean ret=mfFilter.doFilter();		
		assertEquals("result in not right!", true, ret);
	}

}
