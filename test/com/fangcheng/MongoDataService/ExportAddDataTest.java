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
import com.fangcheng.mongo.loaddata.ExportAddData;
import com.fangcheng.tools.ClassConstants;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class ExportAddDataTest {
	
	ExportAddData  ladf = new ExportAddData();
	
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
		boolean ret=ladf.exportData("201603", 100);
		assertEquals(true, ret);
		
	}

	@Test
	public void testPlanData() throws JSONException {
		String file_split = ClassConstants.getFILE_SPLIT();
		DBCursor c = ladf.basicDataService(ClassConstants.getAdddataCollections(),null,3);
		boolean ret=false;
		while(c.hasNext()){
			DBObject object=c.next();
			JSONObject jobj = new JSONObject(object.toString());
			String str=ladf.planData(object,file_split);
			String str1=jobj.getInt("shopId")+file_split+jobj.getInt("shopType")+file_split+
					jobj.getInt("cityId")+file_split+jobj.getString("addDate")+file_split+
					"null";
			System.out.println(str);
			System.out.println(str1.equals(str));
		}
	   Assert.assertEquals("结果不匹配",false,ret);
	}

	@Test
	public void testBasicDataService() throws JSONException {
		DBCursor c = ladf.basicDataService(ClassConstants.getAdddataCollections(),null,4);
		while(c.hasNext()){
			System.out.println(c.next());
		}	
		Assert.assertEquals("结果不匹配", 4,c.size());
	}

	@Test
	public void testDownLoadDataFile() {
		DBCursor b = ladf.basicDataService(ClassConstants.getAdddataCollections(),null,4);
		boolean ret=ladf.downLoadDataFile(b, "E:\\1.txt");
		assertEquals(true, ret);
	}

	@Test
	public void testIfExistsFile() {
		
	
	}

}
