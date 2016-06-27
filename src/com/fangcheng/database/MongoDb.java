package com.fangcheng.database;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.fangcheng.tools.ClassConstants;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceOutput;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.QueryOperators;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

/**
 * mongodb 类
 * 
 * @author Administrator
 *
 */

public class MongoDb {
	private static Logger log = Logger.getLogger(MongoDb.class);
	public int POOLSIZE = 100;
	public int BLOCKSIZE = 100;

	public Mongo m = null; // mongodb
	public DB db = null; // 链接的数据库

	public DBCursor cursor = null;

	public String ip = null;
	public int port = 27017;
	public String database = null;
	public String collection[] = null; // mongodb中collection集合
	public int useCollectionIndex = 0;
	public String ref = "{}";
	public String obj = "{}";

	/**
	 * 创建mongodb连接
	 * 
	 * @param ip
	 *            mongodb的IP地址
	 * @param port
	 *            端口
	 * @param database
	 *            数据库
	 */
	public MongoDb(String ip, int port, String database) {
		try {
			this.ip = ip;
			this.port = port;
			this.database = database;

			m = new Mongo(ip, port);
			MongoOptions opt = m.getMongoOptions();
			opt.connectionsPerHost = POOLSIZE;
			opt.threadsAllowedToBlockForConnectionMultiplier = BLOCKSIZE;

			db = m.getDB(database); // 得到数据库

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StringBuffer error=new StringBuffer();
		   	StackTraceElement [] messages=e.getStackTrace();
		   	int length=messages.length;
		   	for(int i=0;i<length;i++){
		   		error.append(messages[i]);
		   	}
		   	log.info("异常信息："+error);
			System.out.println("数据库连接异常");
			System.exit(1);
		}
	}

	public MongoDb() {
		try {
			this.ip = ClassConstants.MONGODB;
			this.port = ClassConstants.getMongoport();
			this.database = ClassConstants.DATABASE;
			MongoOptions opt = new MongoOptions();
			opt.connectionsPerHost = POOLSIZE;
			opt.threadsAllowedToBlockForConnectionMultiplier = BLOCKSIZE;
			opt.setAutoConnectRetry(true);
			opt.setConnectionsPerHost(100);
			opt.setConnectTimeout(100000);
			opt.setMaxWaitTime(130000);
			opt.setJ(true);
			opt.setFsync(true);
			opt.setMaxAutoConnectRetryTime(100);
			opt.setWtimeout(1000);
			ServerAddress sa = new ServerAddress(ip, port);
			m = new Mongo(sa, opt);
			db = m.getDB(database);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StringBuffer error = new StringBuffer();
			StackTraceElement[] messages = e.getStackTrace();
			int length = messages.length;
			for (int i = 0; i < length; i++) {
				error.append(messages[i]);
			}
			log.debug("异常信息：" + error);

			System.out.println("数据库连接异常");
			System.exit(1);
		}
	}

	/**
	 * 创建mongodb连接：带用户名和密码
	 * 
	 * @param ip
	 *            IP地址
	 * @param port
	 *            端口
	 * @param database
	 *            数据库
	 */
	public MongoDb(String ip, int port, String user, String pwd, String database) {
		// 创建连接
		try {
			this.ip = ip;
			this.port = port;
			this.database = database;

			m = new Mongo(ip, port);
			MongoOptions opt = m.getMongoOptions();
			opt.connectionsPerHost = POOLSIZE;
			opt.threadsAllowedToBlockForConnectionMultiplier = BLOCKSIZE;
			// 得到数据库
			db = m.getDB(database);
			db.authenticate(user, pwd.toCharArray());

		} catch (UnknownHostException e) {
			e.printStackTrace();
			StringBuffer error = new StringBuffer();
			StackTraceElement[] messages = e.getStackTrace();
			int length = messages.length;
			for (int i = 0; i < length; i++) {
				error.append(messages[i]);
			}
			log.debug("异常信息：" + error);

			System.out.println("数据库连接异常");
			System.exit(1);
		}
	}

