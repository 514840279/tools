package org.chuxue.application.common.base;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.NoRepositoryBean;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 文件名 ： MybatisBaseDao.java
 * 包 名 ： org.chuxue.application.common.base
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年7月5日 上午9:53:47
 * 版 本 ： V1.0
 */
@Mapper
@NoRepositoryBean
public interface MybatisBaseDao<T> extends BaseMapper<T> {
	
}
