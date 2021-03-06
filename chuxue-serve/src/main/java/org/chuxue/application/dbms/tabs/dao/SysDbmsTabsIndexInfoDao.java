package org.chuxue.application.dbms.tabs.dao;

import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsIndexInfo;
import org.chuxue.application.common.base.BaseDao;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 文件名 ： SysDicUserIndexCodeDao.java
 * 包 名 ： com.shumeng.application.zhcx.dao
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2018年3月8日 下午1:48:02
 * 版 本 ： V1.0
 */
@Repository("SysDbmsTabsIndexInfoDao")
@DynamicUpdate(true)
@DynamicInsert(true)
public interface SysDbmsTabsIndexInfoDao extends BaseDao<SysDbmsTabsIndexInfo> {
	
	/**
	 * 方法名： findAllByDeleteFlag
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @return
	 * 返 回： List<SysDicUserIndexCode>
	 * 作 者 ： Administrator
	 * @throws
	 */
	@Query("select t from SysDbmsTabsIndexInfo t where t.deleteFlag = 0 order by userOrder ")
	List<SysDbmsTabsIndexInfo> findAllByDeleteFlag();
	
	/**
	 * @方法名 findAllByChart
	 * @功能 TODO(这里用一句话描述这个方法的作用)
	 * @参数 @return
	 * @返回 List<SysDbmsTabsIndexInfo>
	 * @author Administrator
	 * @throws
	 */
	@Query("select t from SysDbmsTabsIndexInfo t where t.chart >0  order by userOrder ")
	List<SysDbmsTabsIndexInfo> findAllByChart();
	
}
