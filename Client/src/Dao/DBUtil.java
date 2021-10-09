package Dao;

import java.sql.*;

/**
 * �������ݿ�Ĺ��� (�����õ���ģʽ)
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
	private static PreparedStatement pstmt; // Ԥ����������

	private DBUtil() {

	}

//	 �����̰߳�ȫ�� ����в�������ʵ������ʱ�������̰߳�ȫ�����⣬����취��ͬ����synchronized
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

	// Ԥ����������
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
//	//���ݿ��ַ
//	private String dbUrl = "jdbc:mysql://localhost:3306/db_book";
//
////�û���
//
//	private String dbUserName = "root";
//
////����
//
//	private String dbPassword = "123456";
//
////��������
//
//	private String jdbcName = "com.mysql.jdbc.Driver";
//
//	// ��ȡ���ݿ�����
//	public Connection getCon() throws Exception {
//		Class.forName(jdbcName);
//		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
//		return con;
//
//	}
//
//	// �ر����ݿ�����
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
//	// �ر����ݿ�����
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