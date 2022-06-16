package org.chuxue.application.bean.manager.dbms;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.chuxue.application.common.base.BaseEntity;

/**
 * @文件名 SysDbmsTabsJdbcInfo.java
 * @包名 org.danyuan.application.dbms.tabs.po
 * @描述 sys_dbms_tabs_jdbc_info的实体类
 * @时间 2020年04月25日 12:15:39
 * @author test
 * @版本 V1.0
 */
@Entity
@Table(name = "sys_dbms_tabs_jdbc_info")
@NamedQuery(name = "SysDbmsTabsJdbcInfo.findAll", query = "SELECT s FROM SysDbmsTabsJdbcInfo s")
public class SysDbmsTabsJdbcInfo extends BaseEntity implements Serializable {
	private static final long	serialVersionUID	= 1L;

	// 分类 ，标识数据库的用途
	@Column(name = "type")
	private String				type;

	// 平台
	@Column(name = "platform")
	private String				platform;

	// 数据库名称
	@Column(name = "database_name")
	private String				databaseName;

	// ip地址
	@Column(name = "ip")
	private String				ip;

	// 端口号
	@Column(name = "port")
	private String				port;

	@Column(name = "app_name")
	private String				appName;

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
	
	/**
	 * 方法名 ： getAppName
	 * 功 能 ： 返回变量 appName 的值
	 *
	 * @return: String
	 */
	public String getAppName() {
		return appName;
	}
	
	/**
	 * 方法名 ： setAppName
	 * 功 能 ： 设置变量 appName 的值
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
