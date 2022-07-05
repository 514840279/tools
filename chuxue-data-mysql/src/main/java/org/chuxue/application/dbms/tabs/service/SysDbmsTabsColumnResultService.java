package org.chuxue.application.dbms.tabs.service;

import java.util.ArrayList;
import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.common.base.MybatisBaseServiceImpl;
import org.chuxue.application.common.base.Page;
import org.chuxue.application.dbms.tabs.dao.SysDbmsTabsColumnResultDao;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsColumnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysDbmsTabsColumnResultService")
public class SysDbmsTabsColumnResultService extends MybatisBaseServiceImpl<SysDbmsTabsColumnResult> {

	@Autowired
	SysDbmsTabsColumnResultDao sysDbmsTabsColumnResultDao;
	
	public List<SysDbmsTabsColumnResult> findAllByTabUuid(Page<SysDbmsTabsColsInfo> vo) {
		SysDbmsTabsColsInfo info = vo.getInfo();
		List<String> list = null;
		if (vo.getList() != null) {
			for (SysDbmsTabsColsInfo sysDbmsTabsInfo : vo.getList()) {
				if (list == null) {
					list = new ArrayList<>();
				}
				list.add(sysDbmsTabsInfo.getColsName());
			}
		}
		List<SysDbmsTabsColumnResult> result = sysDbmsTabsColumnResultDao.findAllByTabUuid(info.getTabsUuid(), info.getColsName(), list);
		return result;
	}

}
