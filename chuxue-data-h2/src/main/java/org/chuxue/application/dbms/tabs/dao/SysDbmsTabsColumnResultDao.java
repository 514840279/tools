package org.chuxue.application.dbms.tabs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsColumnResult;

@Mapper
public interface SysDbmsTabsColumnResultDao {

//	@Query(value = "SELECT UUID() AS UUID,:tabsUuid AS TABS_UUID,REMARKS AS COLS_DESC,COLUMN_NAME AS COLS_NAME, CHARACTER_MAXIMUM_LENGTH AS COLS_LENGTH,TYPE_NAME AS DATA_PRECISION,COLUMN_TYPE AS COLS_TYPE,NULLABLE AS NULLABLE,ORDINAL_POSITION AS COLS_SORT  FROM INFORMATION_SCHEMA.COLUMNS C WHERE TABLE_NAME  =:tabsName", nativeQuery = true)
	List<SysDbmsTabsColumnResult> findAllByTabUuid(@Param("tabsUuid") String uuid, @Param("tabsName") String tabsName, @Param("list") List<String> list);

}
