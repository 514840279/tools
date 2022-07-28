package org.chuxue.application.dbms.code.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsGenerateCodeInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.utils.files.TxtFilesWriter;

/**
 * 文件名 ： GenerateDao.java
 * 包 名 ： org.chuxue.application.dbms.code.service
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年6月30日 上午11:35:53
 * 版 本 ： V1.0
 */
public class GenerateDao {

	/**
	 * 方法名 getGenerateDao
	 * 功能 jpa dao层代码生成
	 * 参数 @param sysDbmsGenerateCodeInfo
	 * 参数 @param tabsInfo
	 * 参数 @param colsInfos
	 * 参数 @param username
	 * 参数 @param pathString
	 * 返回 void
	 * author Administrator @throws
	 */
	public static void getGenerateDao(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, SysDbmsTabsTableInfo tabsInfo, List<SysDbmsTabsColsInfo> colsInfos, String username, String pathString) {
		StringBuilder thirdString = new StringBuilder();
		String[] subpathString = sysDbmsGenerateCodeInfo.getClassPath().split("\\.");
		for (int i = 0; i < subpathString.length && i < 3; i++) {
			thirdString.append(subpathString[i]).append(".");
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("package " + sysDbmsGenerateCodeInfo.getClassPath() + ".dao;\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("import org.chuxue.application.common.base.BaseDao;\r\n");
		
		stringBuilder.append("import " + sysDbmsGenerateCodeInfo.getClassPath() + ".po." + sysDbmsGenerateCodeInfo.getClassName() + ";\r\n");
		stringBuilder.append("import org.springframework.stereotype.Repository;\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("/**\r\n");
		stringBuilder.append(" * @文件名 " + sysDbmsGenerateCodeInfo.getClassName() + "Dao.java\r\n");
		stringBuilder.append(" * @包名 " + sysDbmsGenerateCodeInfo.getClassPath() + ".dao\r\n");
		stringBuilder.append(" * @描述 dao层\r\n");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		stringBuilder.append(" * @时间 " + simpleDateFormat.format(new Date()) + "\r\n");
		stringBuilder.append(" * @author " + username + "\r\n");
		stringBuilder.append(" * @版本 V1.0\r\n");
		stringBuilder.append(" */\r\n");
		stringBuilder.append("@Repository\r\n");
		stringBuilder.append("public interface " + sysDbmsGenerateCodeInfo.getClassName() + "Dao extends BaseDao<" + sysDbmsGenerateCodeInfo.getClassName() + "> {\r\n");
		stringBuilder.append(" \r\n");
		stringBuilder.append("}\r\n");
		stringBuilder.append("");
		
		// 文件写入
		String fineName = pathString + "/" + sysDbmsGenerateCodeInfo.getClassName() + "Dao.java";
		TxtFilesWriter.writeToFile(stringBuilder.toString(), fineName);
		
	}

	/**
	 * 方法名： getGenerateMybatisDao
	 * 功 能： Mybatis 的dao
	 * 参 数： @param sysDbmsGenerateCodeInfo
	 * 参 数： @param tabsInfo
	 * 参 数： @param colsInfos
	 * 参 数： @param username
	 * 参 数： @param string
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	public static void getGenerateMybatisDao(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, SysDbmsTabsTableInfo tabsInfo, List<SysDbmsTabsColsInfo> colsInfos, String username, String pathString) {
		StringBuilder thirdString = new StringBuilder();
		String[] subpathString = sysDbmsGenerateCodeInfo.getClassPath().split("\\.");
		for (int i = 0; i < subpathString.length && i < 3; i++) {
			thirdString.append(subpathString[i]).append(".");
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("package " + sysDbmsGenerateCodeInfo.getClassPath() + ".dao;\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("import org.chuxue.application.common.base.MybatisBaseDao;\r\n");
		
		stringBuilder.append("import " + sysDbmsGenerateCodeInfo.getClassPath() + ".po." + sysDbmsGenerateCodeInfo.getClassName() + ";\r\n");
		stringBuilder.append("import org.apache.ibatis.annotations.Mapper;\r\n");
		
		stringBuilder.append("\r\n");
		stringBuilder.append("/**\r\n");
		stringBuilder.append(" * @文件名 " + sysDbmsGenerateCodeInfo.getClassName() + "Dao.java\r\n");
		stringBuilder.append(" * @包名 " + sysDbmsGenerateCodeInfo.getClassPath() + ".dao\r\n");
		stringBuilder.append(" * @描述 dao层\r\n");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		stringBuilder.append(" * @时间 " + simpleDateFormat.format(new Date()) + "\r\n");
		stringBuilder.append(" * @author " + username + "\r\n");
		stringBuilder.append(" * @版本 V1.0\r\n");
		stringBuilder.append(" */\r\n");
		stringBuilder.append("@Mapper\r\n");
		stringBuilder.append("public interface " + sysDbmsGenerateCodeInfo.getClassName() + "Dao extends MybatisBaseDao<" + sysDbmsGenerateCodeInfo.getClassName() + "> {\r\n");
		stringBuilder.append(" \r\n");
		stringBuilder.append("}\r\n");
		stringBuilder.append("");
		
		// 文件写入
		String fineName = pathString + "/" + sysDbmsGenerateCodeInfo.getClassName() + "Dao.java";
		TxtFilesWriter.writeToFile(stringBuilder.toString(), fineName);
		
	}

	/**
	 * 方法名： getGenerateMybatisXml
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
	public static void getGenerateMybatisXml(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, SysDbmsTabsTableInfo tabsInfo, List<SysDbmsTabsColsInfo> colsInfos, String username, String pathString) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		stringBuilder.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >");
		stringBuilder.append("<mapper namespace=\"org.danyuan.application.dao.WebDao\">");
		stringBuilder.append("");
		stringBuilder.append("</mapper>");
		stringBuilder.append("");

		// 文件写入
		String fineName = pathString + "/" + sysDbmsGenerateCodeInfo.getClassName() + ".xml";
		TxtFilesWriter.writeToFile(stringBuilder.toString(), fineName);

	}
}
