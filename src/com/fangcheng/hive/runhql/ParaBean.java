package com.fangcheng.hive.runhql;

import com.fangcheng.initconfig.RingConfig;
import com.fangcheng.tools.ClassConstants;

public class ParaBean {
    //hql语句中的参数
	private String runtime;
	private String comparetime;
	private static String CITY_KEY="city";
	private static String SHOP_TYPE_KEY="shoptype";
	public String getCity() {
		String cityString=RingConfig.getProperty(CITY_KEY);
		return cityString;
	}
	public String getShoptype() {
		String shoptypeStr=RingConfig.getProperty(SHOP_TYPE_KEY).trim();
		return shoptypeStr;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getComparetime() {
		return comparetime;
	}
	public void setComparetime(String comparetime) {
		this.comparetime = comparetime;
	}
	public String getBrandmalldt() {
		String brandtimeString=ClassConstants.getHIVE_BRAND_MALL_DT();
		return brandtimeString;				
	}	
}
