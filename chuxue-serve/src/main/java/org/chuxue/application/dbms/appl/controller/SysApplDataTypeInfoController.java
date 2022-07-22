package org.chuxue.application.dbms.appl.controller;

import java.util.List;

import org.chuxue.application.bean.manager.appl.SysApplDataTypeInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.Pagination;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.dbms.appl.service.SysApplDataTypeInfoService;
import org.chuxue.application.dbms.appl.vo.SysApplDataTypeInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @文件名 SysApplDataTypeInfoController.java
 * @包名 org.chuxue.application.dbms.appl.controller
 * @描述 controller层
 * @时间 2022年07月20日 10:58:32
 * @author
 * @版本 V1.0
 */
@RestController
@RequestMapping("/sysApplDataTypeInfo")
public class SysApplDataTypeInfoController extends BaseControllerImpl<SysApplDataTypeInfo> implements BaseController<SysApplDataTypeInfo> {

	@Autowired
	SysApplDataTypeInfoService sysApplDataTypeInfoService;

	@PostMapping("/findAllTablesCheck")
	public BaseResult<List<SysApplDataTypeInfoVo>> findAllTablesCheck(@RequestBody SysApplDataTypeInfoVo info) {
		try {
			List<SysApplDataTypeInfoVo> list = sysApplDataTypeInfoService.findAllTablesCheck(info);
			return ResultUtil.success(list);
		} catch (Exception e) {
			return ResultUtil.error(-1, e.getMessage());
		}
	}
	
	@PostMapping("/saveList")
	public BaseResult<String> saveList(@RequestBody Pagination<SysApplDataTypeInfo> vo) {
		try {
			sysApplDataTypeInfoService.saveList(vo.getList());
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(-1, e.getMessage());
		}
	}
}
