package com.fangcheng.function;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.hadoop.hive.ql.exec.UDF;


public class str_to_date extends UDF {
	//比较字段和时间的大小
	public boolean evaluate(String field, String time, String sign) {
		boolean ret = false;
		if(field != null && !"".equals(field)){
			if(">".equals(sign)){
				if(dateDiffer(field, time)> 0)
					ret = true;
				else
					ret = false;
			}else if(">=".equals(sign)){
				if(dateDiffer(field, time)>= 0)
					ret = true;
				else
					ret = false;
			}else if("<=".equals(sign)){
				if(dateDiffer(field, time)<= 0)
					ret = true;
				else
					ret = false;
			}else if("<".equals(sign)){
				if(dateDiffer(field, time)< 0)
					ret = true;
				else
					ret = false;
			}
		}else
			ret = false;
		
		return ret;
	}
	
	public long dateDiffer(String time1, String time2){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long diff = 0;
		try {
		    Date d1 = df.parse(time1);
		    Date d2 = df.parse(time2);
		    diff = d1.getTime()- d2.getTime();
		    //mins =(int) (diff /(1000 * 60 * 60));//小时
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff;
	}
	public static void main(String[] args) {
		
	}
}