	/**
	 * 创建mongodb连接:ipport 和 database
	 * 
	 * @param ip
	 *            IP地址
	 * @param port
	 *            端口
	 * @param database
	 *            数据库
	 */
	public MongoDb(String ipPort, String database) {
		// 创建连接
		try {
			m = new Mongo(ipPort.substring(0, ipPort.indexOf(":")),
					Integer.parseInt(ipPort.substring(ipPort.indexOf(":") + 1)));
			// 得到数据库
			db = m.getDB(database);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			StringBuffer error = new StringBuffer();
			StackTraceElement[] messages = e.getStackTrace();
			int length = messages.length;
			for (int i = 0; i < length; i++) {
				error.append(messages[i]);
			}
			log.debug("异常信息：" + error);

			System.out.println("数据库连接异常");
			System.exit(1);
		}
	}

	/**
	 * 创建mongodb连接: ip ，port
	 * 
	 * @param ip
	 *            IP地址
	 * @param port
	 *            端口
	 *
	 *
	 */

	public MongoDb(String ip, int port) {
		// 创建连接
		try {
			m = new Mongo(ip, port);
			this.ip = ip;
			this.port = port;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			StringBuffer error = new StringBuffer();
			StackTraceElement[] messages = e.getStackTrace();
			int length = messages.length;
			for (int i = 0; i < length; i++) {
				error.append(messages[i]);
			}
			log.debug("异常信息：" + error);
			System.out.println("数据库连接异常");
			System.exit(1);
		}
	}

	public DB getDb(String dbname) {
		return m.getDB(dbname);
	}

	public DBCollection getCollection(String db, String collection) {
		return m.getDB(db).getCollection(collection);
	}

	public DBCollection getCollection(String collection) {
		return db.getCollection(collection);
	}

	public boolean isExistCollection(String collection) {
		return db.collectionExists(collection);
	}

	/**
	 * 调用mapreduce函数 入参 基础表链接、map脚本、reduce脚本、临时表、查询条件
	 */
	public DBCollection mapReduce(DBCollection dbcoll, String map, String reduce, String temTable, DBObject query) {
		// 开始执行mapreduce
		DBCollection mapreduce_temp = null;
		try {
			MapReduceOutput mapReduceOutput = dbcoll.mapReduce(map, reduce, temTable, query);
			log.info("mapreduce输出：" + mapReduceOutput.toString());
			mapreduce_temp = mapReduceOutput.getOutputCollection();
		} catch (Exception e) {
			e.printStackTrace();
			StringBuffer error = new StringBuffer();
			StackTraceElement[] messages = e.getStackTrace();
			int length = messages.length;
			for (int i = 0; i < length; i++) {
				error.append(messages[i]);
			}
			log.debug("异常信息：" + error);
		}
		return mapreduce_temp;
	}


	/**
	 * 切换连接数据库
	 * 
	 * @param database
	 */
	public void changeDataBase(String database) {
		m.getDB(database);
	}

	public void close() {
		this.m.close();
	}

	/**
	 * 获取所有数据库
	 * 
	 * @return
	 */
	public List<String> getDataBase() {
		return m.getDatabaseNames();
	}

	/**
	 * 当前数据库中，存在的所有表(集合)
	 * 
	 * @return
	 */
	public Set<String> getCollectionNames() {
		return db.getCollectionNames();
	}

	/**
	 * 删除一个数据库
	 * 
	 * @param database
	 */
	public void delDataBase(String database) {
		// 禁用
		// m.dropDatabase(database);
	}

	/**
	 * 从db库中获取 表collection的索引信息
	 * 
	 * @param collection
	 * @return
	 */
	public List<DBObject> getCollectionIndex(String collection) {

		DBCollection coll = db.getCollection(collection);
		// 查看一个表的索引
		return coll.getIndexInfo();
	}

	/**
	 * 从db库中获取 表collection表的一条信息
	 * 
	 * @param collection
	 * @return
	 */
	public DBObject getCollectionOne(String collection) {
		DBCollection coll = db.getCollection(collection);
		return coll.findOne();
	}

