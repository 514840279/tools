package org.chuxue.application.dbms.tabs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chuxue.application.common.base.MybatisBaseDao;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsColumnResult;
import org.chuxue.application.dbms.tabs.vo.SysDbmsTabsTableVo;

@Mapper
public interface SysDbmsTabsColumnResultDao extends MybatisBaseDao<SysDbmsTabsColumnResult> {

	List<SysDbmsTabsColumnResult> findAllByTabUuid(@Param("tabsUuid") String uuid, @Param("tabsName") String tabsName, @Param("list") List<String> list);

	/**
	 * 方法名： selectDataMaps
	 * 功 能： 查询表数据
	 * 参 数： @param vo
	 * 参 数： @return
	 * 返 回： List<Map<String,Object>>
	 * 作 者 ： Administrator
	 * @throws
	 */
	List<Map<String, Object>> selectDataMaps(@Param("vo") SysDbmsTabsTableVo vo);

	/**
	 * 方法名： selectDataCount
	 * 功 能： 查询表数据量 最大返回100，控制页面查询数据量
	 * 参 数： @param vo
	 * 参 数： @return
	 * 返 回： Long
	 * 作 者 ： Administrator
	 * @throws
	 */
	Long selectDataCount(@Param("vo") SysDbmsTabsTableVo vo);

}
