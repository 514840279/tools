package org.chuxue.application.softm.roles.controller;

import java.util.List;

import org.chuxue.application.bean.manager.softm.SysUserRolesInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.softm.roles.service.SysUserRolesInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名 ： SysUserRolesController.java
 * 包 名 ： tk.ainiyue.admin.userrole.controller
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Tenghui.Wang
 * 时 间 ： 2016年7月17日 下午3:58:24
 * 版 本 ： V1.0
 */
@RestController
@RequestMapping("/sysUserRoles")
public class SysUserRolesController extends BaseControllerImpl<SysUserRolesInfo> implements BaseController<SysUserRolesInfo> {
	
	//
	private static final Logger		logger	= LoggerFactory.getLogger(SysUserRolesController.class);
	
	//
	@Autowired
	private SysUserRolesInfoService	sysUserRolesInfoService;
	
	/**
	 * 方法名： findAll
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @return
	 * 返 回： List<SysUserRolesInfo>
	 * 作 者 ： Tenghui.Wang
	 * @throws
	 */
	@RequestMapping(path = "/sysUserRolesList", method = RequestMethod.GET)
	public List<SysUserRolesInfo> findAll() {
		logger.info("sysUserRolesList", SysUserRolesController.class);
		return sysUserRolesInfoService.findAll();
	}
	
	@RequestMapping(path = "/findAllBySearchText", method = RequestMethod.POST)
	public Page<SysUserRolesInfo> findAllBySearchText(int pageNumber, int pageSize, SysUserRolesInfo sysUserRolesInfo) {
		logger.info("findAllBySearchText", SysUserRolesController.class);
		return sysUserRolesInfoService.findAllBySearchText(pageNumber, pageSize, sysUserRolesInfo);
	}
	
	@Override
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<SysUserRolesInfo> save(@RequestBody SysUserRolesInfo info) {
		logger.info("save", SysUserRolesController.class);
		try {
			sysUserRolesInfoService.save(info);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}
	
}
