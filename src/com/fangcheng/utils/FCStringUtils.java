package com.fangcheng.utils;
import org.apache.commons.lang3.StringUtils;

public class FCStringUtils extends StringUtils{
	public static String cleanLineFlag(String ori){
		String ret ="";
		if(ori!=null&&ori.length()>0){
			ret = ori.replaceAll("\n", "").replaceAll("\r", "").replace("[", "").replace("]", "").replace("\"", "").trim();
		}
		return ret;
	}
}
