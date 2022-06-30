package org.chuxue.application.bean.manager.dbms;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.chuxue.application.common.base.BaseEntity;

/**
 * @文件名 SysDbmsGenerateCodeInfo.java
 * @包名 org.danyuan.application.dbms.code.po
 * @描述 sys_dbms_generate_code_info的实体类
 * @时间 2020年04月25日 11:26:55
 * @author test
 * @版本 V1.0
 */
@Entity
@Table(name = "sys_dbms_generate_code_info")
@NamedQuery(name = "SysDbmsGenerateCodeInfo.findAll", query = "SELECT s FROM SysDbmsGenerateCodeInfo s")
public class SysDbmsGenerateCodeInfo extends BaseEntity implements Serializable {
	private static final long	serialVersionUID	= 1L;

	@Column(name = "table_uuid")
	private String				tableUuid;
	
	// 生成类的包路径
	@Column(name = "class_path")
	private String				classPath;
	
	// 类名称
	@Column(name = "class_name")
	private String				className;
	
	// 生成html标识 / vue ,router
	@Column(name = "generate_html", length = 1)
	private String				generateHtml;

	// 生成detail标识
	@Column(name = "generate_detail", length = 1)
	private String				generateDetail;

	// 生成orm的框架依赖 jpa/mybatis
	@Column(name = "generate_orm", length = 10)
	private String				generateOrm;

	// 生成Lombok依赖
	@Column(name = "generate_lombok", length = 1)
	private String				generateLombok;

	// 生成控制层标识
	@Column(name = "generate_controller", length = 1)
	private String				generateController;

	// 生成业务处理层标识
	@Column(name = "generate_service", length = 1)
	private String				generateService;

	// 生成dao层标识
	@Column(name = "generate_dao", length = 1)
	private String				generateDao;
	
	// 生成实体类标识
	@Column(name = "generate_entity", length = 1)
	private String				generateEntity;

	// 生成sql标识
	@Column(name = "generate_sql", length = 1)
	private String				generateSql;
	
	// 生成sql文档
	@Column(name = "generate_doc", length = 1)
	private String				generateDoc;
	
	/**
	 * 构造方法：
	 * 描 述： 默认构造函数
	 * 参 数：
	 * 作 者 ： test
	 * @throws
	 */
	public SysDbmsGenerateCodeInfo() {
	}
	
	/**
	 * 方法名 ： getClassPath
	 * 功 能 ： 返回变量 classPath 的值
	 *
	 * @return: String
	 */
	public String getClassPath() {
		return classPath;
	}
	
	/**
	 * 方法名 ： setClassPath
	 * 功 能 ： 设置变量 classPath 的值
	 */
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	
	/**
	 * 方法名 ： getClassName
	 * 功 能 ： 返回变量 className 的值
	 *
	 * @return: String
	 */
	public String getClassName() {
		return className;
	}
	
	/**
	 * 方法名 ： setClassName
	 * 功 能 ： 设置变量 className 的值
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
	/**
	 * 方法名 ： getGenerateHtml
	 * 功 能 ： 返回变量 generateHtml 的值
	 *
	 * @return: String
	 */
	public String getGenerateHtml() {
		return generateHtml;
	}
	
	/**
	 * 方法名 ： setGenerateHtml
	 * 功 能 ： 设置变量 generateHtml 的值
	 */
	public void setGenerateHtml(String generateHtml) {
		this.generateHtml = generateHtml;
	}
	
	/**
	 * 方法名 ： getGenerateDetail
	 * 功 能 ： 返回变量 generateDetail 的值
	 *
	 * @return: String
	 */
	public String getGenerateDetail() {
		return generateDetail;
	}
	
	/**
	 * 方法名 ： setGenerateDetail
	 * 功 能 ： 设置变量 generateDetail 的值
	 */
	public void setGenerateDetail(String generateDetail) {
		this.generateDetail = generateDetail;
	}
	
