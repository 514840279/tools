package org.tools.rollcall.controller;

import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tools.rollcall.po.Person;
import org.tools.rollcall.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController extends BaseControllerImpl<Person> implements BaseController<Person> {
	
	@Autowired
	PersonService personService;
}
