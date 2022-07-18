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
 * @文件名 SysApplTypeInfo.java
 * @包名 org.chuxue.application.bean.manager.appl.po
 * @描述 `sys_appl_type_info`的实体类
 * @时间 2022年07月18日 16:07:49
 * @author
 * @版本 V1.0
 */
@Setter
@Getter
@Entity
@Table(name = "`sys_appl_type_info`")
@NamedQuery(name = "SysApplTypeInfo.findAll", query = "SELECT s FROM SysApplTypeInfo s")
public class SysApplTypeInfo extends BaseEntity implements Serializable {
	private static final long	serialVersionUID	= 1L;
	
	// 应用代码
	@Column(name = "appl_code")
	private String				applCode;
	
	// 按钮类型
	@Column(name = "checkbox_type")
	private String				checkboxType;
	
	// 类型代码
	@Column(name = "type_code")
	private String				typeCode;
	
	//
	@Column(name = "type_name")
	private String				typeName;
	
	/**
	 * 构造方法：
	 * 描 述： 默认构造函数
	 * 参 数：
	 * 作 者 ：
	 * @throws
	 */
	public SysApplTypeInfo() {
	}
	
}
