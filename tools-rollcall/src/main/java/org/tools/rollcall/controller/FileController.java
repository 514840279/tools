package org.tools.rollcall.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.tools.rollcall.common.UserConfig;
import org.tools.rollcall.service.LoadData;

@RestController
@RequestMapping("/file")
public class FileController<T> {
	
	private static final Logger	logger	= LoggerFactory.getLogger(FileController.class);

	@Autowired
	UserConfig					userConfig;

	@Autowired
	LoadData					loadData;
	

	@RequestMapping(path = "/uploadFile")
	public BaseResult<List<String>> uploadFile(HttpServletRequest request) throws UnsupportedEncodingException {
		List<String> listFileName;
		listFileName = new ArrayList<>();
		// 文件保存
		request.setCharacterEncoding("UTF-8");
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = multipartHttpServletRequest.getFiles("file");
		for (MultipartFile multipartFile : files) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String filename = simpleDateFormat.format(new Date()) + "_" + multipartFile.getOriginalFilename();
			InputStream inputStream = null;
			FileOutputStream fos = null;

			String path = userConfig.getFileUploadPath() + "/";
			listFileName.add(path + URLEncoder.encode(filename, "utf-8"));

			File file = new File(path);
			try {
				inputStream = multipartFile.getInputStream();

				if (!file.exists()) {
					file.mkdirs();
				}
				path = path + filename;
				fos = new FileOutputStream(path);

				byte[] b = new byte[1024];
				int byteread = 0;
				while ((byteread = inputStream.read(b)) != -1) {
					fos.write(b, 0, byteread);
					fos.flush();
				}
				fos.close();
				inputStream.close();

			} catch (Exception e) {
				logger.error(e.getMessage());
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
					}
				}
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return ResultUtil.success(listFileName);

	}
	
	@PostMapping("/load")
	public BaseResult<List<String>> load(@RequestBody Map<String, String> map) throws UnsupportedEncodingException {
		try {
			String docPath = map.get("path");
			Boolean flag = loadData.loadData(URLDecoder.decode(docPath, "UTF-8"));
			if (flag) {
				return ResultUtil.success();
			} else {
				return ResultUtil.error("加载未知错误！");
			}
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}
}
