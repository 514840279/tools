package org.chuxue.application.common.base;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @描述 通用类控制层实现类
 * @文件名 BaseControllerImpl.java
 * @包名 org.danyuan.application.common.base
 * @时间 2019年1月16日 下午2:14:54
 * @author Administrator
 * @版本 V1.0
 */
@NoRepositoryBean
public class BaseControllerImpl<T extends BaseEntity> implements BaseController<T> {

	private static final Logger	logger	= LoggerFactory.getLogger(BaseControllerImpl.class);
	
	@Autowired
	BaseService<T>				baseService;

	/**
	 * 分页请求方法
	 *
	 * @方法名 page
	 * @参数 @param vo
	 * @参数 @return
	 * @参考 @see org.danyuan.application.common.base.BaseController#page(org.danyuan.application.common.base.Pagination)
	 * @author Administrator
	 */
	
	@Override
	public BaseResult<Page<T>> page(@RequestBody Pagination<T> vo) {
		logger.info("<page> param vo:{} ", vo.toString());
		try {
			Page<T> page = baseService.page(vo);
			return ResultUtil.success(page);
		} catch (Exception e) {
			logger.error("<page> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}
	}

	/**
	 * 查询所有数据，慎用
	 *
	 * @方法名 findAll
	 * @参数 @param info
	 * @参数 @return
	 * @参考 @see org.danyuan.application.common.base.BaseController#findAll(java.lang.Object)
	 * @author Administrator
	 */
	
	@Override
	public BaseResult<List<T>> findAll(@RequestBody T info) {
		logger.info("<findAll> param vo:{} ", info.toString());
		try {
			List<T> list = baseService.findAll(info);
			return ResultUtil.success(list);
		} catch (Exception e) {
			logger.error("<findAll> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}
	}

	/**
	 * 查询一条记录，
	 *
	 * @方法名 findOne
	 * @参数 @param info
	 * @参数 @return
	 * @参考 @see org.danyuan.application.common.base.BaseController#findOne(java.lang.Object)
	 * @author Administrator
	 */
	
	@Override
	public BaseResult<T> findOne(@RequestBody T info) {
		logger.info("<findOne> param vo:{} ", info.toString());
		try {
			T page = baseService.findOne(info);
			return ResultUtil.success(page);
		} catch (Exception e) {
			logger.error("<findOne> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}
	}

	/**
	 * 单条记录保存
	 *
	 * @方法名 save
	 * @参数 @param info
	 * @参数 @return
	 * @参考 @see org.danyuan.application.common.base.BaseController#save(java.lang.Object)
	 * @author Administrator
	 */
	
	@Override
	public BaseResult<T> save(@RequestBody T info) {
		logger.info("<save> param vo:{} ", info.toString());
		try {
			T page = baseService.save(info);
			return ResultUtil.success(page);
		} catch (Exception e) {
			logger.error("<save> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}
	}

	/**
	 * 多条记录保存
	 *
	 * @方法名 save
	 * @参数 @param vo
	 * @参数 @return
	 * @参考 @see org.danyuan.application.common.base.BaseController#save(org.danyuan.application.common.base.Pagination)
	 * @author Administrator
	 */
	
	@Override
	public BaseResult<T> saveAll(@RequestBody Pagination<T> vo) {
		logger.info("<saveAll> param vo:{} ", vo.toString());
		try {
			baseService.saveAll(vo.getList());
			return ResultUtil.success();
		} catch (Exception e) {
			logger.error("<saveAll> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}
	}

	/**
	 * 多条记录删除
	 *
	 * @方法名 delete
	 * @参数 @param vo
	 * @参数 @return
	 * @参考 @see org.danyuan.application.common.base.BaseController#delete(org.danyuan.application.common.base.Pagination)
	 * @author Administrator
	 */
	
	@Override
	public BaseResult<T> deleteAll(@RequestBody Pagination<T> vo) {
		logger.info("<deleteAll> param vo:{} ", vo.toString());
		try {
			baseService.deleteAll(vo.getList());
			return ResultUtil.success();
		} catch (Exception e) {
			logger.error("<deleteAll> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}
	}

	/**
	 * 单条记录删除
	 *
	 * @方法名 delete
	 * @参数 @param info
	 * @参数 @return
	 * @参考 @see org.danyuan.application.common.base.BaseController#delete(java.lang.Object)
	 * @author Administrator
	 */
	
	@Override
	public BaseResult<T> delete(@RequestBody T info) {
		logger.info("<delete> param vo:{} ", info.toString());
		try {
			baseService.delete(info);
			return ResultUtil.success(info);
		} catch (Exception e) {
			logger.error("<delete> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}
	}

	/**
	 * 删除整个表，慎用
	 *
	 * @方法名 trunc
	 * @参数 @return
	 * @参考 @see org.danyuan.application.common.base.BaseController#trunc()
	 * @author Administrator
	 */
	
	@Override
	public BaseResult<T> trunc() {
		logger.info("<trunc> ");
		try {
			baseService.trunc();
			return ResultUtil.success();
		} catch (Exception e) {
			logger.error("<trunc> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}
	}

	/**
	 * 统计数量
	 *
	 * @方法名 count
	 * @参数 @param info
	 * @参数 @return
	 * @参考 @see org.danyuan.application.common.base.BaseController#count(java.lang.Object)
	 * @author Administrator
	 */
	
	@Override
	public BaseResult<Long> count(@RequestBody T info) {
		logger.info("<count> param vo:{} ", info.toString());
		try {
			Long lengthLong = baseService.count(info);
			return ResultUtil.success(lengthLong);
		} catch (Exception e) {
			logger.error("<count> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}
	}
	
	/**
	 * 方法名 ： findAllBySort
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @param vo
	 * 参 数 ： @return
	 * 参 考 ： @see org.chuxue.application.common.base.BaseController#findAllBySort(org.chuxue.application.common.base.Pagination)
	 * 作 者 ： Administrator
	 */

	@Override
	public BaseResult<List<T>> findAllBySort(@RequestBody Pagination<T> vo) {
		logger.info("<findAllBySort> param vo:{} ", vo.toString());
		try {
			List<T> page = baseService.findAll(vo);
			return ResultUtil.success(page);
		} catch (Exception e) {
			logger.error("<findAllBySort> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}
	}
	
	/**
	 * 方法名 ： pageByInfo
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @param vo
	 * 参 数 ： @return
	 * 参 考 ： @see org.chuxue.application.common.base.BaseController#pageByInfo(org.chuxue.application.common.base.Pagination)
	 * 作 者 ： Administrator
	 */

	@Override
	public BaseResult<Page<T>> pageByInfo(Pagination<T> vo) {
		logger.info("<pageByInfo> param vo:{} ", vo.toString());
		try {
			Page<T> page = baseService.pageByInfo(vo);
			return ResultUtil.success(page);
		} catch (Exception e) {
			logger.error("<pageByInfo> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}
	}

}
