package org.chuxue.application.dbms.tabs.controller;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.Pagination;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.dbms.tabs.service.SysDbmsTabsColsInfoService;
import org.chuxue.application.dbms.tabs.vo.SysDbmsTabsColsInfoVo;
import org.chuxue.application.dbms.tabs.vo.SysDbmsTabsTableVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名 ： SysDbmsTabsColsInfoController.java
 * 包 名 ： tk.ainiyue.danyuan.application.dbm.column.controller
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： wang
 * 时 间 ： 2017年8月3日 下午3:51:35
 * 版 本 ： V1.0
 */
@RestController
@RequestMapping("/sysDbmsTabsColsInfo")
public class SysDbmsTabsColsInfoController extends BaseControllerImpl<SysDbmsTabsColsInfo> implements BaseController<SysDbmsTabsColsInfo> {
	private static final Logger	logger	= LoggerFactory.getLogger(SysDbmsTabsTableInfoController.class);

	@Autowired
	SysDbmsTabsColsInfoService	sysDbmsTabsColsInfoService;
	
	/**
	 * 方法名： findAllByTabsUuid
	 * 功 能： 数据库表信息查询
	 * 参 数： @param vo
	 * 参 数： @return
	 * 返 回： BaseResult<?>
	 * 作 者 ： Administrator
	 * @throws
	 */
	@RequestMapping(value = "/findAllByTabsUuid", method = { RequestMethod.POST })
	public BaseResult<?> findAllByTabsUuid(@RequestBody Pagination<SysDbmsTabsColsInfo> vo) {
		logger.info("数据库表信息查询：{}", vo.toString());
		BaseResult<?> result = sysDbmsTabsColsInfoService.findAllByTabsUuid(vo);
		return result;
	}

	/**
	 * 方法名： importColums
	 * 功 能： 数据库表字段信息导入
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： BaseResult<String>
	 * 作 者 ： Administrator
	 * @throws
	 */
	@RequestMapping(value = "/importColums", method = { RequestMethod.POST })
	public BaseResult<String> importColums(@RequestBody SysDbmsTabsColsInfoVo info) {
		logger.info("数据库表字段信息导入：{}", info.toString());
		String result = sysDbmsTabsColsInfoService.importColums(info);
		return ResultUtil.success(result);
	}
	
	/**
	 * 方法名： searchData
	 * 功 能： 数据库表数据查询
	 * 参 数： @param vo
	 * 参 数： @return
	 * 返 回： BaseResult<?>
	 * 作 者 ： Administrator
	 * @throws
	 */
	@RequestMapping(value = "/searchData", method = { RequestMethod.POST })
	public BaseResult<?> searchData(@RequestBody SysDbmsTabsTableVo vo) {
		logger.info("数据库表数据查询：{}", vo.toString());
		BaseResult<?> result = sysDbmsTabsColsInfoService.searchData(vo);
		return result;
	}
	
}
