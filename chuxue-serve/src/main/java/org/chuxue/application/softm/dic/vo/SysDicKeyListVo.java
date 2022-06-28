package org.chuxue.application.softm.dic.vo;

import java.util.List;

import org.chuxue.application.bean.manager.dic.SysDicKeyList;

/**
 * 文件名 ： SysDicKeyListVo.java
 * 包 名 ： org.chuxue.application.softm.dic.vo
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年6月28日 上午10:49:06
 * 版 本 ： V1.0
 */
public class SysDicKeyListVo extends SysDicKeyList {
	
	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数：
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysDicKeyListVo() {
	}
	
	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param sysDicKeyList
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysDicKeyListVo(SysDicKeyList sysDicKeyList) {
		this.uuid = sysDicKeyList.getUuid();
		this.createTime = sysDicKeyList.getCreateTime();
		this.deleteFlag = sysDicKeyList.getDeleteFlag();
		this.discription = sysDicKeyList.getDiscription();
		this.sort = sysDicKeyList.getSort();
		this.keyOrder = sysDicKeyList.getKeyOrder();
		this.keyValue = sysDicKeyList.getKeyValue();
		this.keyword = sysDicKeyList.getKeyword();
		this.nameCode = sysDicKeyList.getNameCode();
		this.parentsUuid = sysDicKeyList.getParentsUuid();
	}
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long	serialVersionUID	= 1L;
	List<SysDicKeyListVo>		children;
	
	/**
	 * 方法名 ： getChildren
	 * 功 能 ： 返回变量 children 的值
	 *
	 * @return: List<SysDicKeyListVo>
	 */
	public List<SysDicKeyListVo> getChildren() {
		return children;
	}
	
	/**
	 * 方法名 ： setChildren
	 * 功 能 ： 设置变量 children 的值
	 */
	public void setChildren(List<SysDicKeyListVo> children) {
		this.children = children;
	}
	
}