	/**
	 * 方法名 ： getGenerateController
	 * 功 能 ： 返回变量 generateController 的值
	 *
	 * @return: String
	 */
	public String getGenerateController() {
		return generateController;
	}
	
	/**
	 * 方法名 ： setGenerateController
	 * 功 能 ： 设置变量 generateController 的值
	 */
	public void setGenerateController(String generateController) {
		this.generateController = generateController;
	}
	
	/**
	 * 方法名 ： getGenerateService
	 * 功 能 ： 返回变量 generateService 的值
	 *
	 * @return: String
	 */
	public String getGenerateService() {
		return generateService;
	}
	
	/**
	 * 方法名 ： setGenerateService
	 * 功 能 ： 设置变量 generateService 的值
	 */
	public void setGenerateService(String generateService) {
		this.generateService = generateService;
	}
	
	/**
	 * 方法名 ： getGenerateDao
	 * 功 能 ： 返回变量 generateDao 的值
	 *
	 * @return: String
	 */
	public String getGenerateDao() {
		return generateDao;
	}
	
	/**
	 * 方法名 ： setGenerateDao
	 * 功 能 ： 设置变量 generateDao 的值
	 */
	public void setGenerateDao(String generateDao) {
		this.generateDao = generateDao;
	}
	
	/**
	 * 方法名 ： getGenerateEntity
	 * 功 能 ： 返回变量 generateEntity 的值
	 *
	 * @return: String
	 */
	public String getGenerateEntity() {
		return generateEntity;
	}
	
	/**
	 * 方法名 ： setGenerateEntity
	 * 功 能 ： 设置变量 generateEntity 的值
	 */
	public void setGenerateEntity(String generateEntity) {
		this.generateEntity = generateEntity;
	}
	
	/**
	 * 方法名 ： getGenerateSql
	 * 功 能 ： 返回变量 generateSql 的值
	 *
	 * @return: String
	 */
	public String getGenerateSql() {
		return generateSql;
	}
	
	/**
	 * 方法名 ： setGenerateSql
	 * 功 能 ： 设置变量 generateSql 的值
	 */
	public void setGenerateSql(String generateSql) {
		this.generateSql = generateSql;
	}
	
	/**
	 * 方法名 ： getGenerateDoc
	 * 功 能 ： 返回变量 generateDoc 的值
	 *
	 * @return: String
	 */
	public String getGenerateDoc() {
		return generateDoc;
	}
	
	/**
	 * 方法名 ： setGenerateDoc
	 * 功 能 ： 设置变量 generateDoc 的值
	 */
	public void setGenerateDoc(String generateDoc) {
		this.generateDoc = generateDoc;
	}

	/**
	 * 方法名 ： getTableUuid
	 * 功 能 ： 返回变量 tableUuid 的值
	 *
	 * @return: String
	 */
	public String getTableUuid() {
		return tableUuid;
	}
	
	/**
	 * 方法名 ： setTableUuid
	 * 功 能 ： 设置变量 tableUuid 的值
	 */
	public void setTableUuid(String tableUuid) {
		this.tableUuid = tableUuid;
	}
	
	/**
	 * 方法名 ： getGenerateOrm
	 * 功 能 ： 返回变量 generateOrm 的值
	 *
	 * @return: String
	 */
	public String getGenerateOrm() {
		return generateOrm;
	}
	
	/**
	 * 方法名 ： setGenerateOrm
	 * 功 能 ： 设置变量 generateOrm 的值
	 */
	public void setGenerateOrm(String generateOrm) {
		this.generateOrm = generateOrm;
	}
	
	/**
	 * 方法名 ： getGenerateLombok
	 * 功 能 ： 返回变量 generateLombok 的值
	 *
	 * @return: String
	 */
	public String getGenerateLombok() {
		return generateLombok;
	}
	
	/**
	 * 方法名 ： setGenerateLombok
	 * 功 能 ： 设置变量 generateLombok 的值
	 */
	public void setGenerateLombok(String generateLombok) {
		this.generateLombok = generateLombok;
	}
	
}
