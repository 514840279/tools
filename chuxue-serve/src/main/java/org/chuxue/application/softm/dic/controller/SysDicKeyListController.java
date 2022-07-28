/**
 * 文件名：SysDicKeyListController.java 版本信息： 日期：2018年5月16日 Copyright 足下 Corporation 2018 版权所有
 */
package org.chuxue.application.softm.dic.controller;

import java.util.List;

import org.chuxue.application.bean.manager.dic.SysDicKeyList;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.softm.dic.service.SysDicKeyListService;
import org.chuxue.application.softm.dic.vo.SysDicKeyListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名 ： SysDicKeyListController.java
 * 包 名 ： com.shumeng.application.softm.dic.controller
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2018年5月16日 上午9:40:18
 * 版 本 ： V1.0
 */
@RestController
@RequestMapping("/sysDicKeyList")
public class SysDicKeyListController extends BaseControllerImpl<SysDicKeyList> implements BaseController<SysDicKeyList> {
	private static final Logger	logger	= LoggerFactory.getLogger(SysDicKeyListController.class);
	
	@Autowired
	SysDicKeyListService		sysDicKeyListService;

	@PostMapping("/tree")
	public BaseResult<List<SysDicKeyListVo>> tree(@RequestBody SysDicKeyListVo info) {
		try {
			List<SysDicKeyListVo> list = sysDicKeyListService.tree(info);
			return ResultUtil.success(list);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResultUtil.error(e.getMessage());
		}
		
	}
	
}
