/**
 * 文件名：SysDicNameController.java 版本信息： 日期：2018年5月16日 Copyright 足下 Corporation 2018 版权所有
 */
package org.chuxue.application.softm.dic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.chuxue.application.bean.manager.dic.SysDicKeyList;
import org.chuxue.application.bean.manager.dic.SysDicName;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.softm.dic.service.SysDicNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名 ： SysDicNameController.java
 * 包 名 ： com.shumeng.application.softm.dic.controller
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2018年5月16日 上午9:35:38
 * 版 本 ： V1.0
 */
@RestController
@RequestMapping("/sysDicName")
public class SysDicNameController extends BaseControllerImpl<SysDicName> implements BaseController<SysDicName> {
	//
	private static final Logger	logger	= LoggerFactory.getLogger(SysDicNameController.class);
	//
	@Autowired
	private SysDicNameService	sysDicNameService;
	
	@RequestMapping(path = "/checkCode", method = RequestMethod.POST)
	public Map<String, Boolean> checkCode(String code) {
		logger.info("checkCode", SysDicNameController.class);
		boolean boo = sysDicNameService.checkCode(code);
		Map<String, Boolean> map = new HashMap<>();
		map.put("valid", boo);
		return map;
	}
	
	@RequestMapping(path = "/findkeyList", method = RequestMethod.POST)
	public List<SysDicKeyList> findkeyList(@RequestBody SysDicName info) {
		logger.info("findkeyList", SysDicNameController.class);
		List<SysDicKeyList> list = sysDicNameService.findkeyList(info);
		return list;
	}
	
}
