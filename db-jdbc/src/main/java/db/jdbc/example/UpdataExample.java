package db.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdataExample {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/employe";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		try {
			//加载类
			Class.forName(JDBC_DRIVER);
			//建立连接
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("连接数据库成功");
			//执行查询语句
			stat = conn.createStatement();
			String sql = "update user set age = 125 where id in (3,4) ";
			int result = stat.executeUpdate(sql);
			//处理返回结果
			System.out.println(result);
			//关闭资源（加finally{} 部分）
		} catch (Exception e) {
			System.err.println("数据库连接过程错误！");
		} finally {
			try {
				if (stat != null) {
					stat.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.err.println("关闭资源时出现错误！");
			}
		}
	}

}
