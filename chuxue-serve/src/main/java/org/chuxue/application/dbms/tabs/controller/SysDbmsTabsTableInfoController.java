package org.chuxue.application.dbms.tabs.controller;

import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.Pagination;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.dbms.tabs.service.SysDbmsTabsTableInfoService;
import org.chuxue.application.dbms.tabs.vo.SysDbmsTabsIndexInfoVo;
import org.chuxue.application.dbms.tabs.vo.SysDbmsTabsTableInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class SysDbmsTabsTableInfoController extends BaseControllerImpl<SysDbmsTabsTableInfo> implements BaseController<SysDbmsTabsTableInfo> {
	
	private static final Logger	logger	= LoggerFactory.getLogger(SysDbmsTabsTableInfoController.class);
	
	@Autowired
	SysDbmsTabsTableInfoService	sysDbmsTabsTableInfoService;
	
	@RequestMapping(value = "/findAllByJdbcUuid", method = { RequestMethod.POST })
	public BaseResult<Page<SysDbmsTabsTableInfo>> findAllByJdbcUuid(@RequestBody Pagination<SysDbmsTabsTableInfo> vo) {
		logger.info("<findAllByJdbcUuid>数据库表信息查询：{}", vo.toString());
		try {
			BaseResult<Page<SysDbmsTabsTableInfo>> page = sysDbmsTabsTableInfoService.findAllByJdbcUuid(vo);
			return page;
		} catch (Exception e) {
			logger.error("<findAllByJdbcUuid> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}
	}

	@RequestMapping(value = "/importTable", method = { RequestMethod.POST })
	public BaseResult<String> importTable(@RequestBody SysDbmsTabsTableInfoVo info) {
		logger.info("<importTable>数据库表信息查询：{}", info.toString());
		try {
			String result = sysDbmsTabsTableInfoService.importTable(info);
			return ResultUtil.success(result);
		} catch (Exception e) {
			logger.error("<importTable> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}

	}
	
	@RequestMapping(path = "/findAllTablesByIndex", method = RequestMethod.POST)
	public BaseResult<List<SysDbmsTabsTableInfo>> findAllTablesByIndex(@RequestBody SysDbmsTabsIndexInfoVo vo) {
		logger.info("<findAllTablesByIndex> param vo:{} ", vo.toString());
		try {
			List<SysDbmsTabsTableInfo> list = sysDbmsTabsTableInfoService.findAllTablesByIndex(vo);
			return ResultUtil.success(list);
		} catch (Exception e) {
			logger.error("<findAllTablesByIndex> error:{} ", e.getMessage());
			return ResultUtil.error(-1, e.getMessage());
		}

	}
	
}
