package org.tools.application.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tools.application.po.SysTaskInfo;

/**
 * 文件名 ： TaskDao.java
 * 包 名 ： org.tools.application.dao
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 作 者 ： wth
 * 时 间 ： 2022年2月12日 上午9:38:06
 * 版 本 ： V1.0
 */
@Mapper
public interface TaskDao {
	
	/**
	 * 方法名： add
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 返 回： void
	 * 作 者 ： wth
	 *
	 * @throws
	 */
	void addTask(@Param("info") SysTaskInfo info);

	/**
	 * 方法名： updateTask
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 返 回： void
	 * 作 者 ： wth
	 *
	 * @throws
	 */
	void updateTask(@Param("info") SysTaskInfo info);
	
}
