package com.fangcheng.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimePrint {
	
	public static Calendar cal = Calendar.getInstance();
	public static SimpleDateFormat format ;
	
    //时间模板一定要按正常格式来编写(yyyy-MM-dd HH:mm:ss)MM和HH一定要大写
    public static String getTime(String timemoban,int num){
   
    	format = new SimpleDateFormat(timemoban);
    	cal.setTime(new Date()); 
		if(timemoban.endsWith("yyyy")){
			cal.add(Calendar.YEAR, -num);
		}else if(timemoban.endsWith("MM")){
			cal.add(Calendar.MONTH, -num);
		}else if(timemoban.endsWith("HH")){
			cal.add(Calendar.HOUR_OF_DAY, -num);
		}else{
			cal.add(Calendar.DAY_OF_MONTH,-num);
		}
        return format.format(cal.getTime());
	}
    
    public static boolean isValidDate(String inDate,String pattern){
    	boolean ret = false;
	    //set the format to use as a constructor argument
	    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	    dateFormat.setLenient(false);
	    
	    //parse the inDate parameter
	    Date d=null;
		try {
			d = dateFormat.parse(inDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
		if(d!=null){
	    	ret = true;
	    }	    
	    return ret;
	  }
    
    public static String dateSubAdd(String time,String format,int num){
    	String ret=null;
    	SimpleDateFormat formatter = new SimpleDateFormat(format);
    	try {
    	    Date myDate = formatter.parse(time);
    	    Calendar c = Calendar.getInstance();
    	    c.setTime(myDate);
    	    c.add(Calendar.MONTH,-num);
    	    ret = formatter.format(c.getTime());
    	} catch (ParseException e1) {
    	    e1.printStackTrace();
    	}
    	return ret;
    }

    
	public static void main(String[] args) throws ParseException {
//		DateFormat df = new SimpleDateFormat("yyyy-MM");
//		Date date = df.parse("2015-10-01");
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
		for(int i=0;i<3;i++){
			System.out.println(getTime("yyyyMM",2));
		}
		
//		long timestamp = cal.getTimeInMillis();
//		System.out.println(clString);
//		String ret=TimePrint.dateSubAdd("201605", "yyyyMM", 5);
//		
//		String clString=TimePrint.getTime("yyyyMM",2);
//		System.out.println(clString);
	}

}
