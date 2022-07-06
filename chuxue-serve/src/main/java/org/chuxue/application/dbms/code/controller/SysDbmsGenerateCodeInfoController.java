package org.chuxue.application.dbms.code.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	@RequestMapping(value = "/downloadCode", method = RequestMethod.POST)
	public void downloadCode(HttpServletResponse response, @RequestBody String path) throws IOException {
		// 根据参数进行导出xml 并打包返回zip文件路径
		File file = new File(System.getProperty("user.dir") + "/" + OUTPUTFILE + "/" + path + ".zip");
		response.reset();
		response.setContentType("bin");
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		// 创建缓冲区
		byte[] buff = new byte[1024];
		InputStream in = null;
		OutputStream out = null;
		try {
			// 创建输出流
			out = response.getOutputStream();
			// 读取要下载的文件，保存到文件输入流
			in = new FileInputStream(file);
			int len = -1;
			while ((len = in.read(buff)) > 0) {
				out.write(buff, 0, len);
			}

			// 关闭文件流
			in.close();
			// 关闭输出流
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("文件{}讀取失敗.", path);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("失敗.", e.getMessage());
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("失敗.", e.getMessage());
				}
			}
		}

	}

}
