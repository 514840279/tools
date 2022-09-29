package org.chuxue.application.dbms.tabs.controller;

import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsAdviMessInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.dbms.tabs.service.SysDbmsAdviMessInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(path = "/findAllByTable", method = RequestMethod.POST)
	public BaseResult<List<SysDbmsAdviMessInfo>> findAllByTable(@RequestBody SysDbmsAdviMessInfo info) throws ClassNotFoundException {
		try {
			logger.info("<findAllByTable> 数据库表信息查询：{}", info.toString());
			List<SysDbmsAdviMessInfo> list = sysDbmsAdviMessInfoService.findAllByTable(info);
			return ResultUtil.success(list);
		} catch (Exception e) {
			logger.error("<findAllByTable> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}

	}
	
	@RequestMapping(path = "/findOneText", method = RequestMethod.POST)
	public BaseResult<String> findOneText(@RequestBody SysDbmsAdviMessInfo info) throws ClassNotFoundException {
		try {
			logger.info("<findOneText> 数据库表信息查询：{}", info.toString());
			String text = sysDbmsAdviMessInfoService.findOneText(info);
			return ResultUtil.success(text);
		} catch (Exception e) {
			logger.error("<findAllByTable> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}

	}
	
	@RequestMapping(path = "/generateSql", method = RequestMethod.POST)
	public BaseResult<String> generateSql(@RequestBody SysDbmsAdviMessInfo info) {
		try {
			logger.info("<generateSql> 数据库表信息查询：{}", info.toString());
			sysDbmsAdviMessInfoService.generateSql(info);
			return ResultUtil.success();
		} catch (Exception e) {
			logger.error("<generateSql> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}

	}

}
