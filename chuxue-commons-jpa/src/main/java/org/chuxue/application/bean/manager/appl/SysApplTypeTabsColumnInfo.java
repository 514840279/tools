package org.chuxue.application.bean.manager.appl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.chuxue.application.common.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @文件名 SysApplTypeTabsColumnInfo.java
 * @包名 org.chuxue.application.bean.manager.appl.po
 * @描述 `sys_appl_type_tabs_column_info`的实体类
 * @时间 2022年07月18日 16:07:49
 * @author
 * @版本 V1.0
 */
@Setter
@Getter
@Entity
@Table(name = "`sys_appl_type_tabs_column_info`")
@NamedQuery(name = "SysApplTypeTabsColumnInfo.findAll", query = "SELECT s FROM SysApplTypeTabsColumnInfo s")
public class SysApplTypeTabsColumnInfo extends BaseEntity implements Serializable {
	private static final long	serialVersionUID	= 1L;

	// 字段展示管理类
	@Column(name = "cols_type")
	protected String			colsType;

	// 字段id
	@Column(name = "cols_uuid")
	protected String			colsUuid;

	// 表id
	@Column(name = "tabs_uuid")
	protected String			tabsUuid;

	// 类型代码
	@Column(name = "type_code")
	protected String			typeCode;
	
	@Column(name = "cols_type_color")
	protected String			colsTypeColor;
	
	@Column(name = "search_cloumn")
	protected String			searchCloumn;

	/**
	 * 构造方法：
	 * 描 述： 默认构造函数
	 * 参 数：
	 * 作 者 ：
	 * @throws
	 */
	public SysApplTypeTabsColumnInfo() {
	}
	
	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param tabsUuid
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysApplTypeTabsColumnInfo(String tabsUuid) {
		this.tabsUuid = tabsUuid;
	}
	
}
