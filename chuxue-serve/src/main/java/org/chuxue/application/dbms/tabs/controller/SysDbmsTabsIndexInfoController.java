package org.chuxue.application.dbms.tabs.controller;

import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsIndexInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.dbms.tabs.service.SysDbmsTabsIndexInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名 ： SysDbmsTabsIndexInfoController.java
 * 包 名 ： com.shumeng.application.zhcx.controller
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2018年3月8日 下午1:40:42
 * 版 本 ： V1.0
 */

@RestController
@RequestMapping("/sysDbmsTabsIndexInfo")
public class SysDbmsTabsIndexInfoController extends BaseControllerImpl<SysDbmsTabsIndexInfo> implements BaseController<SysDbmsTabsIndexInfo> {
	//
	private static final Logger	logger	= LoggerFactory.getLogger(SysDbmsTabsIndexInfoController.class);

	@Autowired
	SysDbmsTabsIndexInfoService	sysDbmsTabsIndexInfoService;

	@RequestMapping(path = "/chartList", method = RequestMethod.POST)
	public List<SysDbmsTabsIndexInfo> chartList() {
		logger.info("findAll", SysDbmsTabsIndexInfoController.class);
		return sysDbmsTabsIndexInfoService.chartList();

	}
	
}
