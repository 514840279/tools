package org.chuxue.application.dbms.code.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsGenerateCodeInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.utils.files.TxtFilesWriter;

/**
 * 文件名 ： GenerateController.java
 * 包 名 ： org.chuxue.application.dbms.code.service
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年6月30日 上午11:34:02
 * 版 本 ： V1.0
 */
public class GenerateController {
	
	/**
	 * 方法名 getGenerateController
	 * 功能 生成controller
	 * 参数 @param sysDbmsGenerateCodeInfo
	 * 参数 @param tabsInfo
	 * 参数 @param colsInfos
	 * 参数 @param username
	 * 参数 @param pathString
	 * 返回 void
	 * author Administrator @throws
	 */
	public static void getGenerateController(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, SysDbmsTabsTableInfo tabsInfo, List<SysDbmsTabsColsInfo> colsInfos, String username, String pathString) {
//		String thirdString = "";
//		String[] subpathString = sysDbmsGenerateCodeInfo.getClassPath().split("\\.");
//		for (int i = 0; i < subpathString.length && i < 3; i++) {
//			thirdString += subpathString[i] + ".";
//		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("package " + sysDbmsGenerateCodeInfo.getClassPath() + ".controller;\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("import org.chuxue.application.common.base.BaseController;\r\n");
		stringBuilder.append("import org.chuxue.application.common.base.BaseControllerImpl;\r\n");
		stringBuilder.append("import " + sysDbmsGenerateCodeInfo.getClassPath() + ".po." + sysDbmsGenerateCodeInfo.getClassName() + ";\r\n");
		stringBuilder.append("import " + sysDbmsGenerateCodeInfo.getClassPath() + ".service." + sysDbmsGenerateCodeInfo.getClassName() + "Service;\r\n");

//		import org.chuxue.application.common.base.BaseController;
//		import org.chuxue.application.common.base.BaseControllerImpl;
//		import org.chuxue.application.common.base.BaseResult;
//		import org.chuxue.application.common.base.Pagination;
//		import org.chuxue.application.common.base.ResultUtil;
		
		stringBuilder.append("import org.slf4j.Logger;\r\n");
		stringBuilder.append("import org.slf4j.LoggerFactory;\r\n");
		stringBuilder.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
//		stringBuilder.append("import org.springframework.web.bind.annotation.GetMapping;\r\n");
//		stringBuilder.append("import org.springframework.web.bind.annotation.PathVariable;\r\n");
		stringBuilder.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
		stringBuilder.append("import org.springframework.web.bind.annotation.RestController;\r\n");
//		stringBuilder.append("import org.springframework.web.servlet.ModelAndView;\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("/**\r\n");
		stringBuilder.append(" * @文件名 " + sysDbmsGenerateCodeInfo.getClassName() + "Controller.java\r\n");
		stringBuilder.append(" * @包名 " + sysDbmsGenerateCodeInfo.getClassPath() + ".controller\r\n");
		stringBuilder.append(" * @描述 controller层\r\n");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		stringBuilder.append(" * @时间 " + simpleDateFormat.format(new Date()) + "\r\n");
		stringBuilder.append(" * @author " + username + "\r\n");
		stringBuilder.append(" * @版本 V1.0\r\n");
		stringBuilder.append(" */\r\n");
		stringBuilder.append("@RestController\r\n");
		String subServiceNameString = sysDbmsGenerateCodeInfo.getClassName().substring(0, 1).toLowerCase() + sysDbmsGenerateCodeInfo.getClassName().substring(1);
		stringBuilder.append("@RequestMapping(\"/" + subServiceNameString + "\")\r\n");
		stringBuilder.append("public class " + sysDbmsGenerateCodeInfo.getClassName() + "Controller extends BaseControllerImpl<" + sysDbmsGenerateCodeInfo.getClassName() + "> implements BaseController<" + sysDbmsGenerateCodeInfo.getClassName() + "> {\r\n");
		stringBuilder.append("\r\n");
//		stringBuilder.append("	private static final Logger		logger	= LoggerFactory.getLogger(" + sysDbmsGenerateCodeInfo.getClassName() + "Controller.class);\r\n");
//		stringBuilder.append("\r\n");
//		stringBuilder.append("	@Autowired\r\n");
//		stringBuilder.append("	" + sysDbmsGenerateCodeInfo.getClassName() + "Service " + subServiceNameString + "Service;\r\n");
//		stringBuilder.append("\r\n");
//		stringBuilder.append("	@GetMapping(\"/detail/{uuid}\")\r\n");
//		stringBuilder.append("	public ModelAndView name(@PathVariable(\"uuid\") String uuid) {\r\n");
//		stringBuilder.append("	    logger.info(\"detail\", " + sysDbmsGenerateCodeInfo.getClassName() + "Controller.class);\r\n");
//		stringBuilder.append("		ModelAndView modelAndView = new ModelAndView(\"" + sysDbmsGenerateCodeInfo.getClassPath().replace(thirdString, "").replace(".", "/") + "/" + subServiceNameString.toLowerCase() + "detail\");\r\n");
//		stringBuilder.append("		" + sysDbmsGenerateCodeInfo.getClassName() + " info = new " + sysDbmsGenerateCodeInfo.getClassName() + "();\r\n");
//		stringBuilder.append("		info.setUuid(uuid);\r\n");
//		stringBuilder.append("		modelAndView.addObject(\"" + subServiceNameString + "\", " + subServiceNameString + "Service.findOne(info));\r\n");
//		stringBuilder.append("		return modelAndView;\r\n");
//		stringBuilder.append("	}\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("}");
		
		// 文件写入
		String fineName = pathString + "/" + sysDbmsGenerateCodeInfo.getClassName() + "Controller.java";
		TxtFilesWriter.writeToFile(stringBuilder.toString(), fineName);
	}
}
