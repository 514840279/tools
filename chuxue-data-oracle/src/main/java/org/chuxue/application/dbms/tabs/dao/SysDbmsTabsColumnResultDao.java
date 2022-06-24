package org.chuxue.application.dbms.tabs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsColumnResult;

@Mapper
public interface SysDbmsTabsColumnResultDao {
	
	List<SysDbmsTabsColumnResult> findAllByTabUuid(@Param("tabsUuid") String uuid, @Param("tabsName") String tabsName, @Param("list") List<String> list);
	
}
