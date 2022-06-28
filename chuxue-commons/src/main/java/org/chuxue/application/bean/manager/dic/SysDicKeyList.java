package org.chuxue.application.bean.manager.dic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.chuxue.application.common.base.BaseEntity;

/**
 * @文件名 SysDicKeyList.java
 * @包名 org.danyuan.application.softm.dic.po
 * @描述 sys_dic_key_list的实体类
 * @时间 2020年04月25日 16:38:20
 * @author test
 * @版本 V1.0
 */
@Entity
@Table(name = "sys_dic_key_list")
@NamedQuery(name = "SysDicKeyList.findAll", query = "SELECT s FROM SysDicKeyList s")
public class SysDicKeyList extends BaseEntity implements Serializable {
	private static final long	serialVersionUID	= 1L;
	
	// 排序
	@Column(name = "key_order", precision = 10)
	protected Integer			keyOrder;
	
	// 关键字
	@Column(name = "keyword")
	protected String			keyword;
	
	// 列表value
	@Column(name = "key_value")
	protected String			keyValue;
	
	// 外键
	@Column(name = "parents_uuid")
	protected String			parentsUuid;

	// 调用代码
	@Column(name = "name_code")
	protected String			nameCode;
	
	/**
	 * 构造方法：
	 * 描 述： 默认构造函数
	 * 参 数：
	 * 作 者 ： test
	 * @throws
	 */
	public SysDicKeyList() {
	}
	
	/**
	 * 方法名 ： getKeyOrder
	 * 功 能 ： 返回变量 keyOrder 排序 的值
	 *
	 * @return: String
	 */
	public Integer getKeyOrder() {
		return keyOrder;
	}
	
	/**
	 * 方法名 ： setKeyOrder
	 * 功 能 ： 设置变量 keyOrder 排序 的值
	 */
	public void setKeyOrder(Integer keyOrder) {
		this.keyOrder = keyOrder;
	}
	
	/**
	 * 方法名 ： getKeyword
	 * 功 能 ： 返回变量 keyword 关键字 的值
	 *
	 * @return: String
	 */
	public String getKeyword() {
		return keyword;
	}
	
	/**
	 * 方法名 ： setKeyword
	 * 功 能 ： 设置变量 keyword 关键字 的值
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * 方法名 ： getKeyValue
	 * 功 能 ： 返回变量 keyValue 列表value 的值
	 *
	 * @return: String
	 */
	public String getKeyValue() {
		return keyValue;
	}
	
	/**
	 * 方法名 ： setKeyValue
	 * 功 能 ： 设置变量 keyValue 列表value 的值
	 */
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	/**
	 * 方法名 ： getParentsUuid
	 * 功 能 ： 返回变量 parentsUuid 的值
	 *
	 * @return: String
	 */
	public String getParentsUuid() {
		return parentsUuid;
	}

	/**
	 * 方法名 ： setParentsUuid
	 * 功 能 ： 设置变量 parentsUuid 的值
	 */
	public void setParentsUuid(String parentsUuid) {
		this.parentsUuid = parentsUuid;
	}

	/**
	 * 方法名 ： getNameCode
	 * 功 能 ： 返回变量 nameCode 的值
	 *
	 * @return: String
	 */
	public String getNameCode() {
		return nameCode;
	}

	/**
	 * 方法名 ： setNameCode
	 * 功 能 ： 设置变量 nameCode 的值
	 */
	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}

}
