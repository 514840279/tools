package org.tools.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tools.application.dao.TaskDao;
import org.tools.application.po.SysTaskInfo;

/**
 * 文件名 ： TaskService.java
 * 包 名 ： org.tools.application.service
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 作 者 ： wth
 * 时 间 ： 2022年2月12日 上午9:38:32
 * 版 本 ： V1.0
 */
@Service
public class TaskService {
	private Logger	log	= LoggerFactory.getLogger(TaskService.class);
	
	@Autowired
	TaskDao			taskDao;
	
	/**
	 * 方法名： addTask
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 返 回： void
	 * 作 者 ： wth
	 *
	 * @throws
	 */
	public void addTask(SysTaskInfo info) {
		log.info("addTask:{}", info.toString());
		taskDao.addTask(info);
	}
	
	/**
	 * 方法名： updateTask
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 返 回： void
	 * 作 者 ： wth
	 *
	 * @throws
	 */
	public void updateTask(SysTaskInfo info) {
		log.info("updateTask:{}", info.toString());
		taskDao.updateTask(info);
	}

}
