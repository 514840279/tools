package org.chuxue.application.dbms.tabs.controller;

import java.util.HashMap;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.Pagination;
import org.chuxue.application.dbms.tabs.service.SysDbmsTabsInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/sysDbmsTabsInfo")
public class SysDbmsTabsInfoController extends BaseControllerImpl<SysDbmsTabsInfo> implements BaseController<SysDbmsTabsInfo> {
	
	private static final Logger	logger	= LoggerFactory.getLogger(SysDbmsTabsInfoController.class);
	
	@Autowired
	SysDbmsTabsInfoService		sysDbmsTabsInfoService;
	
	@RequestMapping(value = "/findAllByTableUuid", method = { RequestMethod.POST })
	public BaseResult<Page<SysDbmsTabsInfo>> findAllByTableUuid(@RequestBody Pagination<SysDbmsTabsInfo> vo) {
		logger.info("数据库表信息查询：{}", vo.toString());
		BaseResult<Page<SysDbmsTabsInfo>> page = sysDbmsTabsInfoService.findAllByTableUuid(vo);
		return page;
	}

	@Cacheable(value = "user", key = "#root.methodName+#root.args[0]")
	@GetMapping("/a/{id}")
	public String findWord(@PathVariable String id) {
		System.out.println("Cacheing");
		HashMap<String, String> words = new HashMap<>();
		words.put("1", "java");
		words.put("2", "redis");
		words.put("3", "cache");
		return words.get(id);
	}
	
}
