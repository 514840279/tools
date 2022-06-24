package org.chuxue.application.dbms.tabs.po;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件名 ： SysDbmsTabsInfoResult.java
 * 包 名 ： org.danyuan.application.dbms.tabs.po
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2021年10月28日 下午4:16:17
 * 版 本 ： V1.0
 */

@Setter
@Getter
public class SysDbmsTabsInfoResult {
	
	private String	uuid;

	// 数据库表空间大小
	private Integer	tabsSpace;
	
	// 数据库表id
	private String	jdbcUuid;
	
	// 数据库表数据量
	private Integer	tabsRows;
	
	// 数据库表名
	private String	tabsName;
	
	// 表的含义
	private String	tabsDesc;
}
