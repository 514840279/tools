package org.chuxue.application.dbms.tabs.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.chuxue.application.bean.manager.dbms.SysDbmsAdviMessInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsJdbcInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.chuxue.application.common.utils.constant.DatabasePlantEnum;
import org.chuxue.application.dbms.tabs.dao.SysDbmsAdviMessInfoDao;
import org.chuxue.application.dbms.tabs.dao.SysDbmsTabsColsInfoDao;
import org.chuxue.application.dbms.tabs.dao.SysDbmsTabsJdbcInfoDao;
import org.chuxue.application.dbms.tabs.dao.SysDbmsTabsTableInfoDao;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsIndexResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

/**
 * 文件名 ： SysDbmsAdviMessInfoService.java
 * 包 名 ： com.shumeng.application.application.zhcx.service
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2018年4月27日 下午5:27:33
 * 版 本 ： V1.0
 */
@Service
@Transactional
public class SysDbmsAdviMessInfoService extends BaseServiceImpl<SysDbmsAdviMessInfo> implements BaseService<SysDbmsAdviMessInfo> {
	@Autowired
	SysDbmsAdviMessInfoDao	sysDbmsAdviMessInfoDao;

	@Autowired
	RestTemplate			restTemplate;
	
	@Autowired
	SysDbmsTabsJdbcInfoDao	sysDbmsTabsJdbcInfoDao;
	
	@Autowired
	SysDbmsTabsTableInfoDao	sysDbmsTabsTableInfoDao;

	@Autowired
	SysDbmsTabsColsInfoDao	sysDbmsTabsColsInfoDao;

	/**
	 * 方法名： findAllByTable
	 * 功 能：
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： List<SysDbmsAdviMessInfo>
	 * 作 者 ： Administrator
	 * @throws
	 */
	public List<SysDbmsAdviMessInfo> findAllByTable(SysDbmsAdviMessInfo info) {
		Order order = Order.asc("sort");
		Sort sort = Sort.by(order);
		Example<SysDbmsAdviMessInfo> example = Example.of(info);
		List<SysDbmsAdviMessInfo> list = sysDbmsAdviMessInfoDao.findAll(example, sort);
		if (list == null || list.size() == 0) {
			generateSql(info);
			list = sysDbmsAdviMessInfoDao.findAll(example, sort);
		}
		return list;
	}

