package org.chuxue.application.dbms.tabs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsColumnResult;
import org.chuxue.application.dbms.tabs.vo.SysDbmsTabsTableVo;

@Mapper
public interface SysDbmsTabsColumnResultDao {
	
//	@Query(value = "SELECT UUID() AS UUID,:tabsUuid AS TABS_UUID,REMARKS AS COLS_DESC,COLUMN_NAME AS COLS_NAME, CHARACTER_MAXIMUM_LENGTH AS COLS_LENGTH,TYPE_NAME AS DATA_PRECISION,COLUMN_TYPE AS COLS_TYPE,NULLABLE AS NULLABLE,ORDINAL_POSITION AS COLS_SORT  FROM INFORMATION_SCHEMA.COLUMNS C WHERE TABLE_NAME  =:tabsName", nativeQuery = true)
	List<SysDbmsTabsColumnResult> findAllByTabUuid(@Param("tabsUuid") String uuid, @Param("tabsName") String tabsName, @Param("list") List<String> list);
	
	/**
	 * 方法名： selectDataMaps
	 * 功 能： 表数据查询
	 * 参 数： @param vo
	 * 参 数： @return
	 * 返 回： List<Map<String,Object>>
	 * 作 者 ： Administrator
	 * @throws
	 */
	List<Map<String, Object>> selectDataMaps(@Param("vo") SysDbmsTabsTableVo vo);

	/**
	 * 方法名： selectDataCount
	 * 功 能： 表数据量查询
	 * 参 数： @param vo
	 * 参 数： @return
	 * 返 回： Long
	 * 作 者 ： Administrator
	 * @throws
	 */
	Long selectDataCount(@Param("vo") SysDbmsTabsTableVo vo);
	
}
