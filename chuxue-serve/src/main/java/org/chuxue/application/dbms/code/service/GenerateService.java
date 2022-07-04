package org.chuxue.application.dbms.code.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsGenerateCodeInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.utils.files.TxtFilesWriter;

/**
 * 文件名 ： GenerateService.java
 * 包 名 ： org.chuxue.application.dbms.code.service
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年6月30日 上午11:35:03
 * 版 本 ： V1.0
 */
public class GenerateService {

	/**
	 * 方法名 getGenerateService
	 * 功能 service层代码生成
	 * 参数 @param sysDbmsGenerateCodeInfo
	 * 参数 @param tabsInfo
	 * 参数 @param colsInfos
	 * 参数 @param username
	 * 参数 @param pathString
	 * 返回 void
	 * author Administrator @throws
	 */
	public static void getGenerateService(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, SysDbmsTabsTableInfo tabsInfo, List<SysDbmsTabsColsInfo> colsInfos, String username, String pathString) {
//		String thirdString = "";
//		String[] subpathString = sysDbmsGenerateCodeInfo.getClassPath().split("\\.");
//		for (int i = 0; i < subpathString.length && i < 3; i++) {
//			thirdString += subpathString[i] + ".";
//		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("package " + sysDbmsGenerateCodeInfo.getClassPath() + ".service;\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("import org.chuxue.application.common.base.BaseException;\r\n");
		stringBuilder.append("import org.chuxue.application.common.base.BaseService;\r\n");
		stringBuilder.append("import org.chuxue.application.common.base.BaseServiceImpl;\r\n");
		
		stringBuilder.append("import " + sysDbmsGenerateCodeInfo.getClassPath() + ".po." + sysDbmsGenerateCodeInfo.getClassName() + ";\r\n");
		// stringBuilder.append("import " + sysDbmsGenerateCodeInfo.getClassPath() +
		// ".dao." + sysDbmsGenerateCodeInfo.getClassName() + "Dao;\r\n");
		stringBuilder.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
		stringBuilder.append("import org.springframework.stereotype.Service;\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("/**\r\n");
		stringBuilder.append(" * @文件名 " + sysDbmsGenerateCodeInfo.getClassName() + "Service.java\r\n");
		stringBuilder.append(" * @包名 " + sysDbmsGenerateCodeInfo.getClassPath() + ".service\r\n");
		stringBuilder.append(" * @描述 service层\r\n");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		stringBuilder.append(" * @时间 " + simpleDateFormat.format(new Date()) + "\r\n");
		stringBuilder.append(" * @author " + username + "\r\n");
		stringBuilder.append(" * @版本 V1.0\r\n");
		stringBuilder.append(" */\r\n");
		stringBuilder.append("@Service\r\n");
		stringBuilder.append("public class " + sysDbmsGenerateCodeInfo.getClassName() + "Service extends BaseServiceImpl<" + sysDbmsGenerateCodeInfo.getClassName() + "> implements BaseService<" + sysDbmsGenerateCodeInfo.getClassName() + "> {\r\n");
		// stringBuilder.append(" @Autowired\r\n");
		// stringBuilder.append(" private " + sysDbmsGenerateCodeInfo.getClassName() +
		// "Dao " + sysDbmsGenerateCodeInfo.getClassName().substring(0, 1).toLowerCase()
		// + sysDbmsGenerateCodeInfo.getClassName().substring(1) + "Dao;\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("}\r\n");
		stringBuilder.append("");
		
		// 文件写入
		String fineName = pathString + "/" + sysDbmsGenerateCodeInfo.getClassName() + "Service.java";
		TxtFilesWriter.writeToFile(stringBuilder.toString(), fineName);
	}
	
	/**
	 * 方法名： getGenerateMybatisService
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param sysDbmsGenerateCodeInfo
	 * 参 数： @param tabsInfo
	 * 参 数： @param colsInfos
	 * 参 数： @param username
	 * 参 数： @param string
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	public static void getGenerateMybatisService(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, SysDbmsTabsTableInfo tabsInfo, List<SysDbmsTabsColsInfo> colsInfos, String username, String string) {
		// TODO Auto-generated method stub

	}
}
