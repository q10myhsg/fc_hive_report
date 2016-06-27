package com.fangcheng.action;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fangcheng.main.ImportData;
import com.fangcheng.tools.ClassConstants;


public class MongoAction implements Action{
	private static final Logger logger = LoggerFactory.getLogger(MongoAction.class);
	
	@Override
	public void doAction() {
		logger.debug("mongo action start=========");
		ImportData.getDataBase(ClassConstants.API_DATABASE);		
		try {
			ImportData.insertData(ClassConstants.getPage_collection());
			ImportData.insertData(ClassConstants.getComment_collection());
		} catch (JSONException e) {
			e.printStackTrace();
			logger.debug(e.toString());
		}		
	}
}
