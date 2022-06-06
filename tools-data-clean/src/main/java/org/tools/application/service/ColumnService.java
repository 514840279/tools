package org.tools.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tools.application.dao.ColumnDao;

/**
 * 文件名 ： ColumnService.java
 * 包 名 ： org.tools.application.service
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 作 者 ： wth
 * 时 间 ： 2022年2月12日 上午9:45:41
 * 版 本 ： V1.0
 */
@Service
public class ColumnService {

	private Logger	log	= LoggerFactory.getLogger(ColumnService.class);
	
	@Autowired
	ColumnDao		columnDao;

}
