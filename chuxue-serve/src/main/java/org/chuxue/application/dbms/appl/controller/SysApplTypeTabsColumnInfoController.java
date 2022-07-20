package org.chuxue.application.dbms.appl.controller;

import java.util.List;

import org.chuxue.application.bean.manager.appl.SysApplTypeTabsColumnInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.dbms.appl.service.SysApplTypeTabsColumnInfoService;
import org.chuxue.application.dbms.appl.vo.SysApplTypeTabsColumnInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @文件名 SysApplTypeTabsColumnInfoController.java
 * @包名 org.chuxue.application.bean.manager.appl.controller
 * @描述 controller层
 * @时间 2022年07月18日 16:07:49
 * @author
 * @版本 V1.0
 */
@RestController
@RequestMapping("/sysApplTypeTabsColumnInfo")
public class SysApplTypeTabsColumnInfoController extends BaseControllerImpl<SysApplTypeTabsColumnInfo> implements BaseController<SysApplTypeTabsColumnInfo> {

	@Autowired
	SysApplTypeTabsColumnInfoService sysApplTypeTabsColumnInfoService;
	
	@PostMapping("/findAllTablesCheck")
	public BaseResult<List<SysApplTypeTabsColumnInfoVo>> findAllTablesCheck(@RequestBody SysApplTypeTabsColumnInfoVo info) {
		try {
			List<SysApplTypeTabsColumnInfoVo> list = sysApplTypeTabsColumnInfoService.findAllTablesCheck(info);
			return ResultUtil.success(list);
		} catch (Exception e) {
			return ResultUtil.error(-1, e.getMessage());
		}
	}
}
