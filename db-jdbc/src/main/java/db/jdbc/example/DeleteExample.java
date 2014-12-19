package db.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteExample {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/employe";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static final Logger logger = LoggerFactory.getLogger(InsertExample.class);

	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		try {
			//加载类
			Class.forName(JDBC_DRIVER);
			//建立连接
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			logger.info("连接数据库成功");
			//执行查询语句
			stat = conn.createStatement();
			String sql = "delete from user where id in (4,5) ";
			int result = stat.executeUpdate(sql);
			//处理返回结果
			System.out.println(result);
			//关闭资源（加finally{} 部分）
		} catch (Exception e) {
			logger.info("数据库连接过程错误！");
		} finally {
			try {
				if (stat != null) {
					stat.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error("关闭资源时出现错误！");
			}
		}
	}
}
