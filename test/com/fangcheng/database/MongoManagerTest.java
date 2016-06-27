package com.fangcheng.database;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Test;

public class MongoManagerTest {

	@After
	public void tearDown() throws Exception {
		MongoManager.getInstance().close();
	}

	@Test
	public void testGetInstance() {
		MongoDb mo = MongoManager.getInstance();
		if (mo != null) {
			List<String> l = mo.getDataBase();
			assertTrue("数据库连接失败", l.size() > 0);
		} else
			fail("数据库连接失败");
	}

}