	/**
	 * 插入json 其中 不支持[]方法
	 * 
	 * @param collection
	 * @param jsonString
	 */
	public void insert(String collection, String jsonString) {
		DBCollection coll = db.getCollection(collection);

		// BasicDBObject doc=new BasicDBObject();
		if (jsonString.startsWith("[")) {
			log.info("输入mongodb数据格式错误:" + jsonString);
		} else {
			DBObject dbObject = (DBObject) JSON.parse(jsonString);
			coll.insert(dbObject);
		}
		// String转json
		// JSONObject json = JSONObject.fromObject(jsonString);
		// 将json解析成对应的插入信息
	}

	/**
	 * 记录每个时间节点统计出的数据量
	 */
	public void insert_log(DBObject dbo_log, String collection, DB db) {
		db.getCollection(collection).insert(dbo_log);
	}

	public void insert(String collection, BasicDBObject doc) {
		// 添加

		DBCollection coll = db.getCollection(collection);
		//
		coll.insert(doc);
	}

	/**
	 * 通过 doc 去查询mongodb中的对应数据
	 * 
	 * @param collection
	 * @param doc
	 */
	public DBCursor find(String collection, BasicDBObject doc) {
		DBCollection coll = db.getCollection(collection);
		return coll.find(doc);
	}

	public DBCursor find(String collection, BasicDBObject doc, BasicDBObject doc2) {
		DBCollection coll = db.getCollection(collection);
		return coll.find(doc, doc2);
	}

	public DBCursor find(String collection) {
		DBCollection coll = db.getCollection(collection);
		return coll.find();
	}

	/**
	 * 通过 doc 去查询mongodb中的对应数据
	 * 
	 * @param collection
	 * @param doc
	 */
	public MongoDb findCursor(String collection[], BasicDBObject doc) {
		if (collection.length > 0) {
			DBCollection coll = db.getCollection(collection[0]);
			this.cursor = coll.find(doc);
			this.collection = collection;
			this.useCollectionIndex = 0;
		}
		return this;
	}

	public MongoDb findCursor(String collection[], BasicDBObject doc, BasicDBObject doc2) {
		if (collection.length > 0) {
			DBCollection coll = db.getCollection(collection[0]);
			this.cursor = coll.find(doc, doc2);
			this.collection = collection;
			this.useCollectionIndex = 0;
		}
		return this;
	}

	public MongoDb findCursor(String collection[], String ref, String obj) {
		if (collection.length > 0) {
			DBCollection coll = db.getCollection(collection[0]);
			this.cursor = coll.find((DBObject) JSON.parse(ref), (DBObject) JSON.parse(obj));
			this.collection = collection;
			this.useCollectionIndex = 0;
		}
		return this;
	}

	/**
	 * 更新 doc 为doc2
	 * 
	 * @param collection
	 * @param doc
	 * @param doc2
	 * @param upsert
	 *            如果 为true则表示不存在就添加 否则 只更新不添加
	 * @param multi
	 *            如果为true 则表示 值更新查找到的第一个 否则 为全部更新
	 */
	public void update(String collection, BasicDBObject doc, BasicDBObject doc2, boolean upsert, boolean multi) {
		DBCollection coll = db.getCollection(collection);
		coll.update(doc, doc2, upsert, multi);
	}

	/**
	 * 修改doc 到doc2 其中doc为数据库中最后一个doc
	 * 
	 * @param collection
	 * @param doc
	 * @param doc2
	 */
	public void update(String collection, BasicDBObject doc, BasicDBObject doc2) {
		DBCollection coll = db.getCollection(collection);
		coll.update(doc, doc2);
	}

	/**
	 * 插入list类
	 * 
	 * @param collection
	 * @param docList
	 */
	public void insertList(String collection, List<DBObject> docList) {
		DBCollection coll = db.getCollection(collection);
		coll.insert(docList);
	}

