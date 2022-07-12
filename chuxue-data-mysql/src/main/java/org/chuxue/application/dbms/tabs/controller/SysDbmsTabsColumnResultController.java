package org.chuxue.application.dbms.tabs.controller;

import java.util.List;
import java.util.Map;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.MybatisBaseConrollerImpl;
import org.chuxue.application.common.base.ResultPage;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsColumnResult;
import org.chuxue.application.dbms.tabs.service.SysDbmsTabsColumnResultService;
import org.chuxue.application.dbms.tabs.vo.SysDbmsTabsTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysDbmsTabsColumnInfo")
public class SysDbmsTabsColumnResultController extends MybatisBaseConrollerImpl<SysDbmsTabsColumnResult> implements BaseController<SysDbmsTabsColumnResult> {

	@Autowired
	SysDbmsTabsColumnResultService sysDbmsTabsColumnResultService;

	@PostMapping("/findAllByTabUuid")
	public BaseResult<List<SysDbmsTabsColumnResult>> findAllByTabUuid(@RequestBody ResultPage<SysDbmsTabsColsInfo> vo) {
		List<SysDbmsTabsColumnResult> list = sysDbmsTabsColumnResultService.findAllByTabUuid(vo);
		return ResultUtil.success(list);
	}
	
	@PostMapping("/searchData")
	public BaseResult<ResultPage<Map<String, Object>>> searchData(@RequestBody SysDbmsTabsTableVo vo) {
		ResultPage<Map<String, Object>> page = sysDbmsTabsColumnResultService.searchData(vo);
		return ResultUtil.success(page);
	}

}
