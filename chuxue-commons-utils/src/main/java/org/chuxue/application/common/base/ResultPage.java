package org.chuxue.application.common.base;

import java.util.List;

public class ResultPage<T> {
	// 页码
	protected Integer	pageNumber	= 1;
	// 每页数据量大小
	protected Integer	pageSize	= 10;
	
	protected Long		totalElements;
	
	protected List<T>	content;
	
	protected List<T>	list;

	protected T			info;

	public ResultPage() {
	}

	public ResultPage(List<T> content, Long totalElements) {
		this.totalElements = totalElements;
		this.content = content;
	}
	
	/**
	 * 方法名 ： getPageNumber
	 * 功 能 ： 返回变量 pageNumber 的值
	 *
	 * @return: Integer
	 */
	public Integer getPageNumber() {
		return pageNumber;
	}
	
	/**
	 * 方法名 ： setPageNumber
	 * 功 能 ： 设置变量 pageNumber 的值
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	/**
	 * 方法名 ： getPageSize
	 * 功 能 ： 返回变量 pageSize 的值
	 *
	 * @return: Integer
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	
	/**
	 * 方法名 ： setPageSize
	 * 功 能 ： 设置变量 pageSize 的值
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 方法名 ： getTotalElements
	 * 功 能 ： 返回变量 totalElements 的值
	 *
	 * @return: Integer
	 */
	public Long getTotalElements() {
		return totalElements;
	}
	
	/**
	 * 方法名 ： setTotalElements
	 * 功 能 ： 设置变量 totalElements 的值
	 */
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	
	/**
	 * 方法名 ： getContent
	 * 功 能 ： 返回变量 content 的值
	 *
	 * @return: List<T>
	 */
	public List<T> getContent() {
		return content;
	}
	
	/**
	 * 方法名 ： setContent
	 * 功 能 ： 设置变量 content 的值
	 */
	public void setContent(List<T> content) {
		this.content = content;
	}
	
	/**
	 * 方法名 ： getInfo
	 * 功 能 ： 返回变量 info 的值
	 *
	 * @return: T
	 */
	public T getInfo() {
		return info;
	}
	
	/**
	 * 方法名 ： setInfo
	 * 功 能 ： 设置变量 info 的值
	 */
	public void setInfo(T info) {
		this.info = info;
	}
	
	/**
	 * 方法名 ： getList
	 * 功 能 ： 返回变量 list 的值
	 *
	 * @return: List<T>
	 */
	public List<T> getList() {
		return list;
	}
	
	/**
	 * 方法名 ： setList
	 * 功 能 ： 设置变量 list 的值
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
