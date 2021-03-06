package org.chuxue.application.dbms.appl.vo;

import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件名 ： SysApplTypeTabsInfoVo.java
 * 包 名 ： org.chuxue.application.dbms.appl.vo
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年7月19日 下午2:49:10
 * 版 本 ： V1.0
 */
@Setter
@Getter
public class SysApplTypeTabsInfoVo {

	private String	uuid;
	// 表id
	private String	tabsUuid;
	
	// 类型代码
	private String	typeCode;

	// 数据库表名
	private String	applCode;
	
	// 数据库表名
	private String	tabsName;

	// 表的含义
	private String	tabsDesc;

	private String	checkboxType;
	
	private String	tabsRowsType;
	
	private Integer	sort;
	
	private String	jdbcUuid;
	private Integer	deleteFlag;
	private Integer	tabsRows;
	
	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数：
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysApplTypeTabsInfoVo() {
	}

	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param map
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysApplTypeTabsInfoVo(Map<String, Object> map) {
		this.uuid = map.get("uuid") != null ? map.get("uuid").toString() : UUID.randomUUID().toString();
		this.tabsUuid = map.get("tabs_uuid") != null ? map.get("tabs_uuid").toString() : null;
		this.typeCode = map.get("type_code") != null ? map.get("type_code").toString() : null;
		this.tabsName = map.get("tabs_name") != null ? map.get("tabs_name").toString() : null;
		this.tabsDesc = map.get("tabs_desc") != null ? map.get("tabs_desc").toString() : null;
		this.sort = map.get("sort") != null ? Integer.parseInt(map.get("sort").toString()) : null;
		this.checkboxType = map.get("checkbox_type") != null ? map.get("checkbox_type").toString() : null;
		this.tabsRowsType = map.get("tabs_rows_type") != null ? map.get("tabs_rows_type").toString() : "multi-line";
		this.jdbcUuid = map.get("jdbc_uuid") != null ? map.get("jdbc_uuid").toString() : null;
		this.deleteFlag = map.get("delete_flag") != null ? Integer.parseInt(map.get("delete_flag").toString()) : null;
		this.tabsRows = map.get("tabs_rows") != null ? Integer.parseInt(map.get("tabs_rows").toString()) : null;
	}
	
}
