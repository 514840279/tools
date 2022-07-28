/**
 * 文件名：SysDicKeyListService.java 版本信息： 日期：2018年5月16日 Copyright 足下 Corporation 2018 版权所有
 */
package org.chuxue.application.softm.dic.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.chuxue.application.bean.manager.dic.SysDicKeyList;
import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.chuxue.application.softm.dic.dao.SysDicKeyListDao;
import org.chuxue.application.softm.dic.vo.SysDicKeyListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.alibaba.nacos.common.utils.StringUtils;

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
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * 方法名： tree
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： List<SysDicKeyListVo>
	 * 作 者 ： Administrator
	 * @throws
	 */
	public List<SysDicKeyListVo> tree(SysDicKeyListVo vo) throws IllegalAccessException, InvocationTargetException {
		SysDicKeyList info = new SysDicKeyList();
		BeanUtils.copyProperties(vo, info);
		List<SysDicKeyList> list = sysDicKeyListDao.findAll(Example.of(info));
		List<SysDicKeyListVo> re = new ArrayList<>();
		
		re = resultList(list, re);

		return re;
	}

	/**
	 * @return
	 * 方法名： resultList
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param list
	 * 参 数： @param re
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	private List<SysDicKeyListVo> resultList(List<SysDicKeyList> list, List<SysDicKeyListVo> re) {
		if (list == null || list.size() == 0) {
			return null;
		}
		for (SysDicKeyList sysDicKeyList : list) {
			if (StringUtils.isBlank(sysDicKeyList.getParentsUuid())) {
				re.add(toVo(sysDicKeyList, list));
			}
		}
		toSortVo(re);
		
		return re;
	}
	
	/**
	 * @param list
	 * 方法名： toVo
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param sysDicKeyList
	 * 参 数： @return
	 * 返 回： SysDicKeyListVo
	 * 作 者 ： Administrator
	 * @throws
	 */
	private SysDicKeyListVo toVo(SysDicKeyList sysDicKeyList, List<SysDicKeyList> list) {
		SysDicKeyListVo vo = new SysDicKeyListVo(sysDicKeyList);
		List<SysDicKeyListVo> re = new ArrayList<>();
		for (SysDicKeyList sysDicKeyList2 : list) {
			if (sysDicKeyList2.getParentsUuid().equals(vo.getUuid()) && (vo.getChildren() == null)) {
				re.add(toVo(sysDicKeyList, list));
			}
		}
		if (re.size() > 0) {
			// sort
			toSortVo(re);
			vo.setChildren(re);
		}
		return vo;
	}

	/**
	 * 方法名： toSortVo
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param re
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	private void toSortVo(List<SysDicKeyListVo> re) {
		Collections.sort(re, (o1, o2) -> o2.getSort().compareTo(o1.getSort()));
	}

}
