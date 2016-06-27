package com.fangcheng.filter;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class FilterChain extends Filter {
	public static final Logger logger = Logger.getLogger(FilterChain.class.getName());//写控制台
	List<Filter> filters = new LinkedList<Filter>();
	int index = 0;
	public FilterChain addFilter(Filter f) {
		filters.add(f);
		Collections.sort(filters);
		return this;
	}

	@Override
	public boolean doAction() {
		logger.info("do action.....");
		Iterator<Filter> it = filters.iterator();
		boolean flag = true;
		while (flag && it.hasNext()) {
			logger.info("do action....."+flag);
			Filter f = it.next();
			flag = f.doAction();
		}
		return flag;
	}
}
