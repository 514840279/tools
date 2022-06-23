package org.chuxue.application.dbms.tabs.po;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysDbmsTabsColumnResult {

	private String	uuid;
	
	// 表id
	private String	tabsUuid;

	// 字段含义
	private String	colsDesc;

	// 字段名
	private String	colsName;

	// 字段长度
	private Integer	colsLength;
	
	// 数据类型（varchar,number,text） 相比字段类型更加规整
	private String	dataType;
	
	// 字段类型（varchar,number,text）
	private String	colsType;

	// 允许空
	private String	nullable;

	// 数据排序
	private Integer	sort;

	// 数据精度 代表有小数位
	private Integer	dataScale;
}
