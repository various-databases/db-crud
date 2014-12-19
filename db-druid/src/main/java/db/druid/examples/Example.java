package db.druid.examples;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidPooledConnection;

public class Example {

	public void executeUpdateBySQL(String sql) throws SQLException {
		DbPoolConnection dbp = DbPoolConnection.getInstance();
		DruidPooledConnection con = dbp.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
		con.close();
		dbp = null;
	}

}
