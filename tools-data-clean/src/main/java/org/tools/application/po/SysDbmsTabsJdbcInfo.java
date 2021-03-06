package org.tools.application.po;

import java.io.Serializable;

import org.tools.application.common.bean.BaseEntity;

/**
 * @文件名 SysDbmsTabsJdbcInfo.java
 * @包名 org.danyuan.application.dbms.tabs.po
 * @描述 sys_dbms_tabs_jdbc_info的实体类
 * @时间 2020年04月25日 12:15:39
 * @author test
 * @版本 V1.0
 */
public class SysDbmsTabsJdbcInfo extends BaseEntity implements Serializable {
	private static final long	serialVersionUID	= 1L;

	// 数据库驱动类
	private String				driver;

	// 用户名称
	private String				username;

	// 平台
	private String				platform;

	// 数据库名称
	private String				databaseName;

	// 端口号
	private String				port;

	// 分类 ，标识数据库的用途
	private String				type;

	// ip地址
	private String				ip;

	// 用户密码
	private String				password;

	/**
	 * 构造方法：
	 * 描 述： 默认构造函数
	 * 参 数：
	 * 作 者 ： test
	 * @throws
	 */
	public SysDbmsTabsJdbcInfo() {
		super();
	}

	/**
	 * 方法名 ： getDriver
	 * 功 能 ： 返回变量 driver 数据库驱动类 的值
	 *
	 * @return: String
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * 方法名 ： setDriver
	 * 功 能 ： 设置变量 driver 数据库驱动类 的值
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * 方法名 ： getUsername
	 * 功 能 ： 返回变量 username 用户名称 的值
	 *
	 * @return: String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 方法名 ： setUsername
	 * 功 能 ： 设置变量 username 用户名称 的值
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 方法名 ： getPlatform
	 * 功 能 ： 返回变量 platform 的值
	 *
	 * @return: String
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * 方法名 ： setPlatform
	 * 功 能 ： 设置变量 platform 的值
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	 * 方法名 ： getDatabaseName
	 * 功 能 ： 返回变量 databaseName 数据库名称 的值
	 *
	 * @return: String
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * 方法名 ： setDatabaseName
	 * 功 能 ： 设置变量 databaseName 数据库名称 的值
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * 方法名 ： getPort
	 * 功 能 ： 返回变量 port 端口号 的值
	 *
	 * @return: String
	 */
	public String getPort() {
		return port;
	}

	/**
	 * 方法名 ： setPort
	 * 功 能 ： 设置变量 port 端口号 的值
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * 方法名 ： getIp
	 * 功 能 ： 返回变量 ip ip地址 的值
	 *
	 * @return: String
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 方法名 ： setIp
	 * 功 能 ： 设置变量 ip ip地址 的值
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 方法名 ： getPassword
	 * 功 能 ： 返回变量 password 用户密码 的值
	 *
	 * @return: String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 方法名 ： setPassword
	 * 功 能 ： 设置变量 password 用户密码 的值
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 方法名 ： getType
	 * 功 能 ： 返回变量 type 的值
	 *
	 * @return: String
	 */
	public String getType() {
		return type;
	}

	/**
	 * 方法名 ： setType
	 * 功 能 ： 设置变量 type 的值
	 */
	public void setType(String type) {
		this.type = type;
	}

}
