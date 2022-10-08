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
public class MysqlConnUtils {
	private static final Logger logger = LoggerFactory.getLogger(MysqlConnUtils.class);
	
	// 1
	static {
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			logger.error("驱动错误:{}", e.getMessage());
		}
	}
	
	/*
	 * URL		= "jdbc:mysql:///application?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false";
	 * USER		= "root";
	 * PASSWORD	= "514840279@qq.com";

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
