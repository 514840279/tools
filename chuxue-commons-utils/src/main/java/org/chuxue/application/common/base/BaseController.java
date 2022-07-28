package org.chuxue.application.common.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 文件名 ： BaseController.java
 * 包 名 ： tk.ainiyue.danyuan.application.common.base
 * 描 述 ： 通用类控制层接口
 * 机能名称：
 * 技能ID ：
 * 作 者 ： wang
 * 时 间 ： 2018年4月3日 下午11:03:46
 * 版 本 ： V1.0
 */
public interface BaseController<T> {

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	BaseResult<Page<T>> page(@RequestBody Pagination<T> vo);

	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	BaseResult<List<T>> findAll(@RequestBody T info);

	@RequestMapping(value = "/findAllBySort", method = RequestMethod.POST)
	BaseResult<List<T>> findAllBySort(@RequestBody Pagination<T> vo);

	@RequestMapping(value = "/findOne", method = RequestMethod.POST)
	BaseResult<T> findOne(@RequestBody T info);

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	BaseResult<T> save(@RequestBody T info);

	@RequestMapping(value = "/saveAll", method = RequestMethod.POST)
	BaseResult<T> saveAll(@RequestBody Pagination<T> vo);

	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	BaseResult<T> deleteAll(@RequestBody Pagination<T> vo);

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	BaseResult<T> delete(@RequestBody T info);

	@RequestMapping(value = "/count", method = RequestMethod.POST)
	BaseResult<Long> count(@RequestBody T info);

	@RequestMapping(value = "/trunc", method = RequestMethod.POST)
	BaseResult<T> trunc();

}