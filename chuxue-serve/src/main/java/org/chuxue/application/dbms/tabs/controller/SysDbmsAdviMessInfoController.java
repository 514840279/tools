package org.chuxue.application.dbms.tabs.controller;

import org.chuxue.application.bean.manager.dbms.SysDbmsAdviMessInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.dbms.tabs.service.SysDbmsAdviMessInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名 ： Test.java
 * 包 名 ： com.shumeng.application.application.zhcx.controller
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2018年4月24日 下午6:08:33
 * 版 本 ： V1.0
 */
@RestController
@RequestMapping("/sysAdviceMess")
public class SysDbmsAdviMessInfoController extends BaseControllerImpl<SysDbmsAdviMessInfo> implements BaseController<SysDbmsAdviMessInfo> {

	private static final Logger	logger	= LoggerFactory.getLogger(SysDbmsAdviMessInfoController.class);

	@Autowired
	SysDbmsAdviMessInfoService	sysDbmsAdviMessInfoService;
	
}
