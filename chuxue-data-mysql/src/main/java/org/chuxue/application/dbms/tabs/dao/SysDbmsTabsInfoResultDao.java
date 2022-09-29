package org.chuxue.application.dbms.tabs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsInfoResult;

/**
 * 文件名 ： SysTableDao.java
 * 包 名 ： tk.ainiyue.danyuan.application.dbm.table.dao
 * 描 述 ： 表信息查询
 * 机能名称：
 * 技能ID ：
 * 作 者 ： wang
 * 时 间 ： 2017年8月3日 下午3:54:48
 * 版 本 ： V1.0
 */
@Mapper
public interface SysDbmsTabsInfoResultDao {
	
	Long totalAllByJdbcUuid(@Param("jdbcUuid") String jdbcUuid, @Param("tname") String tableName, @Param("list") List<String> list);

	List<SysDbmsTabsInfoResult> findAllByJdbcUuid(@Param("jdbcUuid") String jdbcUuid, @Param("tname") String tableName, @Param("list") List<String> list, @Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);

	SysDbmsTabsInfoResult findOneByTabsName(@Param("info") SysDbmsTabsTableInfo info);
	
}
