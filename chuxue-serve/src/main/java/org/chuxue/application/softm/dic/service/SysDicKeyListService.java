/**
 * 文件名：SysDicKeyListService.java 版本信息： 日期：2018年5月16日 Copyright 足下 Corporation 2018 版权所有
 */
package org.chuxue.application.softm.dic.service;

import java.util.ArrayList;
import java.util.List;

import org.chuxue.application.bean.manager.dic.SysDicKeyList;
import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.chuxue.application.common.base.Pagination;
import org.chuxue.application.softm.dic.dao.SysDicKeyListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

/**
 * 文件名 ： SysDicKeyListService.java
 * 包 名 ： com.shumeng.application.softm.dic.service
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2018年5月16日 上午9:33:53
 * 版 本 ： V1.0
 */
@Service
public class SysDicKeyListService extends BaseServiceImpl<SysDicKeyList> implements BaseService<SysDicKeyList> {

	@Autowired
	private SysDicKeyListDao sysDicKeyListDao;

	/**
	 * 方法名 ： page
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @param pageNumber
	 * 参 数 ： @param pageSize
	 * 参 数 ： @param info
	 * 参 数 ： @param map
	 * 参 数 ： @param order
	 * 参 数 ： @return
	 * 参 考 ： @see com.shumeng.application.common.base.BaseService#page(int, int, java.lang.Object, java.util.Map, org.springframework.data.domain.Sort.Order[])
	 * 作 者 ： Administrator
	 */

	@Override
	public Page<SysDicKeyList> page(Pagination<SysDicKeyList> vo) {
		Sort sort = vo.sort();
		if (sort == null) {
			List<Order> orders = new ArrayList<>();
			Order order = new Order(Direction.ASC, "keyOrder");
			orders.add(order);
			sort = Sort.by(orders);
		}
		if (vo.getInfo() == null) {
			vo.setInfo(new SysDicKeyList());
		}

		Example<SysDicKeyList> example = Example.of(vo.getInfo());

		PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
		Page<SysDicKeyList> page = sysDicKeyListDao.findAll(example, request);
		return page;
	}

}