package org.chuxue.application.dbms.tabs.vo;

import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.base.Pagination;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsIndexResult;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件名 ： SysDbmsTabsTableVo.java
 * 包 名 ： org.chuxue.application.dbms.tabs.vo
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年7月11日 下午4:50:44
 * 版 本 ： V1.0
 */
@Setter
@Getter
public class SysDbmsTabsTableVo extends Pagination<SysDbmsTabsTableInfo> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long				serialVersionUID	= 1L;
	private List<SysDbmsTabsColsInfo>		cols;
	private List<SysDbmsTabsIndexResult>	indexs;
}
