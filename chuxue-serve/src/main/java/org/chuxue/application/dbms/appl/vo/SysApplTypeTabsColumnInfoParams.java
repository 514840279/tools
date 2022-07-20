package org.chuxue.application.dbms.appl.vo;

import java.util.List;

import org.chuxue.application.bean.manager.appl.SysApplTypeTabsColumnInfo;
import org.chuxue.application.bean.manager.appl.SysApplTypeTabsInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件名 ： SysApplTypeTabsColumnInfoParams.java
 * 包 名 ： org.chuxue.application.dbms.appl.vo
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年7月20日 下午5:22:26
 * 版 本 ： V1.0
 */
@Getter
@Setter
public class SysApplTypeTabsColumnInfoParams {

	private List<SysApplTypeTabsColumnInfo>	list;
	private SysApplTypeTabsInfo				info;
}
