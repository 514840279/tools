package org.chuxue.application.common.base;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 文件名 ： MybatisBaseConrollerImpl.java
 * 包 名 ： org.chuxue.application.common.base
 * 描 述 ： 通用coltroller
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年7月5日 上午9:46:15
 * 版 本 ： V1.0
 */

public class MybatisBaseConrollerImpl<S extends MybatisBaseServiceImpl<M, T>, M extends MybatisBaseDao<T>, T extends MybatisBaseEntity> implements BaseController<T> {
	
	private static final Logger	logger	= LoggerFactory.getLogger(MybatisBaseConrollerImpl.class);

	@Autowired
	S							serviceImpl;
	
	/**
	 * 方法名 ： page
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @param vo
	 * 参 数 ： @return
	 * 参 考 ： @see org.chuxue.application.common.base.BaseController#page(org.chuxue.application.common.base.Pagination)
	 * 作 者 ： Administrator
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResult<Page<T>> page(@RequestBody Pagination<T> vo) {
		logger.info("<page> param vo:{} ", vo.toString());
		try {
			
			// 简单分页查询
			QueryWrapper<T> queryWrapper = new QueryWrapper<>();
			// 泛型实际类型
			Class<T> classz = (Class<T>) vo.getInfo().getClass();
			
			// 条件
			if (vo.getSearchList() != null && vo.getSearchList().size() > 0) {
				for (SearchParameters parameter : vo.getSearchList()) {
					// 条件的值不为空的拼接该条件
					if (StringUtils.isNotBlank(parameter.getData())) {
						try {
							// 注解中的字段名
							String filedName = null;
							Field field = null;
							try {
								field = classz.getSuperclass().getDeclaredField(parameter.getColumn());
							} catch (NoSuchFieldException e) {
								field = classz.getDeclaredField(parameter.getColumn());
							}
							
							TableField tableField = field.getAnnotation(TableField.class);
							if (tableField != null) {
								filedName = tableField.value();
							} else if (filedName == null) {
								// 默认驼峰转换字段
								filedName = dbColumn(parameter.getColumn());
							}
							parameter.setColumn(filedName);
							paramterWrapper(queryWrapper, parameter);
						} catch (NoSuchFieldException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			// 排序
			if (vo.getSortList() != null && vo.getSortList().size() > 0) {
				Collections.sort(vo.getSortList(), (o1, o2) -> (o1.getSortIndex() - o2.getSortIndex()));
				for (SortParameters parameters : vo.getSortList()) {
					String filedName = null;
					Field field = null;
					try {
						field = classz.getSuperclass().getDeclaredField(parameters.getSortName());
					} catch (NoSuchFieldException e) {
						field = classz.getDeclaredField(parameters.getSortName());
					}
					TableField tableField = field.getAnnotation(TableField.class);
					if (tableField != null) {
						filedName = tableField.value();
					} else if (filedName == null) {
						// 默认驼峰转换字段
						filedName = dbColumn(parameters.getSortName());
					}
					if ("asc".equals(parameters.getSortOrder())) {
						queryWrapper.orderByAsc(filedName);
					} else if ("desc".equals(parameters.getSortOrder())) {
						queryWrapper.orderByDesc(filedName);
					} else {
						queryWrapper.orderByAsc(filedName);
					}
				}
			}
			long total = serviceImpl.count();
			com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> p = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(vo.getPageNumber(), vo.getPageSize(), total);
			IPage<T> re = serviceImpl.page(p, queryWrapper);
			
			Pageable able = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize());
			PageImpl<T> result = new PageImpl<>(re.getRecords(), able, re.getTotal());
			
			return ResultUtil.success(result);
		} catch (Exception e) {
			logger.error("<page> error:{} ", e.getMessage());
			return ResultUtil.error(e.getMessage());
		}
		
	}

	/**
	 * @param classz
	 * 方法名： paramterWrapper
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param queryWrapper
	 * 参 数： @param parameters
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	private void paramterWrapper(QueryWrapper<T> queryWrapper, SearchParameters parameter) {
		if (StringUtils.isBlank(parameter.getColumn()) || StringUtils.isBlank(parameter.getData()) || StringUtils.isBlank(parameter.getSymbol())) {
			return;
		}
		
		switch (parameter.getOperator()) {
			case "and":
				queryWrapper.and(wrapper -> paramterOperateWrapper(wrapper, parameter));
				break;
			case "or":
				queryWrapper.or(wrapper -> paramterOperateWrapper(wrapper, parameter));
				break;
			default:
				queryWrapper.and(wrapper -> paramterOperateWrapper(wrapper, parameter));
				break;
		}
		if ((parameter.getSubParameters() != null && parameter.getSubParameters().size() > 0) && (parameter.getSubParameters() != null)) {
			for (SearchParameters parameter2 : parameter.getSubParameters()) {
				queryWrapper.and(wrapper -> paramterWrapper(wrapper, parameter2));
			}
		}
		
	}
	
	/**
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @param classz
	 * 方法名： paramterOperateWrapper
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param wrapper
	 * 参 数： @param parameter
	 * 参 数： @return
	 * 返 回： Object
	 * 作 者 ： Administrator
	 * @throws
	 */
	private QueryWrapper<T> paramterOperateWrapper(QueryWrapper<T> queryWrapper, SearchParameters parameter) {
		
		switch (parameter.getSymbol()) {
			case "eq":
				// eq("=", "相等"),
				queryWrapper.eq(parameter.getColumn(), parameter.getData());
				break;
			case "notEq":
				// notEq("!=", "不相等"),
				queryWrapper.ne(parameter.getColumn(), parameter.getData());
				break;
			case "less":
				// less("<", "小于"),
				queryWrapper.le(parameter.getColumn(), parameter.getData());
				break;
			case "lessAndEq":
				// lessAndEq("<=", "小于等于"),
				queryWrapper.and(wrapper -> wrapper.eq(parameter.getColumn(), parameter.getData()).or().le(parameter.getColumn(), parameter.getData()));
				
				break;
			case "great":
				// great(">", "大于"),
				queryWrapper.gt(parameter.getColumn(), parameter.getData());
				break;
			case "greatAndEq":
				// greatAndEq(">=", "大于等于")
				queryWrapper.and(wrapper -> wrapper.eq(parameter.getColumn(), parameter.getData()).or().ge(parameter.getColumn(), parameter.getData()));
				break;
			case "leftlike":
				// like
				queryWrapper.likeLeft(parameter.getColumn(), parameter.getData());
				break;
			case "rightlike":
				// like
				queryWrapper.likeRight(parameter.getColumn(), parameter.getData());
				break;
			case "notlike":
				// like
				queryWrapper.notLike(parameter.getColumn(), parameter.getData());
				break;
			case "like":
				// like
				queryWrapper.like(parameter.getColumn(), parameter.getData());
				break;
			case "isNull":
				// is null
				queryWrapper.isNull(parameter.getColumn());
				break;
			case "isNotNull":
				// is not null
				queryWrapper.isNotNull(parameter.getColumn());
				break;
			default:
				queryWrapper.eq(parameter.getColumn(), parameter.getData());
				break;
		}
		return queryWrapper;
	}
	
