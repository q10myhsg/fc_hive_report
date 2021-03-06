package com.fangcheng.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class MysqlDb {

	private String jdbcDriver = "com.mysql.jdbc.Driver"; // 数据库驱动
    private String dbUrl = "jdbc:mysql://127.0.0.1:3306/fangcheng"; // 数据 URL
    private String dbUsername = "fangcheng_admin"; // 数据库用户名
    private String dbPassword = "fc1234"; // 数据库用户密码
    private Connection conn = null;
    private ResultSet result = null;
    private Statement statement = null;
    
    private int lport = 3306;//本地端口
    private String rhost = "127.0.0.1";//远程MySQL服务器
    private int rport = 3306;//远程MySQL服务端口
    private JSch jsch = new JSch();
    private Session session = null;
    
    public MysqlDb(String jdbcDriver,String dbUrl,String dbUsername,String dbPassword){
    	this.jdbcDriver = jdbcDriver;
    	this.dbUrl = dbUrl;
    	this.dbUsername = dbUsername;
    	this.dbPassword = dbPassword;
    }
    public MysqlDb(String ip,int port,String database,String dbUsername,String dbPassword){
    	this.dbUrl = "jdbc:mysql://"+ip+":"+port+"//"+database;
    	this.dbUsername = dbUsername;
    	this.dbPassword = dbPassword;
    }
    /**
     * 获取ssh链接
     */
	public void openssh() {
		String user = "root";//SSH连接用户名
		String password = "uz%61tIAy";//SSH连接密码
		//password="v9{T7qcyQ";
		String host = "123.57.141.237";//SSH服务器
		//host="123.56.113.168";
		int port = 22;//SSH访问端口
		try {
			session = jsch.getSession(user, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
			int assinged_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 关闭ssh
	 */
	public void closessh() {
		if(null != session)
			session.disconnect();
	}
    /**
     * 获取数据库链接
     */
	public Connection getConnection(){
		try {
			Class.forName(jdbcDriver).newInstance();
			System.out.println(dbUrl);
			System.out.println(dbUsername);
			System.out.println(dbPassword);
			conn = DriverManager.getConnection(dbUrl, dbUsername,dbPassword);
			System.out.println(conn.isClosed());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
    
	public ResultSet sqlSelect(String sqlString) {
		try {
			statement = conn.createStatement();
			// 要执行的SQL语句
			result = statement.executeQuery(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void close(){
		try {
			if(null != result)
				result.close();
			if(null != statement)
				statement.close();
			if(null != conn)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新
	 * 
	 * @param sqlString
	 * @return
	 */
	public boolean sqlUpdate(String sqlString) {
		boolean flag = false;
		Statement statement = null;
		try {
			// statement用来执行SQL语句

			statement = conn.createStatement();
			statement.executeUpdate(sqlString);
			statement.close();
			//conn.commit();
			flag = true;
		} catch (Exception e) {
			// log.error(e.getMessage());
			e.printStackTrace();
		}

		// log.info("执行完成");
		return flag;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MysqlDb my = new MysqlDb("com.mysql.jdbc.Driver","jdbc:mysql://192.168.1.134/fangcheng","fangcheng_admin","fc1234");
		Connection conn = my.getConnection();
		if(conn == null)
			System.out.println("11111111111111111111111");
		else
			System.out.println("222222222222");
	}

}
