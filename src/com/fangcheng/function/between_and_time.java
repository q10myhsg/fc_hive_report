package com.fangcheng.function;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

public class between_and_time extends UDF {
	// 按照between and方式比较时间
	public boolean evaluate(String field, String time1, String time2) {
		boolean ret = false;
		if (field != null && !"".equals(field)) {
			if (compareTime1(field, time1) && compareTime2(field, time2))
				ret = true;
		} else
			ret = false;

		return ret;
	}

	public long dateDiffer(String time1, String time2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long diff = 0;
		try {
			Date d1 = df.parse(time1);
			Date d2 = df.parse(time2);
			diff = d1.getTime() - d2.getTime();
			// mins =(int) (diff /(1000 * 60 * 60));//小时
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff;
	}

	public boolean compareTime1(String field, String time1) {
		boolean ret1 = false;
		// System.out.println("---"+dateDiffer(field, time1));
		if (dateDiffer(field, time1) > 0)
			ret1 = true;
		else
			ret1 = false;

		return ret1;
	}

	public boolean compareTime2(String field, String time1) {
		boolean ret1 = false;
		if (dateDiffer(field, time1) < 0)
			ret1 = true;
		else
			ret1 = false;

		return ret1;
	}
}
