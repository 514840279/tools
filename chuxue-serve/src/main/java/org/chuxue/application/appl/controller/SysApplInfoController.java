package org.chuxue.application.appl.controller;

import org.chuxue.application.bean.manager.appl.SysApplInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @文件名 SysApplInfoController.java
 * @包名 org.chuxue.application.bean.manager.appl.controller
 * @描述 controller层
 * @时间 2022年07月18日 16:07:49
 * @author
 * @版本 V1.0
 */
@RestController
@RequestMapping("/sysApplInfo")
public class SysApplInfoController extends BaseControllerImpl<SysApplInfo> implements BaseController<SysApplInfo> {
	
}