	/**
	 * 插入list joson
	 * 
	 * @param collection
	 * @param docList
	 */
	public void insertListJson(String collection, List<String> docList) {
		DBCollection coll = db.getCollection(collection);
		List<DBObject> list = new LinkedList<DBObject>();
		for (String str : docList) {
			DBObject dbObject = (DBObject) JSON.parse(str);
			list.add(dbObject);
		}
		coll.insert(list);
	}

	/**
	 * 插入map值
	 * 
	 * @param collection
	 * @param map
	 */
	public void insertMap(String collection, Map<Object, Object> map) {
		DBCollection coll = db.getCollection(collection);
		coll.insert(new BasicDBObject(map));
	}

	/**
	 * 找到并且删除
	 * 
	 * @param collection
	 * @param doc
	 */
	public DBObject findAndRemove(String collection, BasicDBObject doc) {
		DBCollection coll = db.getCollection(collection);
		return coll.findAndRemove(doc);
	}

	/**
	 * 通过查询语句查询 key=value
	 * 
	 * @param collection
	 * @param key
	 * @param value
	 * @return
	 */
	public DBCursor findAndCursor(String collection, String key, Object value) {
		DBCollection coll = db.getCollection(collection);
		return coll.find(new BasicDBObject(key, value));
	}

	/**
	 * 通过查询语句查询 key=value
	 * 
	 * @param collection
	 * @param key
	 * @param value
	 * @return
	 */
	public List<DBObject> find(String collection, String key, Object value) {
		DBCollection coll = db.getCollection(collection);
		return coll.find(new BasicDBObject(key, value)).toArray();
	}

	/**
	 * //查询age<=1 print("find: "+coll.find(new BasicDBObject("age", new
	 * BasicDBObject("$lte", 1))).toArray());
	 */
	public List<DBObject> findLte(String collection, String key, Object value) {
		DBCollection coll = db.getCollection(collection);
		return coll.find(new BasicDBObject(key, new BasicDBObject("$lte", value))).toArray();
	}

	/**
	 * //查询age>=1 print(" fint: "+coll.find(new BasicDBObject("age", new
	 * BasicDBObject("$gte", 1))).toArray());
	 * 
	 * @param collection
	 * @param key
	 * @param value
	 * @return
	 */
	public List<DBObject> findGte(String collection, String key, Object value) {
		DBCollection coll = db.getCollection(collection);
		return coll.find(new BasicDBObject(key, new BasicDBObject("$gte", value))).toArray();
	}

	/**
	 * //查询age！=1 print(" fint: "+coll.find(new BasicDBObject("age", new
	 * BasicDBObject("$ne", 1))).toArray());
	 */
	public List<DBObject> findNe(String collection, String key, Object value) {
		DBCollection coll = db.getCollection(collection);
		return coll.find(new BasicDBObject(key, new BasicDBObject("$ne", value))).toArray();
	}

	/**
	 * 查询age=1,2,3 print(" fint: "+coll.find(new BasicDBObject("age", new
	 * BasicDBObject(QueryOperators.IN ,new int[]{1,2,3}))).toArray());
	 */
	public List<DBObject> findIn(String collection, String key, Object value) {
		DBCollection coll = db.getCollection(collection);
		return coll.find(new BasicDBObject(key, new BasicDBObject(QueryOperators.IN, value))).toArray();
	}

	/**
	 * 查询age！=1,2,3 print("find: "+coll.find(new BasicDBObject("age" ,new
	 * BasicDBObject(QueryOperators.NIN ,new int[]{1,2,3}))).toArray());
	 */
	public List<DBObject> findNotIn(String collection, String key, Object value) {
		DBCollection coll = db.getCollection(collection);
		return coll.find(new BasicDBObject(key, new BasicDBObject(QueryOperators.NIN, value))).toArray();
	}

	/**
	 * 只要key不为空
	 * 
	 * @param collection
	 * @param key
	 * @return
	 */
	public List<DBObject> findExist(String collection, String key) {
		DBCollection coll = db.getCollection(collection);
		return coll.find(new BasicDBObject(key, new BasicDBObject(QueryOperators.EXISTS, true))).toArray();
	}







}