	private final static String UNDERLINE = "_";
	
	/**
	 * 方法名： dbColumn
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param filedName
	 * 参 数： @return
	 * 返 回： String
	 * 作 者 ： Administrator
	 * @throws
	 */
	private String dbColumn(String filedName) {
		StringBuilder sb = new StringBuilder(filedName);
		int temp = 0;// 定位
		if (!filedName.contains(UNDERLINE)) {
			for (int i = 0; i < filedName.length(); i++) {
				if (Character.isUpperCase(filedName.charAt(i))) {
					sb.insert(i + temp, UNDERLINE);
					temp += 1;
				}
			}
		}
		return "`" + sb.toString().toUpperCase() + "`";
	}
	
	/**
	 * 方法名 ： findAll
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @param info
	 * 参 数 ： @return
	 * 参 考 ： @see org.chuxue.application.common.base.BaseController#findAll(org.chuxue.application.common.base.BaseEntity)
	 * 作 者 ： Administrator
	 */
	
	@Override
	public BaseResult<List<T>> findAll(@RequestBody T info) {
		logger.info("<findAll> param info:{} ", info.toString());
		try {
			// 简单分页查询
			QueryWrapper<T> queryWrapper = new QueryWrapper<>(info);
			
			List<T> re = serviceImpl.list(queryWrapper);
			return ResultUtil.success(re);
		} catch (Exception e) {
			logger.error("<findAll> error:{} ", e.getMessage());
			return ResultUtil.error(e.getMessage());
		}
	}
	
	/**
	 * 方法名 ： findOne
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @param info
	 * 参 数 ： @return
	 * 参 考 ： @see org.chuxue.application.common.base.BaseController#findOne(org.chuxue.application.common.base.BaseEntity)
	 * 作 者 ： Administrator
	 */
	
