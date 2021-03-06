package org.chuxue.application.dbms.echarts.controller;

import org.chuxue.application.bean.manager.dbms.SysDbmsChartDimensionData;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.dbms.echarts.service.SysDbmsChartDimensionDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @文件名 SysDbmsChartDimensionDataController.java
 * @包名 org.danyuan.application.dbms.echarts.controller
 * @描述 controller层
 * @时间 2020年04月25日 12:15:43
 * @author test
 * @版本 V1.0
 */
@RestController
@RequestMapping("/sysDbmsChartDimensionData")
public class SysDbmsChartDimensionDataController extends BaseControllerImpl<SysDbmsChartDimensionData> implements BaseController<SysDbmsChartDimensionData> {
	
	private static final Logger			logger	= LoggerFactory.getLogger(SysDbmsChartDimensionDataController.class);
	
	@Autowired
	SysDbmsChartDimensionDataService	sysDbmsChartDimensionDataService;
	
}
