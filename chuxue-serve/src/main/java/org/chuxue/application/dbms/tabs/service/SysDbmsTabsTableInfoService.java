package org.chuxue.application.dbms.tabs.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsJdbcInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.chuxue.application.common.base.Pagination;
import org.chuxue.application.dbms.tabs.dao.SysDbmsTabsColsInfoDao;
import org.chuxue.application.dbms.tabs.dao.SysDbmsTabsJdbcInfoDao;
import org.chuxue.application.dbms.tabs.dao.SysDbmsTabsTableInfoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * 文件名 ： SysTableServiceImpl.java
 * 包 名 ： tk.ainiyue.danyuan.application.dbm.table.service.impl
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： wang
 * 时 间 ： 2017年8月3日 下午3:55:43
 * 版 本 ： V1.0
 */
@Service("sysDbmsTabsTableInfoService")
@Transactional
public class SysDbmsTabsTableInfoService extends BaseServiceImpl<SysDbmsTabsTableInfo> implements BaseService<SysDbmsTabsTableInfo> {
	
	//
	private static final Logger	logger	= LoggerFactory.getLogger(SysDbmsTabsTableInfo.class);
	
	@Autowired
	private RestTemplate		restTemplate;

	@Autowired
	SysDbmsTabsJdbcInfoDao		sysDbmsTabsJdbcInfoDao;
	
	@Autowired
	SysDbmsTabsTableInfoDao		sysDbmsTabsTableInfoDao;
	
	@Autowired
	SysDbmsTabsColsInfoDao		sysDbmsTabsColsInfoDao;
	
	/**
	 * 方法名： findAllByTableUuid
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param vo
	 * 参 数： @return
	 * 返 回： Page<SysDbmsTabsInfo>
	 * 作 者 ： Administrator
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Cacheable(cacheNames = "test", key = "targetClass + methodName +#p0")
	public BaseResult<Page<SysDbmsTabsTableInfo>> findAllByJdbcUuid(Pagination<SysDbmsTabsTableInfo> vo) {
		logger.info("微服务访问{}开始。", vo.getInfo().getJdbcUuid());
		// 获取数据库微服务名称
		SysDbmsTabsJdbcInfo jdbc = new SysDbmsTabsJdbcInfo();
		jdbc.setUuid(vo.getInfo().getJdbcUuid());
		Optional<SysDbmsTabsJdbcInfo> op = sysDbmsTabsJdbcInfoDao.findOne(Example.of(jdbc));
		if (op.isPresent()) {
			jdbc = op.get();
			// 对应库的已经加载过 的过滤
			SysDbmsTabsTableInfo info = new SysDbmsTabsTableInfo();
			info.setJdbcUuid(jdbc.getUuid());
			List<SysDbmsTabsTableInfo> list = findAll(info);
			vo.setList(list);
			// 请求微服务，获取未加载的表名称信息
			ResponseEntity<BaseResult> result = restTemplate.postForEntity("http://" + jdbc.getAppName() + "/data/sysDbmsTabsTableInfo/findAllByJdbcUuid", vo, BaseResult.class);
			if (result.getStatusCode().value() == 200 && result.getBody().getCode() == 200) {
				logger.info(result.getBody().toString());
				return result.getBody();
			} else {
				logger.error("微服务访问失败：{}异常。", vo.getInfo().getJdbcUuid());
				return null;
			}
		}
		return null;
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String importTable(SysDbmsTabsTableInfo info) {
		// 获取数据库微服务名称
		SysDbmsTabsJdbcInfo jdbc = new SysDbmsTabsJdbcInfo();
		jdbc.setUuid(info.getJdbcUuid());
		Optional<SysDbmsTabsJdbcInfo> op = sysDbmsTabsJdbcInfoDao.findOne(Example.of(jdbc));
		if (op.isPresent()) {
			jdbc = op.get();

			SysDbmsTabsColsInfo cols = new SysDbmsTabsColsInfo();
			cols.setTabsUuid(info.getUuid());
			cols.setColsName(info.getTabsName());
			org.chuxue.application.common.base.ResultPage<SysDbmsTabsColsInfo> page = new org.chuxue.application.common.base.ResultPage<>();
			page.setInfo(cols);
			// 请求微服务，获取未加载的表名称信息
			ResponseEntity<BaseResult> result = restTemplate.postForEntity("http://" + jdbc.getAppName() + "/data/sysDbmsTabsColumnInfo/findAllByTabUuid", page, BaseResult.class);
			if (result.getStatusCode().value() == 200 && result.getBody().getCode() == 200) {
				logger.info(result.getBody().toString());
				List<LinkedHashMap<String, Object>> li = (List<LinkedHashMap<String, Object>>) result.getBody().getData();
				List<SysDbmsTabsColsInfo> list = new ArrayList<>();
				for (LinkedHashMap map : li) {
					SysDbmsTabsColsInfo sysDbmsTabsColsInfo = new SysDbmsTabsColsInfo();

					try {
						BeanUtils.populate(sysDbmsTabsColsInfo, map);
						list.add(sysDbmsTabsColsInfo);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				sysDbmsTabsColsInfoDao.saveAll(list);
				sysDbmsTabsTableInfoDao.save(info);
				return "OK";
			}

		}
		// 获取

		return null;
	}

}
