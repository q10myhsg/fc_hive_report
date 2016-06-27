package com.fangcheng.initconfig;

import org.apache.log4j.PropertyConfigurator;

public class InitConfig {
	
	static{
		PropertyConfigurator.configure(InitConfig.class.getResource("/").getPath()+"/hive_config/log4j.properties");
		RingConfig.default_config ="./hive_conf/config_hive.properties";
		RingConfig.startConfig();
	}
	
	public static void init(){
		RingConfig.default_config ="./hive_conf/config_hive.properties";
		RingConfig.startConfig();
	}

}
