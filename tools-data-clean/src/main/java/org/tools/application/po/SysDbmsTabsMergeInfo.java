package org.tools.application.po;

import java.io.Serializable;

import org.tools.application.common.BaseEntity;


/**
 * @文件名 SysDbmsTabsMergeInfo.java
 * @包名 org.danyuan.application.dbms.tabs.po
 * @描述 sys_dbms_tabs_merge_info的实体类
 * @时间 2020年01月03日 15:42:38
 * @author test
 * @版本 V1.0
 */
public class SysDbmsTabsMergeInfo extends BaseEntity implements Serializable {
	private static final long	serialVersionUID	= 1L;
	
	//
	private String				colsName2;
	
	//
	private String				colsUuid2;

	//
	private String				tableUuid2;
	
	//
	private String				colsDesc2;
	
	//
	private String				colsName1;
	
	//
	private String				colsDesc1;
	//
	private String				colsUuid1;

	//
	private String				tableUuid1;
	
	/**
	 * 构造方法：
	 * 描 述： 默认构造函数
	 * 参 数：
	 * 作 者 ： test
	 * @throws
	 */
	public SysDbmsTabsMergeInfo() {
		super();
	}
	
	/**
	 * 方法名 ： getColsName2
	 * 功 能 ： 返回变量 colsName2 的值
	 *
	 * @return: String
	 */
	public String getColsName2() {
		return colsName2;
	}
	
	/**
	 * 方法名 ： setColsName2
	 * 功 能 ： 设置变量 colsName2 的值
	 */
	public void setColsName2(String colsName2) {
		this.colsName2 = colsName2;
	}
	
	/**
	 * 方法名 ： getTableUuid2
	 * 功 能 ： 返回变量 tableUuid2 的值
	 *
	 * @return: String
	 */
	public String getTableUuid2() {
		return tableUuid2;
	}
	
	/**
	 * 方法名 ： setTableUuid2
	 * 功 能 ： 设置变量 tableUuid2 的值
	 */
	public void setTableUuid2(String tableUuid2) {
		this.tableUuid2 = tableUuid2;
	}
	
	/**
	 * 方法名 ： getColsDesc2
	 * 功 能 ： 返回变量 colsDesc2 的值
	 *
	 * @return: String
	 */
	public String getColsDesc2() {
		return colsDesc2;
	}
	
	/**
	 * 方法名 ： setColsDesc2
	 * 功 能 ： 设置变量 colsDesc2 的值
	 */
	public void setColsDesc2(String colsDesc2) {
		this.colsDesc2 = colsDesc2;
	}
	
	/**
	 * 方法名 ： getColsName1
	 * 功 能 ： 返回变量 colsName1 的值
	 *
	 * @return: String
	 */
	public String getColsName1() {
		return colsName1;
	}
	
	/**
	 * 方法名 ： setColsName1
	 * 功 能 ： 设置变量 colsName1 的值
	 */
	public void setColsName1(String colsName1) {
		this.colsName1 = colsName1;
	}
	
	/**
	 * 方法名 ： getColsDesc1
	 * 功 能 ： 返回变量 colsDesc1 的值
	 *
	 * @return: String
	 */
	public String getColsDesc1() {
		return colsDesc1;
	}
	
	/**
	 * 方法名 ： setColsDesc1
	 * 功 能 ： 设置变量 colsDesc1 的值
	 */
	public void setColsDesc1(String colsDesc1) {
		this.colsDesc1 = colsDesc1;
	}
	
	/**
	 * 方法名 ： getTableUuid1
	 * 功 能 ： 返回变量 tableUuid1 的值
	 *
	 * @return: String
	 */
	public String getTableUuid1() {
		return tableUuid1;
	}
	
	/**
	 * 方法名 ： setTableUuid1
	 * 功 能 ： 设置变量 tableUuid1 的值
	 */
	public void setTableUuid1(String tableUuid1) {
		this.tableUuid1 = tableUuid1;
	}
	
	/**
	 * @方法名 getColsUuid2
	 * @功能 返回变量 colsUuid2 的值
	 * @return String
	 */
	public String getColsUuid2() {
		return colsUuid2;
	}
	
	/**
	 * @方法名 setColsUuid2
	 * @功能 设置变量 colsUuid2 的值
	 */
	public void setColsUuid2(String colsUuid2) {
		this.colsUuid2 = colsUuid2;
	}
	
	/**
	 * @方法名 getColsUuid1
	 * @功能 返回变量 colsUuid1 的值
	 * @return String
	 */
	public String getColsUuid1() {
		return colsUuid1;
	}
	
	/**
	 * @方法名 setColsUuid1
	 * @功能 设置变量 colsUuid1 的值
	 */
	public void setColsUuid1(String colsUuid1) {
		this.colsUuid1 = colsUuid1;
	}
	
}
