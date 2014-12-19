package db.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectExample {

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
			//执行查询语句
			stat = conn.createStatement();
			String sql = "select id,age,first,last from user";
			ResultSet rs = stat.executeQuery(sql);
			//处理返回结果
			while (rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");
				System.out.println(first + " " + last + "  id :" + id + "  age:" + age);
			}
			//关闭资源（加finally{} 部分）
			rs.close();
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
