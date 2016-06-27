package com.fangcheng.tools;

//import java.util.Calendar;

import java.util.ArrayList;

import com.fangcheng.initconfig.RingConfig;
//import com.mongodb.util.Args;


public class ClassConstants  {
	
	/**
	 * 初始化
	 * */
	public static final String default_config ="./hive_conf/config_hive.properties";//默认属性文件名称	
//	public static final String default_config ="hive_conf\\config_hive.properties";//默认属性文件名称
	static {
		RingConfig.default_config=default_config;
		RingConfig.startConfig();
	}	
	public static String DATE_FORMAT="yyyyMM";
	public static String time=null;


	/**
	 * 需要导入到hive的mongo基础数据信息
	 * */
	public static String MONGODB="123.57.4.152";	
	public static final String MONGODB_KEY="mongoip152";
	
	public static String MONGODB_SOURCE="10.172.228.111";	
	
	public static int MONGODB_PORT=27017;
	public static final String MONGODB_PORT_KEY="mongoport";
	
	public static final int CONN_TIMEOUT=10000;
	public static final int WAIT_TIME=10000;
	public static final int MaxAutoConnect_Time=100;
	public static String page_collection=null;
	public static String comment_collection=null;
	
	public static String DATABASE="dp_api_archive";	
	public static String COLLECTIONS=null;
	
	public static final String MONGO_DIANPING_KEY="mongodianping";
	public static final String MONGO_PAGE_KEY="mongopage";
	public static final String MONGO_ADDDATA_KEY="mongoadddate";
		
	public static String TIME_PATTERN="yyyyMM";
	
	public static String SOURCE_FILE=null;  //数据下载到文件的路径	
	public static final String  SOURCE_FILE_KEY="filepath";
	
	public static String TARGET_FILE=null;
	public static final String TARGET_FILE_KEY="runpath";
	
	public static String BACKUP_FILE=null;                   //备份文件目录
	public static final String BACKUP_FILE_KEY="backfilepath";
	
	public static String RAR_FILE=null;
	public static final String RAR_FILE_KEY="rar_path";
	
	
	public static ArrayList<String> cityList=new ArrayList<>();   //需要统计的地市  1,2,4,5,7
	public static final String CITY_KEY="city";
	
	public static ArrayList<String> SHOP_TYPE=new ArrayList<>();
	public static final String SHOP_TYPE_KEY="shoptype";

	public static String FILE_SPLIT="\001";        //文件分隔符
	public static String FILE_SPLIT_KEY="file_split";
	
	public static String[] COMMENT_REGULAR_EXPRESSION;
	public static final String COMMENT_REGULAR_EXPRESSION_KEY="dianping_name";
	
	public static String[] PAGE_REGULAR_EXPRESSION ;
	public static final String PAGE_REGULAR_EXPRESSION_KEY="page_name";
	
	public static String[] ADDDATA_REGULAR_EXPRESSION ;
	public static final String ADDDATA_REGULAR_EXPRESSION_KEY="adddate_name";
	
	/**
	 * hive配置
	 * */
	public static String SOURCE_IP="123.57.4.152";
	public static String HIVE_JDBC="jdbc:hive://localhost:10000/default";
	public static final String HIVE_JDBC_KEY="hive_jdbc";
	
	public static String HIVE_PAGE_TABLE="hive_page";
	public static final String HIVE_PAGE_KEY="hive_page_table";
	
	public static String HIVE_COMMENT_TABLE="hive_dianping";
	public static final String HIVE_COMMENT_KEY="hive_dianping_table";
	
	public static String HIVE_ADDDATE_TABLE="hive_dianping_adddate";
	public static final String HIVE_ADDDATE_KEY="hive_adddate_table";
	
	public static String HIVE_DP_FINAL="hive_dianping_sort";
	
	public static ArrayList<String> HQL_ID=new ArrayList<>();   //需要统计的地市  1,2,4,5,7
	public static final String HQL_ID_KEY="hqlid";
	
