package org.tools.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tools.application.service.TableService;

/**
 * 文件名 ： TableController.java
 * 包 名 ： org.tools.application.controller
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 作 者 ： wth
 * 时 间 ： 2022年2月12日 上午9:35:23
 * 版 本 ： V1.0
 */
@RestController
@RequestMapping("/table")
public class TableController {
	
	private Logger	log	= LoggerFactory.getLogger(TableController.class);

	@Autowired
	TableService	tableService;
	
}
