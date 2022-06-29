package org.chuxue.application.dbms.code.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.chuxue.application.bean.manager.dbms.SysDbmsGenerateCodeInfo;
import org.chuxue.application.common.base.BaseController;
import org.chuxue.application.common.base.BaseControllerImpl;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.Pagination;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.dbms.code.service.SysDbmsGenerateCodeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @文件名 SysDbmsGenerateCodeInfoController.java
 * @包名 org.danyuan.application.dbms.code.controller
 * @描述 controller层
 * @时间 2020年04月25日 11:26:55
 * @author test
 * @版本 V1.0
 */
@RestController
@RequestMapping("/sysDbmsGenerateCodeInfo")
public class SysDbmsGenerateCodeInfoController extends BaseControllerImpl<SysDbmsGenerateCodeInfo> implements BaseController<SysDbmsGenerateCodeInfo> {
	
	private static final Logger		logger		= LoggerFactory.getLogger(SysDbmsGenerateCodeInfoController.class);
	private static final String		OUTPUTFILE	= "outputfile";
	
	@Autowired
	SysDbmsGenerateCodeInfoService	sysDbmsGenerateCodeInfoService;
	
	@RequestMapping("/generate")
	public BaseResult<String> generate(@RequestBody Pagination<SysDbmsGenerateCodeInfo> vo) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String pathString = simpleDateFormat.format(new Date());
			sysDbmsGenerateCodeInfoService.generate(vo.getList(), vo.getUsername(), pathString);
			return ResultUtil.success(pathString);
		} catch (Exception e) {
			return ResultUtil.error(-1, e.getMessage());
		}
	}

	@RequestMapping(value = "/downloadCode/{path}", method = RequestMethod.GET)
	public void downloadCode(HttpServletResponse response, @PathVariable("path") String path) throws IOException {
		// 根据参数进行导出xml 并打包返回zip文件路径
		File file = new File(System.getProperty("user.dir") + "/" + OUTPUTFILE + "/" + path + ".zip");
		response.setHeader("content-type", "application/octet-stream");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		ServletOutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(file));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			logger.error("文件{}讀取失敗.", path);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					logger.error("失敗.", e.getMessage());
				}
			}
		}
		if (!file.delete()) {
			logger.error("文件刪除失敗.");
		}
		
	}
	
}