	public static String XML_CONFIG="./hive_conf/service.xml";	
	public static final String XML_CONFIG_KEY="xml_path";
	
	public static String HQL_CONFIG="./hive_conf/config_hql.txt";
	public static final String HQL_CONFIG_KEY="hql_path";
	
	public static String HQL_LOADDATA="./config/load_hql";	
	public static final String HQL_LOADDATA_KEY="load_path";
	
	public static String HQL_DP_PAGE="./hive_conf/dp_page_hql";
	public static final String HQL_DP_PAGE_KEY="dp_page_path";
	
	public static String FUN_JAR="/home/hduser/project/function/function.jar";
	public static final String FUN_JAR_KEY="func_path";
	
	public static String[] FUN_NAME=null;
	public static final String FUN_NAME_KEY="func_name";
	
	public static String[] DELET_PARTITON=null;
	public static final String DELET_PARTITON_KEY="delname";
	
	public static int TASK_NUM=5;
	public static String INIT_HQL_PATH="./hive_conf/init_hql";
	
	public static String HIVE_BRAND_MALL_DT="201601";
	public static String HIVE_BRAND_MALL_DT_KEY="hive_brand_mall_dt";
	
	/**
	 * 保留最原始点评adddate
	 * */
	
	public static String API_DATABASE="dp_api_archive";
	public static final String API_DATABASE_KEY="api_db";
	
	public static String[] API_COLLECTION;
	public static final String API_COLLECTION_KEY="api_collection";
	
	public static String API_TMP="api_temp_oup";
	public static final String API_TEMP_KEY="api_temp";
	
	public static String API_HISTORY_ADD="api_temp_oup";
	public static final String API_HISTORY_ADD_KEY="api_history_add";
	
	public static String API_HISTORY_CLOSE="api_temp_oup";
	public static final String API_HISTORY_CLOSE_KEY="api_history_close";
	
	
	/**
	 * sqoop导表
	 * */
	
	public static String SHELL_PAGE="/home/hduser/project/shellpath/";
	public static final String SHELL_PAGE_KEY="shellpage";
	
	public static String STOP_PATH="/home/hduser/source_path/stop/";
	public static final String STOP_PATH_KEY="stoppath";
	
	public static String[] SQOOP_SQL=null;
	public static final String SQOOP_SQL_KEY="sqoopsql";
	
	
	public static String JDBC_URL="jdbc:mysql://192.168.1.134:3306/fangcheng_data_team";
	public static final String JDBC_URL_KEY="jdbc.url";
	
	public static String JDBC_USERNAME="fangcheng_admin";
	public static final String JDBC_USERNAME_KEY="jdbc.username";
	
	public static String JDBC_PASSWORD="fc1234";
	public static final String JDBC_PASSWORD_KEY="jdbc.password";
	
	
	public static String getTime() {
		return time;
	}

	public static void setTime(String time) {
		ClassConstants.time = time;
	}
	
	public static String getPage_collection() {
		return page_collection;
	}

	public static void setPage_collection(String time) {
	    String ret=null;
		boolean isDate=TimePrint.isValidDate(time,ClassConstants.getTimeAddDataPattern());
		if(isDate){
			ret="page_parsed_"+time+"d";
		}	
		ClassConstants.page_collection = ret;
	}

	public static String getComment_collection() {
		return comment_collection;
	}

	public static void setComment_collection(String time) {
		 String ret=null;
			boolean isDate=TimePrint.isValidDate(time,ClassConstants.getTimeAddDataPattern());
			if(isDate){
				ret="api_"+time+"d";
			}	
		ClassConstants.comment_collection = ret;
	}

	public static String getFILE_SPLIT() {
		String ret= RingConfig.getProperty(FILE_SPLIT_KEY).trim();
		if(ret.equals("")) 
			ret=FILE_SPLIT;
		return FILE_SPLIT;
	}
	
