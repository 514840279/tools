package org.chuxue.application.dbms.tabs.dao;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.base.BaseDao;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Repository;

/**
 * 文件名 ： SysTableDao.java
 * 包 名 ： tk.ainiyue.danyuan.application.dbm.table.dao
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： wang
 * 时 间 ： 2017年8月3日 下午3:54:48
 * 版 本 ： V1.0
 */
@Repository("sysDbmsTabsTableInfoDao")
@DynamicUpdate(true)
@DynamicInsert(true)
public interface SysDbmsTabsTableInfoDao extends BaseDao<SysDbmsTabsTableInfo> {
	
}
