package com.fangcheng.function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

public class NewShop extends UDF {
	/**
	 * 判断店铺是否是新店,获取上N月月末时间和店铺收录店铺时间对比,如果上N月时间大于收录时间,说明是新店,否则不是新店
	 * addDate  店铺收录时间
	 * nMonth   上N月时间
	 */
	public boolean evaluate(String addDate, int nMonth) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();  
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-nMonth-1);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		Date strDateTo = calendar.getTime();
		int now = Integer.parseInt(format.format(strDateTo));
		int addtime = Integer.parseInt(new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date(Long.parseLong(addDate))));
		if(now >= addtime)
			return false;
		else
			return true;
	}
	
	public static void main(String[] args) throws ParseException {
		int smon = 4;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();  
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-smon);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		Date strDateTo = calendar.getTime();  
		System.out.println(format.format(strDateTo));
		System.out.println(new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date(Long.parseLong("1449047477000"))));
//		1449047477000
//		1449049162000
//		1449049636000
		SimpleDateFormat format1 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    Long time=new Long(1449049162);  
	    String d = format1.format(time);
	    Date date=format1.parse(d);  
	    System.out.println("Format To String(Date):"+d);  
	    System.out.println("Format To Date:"+date);
	}
	
}
