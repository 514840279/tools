package org.chuxue.application.dbms.tabs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsColsInfo;
import org.chuxue.application.common.base.MybatisBaseServiceImpl;
import org.chuxue.application.common.base.ResultPage;
import org.chuxue.application.dbms.tabs.dao.SysDbmsTabsColumnResultDao;
import org.chuxue.application.dbms.tabs.po.SysDbmsTabsColumnResult;
import org.chuxue.application.dbms.tabs.vo.SysDbmsTabsTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysDbmsTabsColumnResultService")
public class SysDbmsTabsColumnResultService extends MybatisBaseServiceImpl<SysDbmsTabsColumnResult> {
	
	@Autowired
	SysDbmsTabsColumnResultDao sysDbmsTabsColumnResultDao;

	public List<SysDbmsTabsColumnResult> findAllByTabUuid(ResultPage<SysDbmsTabsColsInfo> vo) {
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
	
	/**
	 * 方法名： searchData
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param vo
	 * 参 数： @return
	 * 返 回： List<SysDbmsTabsColumnResult>
	 * 作 者 ： Administrator
	 * @throws
	 */
	public List<Map<String, Object>> searchData(SysDbmsTabsTableVo vo) {
		List<Map<String, Object>> list = sysDbmsTabsColumnResultDao.selectDataMaps(vo);
		return list;
	}
	
}
