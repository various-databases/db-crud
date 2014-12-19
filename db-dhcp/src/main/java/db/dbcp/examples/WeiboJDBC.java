package db.dbcp.examples;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class WeiboJDBC {

	private String db_driver;
	private String db_user;
	private String db_password;
	private String db_name;
	private String db_ip;
	private String db_port;
	private final String db_url;

	private BasicDataSource dataSource;

	/**
	 * 线上环境
	 */
	public WeiboJDBC() {
		dbInit();
		db_url = "jdbc:mysql://" + db_ip + ":" + db_port + "/" + db_name + "?useUnicode=true&characterEncoding=utf-8";
		dbConnection();
	}

	/**
	 * 本地环境
	 */
	public WeiboJDBC(String dbip, String dbuser, String dbpassword, String dbname) {
		dbInit();
		db_user = dbuser;
		db_password = dbpassword;
		db_url = "jdbc:mysql://" + dbip + ":3306/" + dbname + "?useUnicode=true&characterEncoding=utf-8";
		dbConnection();
	}

	/**
	 * 初始化数据库相关参数
	 */
	private void dbInit() {
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("weibo_db.properties"));
			db_driver = props.getProperty("db.driver");
			db_user = props.getProperty("db.user");
			db_password = props.getProperty("db.password");
			db_name = props.getProperty("db.name");
			db_ip = props.getProperty("db.ip");
			db_port = props.getProperty("db.port");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 链接数据库
	 */
	private void dbConnection() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername(db_user);
		dataSource.setPassword(db_password);
		dataSource.setUrl(db_url);
		dataSource.setTestOnBorrow(true);
		dataSource.setValidationQuery("select 1");
	}

	/**
	 * 获取链接
	 */
	private Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭数据库
	 */
	public void dbClose() {
		try {
			dataSource.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public String toString() {
		return new StringBuffer().append("Db driver: " + db_driver + "\n").append("Db user: " + db_user + "\n")
				.append("Db password: " + db_password + "\n").append("Db name: " + db_name + "\n")
				.append("Db ip: " + db_ip + "\n").append("Db port: " + db_port).toString();
	}

	/**
	 * 创建博主扩展数据表，目前是用户标签数据
	 */
	public void createExtendTable(String tablename) throws SQLException {

		String sql = "CREATE TABLE " + tablename + " (`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',"
				+ "`username` bigint(20) unsigned NOT NULL COMMENT '用户名',"
				+ "`tags` varchar(1000) NOT NULL COMMENT '标签数据',`isppuser` tinyint(1) NOT NULL COMMENT '是否是皮皮用户',"
				+ "`isreal` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是真实用户',"
				+ "PRIMARY KEY (`id`),KEY `username` (`username`)) "
				+ "ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='新浪用户扩展数据表' AUTO_INCREMENT=1 ;";
		try (Connection conn = getConnection(); Statement pstmt = conn.createStatement();) {
			pstmt.execute(sql);
		}
	}

	/**
	 * 创建用户粉丝分析数据表
	 */
	public void createFansAnalysisTable(String tablename) throws SQLException {

		String sql = "CREATE TABLE " + tablename + " (`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',"
				+ "`username` bigint(20) unsigned NOT NULL COMMENT '用户名'," + "`result` text NOT NULL COMMENT '分析结果',"
				+ "`lasttime` int(10) unsigned NOT NULL COMMENT '最后纪录时间',"
				+ "PRIMARY KEY (`id`),KEY `username` (`username`)) "
				+ "ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='粉丝分析结果数据表' AUTO_INCREMENT=1 ;";
		try (Connection conn = getConnection(); Statement statement = conn.createStatement();) {
			statement.execute(sql);
		}
	}

}