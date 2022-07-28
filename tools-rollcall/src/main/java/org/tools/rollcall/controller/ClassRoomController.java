package org.tools.rollcall.controller;

import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tools.rollcall.po.ClassRoom;
import org.tools.rollcall.service.ClassRoomService;
import org.tools.rollcall.vo.ClassRoomVo;

@RestController
@RequestMapping("/classRoom")
public class ClassRoomController extends BaseControllerImpl<ClassRoom> implements BaseController<ClassRoom> {

	@Autowired
	ClassRoomService classRoomService;
	
	@PostMapping("/deleteRoom")
	public BaseResult<String> deleteRoom(@RequestBody ClassRoomVo room) {
		try {
			classRoomService.deleteRoom(room);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}

}
