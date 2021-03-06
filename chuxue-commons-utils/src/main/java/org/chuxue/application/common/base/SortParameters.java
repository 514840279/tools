package org.chuxue.application.common.base;

/**
 * 文件名 ： OrderParameters.java
 * 包 名 ： org.danyuan.application.common.base
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2021年10月18日 下午5:36:50
 * 版 本 ： V1.0
 */
public class SortParameters {

	private Integer	sortIndex;	// 顺序
	private String	sortName;	// 排序字段
	private String	sortTitle;	// 排序字段
	private String	sortOrder;	// 排序方式 desc，asc default asc

	/**
	 * 方法名 ： getSortIndex
	 * 功 能 ： 返回变量 sortIndex 的值
	 *
	 * @return: Integer
	 */
	public Integer getSortIndex() {
		return sortIndex;
	}

	/**
	 * 方法名 ： setSortIndex
	 * 功 能 ： 设置变量 sortIndex 的值
	 */
	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}

	/**
	 * 方法名 ： getSortName
	 * 功 能 ： 返回变量 sortName 的值
	 *
	 * @return: String
	 */
	public String getSortName() {
		return sortName;
	}

	/**
	 * 方法名 ： setSortName
	 * 功 能 ： 设置变量 sortName 的值
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	/**
	 * 方法名 ： getSortOrder
	 * 功 能 ： 返回变量 sortOrder 的值
	 *
	 * @return: String
	 */
	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * 方法名 ： setSortOrder
	 * 功 能 ： 设置变量 sortOrder 的值
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * 方法名 ： getSortTitle
	 * 功 能 ： 返回变量 sortTitle 的值
	 *
	 * @return: String
	 */
	public String getSortTitle() {
		return sortTitle;
	}

	/**
	 * 方法名 ： setSortTitle
	 * 功 能 ： 设置变量 sortTitle 的值
	 */
	public void setSortTitle(String sortTitle) {
		this.sortTitle = sortTitle;
	}

}