	/**
	 * 方法名： generateSql
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void generateSql(SysDbmsAdviMessInfo info) {
		deleteAll(findAll(info));
		SysDbmsTabsTableInfo tabs = new SysDbmsTabsTableInfo();
		tabs.setUuid(info.getTabsUuid());
		Optional<SysDbmsTabsTableInfo> top = sysDbmsTabsTableInfoDao.findOne(Example.of(tabs));
		if (top.isPresent()) {
			tabs = top.get();
			SysDbmsTabsJdbcInfo jdbc = new SysDbmsTabsJdbcInfo();
			jdbc.setUuid(tabs.getJdbcUuid());
			Optional<SysDbmsTabsJdbcInfo> op = sysDbmsTabsJdbcInfoDao.findOne(Example.of(jdbc));
			if (op.isPresent()) {
				jdbc = op.get();
				// 请求微服务，获取表信息与配置信息对照
				ResponseEntity<BaseResult> result = restTemplate.postForEntity("http://" + jdbc.getAppName() + "/data/sysDbmsTabsTableInfo/findOneByTabsName", tabs, BaseResult.class);
				if ((result.getStatusCode().value() == 200 && result.getBody().getCode() == 200) && (result.getBody().getData() != null)) {
					LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) result.getBody().getData();
					SysDbmsTabsTableInfo resultData = JSON.parseObject(JSON.toJSONString(map.get("info")), SysDbmsTabsTableInfo.class);
					saveAllTable(info, jdbc, tabs, resultData);
					
					SysDbmsTabsColsInfo cols = new SysDbmsTabsColsInfo(info.getTabsUuid());
					List<SysDbmsTabsColsInfo> al = sysDbmsTabsColsInfoDao.findAll(Example.of(cols));

					List<SysDbmsTabsColsInfo> re = JSON.parseArray(JSON.toJSONString(map.get("cols")), SysDbmsTabsColsInfo.class);
					List<SysDbmsTabsIndexResult> indexs = JSON.parseArray(JSON.toJSONString(map.get("indexs")), SysDbmsTabsIndexResult.class);
					// 请求微服务，获取表字段信息与配置信息对照
					saveAllTableColumn(info, jdbc, tabs, al, re, indexs);
				}
			}
		}
	}

	/**
	 * @param indexs
	 * @param jdbc
	 * @param tabs
	 * 方法名： saveAllTableColumn
	 * 功 能： 比对字段配置和表信息进行处理
	 * 参 数： @param info
	 * 参 数： @param al
	 * 参 数： @param reColumn
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	private void saveAllTableColumn(SysDbmsAdviMessInfo info, SysDbmsTabsJdbcInfo jdbc, SysDbmsTabsTableInfo tabs, List<SysDbmsTabsColsInfo> al, List<SysDbmsTabsColsInfo> reColumn, List<SysDbmsTabsIndexResult> indexs) {
		SysDbmsAdviMessInfo advice = null;
		// 对比字段名称
		for (SysDbmsTabsColsInfo conf : al) {
			for (SysDbmsTabsColsInfo tabsCols : reColumn) {
				if (conf.getColsName().equals(tabsCols.getColsName())) {
					// 判断配置信息并补充
					// 配置列数据类型
					if (StringUtils.isNotBlank(tabsCols.getDataType()) && !tabsCols.getDataType().equals(conf.getDataType())) {
						advice = new SysDbmsAdviMessInfo(UUID.randomUUID().toString(), "列配置修改", tabs.getUuid(), tabs.getJdbcUuid(), 2);
						String executeSql = "update sys_dbms_tabs_cols_info t set  t.data_type = '" + tabsCols.getDataType() + "',t.update_time = current_timestamp() where t.uuid='" + conf.getUuid() + "'";
						advice.setMessage("-- 同步修改配置信息");
						advice.setExecuteSql(executeSql + ";");
						advice.setDeleteFlag(0);
						save(advice);
					}
					if (StringUtils.isNotBlank(tabsCols.getColsType()) && !tabsCols.getColsType().equals(conf.getColsType())) {
						advice = new SysDbmsAdviMessInfo(UUID.randomUUID().toString(), "列配置修改", tabs.getUuid(), tabs.getJdbcUuid(), 2);
						String executeSql = "update sys_dbms_tabs_cols_info t set  t.cols_type = '" + tabsCols.getColsType() + "',t.update_time = current_timestamp() where t.uuid='" + conf.getUuid() + "'";
						advice.setMessage("-- 同步修改配置信息");
						advice.setExecuteSql(executeSql + ";");
						advice.setDeleteFlag(0);
						save(advice);
					}
					// 配置列展示
//					if (sysZhcxTab.getTabsRows() != null && sysZhcxTab.getTabsRows() > 10000) {
//						BigDecimal numNulls = (BigDecimal) resultmap.get("NUM_NULLS");
//						if (numNulls != null && numNulls.intValue() != 0) {
//							if (numNulls.subtract(new BigDecimal(sysZhcxTab.getTabsRows()).multiply(new BigDecimal(0.6f))).intValue() > 0 && 1 == sysZhcxCol.getPageList().intValue()) {
//								advice = new SysDbmsAdviMessInfo(UUID.randomUUID().toString(), "列配置修改", sysZhcxTab.getTabsDesc(), tableName, sysZhcxTab.getJdbcUuid());
//								StringBuilder sBuilder = new StringBuilder();
//								sBuilder.append("-- 表中的空值超过 60% 建议默认列表不展示");
//								advice = new SysDbmsAdviMessInfo(UUID.randomUUID().toString(), "列配置修改", sysZhcxTab.getTabsDesc(), sysZhcx4Tab.getTabsName(), sysZhcxTab.getJdbcUuid());
//								sBuilder.append("update sys_dbms_tabs_cols_info t set  t.PAGE_LIST = '0',t.update_time = sysdate where t.uuid='" + sysZhcxCol.getUuid() + "'");
//								advice.setMessage(sBuilder.toString() + ";");
//								advice.setDeleteFlag(0);
//								sysDbmsAdviMessInfoDao.save(advice);
//							}
//						}
//					}
					// 列数据统计建议添加索引，平台隐藏，实际长度修改(索引修改或重建，索引添加，)
					saveAllTableColumnIndexConfig(jdbc, tabs, conf, tabsCols, indexs);
					
					// 注释和翻译
					if (StringUtils.isNotBlank(conf.getColsDesc()) || StringUtils.isNotBlank(tabsCols.getColsDesc())) {
						StringBuilder sBuilder = new StringBuilder();
						advice = new SysDbmsAdviMessInfo(UUID.randomUUID().toString(), "列配置修改", tabs.getUuid(), tabs.getJdbcUuid(), 2);
						if (StringUtils.isBlank(conf.getColsDesc()) && StringUtils.isNotBlank(tabsCols.getColsDesc())) {
							sBuilder.append("-- 由于配置中的信息没有，建议执行以下语句进行统一：\n");
							sBuilder.append("update sys_dbms_tabs_cols_info t set t.COLS_DESC ='" + tabsCols.getColsDesc() + "' ,t.update_time = sysdate where t.uuid='" + conf.getUuid() + "'; \n");
						} else if (StringUtils.isNotBlank(conf.getColsDesc()) && StringUtils.isBlank(tabsCols.getColsDesc())) {
							sBuilder.append("-- 由于表中字段注释信息没有，建议执行以下语句进行统一：\n");
							switch (DatabasePlantEnum.value(jdbc.getPlatform().toUpperCase())) {
								case MYSQL:
									sBuilder.append("alter table " + tabs.getTabsName() + "  modify column " + conf.getColsName() + " VARCHAR(500) comment '" + conf.getColsDesc() + "';\n");
									break;
								case ORACLE:
									sBuilder.append("comment on column " + tabs.getTabsName() + "." + conf.getColsName() + "  is '" + conf.getColsDesc() + "';\n");
									break;
								default:
									break;
							}
						} else if (!conf.getColsDesc().equals(tabsCols.getColsDesc())) {
							sBuilder.append("-- 由于表中字段注释信息和配置中的信息不一致，建议执行以下语句进行统一：\n");
							sBuilder.append("-- 表字段注释 ： " + tabsCols.getColsDesc() + " 配置表中显示信息：" + conf.getColsName() + ".\n");
							sBuilder.append("-- 建议： 根据配置表中的信息更新表信息 .\n");
							sBuilder.append("--  update sys_dbms_tabs_cols_info t set t.COLS_DESC ='" + tabsCols.getColsDesc() + "' ,t.update_time = sysdate where t.uuid='" + conf.getUuid() + "'; \n");
							switch (DatabasePlantEnum.value(jdbc.getPlatform().toUpperCase())) {
								case MYSQL:
									sBuilder.append("alter table " + tabs.getTabsName() + "  modify column " + conf.getColsName() + " VARCHAR(500) comment '" + conf.getColsDesc() + "';\n");
									break;
								case ORACLE:
									sBuilder.append("comment on column " + tabs.getTabsName() + "." + conf.getColsName() + "  is '" + conf.getColsDesc() + "';\n");
									break;
								default:
									break;
							}
						} else {
							continue;
						}

						advice.setMessage(sBuilder.toString());
						advice.setDeleteFlag(0);
						save(advice);
					}

				}
			}

		}

	}
	
	/**
	 * @param indexs
	 * @param jdbc
	 * 方法名： saveAllTableColumnIndexConfig
	 * 功 能： 创建索引值
	 * 参 数： @param tabs
	 * 参 数： @param conf
	 * 参 数： @param tabsCols
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	private void saveAllTableColumnIndexConfig(SysDbmsTabsJdbcInfo jdbc, SysDbmsTabsTableInfo tabs, SysDbmsTabsColsInfo conf, SysDbmsTabsColsInfo tabsCols, List<SysDbmsTabsIndexResult> indexs) {
		// 列数据统计建议添加索引，(索引修改或重建，索引添加，)
		// 索引修改 一般发现索引创建位置处于数据空间下或者处于其他用户下的索引空间 给出建议修改
		// 索引添加主要提醒当配置表中有userIndex列配置上数据时 索引没有及时创建的给出创建的提示信息
		if (StringUtils.isNotBlank(conf.getIndexCode())) {
			SysDbmsTabsIndexResult indexResult = null;
			for (SysDbmsTabsIndexResult sysDbmsTabsIndexResult : indexs) {
				if (conf.getColsName().equals(sysDbmsTabsIndexResult.getColumnName())) {
					indexResult = sysDbmsTabsIndexResult;
				}
			}
			
			StringBuilder sBuilder = new StringBuilder();
			String tableName = tabs.getTabsName();
			String expactUser = tableName.substring(0, tableName.indexOf("."));
			String expactIndexSpaces = expactUser.replace("\"", "") + "_INDEX";
			String indexName = "IND_" + UUID.randomUUID().toString().replace("-", "").substring(4, 20) + "_" + conf.getSort();
			String columnIndexDescString = StringUtils.isNotBlank(conf.getColsDesc()) ? conf.getColsDesc() : conf.getColsName() + "的索引";
			SysDbmsAdviMessInfo advice = null;
			// oracle 中索引的表空间判断
			switch (DatabasePlantEnum.value(jdbc.getPlatform().toUpperCase())) {
				case MYSQL:
					if (indexResult == null) {
						advice = new SysDbmsAdviMessInfo(UUID.randomUUID().toString(), "索引添加", tabs.getUuid(), tabs.getJdbcUuid(), 4);
						sBuilder.append("-- 由于配置中userIndex不为空，并且期望的索引信息未找到，建议建索引信息：\n");
						sBuilder.append(" ALTER TABLE " + tableName + " ADD INDEX " + indexName + " (" + conf.getColsName() + ")   USING BTREE COMMENT '" + columnIndexDescString + "';");
						
						advice.setMessage(sBuilder.toString());
						advice.setDeleteFlag(0);
						save(advice);
					}
					break;
				case ORACLE:
					if (indexResult != null && (!expactIndexSpaces.contains(indexResult.getIndexSchema()) || !expactIndexSpaces.contains(indexResult.getTablespaceName()))) {
						advice = new SysDbmsAdviMessInfo(UUID.randomUUID().toString(), "索引重建", tabs.getUuid(), tabs.getJdbcUuid(), 5);
						sBuilder.append("-- 由于索引信息并不是期望的值，建议充建索引信息：\n");
						sBuilder.append("-- 预期值：\t 索引所属：" + expactUser + " ;索引的表空间：" + expactIndexSpaces + "\n");
						sBuilder.append("-- 实际值：\t 索引所属：" + indexResult.getIndexSchema() + ";索引的表空间：" + indexResult.getIndexSchema() + "\n");
						sBuilder.append("drop index " + indexResult.getIndexSchema() + "." + indexResult.getIndexName() + ";\n");
						sBuilder.append(" create index " + expactUser + "." + indexName + " on " + tableName + " (" + conf.getColsName() + ")  tablespace " + expactIndexSpaces + ";");

					} else if (indexResult == null) {
						
						advice = new SysDbmsAdviMessInfo(UUID.randomUUID().toString(), "索引添加", tabs.getUuid(), tabs.getJdbcUuid(), 4);
						sBuilder.append("-- 由于配置中userIndex不为空，并且期望的索引信息未找到，建议建索引信息：\n");
						sBuilder.append(" create index " + expactUser + "." + indexName + " on " + tableName + " (" + conf.getColsName() + ")  tablespace " + expactIndexSpaces + ";");
						advice.setMessage(sBuilder.toString());
						advice.setDeleteFlag(0);
						save(advice);

					}
					break;
				case H2:
					break;
				default:
					break;
			}
		}
	}

	/**
	 * @param jdbc
	 * 方法名： saveAllTable
	 * 功 能： 比对查询结果和配置信息进行处理
	 * 参 数： @param info
	 * 参 数： @param tabs
	 * 参 数： @param data
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	private void saveAllTable(SysDbmsAdviMessInfo info, SysDbmsTabsJdbcInfo jdbc, SysDbmsTabsTableInfo tabs, SysDbmsTabsTableInfo result) {
		// 配置信息空的不对的信息直接改掉
		boolean up = false;
		if (tabs.getTabsDesc() == null && StringUtils.isNotBlank(result.getTabsDesc())) {
			tabs.setTabsDesc(result.getTabsDesc());
			up = true;
		}
		if (tabs.getTabsRows() == null || !tabs.getTabsRows().equals(result.getTabsRows())) {
			tabs.setTabsRows(result.getTabsRows());
			up = true;
		}
		if (tabs.getTabsSpace() == null || !tabs.getTabsSpace().equals(result.getTabsSpace())) {
			tabs.setTabsSpace(result.getTabsSpace());
			up = true;
		}
		// 根据表信息补齐配置信息
		if (up) {
			sysDbmsTabsTableInfoDao.save(tabs);
		}
		
		// 针对注释判断不同的话推荐根据配置执行sql
		if (tabs.getTabsDesc() != null && !tabs.getTabsDesc().equals(result.getTabsDesc())) {
			SysDbmsAdviMessInfo advice = new SysDbmsAdviMessInfo(UUID.randomUUID().toString(), "表配置修改", tabs.getUuid(), tabs.getJdbcUuid(), 1);
			StringBuilder sbBuilder = new StringBuilder();
			if (StringUtils.isBlank(result.getTabsDesc())) {
				// 建议直接修改表注释
				sbBuilder.append("-- 由于表中注释信息没有，建议执行以下语句进行统一：\n");
				switch (DatabasePlantEnum.value(jdbc.getPlatform().toUpperCase())) {
					case MYSQL:
						sbBuilder.append("alter table " + tabs.getTabsName() + "  comment  '" + tabs.getTabsDesc() + "';\n");
						break;
					case ORACLE:
						sbBuilder.append("comment on table " + tabs.getTabsName() + "  is '" + tabs.getTabsDesc() + "';\n");
						break;
					default:
						break;
				}
				advice.setExecuteSql(sbBuilder.toString());
			} else {
				sbBuilder.append("-- 由于表中注释信息和配置中的信息不一致，建议执行以下语句进行统一：\n");
				sbBuilder.append("-- 表注释 ： " + result.getTabsDesc() + " 配置表中显示信息：" + tabs.getTabsDesc() + ".\n");
				sbBuilder.append("-- 建议 根据配置表中的信息更新表信息 .\n");
				sbBuilder.append("--  update sys_dbms_tabs_info t set t.tabs_desc ='" + result.getTabsDesc() + "' ,t.update_time = current_timestamp()  where t.uuid='" + tabs.getUuid() + "'; \n");
				switch (DatabasePlantEnum.value(jdbc.getPlatform().toUpperCase())) {
					case MYSQL:
						sbBuilder.append("alter table " + tabs.getTabsName() + "  comment  '" + tabs.getTabsDesc() + "';\n");
						break;
					case ORACLE:
						sbBuilder.append("comment on table " + tabs.getTabsName() + "  is '" + tabs.getTabsDesc() + "';\n");
						break;
					default:
						break;
				}
				advice.setExecuteSql(sbBuilder.toString());
			}
			save(advice);
		}
		
	}
	
	/**
	 * 方法名： findOneText
	 * 功 能： 所有的数据整理成文档
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： String
	 * 作 者 ： Administrator
	 * @throws
	 */
	@Transactional
	public String findOneText(SysDbmsAdviMessInfo info) {
		List<SysDbmsAdviMessInfo> list = findAllByTable(info);
		StringBuilder sbBuilder = new StringBuilder();
		String type = "";
		if (list != null && list.size() > 0) {
			for (SysDbmsAdviMessInfo sysDbmsAdviMessInfo : list) {
				if (sysDbmsAdviMessInfo.getDeleteFlag() == 0) {
					if (!type.equals(sysDbmsAdviMessInfo.getType())) {
						if (!type.equals("")) {
							sbBuilder.append("```\n");
						}
						sbBuilder.append("#### " + sysDbmsAdviMessInfo.getType() + "\n");
						sbBuilder.append("```sql\n");
						type = sysDbmsAdviMessInfo.getType();
					}
					if (StringUtils.isNotBlank(sysDbmsAdviMessInfo.getMessage())) {
						sbBuilder.append(sysDbmsAdviMessInfo.getMessage() + "\n");
					}
					if (StringUtils.isNotBlank(sysDbmsAdviMessInfo.getExecuteSql())) {
						sbBuilder.append(sysDbmsAdviMessInfo.getExecuteSql() + "\n");
					}
				}
			}
			sbBuilder.append("```\n");
		}
		return sbBuilder.toString();
	}

}