	public static String[] getCOMMENT_REGULAR_EXPRESSION() {
		String[] com=null;
		String ret=RingConfig.getProperty(COMMENT_REGULAR_EXPRESSION_KEY).trim();
		if(ret!=null && ret.length()>0)
			com=ret.split(":");
		else 
			com=COMMENT_REGULAR_EXPRESSION;
		
		return com;
	}
	public static String[] getPAGE_REGULAR_EXPRESSION() {
		String[] page=null;
		String ret=RingConfig.getProperty(PAGE_REGULAR_EXPRESSION_KEY).trim();
		if(ret!=null && ret.length()>0)
			page=ret.split(":");
		else 
			page=PAGE_REGULAR_EXPRESSION;
		
		return page;
	}
	public static String[] getADDDATA_REGULAR_EXPRESSION() {
		String[] adddata=null;
		String ret=RingConfig.getProperty(ADDDATA_REGULAR_EXPRESSION_KEY).trim();
		if(ret!=null && ret.length()>0)
			adddata=ret.split(":");
		else 
			adddata=ADDDATA_REGULAR_EXPRESSION;		
		return adddata;
	}
	public static ArrayList<String> getCity() {
		ArrayList<String> cList=new ArrayList<>();
		String ret=RingConfig.getProperty("city").trim();
		if(ret!=null){
			String[] cityId=ret.split(",");
			for(int i=0;i<cityId.length;i++)
				cList.add(cityId[i]);
		}else
			cList=cityList;
		return cList;		
	}	
	
	public static ArrayList<String> getShop_type() {
		ArrayList<String> shopList=new ArrayList<>();
		String ret=RingConfig.getProperty(SHOP_TYPE_KEY).trim();
		if(ret!=null){
			String[] shopStrings=ret.split(",");
			for(int i=0;i<shopStrings.length;i++){
				shopList.add(shopStrings[i]);
			}
		}else{
			shopList=SHOP_TYPE;
		}
		return shopList;
	}

	public static String getMongodb() {
		String ret= RingConfig.getProperty(MONGODB_KEY).trim();
		if(ret==null)	
			ret=MONGODB;
		return ret;
	}


	public static String getDianpingDatabase() {
		String ret=RingConfig.getProperty(MONGO_DIANPING_KEY);
		if(ret!=null && ret.length()>0)
			ret=ret.split(":")[0];
		else
			ret=DATABASE;
		return ret;
	}
	public static String getDangpingCollections() {
		String ret =RingConfig.getProperty(MONGO_DIANPING_KEY);
		if(ret!=null&&ret.length()>1)
			ret=ret.split(":")[1];
		else
			ret=COLLECTIONS;
		return ret;
	}
    public static String getTimeDianpingPattern() {
    	String ret =RingConfig.getProperty(MONGO_DIANPING_KEY);
		if(ret!=null && ret.length()>2)
			ret=ret.split(":")[2];
		else
			ret=TIME_PATTERN;
		return ret;
		
	}
	public static String getAddDataDatabase() {
		String ret=RingConfig.getProperty(MONGO_ADDDATA_KEY);
		if(ret!=null && ret.length()>0)
			ret=ret.split(":")[0];
		else
			ret=DATABASE;
		return ret;
	}
	public static String getAdddataCollections() {
		String ret =RingConfig.getProperty(MONGO_ADDDATA_KEY);
		if(ret!=null){
			ret = ret.split(":")[1];
		}else{
			ret =  COLLECTIONS;
		}
		
		return ret;
	}
	public static String getTimeAddDataPattern() {
    	String ret =RingConfig.getProperty(MONGO_ADDDATA_KEY);
		if(ret!=null&&ret.length()>2)		
			ret=ret.split(":")[2];
		else
			ret=TIME_PATTERN;
		return ret;
		
	}
	public static String getPageDatabase() {
		String ret=RingConfig.getProperty(MONGO_PAGE_KEY);
		if(ret!=null && ret.length()>0)
			ret=ret.split(":")[0];
		else
			ret=DATABASE;
		return ret;
		
	}
	public static String getPageCollections() {
		String ret =RingConfig.getProperty(MONGO_PAGE_KEY);
		if(ret!=null){
			ret = ret.split(":")[1];
		}else{
			ret =  COLLECTIONS;
		}
		
		return ret;
	}
	public static String getTimePagePattern() {
    	String ret =RingConfig.getProperty(MONGO_PAGE_KEY).trim();
    	if(ret!=null&&ret.length()>2)		
			ret=ret.split(":")[2];
		else
			ret=TIME_PATTERN;
		return ret;
		
	}
	public static int getMongoport() {
		String ret=RingConfig.getProperty(MONGODB_PORT_KEY).trim();
		if(ret!=null){
			return Integer.parseInt(ret);
		}else{
			return MONGODB_PORT;
		}
	}
	
