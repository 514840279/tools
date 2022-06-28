/**
 * 文件名：SysDicNameService.java 版本信息： 日期：2018年5月16日 Copyright 足下 Corporation 2018 版权所有
 */
package org.chuxue.application.softm.dic.service;

import java.util.List;
import java.util.Optional;

import org.chuxue.application.bean.manager.dic.SysDicKeyList;
import org.chuxue.application.bean.manager.dic.SysDicName;
import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.chuxue.application.softm.dic.dao.SysDicKeyListDao;
import org.chuxue.application.softm.dic.dao.SysDicNameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

/**
 * 文件名 ： SysDicNameService.java
 * 包 名 ： com.shumeng.application.softm.dic.service
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2018年5月16日 上午9:35:09
 * 版 本 ： V1.0
 */
@Service
public class SysDicNameService extends BaseServiceImpl<SysDicName> implements BaseService<SysDicName> {
	@Autowired
	private SysDicNameDao		sysDicNameDao;
	@Autowired
	private SysDicKeyListDao	sysDicKeyListDao;
	
	/**
	 * 方法名： checkCode
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param url
	 * 参 数： @return
	 * 返 回： boolean
	 * 作 者 ： Administrator
	 * @throws
	 */
	public boolean checkCode(String code) {
		SysDicName info = new SysDicName();
		info.setCode(code);
		Example<SysDicName> example = Example.of(info);
		List<SysDicName> list = sysDicNameDao.findAll(example);
		if (list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 方法名： findkeyList
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param code
	 * 参 数： @return
	 * 返 回： boolean
	 * 作 者 ： Administrator
	 * @throws
	 */
	public List<SysDicKeyList> findkeyList(SysDicName info) {
		Example<SysDicName> example = Example.of(info);
		Optional<SysDicName> reinfo = sysDicNameDao.findOne(example);
		if (reinfo.isPresent()) {
			info = reinfo.get();
			SysDicKeyList key = new SysDicKeyList();
			key.setParentsUuid(info.getUuid());
			
			Example<SysDicKeyList> ke = Example.of(key);
			Order[] order = { new Order(Direction.ASC, "keyOrder"), new Order(Direction.ASC, "createTime") };
			Sort sort = Sort.by(order);
			return sysDicKeyListDao.findAll(ke, sort);
		} else {
			return null;
		}
	}
	
}
