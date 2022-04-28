package org.tools.rollcall.controller;

import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tools.rollcall.po.ClassRoom;
import org.tools.rollcall.service.ClassRoomService;

@RestController
@RequestMapping("/classRoom")
public class ClassRoomController extends BaseControllerImpl<ClassRoom> implements BaseController<ClassRoom> {
	
	@Autowired
	ClassRoomService classRoomService;
}
