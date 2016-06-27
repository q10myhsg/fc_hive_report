package com.fangcheng.filter;



import org.junit.Test;

import com.fangcheng.action.HiveUpDataAction;
import com.fangcheng.action.MongLoadDataAction;

public class FilterChainTest {
	@Test
	public void testFilterChain() {
		FilterChain fc = new FilterChain();
		Filter f1 = new Filter();
		f1.appendAction(new HiveUpDataAction() );
		f1.setPriority(6);
		fc.addFilter(f1);
		Filter f2 = new Filter();
		f2.appendAction(new MongLoadDataAction() );
		f2.setPriority(7);
		fc.addFilter(f2);		
		fc.doAction();
	}
}
