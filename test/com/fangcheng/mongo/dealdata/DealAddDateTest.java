package com.fangcheng.mongo.dealdata;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.fangcheng.database.MongoManager;
import com.fangcheng.mongo.dealdata.DealAddDate;
import com.mongodb.DBCursor;

public class DealAddDateTest {
	
	DealAddDate da=new DealAddDate();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MongoManager.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		 MongoManager.exit();
	}

	@Test
	public void testDeal() {
		boolean ret=da.deal("201607", 1);
		Assert.assertEquals("结果不匹配", true,ret);
	}

	@Test
	public void testGetBasicData() {
		DBCursor c=da.getBasicData("api_201603d", 3);
		while(c.hasNext()){
			System.out.println(c.next());
		}	
		Assert.assertEquals("结果不匹配", 3,c.size());
	}

	@Test
	public void testInsertColl() {
		DBCursor c=da.getBasicData("api_201603d", 3);
		boolean res=da.insertColl(c, "api_history_add_temp", "api_history_close_temp");
		Assert.assertEquals("结果不匹配", true,res);
	}
}