	public static String getSOURCE_FILE() {
		String ret = RingConfig.getProperty(SOURCE_FILE_KEY).trim();
		if(ret!=null){
			if(!ret.endsWith("\\") && !ret.endsWith("/")){
				ret = ret + "/";
			}			
		}else
			ret=SOURCE_FILE;
		return ret;		
	}
	
	public static String getTARGET_FILE() {
		String ret=RingConfig.getProperty(TARGET_FILE_KEY).trim();
		if(ret!=null){
			if(!ret.endsWith("\\") && !ret.endsWith("/")){
				ret = ret + "/";
			}
		}else{
			ret=TARGET_FILE;
		}
		return ret;
	}
	public static String getBACKUP_FILE() {
		String ret=RingConfig.getProperty(BACKUP_FILE_KEY).trim();
		if(ret!=null){
			if(!ret.endsWith("\\") && !ret.endsWith("/")){
				ret = ret + "/";
			}
		}else{
			ret=BACKUP_FILE;
		}
		return ret;
	}
	public static String getRAR_FILE() {
		String ret=RingConfig.getProperty(RAR_FILE_KEY).trim();
		if(ret!=null){
			if(!ret.endsWith("\\") && !ret.endsWith("/")){
				ret = ret + "/";
			}
		}else{
			ret=RAR_FILE;
		}
		return ret;
	}
	
	public static String getHIVE_JDBC() {
		String ret=RingConfig.getProperty(HIVE_JDBC_KEY).trim();
		if(ret==null)
			ret=HIVE_JDBC;
		return ret;
	}
	public static String getHIVE_PAGE_TABLE() {
		String ret=RingConfig.getProperty(HIVE_PAGE_KEY).trim();
		if(ret==null)
			ret=HIVE_PAGE_TABLE;	
		return ret;
	}
	public static String getHIVE_COMMENT_TABLE() {
		String ret=RingConfig.getProperty(HIVE_COMMENT_KEY).trim();
		if(ret==null)
			ret=HIVE_COMMENT_TABLE;	
		return ret;
	}
	public static String getHIVE_ADDDATE_TABLE() {
		String ret=RingConfig.getProperty(HIVE_ADDDATE_KEY).trim();
		if(ret==null)
			ret=HIVE_ADDDATE_TABLE;	
		return ret;
	}
	public static ArrayList<String> getHQL_ID() {
		ArrayList<String> hqlList=new ArrayList<>();
		String ret=RingConfig.getProperty(HQL_ID_KEY).trim();
		if(ret!=null){
			String[] hqlid=ret.split(",");
			for(int i=0;i<hqlid.length;i++)
				hqlList.add(hqlid[i]);			
		}else
			hqlList=HQL_ID;
		return hqlList;
	}
	public static String getHQL_CONFIG() {
		String ret=RingConfig.getProperty(HQL_CONFIG_KEY).trim();
		if(ret==null)
			ret=HQL_CONFIG;	
		return ret;
	}
	public static String getHQL_LOADDATA() {
		String ret=RingConfig.getProperty(HQL_LOADDATA_KEY).trim();
		if(ret==null)
			ret=HQL_LOADDATA;	
		return ret;
	}

