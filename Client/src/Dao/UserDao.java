package Dao;


import com.ChatRoom.common.User;
//import com.qq.
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.qq.server.utils;

public class UserDao {
//	private UserBean user;
	public static PreparedStatement pstmt2; //预编译语句对象
//	 static boolean flag = false;
	public static boolean findUser(User user){
		//?为占位符
		String sql = "select * from client where id = ? AND password = ?";
		try {
			pstmt2 = DBUtil.getPstmt(sql);
			pstmt2.setString(1, user.getUserId()); //1是第一个问号
			pstmt2.setString(2, user.getPassword());
			ResultSet rs = pstmt2.executeQuery(); //执行查询语句
			if (rs.next()) {
				return true;
				//System.out.println("1");
			}
			/*else {
				//System.out.println("2");
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}