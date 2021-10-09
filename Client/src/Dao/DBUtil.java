package Dao;

import java.sql.*;

/**
 * 连接数据库的工具 (尽量用单例模式)
 *
 *
 */
public class DBUtil {
	private static final String USER = "root";
	private static final String PWD = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/sys";
	private static Statement stmt;
	private static Connection con;
	private static DBUtil utils = null;
	private static PreparedStatement pstmt; // 预编译语句对象

	private DBUtil() {

	}

//	 不是线程安全的 如果有并发访问实例化的时候会出现线程安全的问题，解决办法加同步锁synchronized
//	public synchronized static com.qq.utils.DBUtil getDBUtil() {
//		if (utils == null) {
//			utils = new com.qq.utils.DBUtil().DBUtil();
//			return utils;
//		}
//		return utils;
//	}

	public static Connection getConn() {
		if (con == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(URL, USER, PWD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	public static Statement getStatement() {
		if (stmt == null) {
			try {
				if (con == null) {
					con = getConn();
				}
				stmt = con.createStatement();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return stmt;
	}

	// 预编译语句对象
	public static PreparedStatement getPstmt(String sql){
		if (pstmt == null) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(URL, USER, PWD);
				pstmt = con.prepareStatement(sql);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		else {
			try {
				pstmt = con.prepareStatement(sql);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return pstmt;
	}
}


//package utils;
//public class DbUtil {
//
//	//数据库地址
//	private String dbUrl = "jdbc:mysql://localhost:3306/db_book";
//
////用户名
//
//	private String dbUserName = "root";
//
////密码
//
//	private String dbPassword = "123456";
//
////驱动名称
//
//	private String jdbcName = "com.mysql.jdbc.Driver";
//
//	// 获取数据库连接
//	public Connection getCon() throws Exception {
//		Class.forName(jdbcName);
//		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
//		return con;
//
//	}
//
//	// 关闭数据库连接
//	public void closeCon(Statement smt  , Connection con) throws Exception {
//		if (smt != null) {
//			smt.close();
//		}
//
//		if (con != null) {
//			con.close();
//		}
//
//	}
//
//
//	// 关闭数据库连接
//	public void closeCon(Connection con) throws Exception {
//
//		if (con != null) {
//			con.close();
//		}
//
//	}
//
//
//
//
//
//
//}