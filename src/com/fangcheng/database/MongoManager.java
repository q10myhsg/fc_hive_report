package com.fangcheng.database;

public class MongoManager {
	private static MongoDb ins = null;

	public static MongoDb getInstance() {
		if (ins == null) {
			synchronized (MongoManager.class) {
				if (ins == null) {
					ins = new MongoDb();
				}
			}
		}
		return ins;
	}

	public static void exit() {
		getInstance().close();
	}
}
