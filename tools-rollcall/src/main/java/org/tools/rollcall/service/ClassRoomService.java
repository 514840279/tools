package org.tools.rollcall.service;

import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tools.rollcall.dao.ClassRoomDao;
import org.tools.rollcall.po.ClassRoom;

@Service
public class ClassRoomService extends BaseServiceImpl<ClassRoom> implements BaseService<ClassRoom> {

	@Autowired
	ClassRoomDao classRoomDao;
}
