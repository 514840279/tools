package org.chuxue.application.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chuxue.application.common.base.MybatisBaseDao;
import org.chuxue.application.po.Flow;
import org.chuxue.application.po.Jijin;
import org.chuxue.application.vo.JijinVo;

/**
 * 文件名 ： JijinDao.java
 * 包 名 ： org.chuxue.application.dao
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年8月26日 下午3:30:22
 * 版 本 ： V1.0
 */
@Mapper
public interface JijinDao extends MybatisBaseDao<Jijin> {

	/**
	 * 方法名： search
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： JijinVo
	 * 作 者 ： Administrator
	 * @throws
	 */
	List<JijinVo> search(@Param("info") Jijin info);
	
	/**
	 * 方法名： goumai
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	void goumai(@Param("info") Flow info);

	/**
	 * 方法名： maichu
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	void maichu(@Param("info") Flow info);

	/**
	 * 方法名： save
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param ji
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	void save(@Param("info") Jijin ji);

	/**
	 * 方法名： deleteFlow
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param uuid
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	void deleteFlow(@Param("uuid") String uuid);
	
	/**
	 * 方法名： deleteJijin
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param uuid
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	void deleteJijin(@Param("uuid") String uuid);
	
}
