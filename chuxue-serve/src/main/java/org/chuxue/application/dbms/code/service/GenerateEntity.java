package org.chuxue.application.dbms.code.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsGenerateCodeInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.utils.files.TxtFilesWriter;
import org.chuxue.application.common.utils.string.StringUtils;

/**
 * @文件名 GenerateEntity.java
 * @包名 org.danyuan.application.dbms.code.service
 * @描述 实体代码生成
 * @时间 2019年1月17日 上午11:46:53
 * @author Administrator
 * @版本 V1.0
 */
public class GenerateEntity {
	
	/**
	 * 方法名 generate
	 * 功能 生成实体类文件 jpa 版
	 * 参数 @param sysDbmsGenerateCodeInfo
	 * 参数 @param tabsInfo
	 * 参数 @param colsInfos
	 * 参数 @param username
	 * 参数 @param pathString
	 * 返回 void
	 *
	 * @author Administrator @throws
	 */
	public static void generate(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, SysDbmsTabsTableInfo tabsInfo, List<SysDbmsTabsColsInfo> colsInfos, String username, String pathString) {
		StringBuilder thirdString = new StringBuilder();
		String[] subpathString = sysDbmsGenerateCodeInfo.getClassPath().split("\\.");
		for (int i = 0; i < subpathString.length && i < 3; i++) {
			thirdString.append(subpathString[i]).append(".");
		}
		// 拼接文件内容
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("package " + sysDbmsGenerateCodeInfo.getClassPath() + ".po;\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("import java.io.Serializable;\r\n");
		// 拼接字段和get，set方法和需要的引入
		StringBuilder stringBuilderProperties = new StringBuilder();
		StringBuilder stringBuilderMethod = new StringBuilder();
		StringBuilder stringBuilderImport = new StringBuilder();
		spellString(colsInfos, stringBuilderProperties, stringBuilderMethod, stringBuilderImport);
		if (stringBuilderImport.toString().contains("import java.util.Date;")) {
			stringBuilderImport.append("import javax.persistence.Temporal;\r\n");
			stringBuilderImport.append("import javax.persistence.TemporalType;\r\n");
			stringBuilderImport.append("\r\n");
			stringBuilderImport.append("import com.fasterxml.jackson.annotation.JsonFormat;\r\n");
		}
		stringBuilder.append(stringBuilderImport);
		stringBuilder.append("import javax.persistence.Column;\r\n");
		stringBuilder.append("import javax.persistence.Entity;\r\n");
		stringBuilder.append("import javax.persistence.NamedQuery;\r\n");
		stringBuilder.append("import javax.persistence.Table;\r\n");
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateLombok())) {
			stringBuilder.append("import lombok.Getter;\r\n");
			stringBuilder.append("import lombok.Setter;\r\n");
		}
		
