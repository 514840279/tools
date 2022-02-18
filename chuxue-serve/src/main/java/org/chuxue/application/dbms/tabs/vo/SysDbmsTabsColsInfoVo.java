package org.chuxue.application.dbms.tabs.vo;

import java.util.ArrayList;
import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.common.base.Pagination;

public class SysDbmsTabsColsInfoVo extends Pagination<SysDbmsTabsColsInfo> {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long	serialVersionUID	= -1098342939088321805L;
	private String				userindex;
	private String				uservalue;
	private String				jdbcUuid;
	private String				tabsUuid;
	private String				tabsName;
	private String				dbType;
	private String				esName;
	private String				tabsDesc;
	private Integer				total;
	private String				type;
	private String				paramString;
	private List<MulteityParam>	paramList			= new ArrayList<>();
	
	private String				mapString;
	
	/**
	 * 方法名 ： getUserindex
	 * 功 能 ： 返回变量 userindex 的值
	 *
	 * @return: String
	 */
	public String getUserindex() {
		return userindex;
	}
	
	/**
	 * 方法名 ： setUserindex
	 * 功 能 ： 设置变量 userindex 的值
	 */
	public void setUserindex(String userindex) {
		this.userindex = userindex;
	}
	
	/**
	 * 方法名 ： getUservalue
	 * 功 能 ： 返回变量 uservalue 的值
	 *
	 * @return: String
	 */
	public String getUservalue() {
		return uservalue;
	}
	
	/**
	 * 方法名 ： setUservalue
	 * 功 能 ： 设置变量 uservalue 的值
	 */
	public void setUservalue(String uservalue) {
		this.uservalue = uservalue;
	}
	
	/**
	 * 方法名 ： getDbType
	 * 功 能 ： 返回变量 dbType 的值
	 *
	 * @return: String
	 */
	public String getDbType() {
		return dbType;
	}
	
	/**
	 * 方法名 ： setDbType
	 * 功 能 ： 设置变量 dbType 的值
	 */
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	
	/**
	 * 方法名 ： getEsName
	 * 功 能 ： 返回变量 esName 的值
	 *
	 * @return: String
	 */
	public String getEsName() {
		return esName;
	}
	
	/**
	 * 方法名 ： setEsName
	 * 功 能 ： 设置变量 esName 的值
	 */
	public void setEsName(String esName) {
		this.esName = esName;
	}
	
	/**
	 * 方法名 ： getTotal
	 * 功 能 ： 返回变量 total 的值
	 *
	 * @return: Integer
	 */
	public Integer getTotal() {
		return total;
	}
	
	/**
	 * 方法名 ： setTotal
	 * 功 能 ： 设置变量 total 的值
	 */
	public void setTotal(Integer total) {
		this.total = total;
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
	 * 方法名 ： getParamString
	 * 功 能 ： 返回变量 paramString 的值
	 *
	 * @return: String
	 */
	public String getParamString() {
		return paramString;
	}
	
	/**
	 * 方法名 ： setParamString
	 * 功 能 ： 设置变量 paramString 的值
	 */
	public void setParamString(String paramString) {
		this.paramString = paramString;
	}
	
	/**
	 * 方法名 ： getParamList
	 * 功 能 ： 返回变量 paramList 的值
	 *
	 * @return: List<MulteityParam>
	 */
	public List<MulteityParam> getParamList() {
		return paramList;
	}
	
	/**
	 * 方法名 ： setParamList
	 * 功 能 ： 设置变量 paramList 的值
	 */
	public void setParamList(List<MulteityParam> paramList) {
		this.paramList = paramList;
	}
	
	/**
	 * 方法名 ： getMapString
	 * 功 能 ： 返回变量 mapString 的值
	 *
	 * @return: String
	 */
	public String getMapString() {
		return mapString;
	}
	
	/**
	 * 方法名 ： setMapString
	 * 功 能 ： 设置变量 mapString 的值
	 */
	public void setMapString(String mapString) {
		this.mapString = mapString;
	}
	
	/**
	 * 方法名 ： getTabsUuid
	 * 功 能 ： 返回变量 tabsUuid 的值
	 *
	 * @return: String
	 */
	public String getTabsUuid() {
		return tabsUuid;
	}
	
	/**
	 * 方法名 ： setTabsUuid
	 * 功 能 ： 设置变量 tabsUuid 的值
	 */
	public void setTabsUuid(String tabsUuid) {
		this.tabsUuid = tabsUuid;
	}
	
	/**
	 * 方法名 ： getTabsName
	 * 功 能 ： 返回变量 tabsName 的值
	 *
	 * @return: String
	 */
	public String getTabsName() {
		return tabsName;
	}
	
	/**
	 * 方法名 ： setTabsName
	 * 功 能 ： 设置变量 tabsName 的值
	 */
	public void setTabsName(String tabsName) {
		this.tabsName = tabsName;
	}
	
	/**
	 * 方法名 ： getTabsDesc
	 * 功 能 ： 返回变量 tabsDesc 的值
	 *
	 * @return: String
	 */
	public String getTabsDesc() {
		return tabsDesc;
	}
	
	/**
	 * 方法名 ： setTabsDesc
	 * 功 能 ： 设置变量 tabsDesc 的值
	 */
	public void setTabsDesc(String tabsDesc) {
		this.tabsDesc = tabsDesc;
	}

	/**
	 * 方法名 ： toString
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @return
	 * 参 考 ： @see java.lang.Object#toString()
	 * 作 者 ： wth
	 */
	
	@Override
	public String toString() {
		return "SysDbmsTabsColsInfoVo [userindex=" + userindex + ", uservalue=" + uservalue + ", jdbcUuid=" + jdbcUuid + ", tabsUuid=" + tabsUuid + ", tabsName=" + tabsName + ", dbType=" + dbType + ", esName=" + esName + ", tabsDesc=" + tabsDesc + ", total=" + total + ", type=" + type + ", paramString=" + paramString + ", paramList=" + paramList + ", mapString=" + mapString + "]";
	}

	/**
	 * @方法名 getJdbcUuid
	 * @功能 返回变量 jdbcUuid 的值
	 * @return String
	 */
	public String getJdbcUuid() {
		return jdbcUuid;
	}
	
	/**
	 * @方法名 setJdbcUuid
	 * @功能 设置变量 jdbcUuid 的值
	 */
	public void setJdbcUuid(String jdbcUuid) {
		this.jdbcUuid = jdbcUuid;
	}
	
}