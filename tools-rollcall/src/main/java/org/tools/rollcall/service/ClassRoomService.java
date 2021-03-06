package org.tools.rollcall.service;

import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tools.rollcall.dao.ClassRoomDao;
import org.tools.rollcall.dao.PersonDao;
import org.tools.rollcall.po.ClassRoom;
import org.tools.rollcall.po.Person;
import org.tools.rollcall.vo.ClassRoomVo;

@Service
public class ClassRoomService extends BaseServiceImpl<ClassRoom> implements BaseService<ClassRoom> {

	@Autowired
	ClassRoomDao	classRoomDao;
	
	@Autowired
	PersonDao		personDao;

	@Transactional
	public void deleteRoom(ClassRoomVo vo) {
		Person p = new Person(vo.getUuid());
		personDao.deleteAll(personDao.findAll(Example.of(p)));
		ClassRoom room = new ClassRoom();
		BeanUtils.copyProperties(vo, room);
		classRoomDao.delete(room);
		classRoomDao.flush();
	}
}
