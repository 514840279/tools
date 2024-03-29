package org.chuxue.application.common.base;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 文件名 ： BaseService.java
 * 包 名 ： tk.ainiyue.danyuan.application.common.base
 * 描 述 ： 通用服务层接口
 * 作 者 ： Administrator
 * 时 间 ： 2017年10月10日 下午2:06:45
 * 版 本 ： V1.0
 */
public interface BaseService<T extends BaseEntity> {
	
	T findOne(T entity);
	
	T findById(String id);
	
	List<T> findAll(T entity);
	
	List<T> findAll(Pagination<T> vo);
	
	Page<T> page(Pagination<T> vo);
	
	T save(T entity);
	
	void saveAll(List<T> entities);
	
	void delete(T entity);
	
	void deleteAll(List<T> entities);
	
	void trunc();

	/**
	 * @方法名 count
	 * @功能 TODO(这里用一句话描述这个方法的作用)
	 * @参数 @param info
	 * @参数 @return
	 * @返回 Long
	 * @author Administrator
	 * @throws
	 */
	Long count(T info);
	
	/**
	 * 方法名： pageByInfo
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param vo
	 * 参 数： @return
	 * 返 回： Page<T>
	 * 作 者 ： Administrator
	 * @throws
	 */
	Page<T> pageByInfo(Pagination<T> vo);
}
