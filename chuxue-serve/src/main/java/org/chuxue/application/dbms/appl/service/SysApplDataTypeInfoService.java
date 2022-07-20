package org.chuxue.application.dbms.appl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.chuxue.application.bean.manager.appl.SysApplDataTypeInfo;
import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.chuxue.application.dbms.appl.vo.SysApplDataTypeInfoVo;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

/**
 * @文件名 SysApplDataTypeInfoService.java
 * @包名 org.chuxue.application.dbms.appl.service
 * @描述 service层
 * @时间 2022年07月20日 10:58:32
 * @author
 * @版本 V1.0
 */
@Service
public class SysApplDataTypeInfoService extends BaseServiceImpl<SysApplDataTypeInfo> implements BaseService<SysApplDataTypeInfo> {

	@PersistenceContext
	EntityManager em;
	
	/**
	 * 方法名： findAllTablesCheck
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： List<SysApplDataTypeInfoVo>
	 * 作 者 ： Administrator
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<SysApplDataTypeInfoVo> findAllTablesCheck(SysApplDataTypeInfoVo info) {
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append("select t2.uuid,t1.type_code,t1.type_name,t2.appl_code,t2.checkbox_type ");
		sbBuilder.append("from sys_dbms_tabs_type_info t1  ");
		sbBuilder.append("left join sys_appl_data_type_info t2 on t1.type_code = t2.type_code ");
		if (info != null && info.getApplCode() != null) {
			sbBuilder.append(" and t2.appl_code = '" + info.getApplCode() + "'  ");
		}
		if (info != null) {
			sbBuilder.append("where 1=1  ");
			if (info.getTypeName() != null) {
				sbBuilder.append(" and t1.type_name like '%" + info.getTypeName() + "%'  ");
			}
			if (info.getTypeCode() != null) {
				sbBuilder.append(" and t1.tabs_desc like '%" + info.getTypeCode() + "%'  ");
			}
		}
		sbBuilder.append("order by t2.sort  ");

		Query query = em.createNativeQuery(sbBuilder.toString());
		query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> l = query.getResultList();
		List<SysApplDataTypeInfoVo> result = new ArrayList<>();
		for (Map<String, Object> map : l) {
			SysApplDataTypeInfoVo vo = new SysApplDataTypeInfoVo(map);
			result.add(vo);
		}
		return result;
	}

	/**
	 * 方法名： saveList
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param list
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	public void saveList(List<SysApplDataTypeInfo> list) {
		if (list != null && list.size() > 0) {
			SysApplDataTypeInfo info = new SysApplDataTypeInfo(list.get(0).getApplCode());
			List<SysApplDataTypeInfo> rel = findAll(info);
			if (rel != null && rel.size() > 0) {
				deleteAll(rel);
			}
			for (SysApplDataTypeInfo sysApplTypeTabsInfo : list) {
				save(sysApplTypeTabsInfo);
			}
		}
		
	}
	
}
