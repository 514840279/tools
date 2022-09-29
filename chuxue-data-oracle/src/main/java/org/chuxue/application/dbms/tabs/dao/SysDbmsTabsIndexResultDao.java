package org.chuxue.application.dbms.tabs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsIndexResult;

/**
 * 文件名 ： SysDbmsTabsIndexResultDao.java
 * 包 名 ： org.chuxue.application.dbms.tabs.dao
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年9月29日 上午10:40:15
 * 版 本 ： V1.0
 */
@Mapper
public interface SysDbmsTabsIndexResultDao {
	
	/**
	 * 方法名： findOneByTabsName
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： List<SysDbmsTabsColsInfo>
	 * 作 者 ： Administrator
	 * @throws
	 */
	List<SysDbmsTabsIndexResult> findAllByTabsName(@Param("info") SysDbmsTabsTableInfo info);
	
}
