package org.tools.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tools.application.common.BaseResult;
import org.tools.application.common.ResultUtil;
import org.tools.application.po.SysTaskInfo;
import org.tools.application.service.TaskService;

/**
 * 文件名 ： TaskController.java
 * 包 名 ： org.tools.application.controller
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 作 者 ： wth
 * 时 间 ： 2022年2月12日 上午9:10:33
 * 版 本 ： V1.0
 */
@RestController
@RequestMapping("/task")
public class TaskController {

	private Logger	log	= LoggerFactory.getLogger(TaskController.class);

	@Autowired
	TaskService		taskService;

	@PostMapping("/addTask")
	public BaseResult<SysTaskInfo> addTask(@RequestBody SysTaskInfo info) {
		log.info("addTask:{}", info.toString());
		try {
			taskService.addTask(info);
			return ResultUtil.success();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResultUtil.error(e.getMessage());
		}

	}

	@PostMapping("/updateTask")
	public BaseResult<SysTaskInfo> updateTask(@RequestBody SysTaskInfo info) {
		log.info("updateTask:{}", info.toString());
		try {
			taskService.updateTask(info);
			return ResultUtil.success();
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResultUtil.error(e.getMessage());
		}

	}
	
}
