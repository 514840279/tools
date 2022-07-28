package org.chuxue.application.dbms.tabs.vo;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件名 ： SysDbmsTabsTableInfoVo.java
 * 包 名 ： org.chuxue.application.dbms.tabs.vo
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年7月28日 上午10:41:13
 * 版 本 ： V1.0
 */
@Setter
@Getter
public class SysDbmsTabsTableInfoVo extends SysDbmsTabsTableInfo {
	
	// 数据库表空间大小
	private String tabsSpace;

	// 数据库表名
	private String tabsName;

	// 表的含义
	private String tabsDesc;

	// 数据库表数据量
	private Integer tabsRows;

	// 数据库表类型code
	private String typeCode;

	// 数据库表id
	private String jdbcUuid;
}