	@Override
	public BaseResult<T> findOne(@RequestBody T info) {
		logger.info("<findAll> param info:{} ", info.toString());
		try {
			// 简单分页查询
			QueryWrapper<T> queryWrapper = new QueryWrapper<>(info);
			
			T re = serviceImpl.getOne(queryWrapper);
			return ResultUtil.success(re);
		} catch (Exception e) {
			logger.error("<findAll> error:{} ", e.getMessage());
			return ResultUtil.error(e.getMessage());
		}
	}
	
	/**
	 * 方法名 ： save
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @param info
	 * 参 数 ： @return
	 * 参 考 ： @see org.chuxue.application.common.base.BaseController#save(org.chuxue.application.common.base.BaseEntity)
	 * 作 者 ： Administrator
	 */
	
	@Override
	public BaseResult<T> save(@RequestBody T info) {
		logger.info("<save> param info:{} ", info.toString());
		try {
			boolean f = false;
			if (info.getUuid() == null) {
				info.setCreateTime(new Date());
				info.setDeleteFlag(0);
				f = serviceImpl.saveOrUpdate(info);
			} else {
				info.setUpdateTime(new Date());
				f = serviceImpl.updateById(info);
			}
			if (f) {
				return ResultUtil.success();
			} else {
				logger.error("<save> error:{} ", "更新错误");
				return ResultUtil.error("更新错误");
			}
		} catch (Exception e) {
			logger.error("<save> error:{} ", e.getMessage());
			return ResultUtil.error(e.getMessage());
		}
	}
	
	/**
	 * 方法名 ： saveAll
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @param vo
	 * 参 数 ： @return
	 * 参 考 ： @see org.chuxue.application.common.base.BaseController#saveAll(org.chuxue.application.common.base.Pagination)
	 * 作 者 ： Administrator
	 */
	
	@Override
	public BaseResult<T> saveAll(@RequestBody Pagination<T> vo) {
		logger.info("<saveAll> param vo:{} ", vo.toString());
		try {
			boolean f = serviceImpl.saveOrUpdateBatch(vo.getList());
			if (f) {
				return ResultUtil.success();
			} else {
				logger.error("<saveAll> error:{} ", "更新错误");
				return ResultUtil.error("更新错误");
			}
		} catch (Exception e) {
			logger.error("<saveAll> error:{} ", e.getMessage());
			return ResultUtil.error(e.getMessage());
		}
	}
	
	/**
	 * 方法名 ： deleteAll
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @param vo
	 * 参 数 ： @return
	 * 参 考 ： @see org.chuxue.application.common.base.BaseController#deleteAll(org.chuxue.application.common.base.Pagination)
	 * 作 者 ： Administrator
	 */
	
	@Override
	public BaseResult<T> deleteAll(@RequestBody Pagination<T> vo) {
		logger.info("<deleteAll> param vo:{} ", vo.toString());
		try {
			boolean f = serviceImpl.removeBatchByIds(vo.getList());
			if (f) {
				return ResultUtil.success();
			} else {
				logger.error("<deleteAll> error:{} ", "更新错误");
				return ResultUtil.error("更新错误");
			}
		} catch (Exception e) {
			logger.error("<deleteAll> error:{} ", e.getMessage());
			return ResultUtil.error(e.getMessage());
		}
	}
	
	/**
	 * 方法名 ： delete
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @param info
	 * 参 数 ： @return
	 * 参 考 ： @see org.chuxue.application.common.base.BaseController#delete(org.chuxue.application.common.base.BaseEntity)
	 * 作 者 ： Administrator
	 */
	
	@Override
	public BaseResult<T> delete(@RequestBody T info) {
		logger.info("<delete> param info:{} ", info.toString());
		try {
			boolean f = serviceImpl.removeById(info);
			if (f) {
				return ResultUtil.success();
			} else {
				logger.error("<delete> error:{} ", "更新错误");
				return ResultUtil.error("更新错误");
			}
		} catch (Exception e) {
			logger.error("<delete> error:{} ", e.getMessage());
			return ResultUtil.error(e.getMessage());
		}
	}
	
	/**
	 * 方法名 ： count
	 * 功 能 ： 按条件统计
	 * 参 数 ： @param info
	 * 参 数 ： @return
	 * 参 考 ： @see org.chuxue.application.common.base.BaseController#count(org.chuxue.application.common.base.BaseEntity)
	 * 作 者 ： Administrator
	 */
	
