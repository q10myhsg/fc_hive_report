package com.fangcheng.filter;

import com.fangcheng.action.Action;
/**
 * filter 基类
 */
public class Filter implements Comparable<Filter> {
	/*
	 * default priority
	 */
	private Integer priority = 5;

	/**
	 * 如果通过了filter 则做什么动作
	 */

	private Action action;

	public boolean doAction() {
		boolean ret = doFilter();
		if (ret) {
			if (action != null) {
				action.doAction();
			}
		}
		return ret;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public boolean doFilter() {
		return true;
	}

	public Integer getPriority() {
		return priority;
	}

	public void appendAction(Action action) {
		this.action = action;
	}

	@Override
	public int compareTo(Filter f) {
		return getPriority().compareTo(f.getPriority());
	}
}
