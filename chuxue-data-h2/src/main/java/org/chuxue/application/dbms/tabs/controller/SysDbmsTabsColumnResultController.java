package org.chuxue.application.dbms.tabs.controller;

import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.Page;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsColumnResult;
import org.chuxue.application.dbms.tabs.service.SysDbmsTabsColumnResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysDbmsTabsColumnInfo")
public class SysDbmsTabsColumnResultController {

	@Autowired
	SysDbmsTabsColumnResultService sysDbmsTabsColumnResultService;

	@PostMapping("/findAllByTabUuid")
	public BaseResult<List<SysDbmsTabsColumnResult>> findAllByTabUuid(@RequestBody Page<SysDbmsTabsColsInfo> vo) {
		List<SysDbmsTabsColumnResult> list = sysDbmsTabsColumnResultService.findAllByTabUuid(vo);
		return ResultUtil.success(list);
	}
}
