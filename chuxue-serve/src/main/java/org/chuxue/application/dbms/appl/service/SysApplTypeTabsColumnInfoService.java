package org.chuxue.application.dbms.appl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.chuxue.application.bean.manager.appl.SysApplTypeTabsColumnInfo;
import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.chuxue.application.dbms.appl.vo.SysApplTypeTabsColumnInfoVo;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

/**
 * @文件名 SysApplTypeTabsColumnInfoService.java
 * @包名 org.chuxue.application.bean.manager.appl.service
 * @描述 service层
 * @时间 2022年07月18日 16:07:49
 * @author
 * @版本 V1.0
 */
@Service
public class SysApplTypeTabsColumnInfoService extends BaseServiceImpl<SysApplTypeTabsColumnInfo> implements BaseService<SysApplTypeTabsColumnInfo> {
	
	@PersistenceContext
	EntityManager em;

	/**
	 * 方法名： findAllTablesCheck
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： List<SysApplTypeTabsColumnInfoVo>
	 * 作 者 ： Administrator
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<SysApplTypeTabsColumnInfoVo> findAllTablesCheck(SysApplTypeTabsColumnInfoVo info) {
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append("select c2.uuid,'" + info.getTypeCode() + "' as type_code,c1.tabs_uuid,c1.uuid as cols_uuid,c1.cols_name,c1.cols_desc,c2.cols_type,c2.cols_type_color,c2.delete_flag,c2.sort,c2.search_cloumn ");
		sbBuilder.append("from sys_dbms_tabs_cols_info c1 ");
		sbBuilder.append("left join sys_appl_type_tabs_column_info c2 on c1.uuid = c2.cols_uuid and c1.tabs_uuid = c2.tabs_uuid  ");
		if (info != null && info.getTypeCode() != null) {
			sbBuilder.append(" and c2.type_code  = '" + info.getTypeCode() + "'  ");
		}
		
		sbBuilder.append("where (c1.delete_flag = 0 or c1.delete_flag is null)   ");
		if ((info != null) && (info.getTabsUuid() != null)) {
			sbBuilder.append(" and  c1.tabs_uuid = '" + info.getTabsUuid() + "'  ");
		}
		sbBuilder.append("order by c2.delete_flag asc ,c2.search_cloumn asc,if(c2.sort is null,999,c2.sort) asc,c1.sort  ");

		Query query = em.createNativeQuery(sbBuilder.toString());
		query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> l = query.getResultList();
		List<SysApplTypeTabsColumnInfoVo> result = new ArrayList<>();
		for (Map<String, Object> map : l) {
			SysApplTypeTabsColumnInfoVo vo = new SysApplTypeTabsColumnInfoVo(map);
			result.add(vo);
		}
		return result;
	}

}
