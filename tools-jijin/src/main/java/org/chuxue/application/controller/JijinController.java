package org.chuxue.application.controller;

import java.util.List;

import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.MybatisBaseConrollerImpl;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.dao.JijinDao;
import org.chuxue.application.po.Flow;
import org.chuxue.application.po.Jijin;
import org.chuxue.application.service.JijinService;
import org.chuxue.application.vo.JijinVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名 ： JinjinController.java
 * 包 名 ： org.chuxue.application.controller
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年8月26日 下午3:20:05
 * 版 本 ： V1.0
 */
@RestController
@RequestMapping("/jijin")
public class JijinController extends MybatisBaseConrollerImpl<JijinService, JijinDao, Jijin> {

	@Autowired
	JijinService jijinService;
	
	@RequestMapping("search")
	public BaseResult<List<JijinVo>> search(@RequestBody Jijin info) {
		List<JijinVo> list = jijinService.search(info);
		return ResultUtil.success(list);
	}
	
	@RequestMapping("mairu")
	public BaseResult<List<JijinVo>> mairu(@RequestBody Flow info) {
		List<JijinVo> list = jijinService.goumai(info);
		return ResultUtil.success(list);
	}
	
	@RequestMapping("maichu")
	public BaseResult<List<JijinVo>> maichu(@RequestBody Flow info) {
		List<JijinVo> list = jijinService.maichu(info);
		return ResultUtil.success(list);
	}
	
}
