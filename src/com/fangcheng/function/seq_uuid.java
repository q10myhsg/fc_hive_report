package com.fangcheng.function;

import java.util.UUID;

import org.apache.hadoop.hive.ql.exec.UDF;

public class seq_uuid extends UDF {
	//java提供的一种生成主键的方法
	public String evaluate(String original) {
		return UUID.randomUUID().toString();
	}
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString());
	}
}
