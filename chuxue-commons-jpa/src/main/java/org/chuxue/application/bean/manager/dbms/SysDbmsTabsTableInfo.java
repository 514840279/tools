package org.chuxue.application.bean.manager.dbms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.chuxue.application.common.base.BaseEntity;

/**
 * @文件名 SysDbmsTabsInfo.java
 * @包名 org.danyuan.application.dbms.tabs.po
 * @描述 sys_dbms_tabs_info的实体类
 * @时间 2020年04月25日 12:15:38
 * @author test
 * @版本 V1.0
 */
@Entity
@Table(name = "sys_dbms_tabs_table_info")
@NamedQuery(name = "SysDbmsTabsTableInfo.findAll", query = "SELECT s FROM SysDbmsTabsTableInfo s")
public class SysDbmsTabsTableInfo extends BaseEntity {

	// 数据库表空间大小
	@Column(name = "tabs_space")
	private String	tabsSpace;

	// 数据库表名
	@Column(name = "tabs_name")
	private String	tabsName;

	// 表的含义
	@Column(name = "tabs_desc")
	private String	tabsDesc;

	// 数据库表数据量
	@Column(name = "tabs_rows", precision = 10)
	private Integer	tabsRows;

	// 数据库表类型code
	@Column(name = "type_code")
	private String	typeCode;

	// 数据库表id
	@Column(name = "jdbc_uuid")
	private String	jdbcUuid;

	/**
	 * 构造方法：
	 * 描 述： 默认构造函数
	 * 参 数：
	 * 作 者 ： test
	 * @throws
	 */
	public SysDbmsTabsTableInfo() {
	}

	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param tabsSpace
	 * 参 数： @param tabsName
	 * 参 数： @param tabsDesc
	 * 参 数： @param tabsRows
	 * 参 数： @param typeUuid
	 * 参 数： @param dbType
	 * 参 数： @param jdbcUuid
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysDbmsTabsTableInfo(String uuid, String jdbcUuid, String tabsName, Integer tabsRows) {
		super.uuid = uuid;
		this.jdbcUuid = jdbcUuid;
		this.tabsName = tabsName;
		this.tabsRows = tabsRows;
	}

	/**
	 * 方法名 ： getTabsSpace
	 * 功 能 ： 返回变量 tabsSpace 的值
	 *
	 * @return: String
	 */
	public String getTabsSpace() {
		return tabsSpace;
	}

	/**
	 * 方法名 ： setTabsSpace
	 * 功 能 ： 设置变量 tabsSpace 的值
	 */
	public void setTabsSpace(String tabsSpace) {
		this.tabsSpace = tabsSpace;
	}

	/**
	 * 方法名 ： getTabsName
	 * 功 能 ： 返回变量 tabsName 数据库表名 的值
	 *
	 * @return: String
	 */
	public String getTabsName() {
		return tabsName;
	}

	/**
	 * 方法名 ： setTabsName
	 * 功 能 ： 设置变量 tabsName 数据库表名 的值
	 */
	public void setTabsName(String tabsName) {
		this.tabsName = tabsName;
	}

	/**
	 * 方法名 ： getTabsDesc
	 * 功 能 ： 返回变量 tabsDesc 表的含义 的值
	 *
	 * @return: String
	 */
	public String getTabsDesc() {
		return tabsDesc;
	}

	/**
	 * 方法名 ： setTabsDesc
	 * 功 能 ： 设置变量 tabsDesc 表的含义 的值
	 */
	public void setTabsDesc(String tabsDesc) {
		this.tabsDesc = tabsDesc;
	}
	
	/**
	 * 方法名 ： getTabsRows
	 * 功 能 ： 返回变量 tabsRows 数据库表数据量 的值
	 *
	 * @return: String
	 */
	public Integer getTabsRows() {
		return tabsRows;
	}

	/**
	 * 方法名 ： setTabsRows
	 * 功 能 ： 设置变量 tabsRows 数据库表数据量 的值
	 */
	public void setTabsRows(Integer tabsRows) {
		this.tabsRows = tabsRows;
	}
	
	/**
	 * 方法名 ： getJdbcUuid
	 * 功 能 ： 返回变量 jdbcUuid 数据库表id 的值
	 *
	 * @return: String
	 */
	public String getJdbcUuid() {
		return jdbcUuid;
	}

	/**
	 * 方法名 ： setJdbcUuid
	 * 功 能 ： 设置变量 jdbcUuid 数据库表id 的值
	 */
	public void setJdbcUuid(String jdbcUuid) {
		this.jdbcUuid = jdbcUuid;
	}

	/**
	 * 方法名 ： getTypeCode
	 * 功 能 ： 返回变量 typeCode 的值
	 *
	 * @return: String
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * 方法名 ： setTypeCode
	 * 功 能 ： 设置变量 typeCode 的值
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	/**
	 * 方法名 ： toString
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @return
	 * 参 考 ： @see java.lang.Object#toString()
	 * 作 者 ： Administrator
	 */

	@Override
	public String toString() {
		return "SysDbmsTabsTableInfo [tabsSpace=" + tabsSpace + ", tabsName=" + tabsName + ", tabsDesc=" + tabsDesc + ", tabsRows=" + tabsRows + ", typeCode=" + typeCode + ", jdbcUuid=" + jdbcUuid + "]";
	}

}
