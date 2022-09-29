package org.chuxue.application.dbms.tabs.po;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件名 ： SysDbmsTabsIndexInfo.java
 * 包 名 ： org.chuxue.application.dbms.tabs.po
 * 描 述 ： 表索引信息
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年9月29日 上午10:12:36
 * 版 本 ： V1.0
 */
@Setter
@Getter
public class SysDbmsTabsIndexResult {

	private String	tableSchema;
	private String	tableName;
	private String	nonUnique;
	private String	indexSchema;
	private String	indexName;
	private String	columnName;
	private String	nullable;
	private String	indexType;
	private String	indexComment;
	private String	tablespaceName;
	
}
