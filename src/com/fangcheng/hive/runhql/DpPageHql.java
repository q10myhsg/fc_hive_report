package com.fangcheng.hive.runhql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcheng.database.HiveDb;
import com.fangcheng.service.HqlInterface;
import com.fangcheng.tools.ClassConstants;
import com.fangcheng.tools.TimePrint;
import com.fangcheng.utils.FCFileUtils;

public class DpPageHql implements HqlInterface{
	private static final Logger logger = Logger.getLogger(DpPageHql.class.getName());//写控制台
	private static final Log log = LogFactory.getLog(HiveData.class);//写日志文件	
	private static Connection con = null;
	private static Statement stmt = null;
	
	public static void hiveConnection(){
		try {
			//链接hive数据库
			con = HiveDb.getConn();
			stmt = con.createStatement();
			//设定reduce任务的个数
			logger.info(".....hive 初始化设置....");
			String MRtaskNum="set taskNum="+ClassConstants.TASK_NUM;
			stmt.executeQuery(MRtaskNum);
			//hive 初始化设置
			ArrayList<String> initHqlArrayList=DpPageHql.readInitHql(ClassConstants.INIT_HQL_PATH);
			for(int i=0;i<initHqlArrayList.size();i++)
				stmt.executeQuery(initHqlArrayList.get(i));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	logger.info("异常信息："+error);
		}
	}
	
	@Override
	public boolean execute(String run_time){
		boolean ret=true;
		String time = run_time;
		try{
			//实例化Hql参数类ParaBean
			logger.info("设置hql执行时的参数。。。。。。。。。。。。");
			ParaBean param=new ParaBean();
			param.setRuntime(time);
			param.setComparetime(TimePrint.dateSubAdd(time, ClassConstants.DATE_FORMAT, 1));
			logger.info("执行hql语句。。。。。。。。。。。。");
			queryHql(param);
			logger.info("drop表分区。。。。。。。。。。。。");
			deletePartition();
		} catch (Exception e) {
			ret=false;
			e.printStackTrace();
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	logger.info("异常信息："+error);
		}
		return ret;
	}
	public static String judgeBrandMall(String time){
		String ret=null;		
		//依据hive_page表中的shops字段判断是否计算表#提取mall中店铺列表hive_brand_inmallid和hive_brand_inmallname			
		String shopHql="select * from hive_page where dt='"+time+"' and shops <>\"\" ";
		ResultSet shop;
		try {
			shop = stmt.executeQuery(shopHql);
			boolean shopflag=shop.next();
			if(shopflag==false){
				String dtStr="select distinct(dt) dt from hive_brand_inmallname";
				ResultSet hb=stmt.executeQuery(dtStr);
				while(hb.next()){
					ret=hb.getString("dt");
				}					
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return ret;
	}
	/*
	 * 执行多个动态语句
	 * */
	public void queryHql(ParaBean param){
		try {
			hiveConnection();
			//设置hql语句中的参数
			stmt.executeQuery("SET mapred.reduce.tasks="+ClassConstants.TASK_NUM);
			stmt.executeQuery("set date="+param.getRuntime());
			stmt.executeQuery("set comparedate="+param.getComparetime());
			stmt.executeQuery("set city=("+param.getCity()+")");
			stmt.executeQuery("set shoptype=("+param.getShoptype()+")");
			stmt.executeQuery("");
						
			logger.info(".....hive 中执行hql语句，生成数据报告...");
			//读取hql语句
			HashMap<String, String> hqlHashMap=getHql(ClassConstants.getHQL_DP_PAGE());
			ArrayList<String> hqlidStr=ClassConstants.getHQL_ID();
			
			//设置hql语句中的参数
			stmt.executeQuery("set adddate="+param.getBrandmalldt());	
			
			//执行hql语句
			for(int i=0;i<hqlHashMap.size();i++){
				logger.info("执行hql生成表："+hqlidStr.get(i));
				if(hqlidStr.get(i).equals("brand_inmallid")||hqlidStr.get(i).equals("brand_inmallname"))
					continue;
				logger.info("执行hql生成表："+hqlidStr.get(i));
				try {
					stmt.execute(hqlHashMap.get(hqlidStr.get(i)));
				} catch (Exception e) {
					e.printStackTrace();					
					log.info(e.getCause(), e);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getCause(), e);
		}finally{
			HiveDb.close(stmt, con);	     
		}
	}
	/*
	 * 读取hql语句
	 * */
	public static HashMap<String, String> getHql(String hqlfile){
		
		HashMap<String, String> hqlHashMap=new HashMap<>();
		ArrayList<String> hqlid=ClassConstants.getHQL_ID();
		try {
			String hqlStr=FCFileUtils.readFile(hqlfile);
			ArrayList<String> hqlList=FCFileUtils.splitHql(hqlStr);
			for(int i=0;i<hqlList.size();i++)
				hqlHashMap.put(hqlid.get(i), hqlList.get(i));
		} catch (IOException e) {
			e.printStackTrace();
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	logger.info("异常信息："+error);
		}	
		return hqlHashMap;
	}
	/*
	 * 读取hql初始化语句
	 * */
	public static ArrayList<String> readInitHql(String initfile){
		ArrayList<String> initList=new ArrayList<>();
		try {
			String initStr = FCFileUtils.readFile(initfile);
			initList=FCFileUtils.splitHql(initStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return initList;
	}
	/*
	 * 删除hive数据库中的数据
	 * */
	@Override
	public boolean deletePartition() {
		//alter table web_access_log_y drop partition(receive_day='?')
		boolean ret=true;	
		try {
			hiveConnection();
			String[] tables = ClassConstants.getDELET_PARTITON();
			for(int i=0;i<tables.length;i++){
				String[] tmpStr=tables[i].split(":");
				String time=TimePrint.getTime(tmpStr[2],Integer.parseInt(tmpStr[3]));
				String deletHql="alter table "+tmpStr[0]+" drop partition("+tmpStr[1]+"='"+time+"')";
				logger.info("delet table :"+ tmpStr[0]+": dt="+ time);
				stmt.executeQuery(deletHql);
			}
		} catch (Exception e) {
			ret=false;
			// TODO Auto-generated catch block
			e.printStackTrace();
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	logger.info("异常信息："+error);
		}finally{
			HiveDb.close(stmt, con);	     
		}
		return ret;
	}

	public static void main(String[] args) {
		DpPageHql cph=new DpPageHql();		
		if(args.length==1 && args[0]!=null){		
		    String runtime=args[0];
		    boolean ret=cph.execute(runtime);  
		    logger.info("run hql:"+ ret);
		}else{
			logger.info("...日期参数输入错误,请输入正确的日期格式：yyyyMM..........");
		}
		HiveDb.close(stmt, con);	      
	}

}