	public static String getHQL_DP_PAGE() {
		String ret=RingConfig.getProperty(HQL_DP_PAGE_KEY).trim();
		if(ret==null)
			ret=HQL_DP_PAGE;	
		return ret;
	}
	public static String getFUN_JAR() {
		String ret=RingConfig.getProperty(FUN_JAR_KEY).trim();
		if(ret==null)
			ret=FUN_JAR;	
		return ret;
	}
	public static String[] getFUN_NAME() {
		String fun=RingConfig.getProperty(FUN_NAME_KEY).trim();
		String[] ret=null;
		if(fun!=null)
			ret=fun.split(",");
		else {
			ret=FUN_NAME;
		}
		return ret;
	}
	public static String[] getDELET_PARTITON() {
		String[] deletStr = null;
		String ret=RingConfig.getProperty(DELET_PARTITON_KEY).trim();
		if(ret!=null)
			deletStr=ret.split(",");
		else 
			deletStr=DELET_PARTITON;			
		return deletStr;
	}
	
	public static String getHIVE_BRAND_MALL_DT() {
		String ret=RingConfig.getProperty(HIVE_BRAND_MALL_DT_KEY).trim();
		if(ret==null)
			ret=HIVE_BRAND_MALL_DT;
		return ret;
	}
	public static String getXML_CONFIG() {
		String ret=RingConfig.getProperty(XML_CONFIG_KEY).trim();
		if(ret==null)
			ret=XML_CONFIG;	
		return ret;
	}
	
	public static String getAPI_DATABASE() {
		String ret=RingConfig.getProperty(API_DATABASE_KEY).trim();
		if(ret==null)
			ret=API_DATABASE;
		return ret;
	}

	public static String[] getAPI_COLLECTION() {
		String ret=RingConfig.getProperty(API_COLLECTION_KEY).trim();
		String[] res;
		if(ret!=null && ret.length()>0)
			res=ret.split(",");
		else 
			res=new String[0];
		return res;
	}

	public static String getAPI_TMP() {
		String ret=RingConfig.getProperty(API_TEMP_KEY).trim();
		if(ret==null)
			ret=API_TMP;
		return ret;
	}

	public static String getAPI_HISTORY_ADD() {
		String ret=RingConfig.getProperty(API_HISTORY_ADD_KEY).trim();
		if(ret==null)
			ret=API_HISTORY_ADD;
		return ret;
	}

	public static String getAPI_HISTORY_CLOSE() {
		String ret=RingConfig.getProperty(API_HISTORY_CLOSE_KEY).trim();
		if(ret==null)
			ret=API_HISTORY_CLOSE;
		return ret;
	}
	
	public static String getSHELL_PAGE() {
		String ret=RingConfig.getProperty(SHELL_PAGE_KEY).trim();
		if(ret!=null){
			if(!ret.endsWith("\\") && !ret.endsWith("/")){
				ret = ret + "/";
			}
		}else{
			ret=SHELL_PAGE;
		}
		return ret;
	}

	public static String getSTOP_PATH() {
		String ret=RingConfig.getProperty(STOP_PATH_KEY).trim();
		if(ret!=null){
			if(!ret.endsWith("\\") && !ret.endsWith("/")){
				ret = ret + "/";
			}
		}else{
			ret=STOP_PATH;
		}
		return ret;
	}

	public static String[] getSQOOP_SQL() {
		String[] ret=null;
		String retString=RingConfig.getProperty(SQOOP_SQL_KEY).trim();
		if(retString!=null)
			ret=retString.split("]");
		else 
			ret=SQOOP_SQL;
		return ret;
	}

	public static String getJDBC_URL() {
		String retString=RingConfig.getProperty(JDBC_URL_KEY).trim();
		if(retString==null)
			retString=JDBC_URL;
		return retString;
	}

	public static String getJDBC_USERNAME() {
		String retString=RingConfig.getProperty(JDBC_USERNAME_KEY).trim();
		if(retString==null)
			retString=JDBC_USERNAME;
		return retString;
	}

	public static String getJDBC_PASSWORD() {
		String retString=RingConfig.getProperty(JDBC_PASSWORD_KEY).trim();
		if(retString==null)
			retString=JDBC_PASSWORD;
		return retString;
	}

}
