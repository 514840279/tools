package org.chuxue.application.dbms.appl.vo;

import java.util.Map;

import org.chuxue.application.bean.manager.appl.SysApplDataTypeInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件名 ： SysApplDataTypeInfoVo.java
 * 包 名 ： org.chuxue.application.dbms.appl.vo
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年7月20日 上午11:02:41
 * 版 本 ： V1.0
 */
@Getter
@Setter
public class SysApplDataTypeInfoVo extends SysApplDataTypeInfo {
	
	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param map
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysApplDataTypeInfoVo(Map<String, Object> map) {
		this.uuid = map.get("uuid") != null ? map.get("uuid").toString() : null;
		this.typeName = map.get("type_name") != null ? map.get("type_name").toString() : null;
		this.typeCode = map.get("type_code") != null ? map.get("type_code").toString() : null;
		this.applCode = map.get("appl_code") != null ? map.get("appl_code").toString() : null;
		this.checkboxType = map.get("checkbox_type") != null ? map.get("checkbox_type").toString() : null;
	}

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long	serialVersionUID	= 1L;
	private String				typeName;
	
	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param applCode
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysApplDataTypeInfoVo(String applCode) {
		super(applCode);
	}

	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param applCode
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysApplDataTypeInfoVo() {
	}

}
