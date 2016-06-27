package com.fangcheng.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.protobuf.TextFormat.ParseException;

public class DataUtil {
	
	public boolean isValidDate(String inDate,String pattern){	   
		boolean ret = false;
	    //set the format to use as a constructor argument
	    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	    dateFormat.setLenient(false);
	    //parse the inDate parameter
	    Date d=null;
	    try {
			d = dateFormat.parse(inDate);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		if(d!=null){
	    	ret = true;
	    }	    
	    return ret;
	  }


	public static void main(String[] args) throws ParseException, java.text.ParseException {
		 DataUtil test = new DataUtil();

		    System.out.println(test.isValidDate("2004-02-29","yyyy-MM-dd"));
		    System.out.println(test.isValidDate("20050228","yyyyMMdd"));
		    System.out.println(test.isValidDate("20150106","yyyyMM"));
	}

}
