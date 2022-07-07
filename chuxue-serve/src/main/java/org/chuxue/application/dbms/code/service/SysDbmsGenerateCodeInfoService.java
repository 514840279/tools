package org.chuxue.application.dbms.code.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.chuxue.application.bean.manager.dbms.SysDbmsGenerateCodeInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.base.BaseException;
import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.chuxue.application.common.utils.files.CompressFile;
import org.chuxue.application.common.utils.files.FileDelete;
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
		for (SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo : list) {
			// javafile 路径
			String pathtempString = path + "/src/main/java/" + sysDbmsGenerateCodeInfo.getClassPath().replace(".", "/");

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
					generateMybatis(username, sysDbmsGenerateCodeInfo, path, pathtempString, colsInfos, tabsInfo);
				}
				StringBuilder thirdString = new StringBuilder();
				String[] subpathString = sysDbmsGenerateCodeInfo.getClassPath().split("\\.");
				for (int i = 0; i < subpathString.length && i < 3; i++) {
					if (i > 0) {
						thirdString.append(".");
					}
					thirdString.append(subpathString[i]);
				}
				// html类生成
				if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateHtml())) {
					// static 资源文件路径
					pathtempString = path + "/src/views/manager/" + sysDbmsGenerateCodeInfo.getClassPath().replace(thirdString.toString(), "").replace(".", "/").toLowerCase();
					if ("/".equals(pathtempString.substring(pathtempString.length() - 1))) {
						pathtempString = pathtempString.substring(0, pathtempString.length() - 1);
					}
					GenerateHtml.generateVue3(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
					pathtempString = path;
					GenerateHtml.generateRouter(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
					pathtempString = path + "/src/interface";
					GenerateHtml.generateTsEntity(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString);
					
				}

				// Sql 语句
				if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateSql())) {
					// sql 脚本文件路径
					pathtempString = path + "/src/main/resources/sql/";
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
		try (FileOutputStream fos1 = new FileOutputStream(new File(path + ".zip"))) {
			// 打包文件
			CompressFile.toZip(path, fos1, true);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// 清空 文件夹
		FileDelete.delFolder(path);
	}

	/**
	 * @param pathtempString2
	 * 方法名： generateMybatis
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
	private void generateMybatis(String username, SysDbmsGenerateCodeInfo sysDbmsGenerateCodeInfo, String path, String pathtempString, List<SysDbmsTabsColsInfo> colsInfos, SysDbmsTabsTableInfo tabsInfo) {
		// 实体类生成
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateEntity())) {
			GenerateEntity.generateMybatis(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/po");
		}
		// dao类生成
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateDao())) {
			GenerateDao.getGenerateMybatisDao(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/dao");
			String pathtemp = path + "/src/main/resources/mapper/";
			GenerateDao.getGenerateMybatisXml(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtemp);
		}
		// service类生成
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateService())) {
			GenerateService.getGenerateMybatisService(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/service");
		}
		// controller类生成
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateController())) {
			GenerateController.getGenerateMybatisController(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/controller");
		}
		
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
		// 实体类生成
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateEntity())) {
			GenerateEntity.generate(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/po");
		}
		// dao类生成
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateDao())) {
			GenerateDao.getGenerateDao(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/dao");
		}
		// service类生成
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateService())) {
			GenerateService.getGenerateService(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/service");
		}
		// controller类生成
		if ("Y".equals(sysDbmsGenerateCodeInfo.getGenerateController())) {
			GenerateController.getGenerateController(sysDbmsGenerateCodeInfo, tabsInfo, colsInfos, username, pathtempString + "/controller");
		}
	}

}
