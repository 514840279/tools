package org.chuxue.application.dbms.tabs.service;

import java.util.ArrayList;
import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.base.ResultPage;
import org.chuxue.application.dbms.tabs.dao.SysDbmsTabsInfoResultDao;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsInfoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service("sysDbmsTabsInfoService")
public class SysDbmsTabsInfoResultService {

	//
	private static final Logger	logger	= LoggerFactory.getLogger(SysDbmsTabsInfoResultService.class);

	@Autowired
	SysDbmsTabsInfoResultDao	sysDbmsTabsInfoResultDao;

	/**
	 * 方法名： findAllByTableUuid
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param vo
	 * 参 数： @return
	 * 返 回： Page<SysDbmsTabsInfo>
	 * 作 者 ： Administrator @throws
	 */
	@Transactional
	public ResultPage<SysDbmsTabsInfoResult> findAllByJdbcUuid(ResultPage<SysDbmsTabsTableInfo> vo) {
		logger.info("微服务访问{}开始。", vo.getInfo().getJdbcUuid());
		String tableName = vo.getInfo().getTabsName();

		List<String> list = null;
		if (vo.getList() != null) {
			for (SysDbmsTabsTableInfo sysDbmsTabsInfo : vo.getList()) {
				if (list == null) {
					list = new ArrayList<>();
				}
				list.add(sysDbmsTabsInfo.getTabsName());
			}
		}
		List<SysDbmsTabsInfoResult> page = sysDbmsTabsInfoResultDao.findAllByJdbcUuid(vo.getInfo().getJdbcUuid(), tableName, list, vo.getPageNumber(), vo.getPageSize());
		Integer total = vo.getTotalElements();
		if (total == null || total == 0) {
			total = sysDbmsTabsInfoResultDao.totalAllByJdbcUuid(vo.getInfo().getJdbcUuid(), tableName, list);
		}

		return new ResultPage<>(total, page);
	}

}