	@Override
	public BaseResult<Long> count(@RequestBody T info) {
		logger.info("<count> param info:{} ", info.toString());
		try {
			// 简单分页查询
			QueryWrapper<T> queryWrapper = new QueryWrapper<>(info);
			
			Long l = serviceImpl.count(queryWrapper);
			return ResultUtil.success(l);
		} catch (Exception e) {
			logger.error("<count> error:{} ", e.getMessage());
			return ResultUtil.error(e.getMessage());
		}
	}
	
	/**
	 * 方法名 ： trunc
	 * 功 能 ： 截断，mybatis中没有只能用remove替代，
	 * 参 数 ： @return
	 * 参 考 ： @see org.chuxue.application.common.base.BaseController#trunc()
	 * 作 者 ： Administrator
	 */
	
	@Override
	public BaseResult<T> trunc() {
		logger.info("<trunc>  ");
		try {
			serviceImpl.remove(null);
			return ResultUtil.success();
		} catch (Exception e) {
			logger.error("<trunc> error:{} ", e.getMessage());
			return ResultUtil.error(e.getMessage());
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
	public BaseResult<List<T>> findAllBySort(Pagination<T> vo) {
		try {
			// 简单分页查询
			QueryWrapper<T> queryWrapper = new QueryWrapper<>();
			// 条件
			queryWrapper.setEntity(vo.getInfo());
			// 排序
			if (vo.getSortList() != null && vo.getSortList().size() > 0) {
				Collections.sort(vo.getSortList(), (o1, o2) -> (o1.getSortIndex() - o2.getSortIndex()));
				for (SortParameters parameters : vo.getSortList()) {
					if ("asc".equals(parameters.getSortOrder())) {
						queryWrapper.orderByAsc(parameters.getSortName());
					} else if ("desc".equals(parameters.getSortOrder())) {
						queryWrapper.orderByDesc(parameters.getSortName());
					} else {
						queryWrapper.orderByAsc(parameters.getSortName());
					}
				}
			}
			List<T> re = serviceImpl.list(queryWrapper);
			
			return ResultUtil.success(re);
		} catch (Exception e) {
			logger.error("<findAllBySort> error:{} ", e.getMessage());
			return ResultUtil.error(e.getMessage());
		}
	}
	
	@Override
	public BaseResult<Page<T>> pageByInfo(Pagination<T> vo) {
		logger.info("<page> param vo:{} ", vo.toString());
		try {
			
			// 简单分页查询
			QueryWrapper<T> queryWrapper = new QueryWrapper<>();
			// 泛型实际类型
			@SuppressWarnings("unchecked")
			Class<T> classz = (Class<T>) vo.getInfo().getClass();
			
			// 条件
			if (vo.getInfo() != null) {
				Field[] fields = classz.getSuperclass().getDeclaredFields();
				for (Field field : fields) {
//					field.get(fields)
				
				}
				
			}
			// 排序
			if (vo.getSortList() != null && vo.getSortList().size() > 0) {
				Collections.sort(vo.getSortList(), (o1, o2) -> (o1.getSortIndex() - o2.getSortIndex()));
				for (SortParameters parameters : vo.getSortList()) {
					String filedName = null;
					Field field = null;
					try {
						field = classz.getSuperclass().getDeclaredField(parameters.getSortName());
					} catch (NoSuchFieldException e) {
						field = classz.getDeclaredField(parameters.getSortName());
					}
					TableField tableField = field.getAnnotation(TableField.class);
					if (tableField != null) {
						filedName = tableField.value();
					} else if (filedName == null) {
						// 默认驼峰转换字段
						filedName = dbColumn(parameters.getSortName());
					}
					if ("asc".equals(parameters.getSortOrder())) {
						queryWrapper.orderByAsc(filedName);
					} else if ("desc".equals(parameters.getSortOrder())) {
						queryWrapper.orderByDesc(filedName);
					} else {
						queryWrapper.orderByAsc(filedName);
					}
				}
			}
			long total = serviceImpl.count();
			com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> p = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(vo.getPageNumber(), vo.getPageSize(), total);
			IPage<T> re = serviceImpl.page(p, queryWrapper);
			
			Pageable able = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize());
			PageImpl<T> result = new PageImpl<>(re.getRecords(), able, re.getTotal());
			
			return ResultUtil.success(result);
		} catch (Exception e) {
			logger.error("<page> error:{} ", e.getMessage());
			return ResultUtil.error(e.getMessage());
		}
	}
	
}
