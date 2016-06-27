package com.fangcheng.function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

public class mature_shop extends UDF {
	/**
	 * 判断店铺是否成熟,开店3年以上为成熟
	 * addDate 店铺收录时间
	 * nMonth  默认为1,指上月月末日期
	 * nYear   N年之前
	 */
	public boolean evaluate(String addDate, int nMonth, int nYear) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();  
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.MONTH, month-nMonth);
		calendar.set(Calendar.YEAR, year-nYear);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		Date strDateTo = calendar.getTime();  
		int now = Integer.parseInt(format.format(strDateTo));
		int addtime = Integer.parseInt(new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date(Long.parseLong(addDate))));
		if(now >= addtime)
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();  
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.YEAR, year-3);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		Date strDateTo = calendar.getTime();  
		System.out.println(format.format(strDateTo));
		System.out.println(new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date(Long.parseLong("1079423880000"))));
	}
	
}
