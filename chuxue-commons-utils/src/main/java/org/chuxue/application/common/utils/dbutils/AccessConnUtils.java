package org.chuxue.application.common.utils.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: javalearning
 * @Date: 2018/7/11 11:03
 * @Author: hyman.hu
 * @Description: 工具类
 */
public class AccessConnUtils {
	private static final Logger logger = LoggerFactory.getLogger(AccessConnUtils.class);
	
	/*
	 * 加载驱动
	 */
	static {
		try {
			// Step 1: Loading or registering Oracle JDBC driver class
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException cnfex) {
			logger.error("Problem in loading or registering MS Access JDBC driver:{}", cnfex.getMessage());
		}
	}
	
	public static Connection getConnection(String filepath) {
		try {
			return DriverManager.getConnection("jdbc:ucanaccess://" + filepath);
		} catch (SQLException e) {
			logger.error("路径错误:{}", e.getMessage());
		}
		return null;
	}

	// 关闭资源
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();// 这里出现异常了，rs关闭了吗？，如果没有怎么解决,ps , con也是一样的。
			}
		} catch (SQLException e) {
			logger.error("错误:{}", e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				logger.error("错误:{}", e.getMessage());
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						logger.error("错误:{}", e.getMessage());
					}
				}
			}
		}
	}
}
