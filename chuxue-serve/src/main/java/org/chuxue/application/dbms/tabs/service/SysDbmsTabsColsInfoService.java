package org.chuxue.application.dbms.tabs.service;

import java.util.List;
import java.util.Optional;

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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 文件名 ： SysDbmsTabsColsInfoService.java 包 名 ：
 * tk.ainiyue.danyuan.application.dbm.column.service.impl 描 述 ：
 * TODO(用一句话描述该文件做什么) 机能名称： 技能ID ： 作 者 ： wang 时 间 ： 2017年8月3日 下午3:52:36 版 本 ：
 * V1.0
 */
@Service("sysDbmsTabsColsInfoService")
public class SysDbmsTabsColsInfoService extends BaseServiceImpl<SysDbmsTabsColsInfo> implements BaseService<SysDbmsTabsColsInfo> {
	private static final Logger	logger	= LoggerFactory.getLogger(SysDbmsTabsColsInfoService.class);

	@Autowired
	RestTemplate				restTemplate;

	@Autowired
	SysDbmsTabsJdbcInfoDao		sysDbmsTabsJdbcInfoDao;

	@Autowired
	SysDbmsTabsColsInfoDao		sysDbmsTabsColsInfoDao;

	@Autowired
	SysDbmsTabsTableInfoDao		sysDbmsTabsTableInfoDao;

	@SuppressWarnings({ "rawtypes" })
	public BaseResult findAllByTabsUuid(Pagination<SysDbmsTabsColsInfo> vo) {
		SysDbmsTabsColsInfo cols = vo.getInfo();
		SysDbmsTabsTableInfo tabs = new SysDbmsTabsTableInfo();
		tabs.setUuid(cols.getTabsUuid());
		Optional<SysDbmsTabsTableInfo> top = sysDbmsTabsTableInfoDao.findOne(Example.of(tabs));
		if (top.isPresent()) {
			tabs = top.get();
			SysDbmsTabsJdbcInfo jdbc = new SysDbmsTabsJdbcInfo();
			jdbc.setUuid(tabs.getJdbcUuid());
			Optional<SysDbmsTabsJdbcInfo> op = sysDbmsTabsJdbcInfoDao.findOne(Example.of(jdbc));
			if (op.isPresent()) {
				jdbc = op.get();
				
				SysDbmsTabsColsInfo pcols = new SysDbmsTabsColsInfo();
				pcols.setTabsUuid(tabs.getUuid());
				pcols.setColsName(tabs.getTabsName());
				org.chuxue.application.common.base.ResultPage<SysDbmsTabsColsInfo> page = new org.chuxue.application.common.base.ResultPage<>();
				page.setInfo(pcols);
				
				List<SysDbmsTabsColsInfo> al = sysDbmsTabsColsInfoDao.findAll(Example.of(cols));
				page.setList(al);
				
				// 请求微服务，获取未加载的表名称信息
				ResponseEntity<BaseResult> result = restTemplate.postForEntity("http://" + jdbc.getAppName() + "/data/sysDbmsTabsColumnInfo/findAllByTabUuid", page, BaseResult.class);
				
				if (result.getStatusCode().value() == 200 && result.getBody().getCode() == 200) {
					logger.info(result.getBody().toString());
					return result.getBody();
//					List<LinkedHashMap<String, Object>> li = (List<LinkedHashMap<String, Object>>) result.getBody().getData();
//					List<SysDbmsTabsColsInfo> list = new ArrayList<>();
//					for (LinkedHashMap map : li) {
//						SysDbmsTabsColsInfo sysDbmsTabsColsInfo = new SysDbmsTabsColsInfo();
//
//						try {
//							BeanUtils.populate(sysDbmsTabsColsInfo, map);
//							list.add(sysDbmsTabsColsInfo);
//						} catch (IllegalAccessException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (InvocationTargetException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//					sysDbmsTabsColsInfoDao.saveAll(list);
//					return "OK";
				}
			}
		}
		return null;
	}
	
	/**
	 * 方法名： importColums
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： String
	 * 作 者 ： Administrator
	 * @throws
	 */
	public String importColums(SysDbmsTabsColsInfo info) {
		sysDbmsTabsColsInfoDao.save(info);
		return "OK";
	}
	
}
