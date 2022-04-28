package org.tools.rollcall.service;

import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tools.rollcall.dao.PersonDao;
import org.tools.rollcall.po.Person;

@Service
public class PersonService extends BaseServiceImpl<Person> implements BaseService<Person> {
	
	@Autowired
	PersonDao personDao;
}
