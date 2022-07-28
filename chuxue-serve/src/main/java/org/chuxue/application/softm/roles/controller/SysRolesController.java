package org.chuxue.application.softm.roles.controller;

import java.util.List;

import org.chuxue.application.bean.manager.softm.SysRolesInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.softm.roles.service.SysRolesInfoService;
import org.chuxue.application.softm.roles.vo.SysRolesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名 ： SysRolesController.java
 * 包 名 ： tk.ainiyue.admin.roles.controller
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Tenghui.Wang
 * 时 间 ： 2016年7月17日 下午3:54:27
 * 版 本 ： V1.0
 */
@RestController
@RequestMapping("/sysRoles")
public class SysRolesController extends BaseControllerImpl<SysRolesInfo> implements BaseController<SysRolesInfo> {
	//
	private static final Logger	logger	= LoggerFactory.getLogger(SysRolesController.class);
	
	//
	@Autowired
	private SysRolesInfoService	sysRolesInfoService;
	
	/**
	 * 方法名： findAll
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @return
	 * 返 回： List<SysRolesJurisdictionInfo>
	 * 作 者 ： Tenghui.Wang
	 * @throws
	 */
	@Override
	@RequestMapping(path = "/findAll", method = RequestMethod.POST)
	public BaseResult<List<SysRolesInfo>> findAll(@RequestBody SysRolesInfo info) {
		logger.info("sysRolesList", SysRolesController.class);
		return ResultUtil.success(sysRolesInfoService.findAll());
	}
	
	@RequestMapping(path = "/findAllRoleBySearchText", method = RequestMethod.POST)
	public List<SysRolesInfo> findAllRoleBySearchText(@RequestBody SysRolesVo info) {
		logger.info("findAllBySearchText", SysRolesController.class);
		return sysRolesInfoService.findAllRoleBySearchText(info.getUserId());
	}
}
