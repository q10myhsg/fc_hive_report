package com.fangcheng.MongoDataService;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.fangcheng.database.MongoManager;
import com.fangcheng.mongo.loaddata.ExportDianpingData;
import com.fangcheng.tools.ClassConstants;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ExportDianpingDataTest {
	
	ExportDianpingData  ldtf = new ExportDianpingData();
	
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
	public void testExportData() {
		boolean ret=ldtf.exportData("201603", 6);
		assertEquals(true, ret);
	}

	@Test
	public void testPlanData() throws JSONException {
		String file_split = ClassConstants.getFILE_SPLIT();
		DBCursor c = ldtf.basicDataService(ClassConstants.getDangpingCollections(),null,3);
		boolean ret=false;
		while(c.hasNext()){
			DBObject object=c.next();
			JSONObject jobj = new JSONObject(object.toString());
			String str=ldtf.planData(object,file_split);
			System.out.println(str);
			System.out.println(jobj);
		}
	   Assert.assertEquals("结果不匹配",false,ret);
	}

	@Test
	public void testIsInt() {
	}
}