		stringBuilder.append("\r\n");
		stringBuilder.append("import org.chuxue.application.common.base.BaseEntity;\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("/**\r\n");
		stringBuilder.append(" * @文件名 " + sysDbmsGenerateCodeInfo.getClassName() + ".java\r\n");
		stringBuilder.append(" * @包名 " + sysDbmsGenerateCodeInfo.getClassPath() + ".po\r\n");
		stringBuilder.append(" * @描述 " + tabsInfo.getTabsName().substring(tabsInfo.getTabsName().lastIndexOf(".") + 1) + "的实体类\r\n");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		stringBuilder.append(" * @时间 " + simpleDateFormat.format(new Date()) + "\r\n");
		stringBuilder.append(" * @author " + username + "\r\n");
		stringBuilder.append(" * @版本 V1.0\r\n");
		stringBuilder.append(" */\r\n");
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateLombok())) {
			stringBuilder.append("@Setter\r\n");
			stringBuilder.append("@Getter\r\n");
		}
		stringBuilder.append("@Entity\r\n");
		stringBuilder.append("@Table(name = \"" + tabsInfo.getTabsName().substring(tabsInfo.getTabsName().lastIndexOf(".") + 1) + "\")\r\n");
		stringBuilder.append("@NamedQuery(name = \"" + sysDbmsGenerateCodeInfo.getClassName() + ".findAll\", query = \"SELECT s FROM " + sysDbmsGenerateCodeInfo.getClassName() + " s\")\r\n");
		stringBuilder.append("public class " + sysDbmsGenerateCodeInfo.getClassName() + " extends BaseEntity implements Serializable {\r\n");
		stringBuilder.append("	private static final long	serialVersionUID	= 1L;\r\n");
		stringBuilder.append("\r\n");
		
		// 拼接属性
		stringBuilder.append(stringBuilderProperties);
		stringBuilder.append("\r\n");
		// 默认构造
		stringBuilder.append("	/**  \r\n");
		stringBuilder.append("	*  构造方法： \r\n");
		stringBuilder.append("	*  描    述： 默认构造函数  \r\n");
		stringBuilder.append("	*  参    数： \r\n");
		stringBuilder.append("	*  作    者 ： " + username + "  \r\n");
		stringBuilder.append("	*  @throws  \r\n");
		stringBuilder.append("	*/\r\n");
		stringBuilder.append("	public " + sysDbmsGenerateCodeInfo.getClassName() + "() {\r\n");
		stringBuilder.append("		super();\r\n");
		stringBuilder.append("	}\r\n");
		stringBuilder.append("\r\n");
		// lombok 不需要添加getset
		if (sysDbmsGenerateCodeInfo.getGenerateLombok() == null || !"Y".equals(sysDbmsGenerateCodeInfo.getGenerateLombok())) {
			// 拼接get，set
			stringBuilder.append(stringBuilderMethod);
		}
		stringBuilder.append("\r\n");
		
		// TODO 构造
		
		// TODO tostring
		
		// ...
		stringBuilder.append("\r\n");
		stringBuilder.append("}");
		
		// 文件写入
		String fineName = pathString + "/" + sysDbmsGenerateCodeInfo.getClassName() + ".java";
		TxtFilesWriter.writeToFile(stringBuilder.toString(), fineName);
	}
	
	/**
	 * 方法名： spellString
	 * 功 能： 拼接字段和get，set方法
	 * 参 数： @param colsInfos
	 * 参 数： @param stringBuilderProperties
	 * 参 数： @param stringBuilderMethod
	 * 返 回： void
	 * 作 者 ：wang @throws
	 */
	private static void spellString(List<SysDbmsTabsColsInfo> colsInfos, StringBuilder stringBuilderProperties, StringBuilder stringBuilderMethod, StringBuilder stringBuilderImport) {
		
		for (SysDbmsTabsColsInfo sysDbmsTabsColsInfo : colsInfos) {
			// 属性
			String colsName = sysDbmsTabsColsInfo.getColsName().toLowerCase();
			if ("uuid".equals(colsName) || "discription".equals(colsName) || "create_time".equals(colsName) || "create_user".equals(colsName) || "update_time".equals(colsName) || "update_user".equals(colsName) || "delete_flag".equals(colsName) || "sort".equals(colsName)) {
				continue;
			}
			String propertiesName = makeProperties(colsName);
			String colsDesc = sysDbmsTabsColsInfo.getColsDesc();
			String colsType = sysDbmsTabsColsInfo.getColsType().toLowerCase();
			Integer length = sysDbmsTabsColsInfo.getColsLength();
			String nullable = nullableFormatter(sysDbmsTabsColsInfo.getNullable());

			Integer dataprecision = sysDbmsTabsColsInfo.getDataPrecision();
			Integer datascale = sysDbmsTabsColsInfo.getDataScale();
			// 确定 对应的数据类型
			String propertiesType = "";
			if (colsType.contains("char") || colsType.contains("text")) {
				propertiesType = " String ";
			} else if (colsType.contains("int") || colsType.contains("number") || colsType.contains("boolean")) {
				if (colsType.contains("number") && colsType.contains(",")) {
					propertiesType = " BigDecimal ";
					if (!stringBuilderImport.toString().contains("import java.math.BigDecimal;")) {
						stringBuilderImport.append("import java.math.BigDecimal;\r\n");
					}
				} else {
					propertiesType = " Integer ";
				}
			} else if (colsType.contains("date") || colsType.contains("time")) {
				propertiesType = " Date ";
				if (!stringBuilderImport.toString().contains("import java.util.Date;")) {
					stringBuilderImport.append("import java.util.Date;\r\n");
				}
			} else if (colsType.contains("double") || colsType.contains("float") || colsType.contains("decimal")) {
				propertiesType = " BigDecimal ";
			}
			
			// 拼写属性
			spellProperties(stringBuilderProperties, propertiesName, propertiesType, colsDesc, colsType, colsName, length, nullable, dataprecision, datascale);
			
			// 拼写get，set
			spellMethod(stringBuilderMethod, propertiesName, propertiesType, colsDesc, colsType, colsName);
		}
		if (stringBuilderImport.toString().length() > 0) {
			stringBuilderImport.append("\r\n");
		}
		
	}
	
	/**
	 * 方法名： nullableFormatter
	 * 功 能： 格式化nullable
	 * 参 数： @param nullable
	 * 参 数： @return
	 * 返 回： String
	 * 作 者 ： Administrator
	 * @throws
	 */
	private static String nullableFormatter(String nullable) {
		if (StringUtils.isBankCard(nullable)) {
			return null;
		}
		if ("1".equals(nullable)) {
			return "Y";
		} else if ("0".equals(nullable)) {
			return "N";
		} else {
			return nullable;
		}
	}
	
	/**
	 * 方法名： spellMethod
	 * 功 能： 拼写get，set
	 * 参 数： @param stringBuilderMethod
	 * 参 数： @param propertiesName
	 * 参 数： @param propertiesType
	 * 参 数： @param colsDesc
	 * 参 数： @param colsType
	 * 参 数： @param colsName
	 * 返 回： void
	 * 作 者 ： wang @throws
	 */
	private static void spellMethod(StringBuilder stringBuilderMethod, String propertiesName, String propertiesType, String colsDesc, String colsType, String colsName) {
		String upPropertiesName = propertiesName.substring(0, 1).toUpperCase() + propertiesName.substring(1);
		// get
		stringBuilderMethod.append("\r\n");
		stringBuilderMethod.append("	/**\r\n");
		stringBuilderMethod.append("	 * 方法名 ： get" + upPropertiesName + "\r\n");
		stringBuilderMethod.append("	 * 功 能 ： 返回变量 " + propertiesName + " " + colsDesc + " 的值\r\n");
		stringBuilderMethod.append("	 *\r\n");
		stringBuilderMethod.append("	 * @return: String \r\n");
		stringBuilderMethod.append("	 */\r\n");
		stringBuilderMethod.append("	public " + propertiesType + " get" + upPropertiesName + "() {\r\n");
		stringBuilderMethod.append("		return " + propertiesName + ";\r\n");
		stringBuilderMethod.append("	}\r\n");
		// set
		stringBuilderMethod.append("\r\n");
		stringBuilderMethod.append("	/**\r\n");
		stringBuilderMethod.append("	 * 方法名 ： set" + upPropertiesName + "\r\n");
		stringBuilderMethod.append("	 * 功 能 ： 设置变量 " + propertiesName + " " + colsDesc + " 的值\r\n");
		stringBuilderMethod.append("	 */\r\n");
		stringBuilderMethod.append("	public void set" + upPropertiesName + "(" + propertiesType + " " + propertiesName + ") {\r\n");
		stringBuilderMethod.append("		this." + propertiesName + " = " + propertiesName + ";\r\n");
		stringBuilderMethod.append("	}\r\n");
	}
	
	/**
	 * 方法名： spellProperties
	 * 功 能： 拼写属性
	 * 参 数： @param stringBuilderProperties
	 * 参 数： @param propertiesName
	 * 参 数： @param propertiesType
	 * 参 数： @param colsDesc
	 * 参 数： @param colsType
	 * 参 数： @param colsName
	 * 返 回： void
	 * 作 者 ： wang @throws
	 */
	private static void spellProperties(StringBuilder stringBuilderProperties, String propertiesName, String propertiesType, String colsDesc, String colsType, String colsName, Integer length, String nullable, Integer dataprecision, Integer scale) {
		stringBuilderProperties.append("\r\n");
		stringBuilderProperties.append("	// " + colsDesc + "\r\n");
		if (colsType != null) {
			if ("datetime".equals(colsType.toLowerCase()) || colsType.toLowerCase().contains("datetime")) {
				stringBuilderProperties.append("	@Temporal(TemporalType.DATE)\r\n");
				stringBuilderProperties.append("	@DateTimeFormat(style = \"yyyy-MM-dd HH:mm:ss\")\r\n");
				stringBuilderProperties.append("	@JsonFormat(locale = \"zh\", timezone = \"GMT+8\", pattern = \"yyyy-MM-dd HH:mm:ss\")\r\n");
				stringBuilderProperties.append("	@Column(name = \"" + colsName + "\",nullable =" + ("Y".equals(nullable) ? "true" : "false") + ")\r\n");
			} else if ("date".equals(colsType.toLowerCase()) || colsType.toLowerCase().contains("date")) {
				stringBuilderProperties.append("	@Temporal(TemporalType.DATE)\r\n");
				stringBuilderProperties.append("	@DateTimeFormat(style = \"yyyy-MM-dd\")\r\n");
				stringBuilderProperties.append("	@JsonFormat(locale = \"zh\", timezone = \"GMT+8\", pattern = \"yyyy-MM-dd\")\r\n");
				stringBuilderProperties.append("	@Column(name = \"" + colsName + "\",nullable =" + ("Y".equals(nullable) ? "true" : "false") + ")\r\n");
			} else if ("timestamp".equals(colsType.toLowerCase()) || colsType.toLowerCase().contains("timestamp")) {
				stringBuilderProperties.append("	@Temporal(TemporalType.TIMESTAMP)\r\n");
				stringBuilderProperties.append("	@DateTimeFormat(style = \"yyyy-MM-dd HH:mm:ss\")\r\n");
				stringBuilderProperties.append("	@JsonFormat(locale = \"zh\", timezone = \"GMT+8\", pattern = \"yyyy-MM-dd HH:mm:ss\")\r\n");
				stringBuilderProperties.append("	@Column(name = \"" + colsName + "\",nullable =" + ("Y".equals(nullable) ? "true" : "false") + ")\r\n");
			} else if ("time".equals(colsType.toLowerCase()) || colsType.toLowerCase().contains("time")) {
				stringBuilderProperties.append("	@Temporal(TemporalType.TIME)\r\n");
				stringBuilderProperties.append("	@DateTimeFormat(style = \"HH:mm:ss\")\r\n");
				stringBuilderProperties.append("	@JsonFormat(locale = \"zh\", timezone = \"GMT+8\", pattern = \"HH:mm:ss\")\r\n");
				stringBuilderProperties.append("	@Column(name = \"" + colsName + "\",nullable =" + ("Y".equals(nullable) ? "true" : "false") + ")\r\n");
			} else if ("int".equals(colsType.toLowerCase()) || "long".equals(colsType.toLowerCase()) || "tinyint".equals(colsType.toLowerCase())) {
				stringBuilderProperties.append("	@Column(name = \"" + colsName + "\"" + (dataprecision == null ? "" : ",precision=" + dataprecision) + ")\r\n");
			} else if ("number".equals(colsType.toLowerCase()) || "float".equals(colsType.toLowerCase()) || "double".equals(colsType.toLowerCase()) || "decimal".equals(colsType.toLowerCase())) {
				stringBuilderProperties.append("	@Column(name = \"" + colsName + "\"" + (dataprecision == null ? "" : ",precision=" + dataprecision) + (scale == null ? "" : ",scale=" + scale) + ")\r\n");
			} else if ("char".equals(colsType.toLowerCase()) || "varchar".equals(colsType.toLowerCase()) || "text".equals(colsType.toLowerCase()) || "varchar2".equals(colsType.toLowerCase()) || colsType.toLowerCase().contains("char")) {
				stringBuilderProperties.append("	@Column(name = \"" + colsName + "\"" + (dataprecision == null ? "" : ",precision=" + dataprecision) + ")\r\n");
				
			} else {
				stringBuilderProperties.append("	@Column(name = \"" + colsName + "\")\r\n");
			}
		} else {
			stringBuilderProperties.append("	@Column(name = \"" + colsName + "\")\r\n");
		}
		
		stringBuilderProperties.append("	private " + propertiesType + "	" + propertiesName + ";\r\n");
	}
	
	/**
	 * 方法名： makeProperties
	 * 功 能： 属性生成
	 * 参 数： @param colsName
	 * 参 数： @return
	 * 返 回： String
	 * 作 者 ： wang @throws
	 */
	public static String makeProperties(String colsName) {
		String[] strs = colsName.split("_");
		String propertiesName = "";
		for (int i = 0; i < strs.length; i++) {
			if (i > 0) {
				propertiesName += strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1);
			} else {
				propertiesName = strs[i];
			}
		}
		return propertiesName;
	}

	/**
	 * 方法名： generateMybatis
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
	public static void generateMybatis(SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, SysDbmsTabsTableInfo tabsInfo, List<SysDbmsTabsColsInfo> colsInfos, String username, String pathString) {
		StringBuilder thirdString = new StringBuilder();
		String[] subpathString = sysDbmsGenerateCodeInfo.getClassPath().split("\\.");
		for (int i = 0; i < subpathString.length && i < 3; i++) {
			thirdString.append(subpathString[i]).append(".");
		}
		// 拼接文件内容
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("package " + sysDbmsGenerateCodeInfo.getClassPath() + ".po;\r\n");
		stringBuilder.append("\r\n");
		stringBuilder.append("import java.io.Serializable;\r\n");
		// 拼接字段和get，set方法和需要的引入
		StringBuilder stringBuilderProperties = new StringBuilder();
		StringBuilder stringBuilderMethod = new StringBuilder();
		StringBuilder stringBuilderImport = new StringBuilder();
		spellMybatisString(colsInfos, stringBuilderProperties, stringBuilderMethod, stringBuilderImport);
		if (stringBuilderImport.toString().contains("import java.util.Date;")) {
			stringBuilderImport.append("\r\n");
			stringBuilderImport.append("import com.fasterxml.jackson.annotation.JsonFormat;\r\n");
		}
		stringBuilder.append(stringBuilderImport);
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateLombok())) {
			stringBuilder.append("import lombok.Getter;\r\n");
			stringBuilder.append("import lombok.Setter;\r\n");
		}
		
		stringBuilder.append("\r\n");
		stringBuilder.append("/**\r\n");
		stringBuilder.append(" * @文件名 " + sysDbmsGenerateCodeInfo.getClassName() + ".java\r\n");
		stringBuilder.append(" * @包名 " + sysDbmsGenerateCodeInfo.getClassPath() + ".po\r\n");
		stringBuilder.append(" * @描述 " + tabsInfo.getTabsName().substring(tabsInfo.getTabsName().lastIndexOf(".") + 1) + "的实体类\r\n");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		stringBuilder.append(" * @时间 " + simpleDateFormat.format(new Date()) + "\r\n");
		stringBuilder.append(" * @author " + username + "\r\n");
		stringBuilder.append(" * @版本 V1.0\r\n");
		stringBuilder.append(" */\r\n");
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateLombok())) {
			stringBuilder.append("@Setter\r\n");
			stringBuilder.append("@Getter\r\n");
		}
		stringBuilder.append("@TableName(value =  \"" + tabsInfo.getTabsName().substring(tabsInfo.getTabsName().lastIndexOf(".") + 1) + "\")\r\n");
		stringBuilder.append("public class " + sysDbmsGenerateCodeInfo.getClassName() + " extends BaseEntity implements Serializable {\r\n");
		stringBuilder.append("	private static final long	serialVersionUID	= 1L;\r\n");
		stringBuilder.append("\r\n");
		
		// 拼接属性
		stringBuilder.append(stringBuilderProperties);
		stringBuilder.append("\r\n");
		// 默认构造
		stringBuilder.append("	/**  \r\n");
		stringBuilder.append("	*  构造方法： \r\n");
		stringBuilder.append("	*  描    述： 默认构造函数  \r\n");
		stringBuilder.append("	*  参    数： \r\n");
		stringBuilder.append("	*  作    者 ： " + username + "  \r\n");
		stringBuilder.append("	*  @throws  \r\n");
		stringBuilder.append("	*/\r\n");
		stringBuilder.append("	public " + sysDbmsGenerateCodeInfo.getClassName() + "() {\r\n");
		stringBuilder.append("		super();\r\n");
		stringBuilder.append("	}\r\n");
		stringBuilder.append("\r\n");
		// lombok 不需要添加getset
		if (sysDbmsGenerateCodeInfo.getGenerateLombok() == null || !"Y".equals(sysDbmsGenerateCodeInfo.getGenerateLombok())) {
			// 拼接get，set
			stringBuilder.append(stringBuilderMethod);
		}
		stringBuilder.append("\r\n");
		
		// TODO 构造
		
		// TODO tostring
		
		// ...
		stringBuilder.append("\r\n");
		stringBuilder.append("}");
		
		// 文件写入
		String fineName = pathString + "/" + sysDbmsGenerateCodeInfo.getClassName() + ".java";
		TxtFilesWriter.writeToFile(stringBuilder.toString(), fineName);
		
	}

	/**
	 * 方法名： spellMybatisString
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param colsInfos
	 * 参 数： @param stringBuilderProperties
	 * 参 数： @param stringBuilderMethod
	 * 参 数： @param stringBuilderImport
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	private static void spellMybatisString(List<SysDbmsTabsColsInfo> colsInfos, StringBuilder stringBuilderProperties, StringBuilder stringBuilderMethod, StringBuilder stringBuilderImport) {
		for (SysDbmsTabsColsInfo sysDbmsTabsColsInfo : colsInfos) {
			// 属性
			String colsName = sysDbmsTabsColsInfo.getColsName().toLowerCase();
			if ("uuid".equals(colsName) || "discription".equals(colsName) || "create_time".equals(colsName) || "create_user".equals(colsName) || "update_time".equals(colsName) || "update_user".equals(colsName) || "delete_flag".equals(colsName) || "sort".equals(colsName)) {
				continue;
			}
			String propertiesName = makeProperties(colsName);
			String colsDesc = sysDbmsTabsColsInfo.getColsDesc();
			String colsType = sysDbmsTabsColsInfo.getColsType().toLowerCase();
			Integer length = sysDbmsTabsColsInfo.getColsLength();
			String nullable = nullableFormatter(sysDbmsTabsColsInfo.getNullable());

			Integer dataprecision = sysDbmsTabsColsInfo.getDataPrecision();
			Integer datascale = sysDbmsTabsColsInfo.getDataScale();
			// 确定 对应的数据类型
			String propertiesType = "";
			if (colsType.contains("char") || colsType.contains("text")) {
				propertiesType = " String ";
			} else if (colsType.contains("int") || colsType.contains("number") || colsType.contains("boolean")) {
				if (colsType.contains("number") && colsType.contains(",")) {
					propertiesType = " BigDecimal ";
					if (!stringBuilderImport.toString().contains("import java.math.BigDecimal;")) {
						stringBuilderImport.append("import java.math.BigDecimal;\r\n");
					}
				} else {
					propertiesType = " Integer ";
				}
			} else if (colsType.contains("date") || colsType.contains("time")) {
				propertiesType = " Date ";
				if (!stringBuilderImport.toString().contains("import java.util.Date;")) {
					stringBuilderImport.append("import java.util.Date;\r\n");
				}
			} else if (colsType.contains("double") || colsType.contains("float") || colsType.contains("decimal")) {
				propertiesType = " BigDecimal ";
			}
			
			// 拼写属性
			spellMybatisProperties(stringBuilderProperties, propertiesName, propertiesType, colsDesc, colsType, colsName, length, nullable, dataprecision, datascale);
			
			// 拼写get，set
			spellMethod(stringBuilderMethod, propertiesName, propertiesType, colsDesc, colsType, colsName);
		}
		if (stringBuilderImport.toString().length() > 0) {
			stringBuilderImport.append("\r\n");
		}
	}

	/**
	 * 方法名： spellMybatisProperties
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param stringBuilderProperties
	 * 参 数： @param propertiesName
	 * 参 数： @param propertiesType
	 * 参 数： @param colsDesc
	 * 参 数： @param colsType
	 * 参 数： @param colsName
	 * 参 数： @param length
	 * 参 数： @param nullable
	 * 参 数： @param dataprecision
	 * 参 数： @param datascale
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	private static void spellMybatisProperties(StringBuilder stringBuilderProperties, String propertiesName, String propertiesType, String colsDesc, String colsType, String colsName, Integer length, String nullable, Integer dataprecision, Integer scale) {
		stringBuilderProperties.append("\r\n");
		stringBuilderProperties.append("	// " + colsDesc + "\r\n");
		if (colsType != null) {
			if ("datetime".equals(colsType.toLowerCase()) || colsType.toLowerCase().contains("datetime")) {
				stringBuilderProperties.append("	@JsonFormat(locale = \"zh\", timezone = \"GMT+8\", pattern = \"yyyy-MM-dd HH:mm:ss\")\r\n");
				stringBuilderProperties.append("	@TableField(value = \"" + colsName + "\")\r\n");
			} else if ("date".equals(colsType.toLowerCase()) || colsType.toLowerCase().contains("date")) {
				stringBuilderProperties.append("	@JsonFormat(locale = \"zh\", timezone = \"GMT+8\", pattern = \"yyyy-MM-dd\")\r\n");
				stringBuilderProperties.append("	@TableField(value = \"" + colsName + "\")\r\n");
			} else if ("timestamp".equals(colsType.toLowerCase()) || colsType.toLowerCase().contains("timestamp")) {
				stringBuilderProperties.append("	@JsonFormat(locale = \"zh\", timezone = \"GMT+8\", pattern = \"yyyy-MM-dd HH:mm:ss\")\r\n");
				stringBuilderProperties.append("	@TableField(value = \"" + colsName + "\")\r\n");
			} else if ("time".equals(colsType.toLowerCase()) || colsType.toLowerCase().contains("time")) {
				stringBuilderProperties.append("	@JsonFormat(locale = \"zh\", timezone = \"GMT+8\", pattern = \"HH:mm:ss\")\r\n");
				stringBuilderProperties.append("	@TableField(value = \"" + colsName + "\")\r\n");
			} else if ("int".equals(colsType.toLowerCase()) || "long".equals(colsType.toLowerCase()) || "tinyint".equals(colsType.toLowerCase())) {
				stringBuilderProperties.append("	@TableField(value = \"" + colsName + "\")\r\n");
			} else if ("number".equals(colsType.toLowerCase()) || "float".equals(colsType.toLowerCase()) || "double".equals(colsType.toLowerCase()) || "decimal".equals(colsType.toLowerCase())) {
				stringBuilderProperties.append("	@TableField(value = \"" + colsName + "\"" + (scale == null ? "" : ",numericScale=" + scale) + ")\r\n");
			} else if ("text".equals(colsType.toLowerCase()) || colsType.toLowerCase().contains("text")) {
				stringBuilderProperties.append("	@TableField(value = \"" + colsName + "\",select= false )\r\n");
				
			} else if ("char".equals(colsType.toLowerCase()) || "varchar".equals(colsType.toLowerCase()) || "text".equals(colsType.toLowerCase()) || "varchar2".equals(colsType.toLowerCase()) || colsType.toLowerCase().contains("char")) {
				stringBuilderProperties.append("	@TableField(value = \"" + colsName + "\"" + ")\r\n");
				
			} else {
				stringBuilderProperties.append("	@TableField(value = \"" + colsName + "\")\r\n");
			}
		} else {
			stringBuilderProperties.append("	@TableField(value = \"" + colsName + "\")\r\n");
		}
		
		stringBuilderProperties.append("	private " + propertiesType + "	" + propertiesName + ";\r\n");

	}
	
}
