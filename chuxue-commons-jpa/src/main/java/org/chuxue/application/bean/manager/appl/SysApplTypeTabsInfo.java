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
 * @文件名 SysApplTypeTabsInfo.java
 * @包名 org.chuxue.application.bean.manager.appl.po
 * @描述 `sys_appl_type_tabs_info`的实体类
 * @时间 2022年07月18日 16:07:49
 * @author
 * @版本 V1.0
 */
@Setter
@Getter
@Entity
@Table(name = "`sys_appl_type_tabs_info`")
@NamedQuery(name = "SysApplTypeTabsInfo.findAll", query = "SELECT s FROM SysApplTypeTabsInfo s")
public class SysApplTypeTabsInfo extends BaseEntity implements Serializable {
	private static final long	serialVersionUID	= 1L;
	
	// 表id
	@Column(name = "tabs_uuid")
	private String				tabsUuid;
	
	// 类型代码
	@Column(name = "type_code")
	private String				typeCode;
	
	@Column(name = "checkbox_type")
	private String				checkboxType;
	
	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数：
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysApplTypeTabsInfo() {
	}
	
	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param typeCode2
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysApplTypeTabsInfo(String typeCode) {
		this.typeCode = typeCode;
	}
	
}
