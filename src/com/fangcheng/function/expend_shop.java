package com.fangcheng.function;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

public class expend_shop extends UDF {
	/**
	 * 判断店铺是否是扩张的店铺,该店铺品牌开店倾向强烈(近3个月新开店铺超过2家)
	 * addDate  店铺收录时间
	 * nMonth   上N月时间
	 */
	public int evaluate(String addDate, int nMonth) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();  
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-nMonth-1);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		Date strDateTo = calendar.getTime();
		int now = Integer.parseInt(format.format(strDateTo));
		int addtime = Integer.parseInt(new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date(Long.parseLong(addDate))));
		if(now >= addtime)
			return 0;
		else
			return 1;
	}

}
