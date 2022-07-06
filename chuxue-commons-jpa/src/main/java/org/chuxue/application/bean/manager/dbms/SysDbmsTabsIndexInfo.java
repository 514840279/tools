package org.chuxue.application.bean.manager.dbms;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.chuxue.application.common.base.BaseEntity;

/**
 * @文件名 SysDbmsUserIndexInfo.java
 * @包名 org.danyuan.application.dbms.tabs.po
 * @描述 sys_dbms_user_index_info的实体类
 * @时间 2020年04月25日 12:15:39
 * @author test
 * @版本 V1.0
 */
@Entity
@Table(name = "sys_dbms_tabs_index_info")
@NamedQuery(name = "SysDbmsTabsIndexInfo.findAll", query = "SELECT s FROM SysDbmsTabsIndexInfo s")
public class SysDbmsTabsIndexInfo extends BaseEntity implements Serializable {
	private static final long	serialVersionUID	= 1L;
	
	// 提示消息
	@Column(name = "index_placeholder")
	private String				indexPlaceholder;
	
	// 支持多个聚合以名称分组
	@Column(name = "multeity", precision = 10)
	private Integer				multeity;
	
	// 索引值
	@Column(name = "index_code")
	private String				indexCode;
	
	// 索引描述
	@Column(name = "index_name")
	private String				indexName;
	
	// 支持图表neo4j类型 主要是名称人名
	@Column(name = "chart", precision = 10)
	private Integer				chart;
	
	/**
	 * 构造方法：
	 * 描 述： 默认构造函数
	 * 参 数：
	 * 作 者 ： test
	 * @throws
	 */
	public SysDbmsTabsIndexInfo() {
		super();
	}

	/**
	 * 方法名 ： getMulteity
	 * 功 能 ： 返回变量 multeity 的值
	 *
	 * @return: String
	 */
	public Integer getMulteity() {
		return multeity;
	}
	
	/**
	 * 方法名 ： setMulteity
	 * 功 能 ： 设置变量 multeity 的值
	 */
	public void setMulteity(Integer multeity) {
		this.multeity = multeity;
	}
	
	/**
	 * 方法名 ： getChart
	 * 功 能 ： 返回变量 chart 的值
	 *
	 * @return: String
	 */
	public Integer getChart() {
		return chart;
	}
	
	/**
	 * 方法名 ： setChart
	 * 功 能 ： 设置变量 chart 的值
	 */
	public void setChart(Integer chart) {
		this.chart = chart;
	}

	/**
	 * 方法名 ： getIndexPlaceholder
	 * 功 能 ： 返回变量 indexPlaceholder 的值
	 *
	 * @return: String
	 */
	public String getIndexPlaceholder() {
		return indexPlaceholder;
	}

	/**
	 * 方法名 ： setIndexPlaceholder
	 * 功 能 ： 设置变量 indexPlaceholder 的值
	 */
	public void setIndexPlaceholder(String indexPlaceholder) {
		this.indexPlaceholder = indexPlaceholder;
	}

	/**
	 * 方法名 ： getIndexCode
	 * 功 能 ： 返回变量 indexCode 的值
	 *
	 * @return: String
	 */
	public String getIndexCode() {
		return indexCode;
	}

	/**
	 * 方法名 ： setIndexCode
	 * 功 能 ： 设置变量 indexCode 的值
	 */
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	/**
	 * 方法名 ： getIndexName
	 * 功 能 ： 返回变量 indexName 的值
	 *
	 * @return: String
	 */
	public String getIndexName() {
		return indexName;
	}

	/**
	 * 方法名 ： setIndexName
	 * 功 能 ： 设置变量 indexName 的值
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

}
