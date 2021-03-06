package org.chuxue.application.dbms.tabs.controller;

import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsMergeInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.Pagination;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.dbms.tabs.service.SysDbmsTabsMergeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @文件名 SysDbmsTabsMergeInfoController.java
 * @包名 org.danyuan.application.dbms.tabs.controller
 * @描述 controller层
 * @时间 2020年01月03日 15:42:38
 * @author test
 * @版本 V1.0
 */
@RestController
@RequestMapping("/sysDbmsTabsMergeInfo")
public class SysDbmsTabsMergeInfoController extends BaseControllerImpl<SysDbmsTabsMergeInfo> implements BaseController<SysDbmsTabsMergeInfo> {
	
	private static final Logger	logger	= LoggerFactory.getLogger(SysDbmsTabsMergeInfoController.class);
	
	@Autowired
	SysDbmsTabsMergeInfoService	sysDbmsTabsMergeInfoService;
	
	@RequestMapping(path = "/page1", method = RequestMethod.POST)
	public List<SysDbmsTabsColsInfo> page1(@RequestBody Pagination<SysDbmsTabsMergeInfo> vo) {
		logger.info("page1", SysDbmsTabsMergeInfoController.class);
		return sysDbmsTabsMergeInfoService.page1(vo);
	}
	
	@RequestMapping(path = "/page2", method = RequestMethod.POST)
	public List<SysDbmsTabsColsInfo> page2(@RequestBody Pagination<SysDbmsTabsMergeInfo> vo) {
		logger.info("page2", SysDbmsTabsMergeInfoController.class);
		return sysDbmsTabsMergeInfoService.page2(vo);
	}
	
	@RequestMapping(path = "/merge", method = RequestMethod.POST)
	public String merge(@RequestBody Pagination<SysDbmsTabsMergeInfo> vo) {
		logger.info("merge", SysDbmsTabsMergeInfoController.class);
		return sysDbmsTabsMergeInfoService.merge(vo);
	}
	
	@RequestMapping(path = "/loadSql", method = RequestMethod.POST)
	public BaseResult<String> loadSql(@RequestBody SysDbmsTabsMergeInfo vo) {
		logger.info("loadSql", SysDbmsTabsMergeInfoController.class);
		return ResultUtil.success(sysDbmsTabsMergeInfoService.loadSql(vo));
	}
	
}
