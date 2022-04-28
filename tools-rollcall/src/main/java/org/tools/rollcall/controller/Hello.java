package org.tools.rollcall.controller;

import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
	
	@RequestMapping("/test")
	public BaseResult<String> test() {
		return ResultUtil.success("test");
	}
}
