package org.chuxue.application.dbms.tabs.service;

import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsIndexInfo;
import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.chuxue.application.dbms.tabs.dao.SysDbmsTabsIndexInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文件名 ： SysDicUserIndexCode.java
 * 包 名 ： com.shumeng.application.zhcx.service
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2018年3月8日 下午1:49:27
 * 版 本 ： V1.0
 */
@Service
public class SysDbmsTabsIndexInfoService extends BaseServiceImpl<SysDbmsTabsIndexInfo> implements BaseService<SysDbmsTabsIndexInfo> {
	@Autowired
	private SysDbmsTabsIndexInfoDao sysDbmsTabsIndexInfoDao;

	/**
	 * 方法名： chartList
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @return
	 * 返 回： List<SysDicUserIndexCode>
	 * 作 者 ： Administrator
	 * @throws
	 */
	public List<SysDbmsTabsIndexInfo> chartList() {
		return sysDbmsTabsIndexInfoDao.findAllByChart();
	}
}
