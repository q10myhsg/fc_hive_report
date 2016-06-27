package com.fangcheng.tools;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ClassConstantsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ClassConstants.getCity();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFILE_SPLIT() {
		assertEquals("配置不匹配", "\001", ClassConstants.getFILE_SPLIT());
	}

	@Test
	public void testGetCOMMENT_REGULAR_EXPRESSION() {
		String retString="city_.*?\\_dynamic_\\d{6}\\.txt:yyyyMM:3";
		assertArrayEquals("配置不匹配", retString.split(":"), ClassConstants.getCOMMENT_REGULAR_EXPRESSION());
	}

	@Test
	public void testGetPAGE_REGULAR_EXPRESSION() {
		String retString="city_page_\\d{6}\\.txt:yyyyMM:3";
		assertArrayEquals("配置不匹配", retString.split(":"), ClassConstants.getPAGE_REGULAR_EXPRESSION());
	}

	@Test
	public void testGetADDDATA_REGULAR_EXPRESSION() {		
		String retString="api_history_add_\\d{6}\\.txt:yyyyMM:3";
		assertArrayEquals("配置不匹配", retString.split(":"), ClassConstants.getADDDATA_REGULAR_EXPRESSION());
	}

	@Test
	public void testGetCity() {
		String[] city={"1","2","3","4","5","7","8","9","10","15","16","18","21","70","79","110","160","267","344"};
		ArrayList<String> cList=new ArrayList<String>();
		for(int i=0;i<city.length;i++)
			cList.add(city[i]);
		assertEquals("city配置不匹配", cList, ClassConstants.getCity());
	}

	@Test
	public void testGetShop_type() {
		String st="10,15,20,30,45,50,55,60,65,70,80,90";
		String[] shoptype=st.split(",");
		ArrayList<String> shopList=new ArrayList<>();
		for(int i=0;i<shoptype.length;i++)
			shopList.add(shoptype[i]);
		assertEquals("shoptype 配置不对",shopList,ClassConstants.getShop_type());
	}

	@Test
	public void testGetMongodb() {
		assertEquals("10.172.228.111", ClassConstants.getMongodb());
	}

	@Test
	public void testGetDianpingDatabase() {
		assertEquals(27017, ClassConstants.getMongoport());
	}

	@Test
	public void testGetDangpingCollections() {
		assertEquals("api_201603d", ClassConstants.getDangpingCollections());
	}

	@Test
	public void testGetTimeDianpingPattern() {
		assertEquals("yyyyMM", ClassConstants.getTimeDianpingPattern());
	}

	@Test
	public void testGetAddDataDatabase() {
		assertEquals("dp_api_archive", ClassConstants.getAddDataDatabase());
	}

	@Test
	public void testGetAdddataCollections() {
		assertEquals("api_history_add", ClassConstants.getAdddataCollections());
	}

	@Test
	public void testGetTimeAddDataPattern() {
		assertEquals("yyyyMM", ClassConstants.getTimeAddDataPattern());
	}

	@Test
	public void testGetPageDatabase() {
		assertEquals("dp_api_archive", ClassConstants.getPageDatabase());
	}

	@Test
	public void testGetPageCollections() {
		assertEquals("page_parsed_201603d", ClassConstants.getPageCollections());
	}

	@Test
	public void testGetTimePagePattern() {
		assertEquals("yyyyMM", ClassConstants.getTimePagePattern());
	}

	@Test
	public void testGetMongoport() {
		assertEquals(27017, ClassConstants.getMongoport());
	}

	@Test
	public void testGetSOURCE_FILE() {
		assertEquals("文件路径和配置不同","E:\\workspace_2015\\", ClassConstants.getSOURCE_FILE());
	}

	@Test
	public void testGetTARGET_FILE() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBACKUP_FILE() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRAR_FILE() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTime() {
		assertEquals("201603", ClassConstants.getTime());
	}

	@Test
	public void testGetHIVE_JDBC() {
		assertEquals("jdbc:hive://192.168.1.64:10000/default", ClassConstants.getHIVE_JDBC());
	}

	@Test
	public void testGetHIVE_PAGE_TABLE() {
		assertEquals("hive_page", ClassConstants.getHIVE_PAGE_TABLE());
	}

	@Test
	public void testGetHIVE_COMMENT_TABLE() {
		assertEquals("hive_dianping", ClassConstants.getHIVE_COMMENT_TABLE());
	}

	@Test
	public void testGetHIVE_ADDDATE_TABLE() {
		assertEquals("hive_dianping_adddate", ClassConstants.getHIVE_ADDDATE_TABLE());
	}

	@Test
	public void testGetHQL_ID() {
		ArrayList<String> hql=new ArrayList<>();
		String hid="dianping_filter,page_filter,dianping_page,dianping_page_temp,dianping_adddate_temp,dianping_adddate_temp2,dianping_adddate,dianping_transdate,dianping_change,city_brand1,city_brand2,brand_inmallid,brand_inmallname,brand_tag1,brand_tag2,dianping_final_temp,dianping_final,dianping_sort_temp,dianping_sort_max,dianping_sort";
		String[] hqlidString=hid.split(",");
		for(int i=0;i<hqlidString.length;i++)
			 hql.add(hqlidString[i]);
		
		assertEquals(hql, ClassConstants.getHQL_ID());
	}

	@Test
	public void testGetHQL_CONFIG() {
		assertEquals("./config/config_hql.txt", ClassConstants.getHQL_CONFIG());
	}

	@Test
	public void testGetFUN_JAR() {
		assertEquals("/home/hduser/project/function/function.jar", ClassConstants.getFUN_JAR());
	}

	@Test
	public void testGetFUN_NAME() {
		String retString="expend_shop,new_shop,mature_shop";
		String[] ret=retString.split(",");
		assertArrayEquals(ret, ClassConstants.getFUN_NAME());
	}

	@Test
	public void testGetDELET_PARTITON() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetXML_CONFIG() {
		assertEquals("./config/service.xml", ClassConstants.getXML_CONFIG());
	}

}
