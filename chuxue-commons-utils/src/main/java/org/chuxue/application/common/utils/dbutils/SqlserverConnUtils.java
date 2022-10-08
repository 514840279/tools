package org.chuxue.application.common.utils.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 */
public class SqlserverConnUtils {

	private static final Logger logger = LoggerFactory.getLogger(SqlserverConnUtils.class);
	
	// 1
	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			logger.error("驱动错误:{}", e.getMessage());
		}
	}
	
	/*
	 * 	URL			= "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=jsp";
	 *	USER		= "sa";
	 *	PASSWORD	= ".";
	 *
	 */
	public static Connection getConnection(String url, String user, String password) {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			logger.error("错误:{}", e.getMessage());
		}
		return null;
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error("错误:{}", e.getMessage());
		}
	}
	
	public static void close(Statement state) {
		try {
			if (state != null) {
				state.close();
			}
		} catch (SQLException e) {
			logger.error("错误:{}", e.getMessage());
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.error("错误:{}", e.getMessage());
		}
	}
}
