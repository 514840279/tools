package org.chuxue.application.dbms.code.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.chuxue.application.bean.manager.dbms.SysDbmsGenerateCodeInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.base.BaseException;
import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.chuxue.application.dbms.tabs.dao.SysDbmsTabsColsInfoDao;
import org.chuxue.application.dbms.tabs.dao.SysDbmsTabsTableInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.alibaba.nacos.common.utils.StringUtils;

/**
 * @文件名 SysDbmsGenerateCodeInfoService.java
 * @包名 org.danyuan.application.dbms.code.service
 * @描述 service层
 * @时间 2020年04月25日 11:26:55
 * @author test
 * @版本 V1.0
 */
@Service
public class SysDbmsGenerateCodeInfoService extends BaseServiceImpl<SysDbmsGenerateCodeInfo> implements BaseService<SysDbmsGenerateCodeInfo> {
	
	@Autowired
	SysDbmsTabsColsInfoDao		sysDbmsTabsColsInfoDao;
	@Autowired
	SysDbmsTabsTableInfoDao		sysDbmsTabsInfoDao;
	
	private static final String	OUTPUTFILE	= "outputfile";
	
	/**
	 * @throws FileNotFoundException
	 *             方法名 generate
	 *             功能 代码生成控制
	 *             参数 @param list
	 *             参数 @param username
	 *             参数 @param pathString
	 *             返回 void @author
	 *             Administrator @throws
	 */
	public void generate(List<SysDbmsGenerateCodeInfo> list, String username, String pathString) throws FileNotFoundException {
		String path = System.getProperty("user.dir") + "/" + OUTPUTFILE + "/" + pathString;
		File file = new File(path);
		for (SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo : list) {
			// javafile 路径
			String pathtempString = path + "/src/main/java/" + sysDbmsGenerateCodeInfo.getClassPath().replace(".", "/");
			file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			SysDbmsTabsColsInfo colsInfo = new SysDbmsTabsColsInfo();
			colsInfo.setTabsUuid(sysDbmsGenerateCodeInfo.getTableUuid());
			Example<SysDbmsTabsColsInfo> example = Example.of(colsInfo);
			List<SysDbmsTabsColsInfo> colsInfos = sysDbmsTabsColsInfoDao.findAll(example);
			
			SysDbmsTabsTableInfo tabsInfo = new SysDbmsTabsTableInfo();
			Optional<SysDbmsTabsTableInfo> op = sysDbmsTabsInfoDao.findById(sysDbmsGenerateCodeInfo.getTableUuid());
			if (op.isPresent()) {
				tabsInfo = op.get();
				if ("JPA".equals(sysDbmsGenerateCodeInfo.getGenerateOrm()) || StringUtils.isBlank(sysDbmsGenerateCodeInfo.getGenerateOrm())) {
					generateJpa(username, sysDbmsGenerateCodeInfo, pathtempString, colsInfos, tabsInfo);

				} else if ("mybatis".equals(sysDbmsGenerateCodeInfo.getGenerateOrm())) {

				}
				String thirdString = "";
				String[] subpathString = sysDbmsGenerateCodeInfo.getClassPath().split("\\.");
				for (int i = 0; i < subpathString.length && i < 3; i++) {
					thirdString += subpathString[i] + ".";
				}
				// html类生成
				if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateHtml())) {
					// static 资源文件路径
					pathtempString = path + "/src/views/" + sysDbmsGenerateCodeInfo.getClassPath().replace(thirdString, "").replace(".", "/").toLowerCase();
					file = new File(pathtempString);
					if (!file.exists()) {
						file.mkdirs();
					}
					GenerateHtml.generateVue3(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathString);
					GenerateHtml.generateRouter(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathString);
//					// html类生成
//					GenerateHtml.generate(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
//					// js类生成
//					GenerateJs.generate(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
				}
				
				// detailhtml类生成
				if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateDetail())) {
					// templates 模板路径
					pathtempString = path + "/src/views/" + sysDbmsGenerateCodeInfo.getClassPath().replace(thirdString, "").replace(".", "/").toLowerCase();
					file = new File(pathtempString);
					if (!file.exists()) {
						file.mkdirs();
					}
//					GenerateHtml.generateDetail(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
					GenerateHtml.generateVue3Detail(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathString);
					
					// js类生成
					// static 资源文件路径
//					pathtempString = path + "/src/main/resources/static/pages/" + sysDbmsGenerateCodeInfo.getClassPath().replace(thirdString, "").replace(".", "/").toLowerCase();
//					file = new File(pathtempString);
//					if (!file.exists()) {
//						file.mkdirs();
//					}
//					GenerateJs.generateDetail(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
				}
				
				// Sql 语句
				if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateSql())) {
					// sql 脚本文件路径
					pathtempString = path + "/src/main/resources/sql/";
					file = new File(pathtempString);
					if (!file.exists()) {
						file.mkdirs();
					}
					// Sql ddl 语句
					GenerateSql.generate(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
					// Sql ddl 语句
					GenerateSql.generateOracle(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
					// Sql 管理员权限 语句
//					GenerateSql.generateConfig(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
				}
				
				// 数据文当 接口文档 功能介绍
				if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateDoc())) {
					// sql 脚本文件路径
					try {
						pathtempString = path + "/数据结构.xlsx";
						GenerateDoc.generateXlsx(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
						pathtempString = path + "/数据结构.xls";
						GenerateDoc.generateXls(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
					} catch (IOException e) {
						throw new BaseException(String.format("生成文件%s錯誤:%s.", pathtempString, e.getMessage()));
					}
				}
			}
		}
		// 打包文件
//		FileOutputStream fos1 = new FileOutputStream(new File(path + ".zip"));
//		CompressFile.toZip(path, fos1, true);
		
		// 清空 文件夹
//		FileDelete.delFolder(path);
	}

	/**
	 * 方法名： generateJpa
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param username
	 * 参 数： @param sysDbmsGenerateCodeInfo
	 * 参 数： @param pathtempString
	 * 参 数： @param colsInfos
	 * 参 数： @param tabsInfo
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	private void generateJpa(String username, SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, String pathtempString, List<SysDbmsTabsColsInfo> colsInfos, SysDbmsTabsTableInfo tabsInfo) {
		File file;
		// 实体类生成
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateEntity())) {
			file = new File(pathtempString + "/po");
			if (!file.exists()) {
				file.mkdirs();
			}
			GenerateEntity.generate(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/po");
		}
		// dao类生成
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateDao())) {
			file = new File(pathtempString + "/dao");
			if (!file.exists()) {
				file.mkdirs();
			}
			GenerateDao.getGenerateDao(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/dao");
		}
		// service类生成
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateService())) {
			file = new File(pathtempString + "/service");
			if (!file.exists()) {
				file.mkdirs();
			}
			GenerateService.getGenerateService(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/service");
		}
		// controller类生成
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateController())) {
			file = new File(pathtempString + "/controller");
			if (!file.exists()) {
				file.mkdirs();
			}
			GenerateController.getGenerateController(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/controller");
		}
	}

}
