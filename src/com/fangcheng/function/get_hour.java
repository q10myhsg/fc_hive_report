package com.fangcheng.function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.hadoop.hive.ql.exec.UDF;

public class get_hour extends UDF {
	
	@SuppressWarnings("static-access")
	public String evaluate(String original, String dateForamt) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateForamt);
    	java.util.Date date = null;
		try {
			date = sdf.parse(original);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String time = sdf.format(date);
    	Calendar cd = Calendar.getInstance();
    	try {
    	      cd.setTime(sdf.parse(time));
    	} catch (ParseException e) {
    	       e.printStackTrace();
    	}
    	
    	if(cd.get(cd.HOUR_OF_DAY)< 10)
    		return "0"+ String.valueOf(cd.get(cd.HOUR_OF_DAY));
    	else
    		return String.valueOf(cd.get(cd.HOUR_OF_DAY));
	}

}
