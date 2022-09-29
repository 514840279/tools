package org.chuxue.application.dbms.tabs.controller;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.ResultPage;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsInfoResult;
import org.chuxue.application.dbms.tabs.service.SysDbmsTabsInfoResultService;
import org.chuxue.application.dbms.tabs.vo.SysDbmsTabsTableVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名 ： SysDbmsTabsInfoController.java
 * 包 名 ： tk.ainiyue.danyuan.application.dbm.table.controller
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： wang
 * 时 间 ： 2017年8月3日 下午3:54:36
 * 版 本 ： V1.0
 */
@RestController
@RequestMapping("/sysDbmsTabsTableInfo")
public class SysDbmsTabsInfoResultController {

	private static final Logger		logger	= LoggerFactory.getLogger(SysDbmsTabsInfoResultController.class);

	@Autowired
	SysDbmsTabsInfoResultService	sysDbmsTabsInfoResultService;

	@RequestMapping(value = "/findAllByJdbcUuid", method = { RequestMethod.POST })
	public BaseResult<ResultPage<SysDbmsTabsInfoResult>> findAllByJdbcUuid(@RequestBody ResultPage<SysDbmsTabsTableInfo> vo) {
		logger.info("数据库表信息查询：{}", vo.toString());
		ResultPage<SysDbmsTabsInfoResult> page = sysDbmsTabsInfoResultService.findAllByJdbcUuid(vo);
		return ResultUtil.success(page);
	}

	@RequestMapping(value = "/findOneByTabsName", method = { RequestMethod.POST })
	public BaseResult<SysDbmsTabsTableVo> findOneByTabsName(@RequestBody SysDbmsTabsTableInfo info) {
		logger.info("数据库表信息查询：{}", info.toString());
		SysDbmsTabsTableVo re = sysDbmsTabsInfoResultService.findOneByTabsName(info);
		return ResultUtil.success(re);
	}

}
