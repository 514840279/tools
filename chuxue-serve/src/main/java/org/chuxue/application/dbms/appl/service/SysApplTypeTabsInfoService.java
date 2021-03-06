package org.chuxue.application.dbms.appl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.chuxue.application.bean.manager.appl.SysApplTypeTabsColumnInfo;
import org.chuxue.application.bean.manager.appl.SysApplTypeTabsInfo;
import org.chuxue.application.bean.manager.dbms.SysDbmsTabsTableInfo;
import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.chuxue.application.dbms.appl.vo.SysApplTypeTabsColumnInfoParams;
import org.chuxue.application.dbms.appl.vo.SysApplTypeTabsInfoVo;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @文件名 SysApplTypeTabsInfoService.java
 * @包名 org.chuxue.application.bean.manager.appl.service
 * @描述 service层
 * @时间 2022年07月18日 16:07:49
 * @author
 * @版本 V1.0
 */
@Service
public class SysApplTypeTabsInfoService extends BaseServiceImpl<SysApplTypeTabsInfo> implements BaseService<SysApplTypeTabsInfo> {
	
	@PersistenceContext
	EntityManager						em;

	@Autowired
	SysApplTypeTabsColumnInfoService	sysApplTypeTabsColumnInfoService;
	
	/**
	 * 方法名： findAllTablesCheck
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： List<SysApplTypeTabsInfo>
	 * 作 者 ： Administrator
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<SysApplTypeTabsInfoVo> findAllTablesCheck(SysApplTypeTabsInfoVo info) {
		if (info != null) {
			StringBuilder sbBuilder = new StringBuilder();
			sbBuilder.append("select t2.uuid,t1.uuid as tabs_uuid,t2.type_code, t2.sort,t1.tabs_name,t1.tabs_desc,t2.checkbox_type,if(t2.tabs_rows_type is null,'multi-line',t2.tabs_rows_type)  as tabs_rows_type ");
			sbBuilder.append("from sys_dbms_tabs_table_info t1 ");
			sbBuilder.append("left join sys_appl_type_tabs_info t2 on t1.uuid = t2.tabs_uuid ");
			if (info.getTypeCode() != null) {
				sbBuilder.append(" and t2.type_code = '" + info.getTypeCode() + "'  ");
			}

			sbBuilder.append("where t1.type_code in (  ");
			sbBuilder.append("select  d.type_code from sys_appl_data_type_info d where d.appl_code ='" + info.getApplCode() + "' ");
			sbBuilder.append(")  ");
			if (info.getTabsName() != null) {
				sbBuilder.append(" and t1.tabs_name like '%" + info.getTabsName() + "%'  ");
			}
			if (info.getTabsDesc() != null) {
				sbBuilder.append(" and t1.tabs_desc like '%" + info.getTabsDesc() + "%'  ");
			}
			sbBuilder.append("order by t2.checkbox_type desc,t2.tabs_rows_type desc, t2.sort,t1.tabs_name  ");

			Query query = em.createNativeQuery(sbBuilder.toString());
			query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String, Object>> l = query.getResultList();
			List<SysApplTypeTabsInfoVo> result = new ArrayList<>();
			for (Map<String, Object> map : l) {
				SysApplTypeTabsInfoVo vo = new SysApplTypeTabsInfoVo(map);
				result.add(vo);
			}
			return result;
		}
		return null;
	}

	/**
	 * 方法名： saveList
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param list
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	@Transactional
	public void saveList(List<SysApplTypeTabsInfo> list) {
		if (list != null && list.size() > 0) {
			SysApplTypeTabsInfo info = new SysApplTypeTabsInfo(list.get(0).getTypeCode());
			List<SysApplTypeTabsInfo> rel = findAll(info);
			if (rel != null && rel.size() > 0) {
				deleteAll(rel);
			}
			saveAll(list);
		}
		
	}
	
	/**
	 * 方法名： saveColumns
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param param
	 * 返 回： void
	 * 作 者 ： Administrator
	 * @throws
	 */
	@Transactional
	public void saveColumns(SysApplTypeTabsColumnInfoParams param) {
		SysApplTypeTabsInfo info = param.getInfo();
		SysApplTypeTabsInfo delinfo = new SysApplTypeTabsInfo();
		delinfo.setTypeCode(info.getTypeCode());
		delinfo = findOne(delinfo);
		if (delinfo != null) {
			delete(delinfo);
		}
		
		List<SysApplTypeTabsColumnInfo> list = param.getList();
		sysApplTypeTabsColumnInfoService.saveAll(list);
		save(info);

	}

	/**
	 * 方法名： findSingleTable
	 * 功 能： 查询
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： SysDbmsTabsTableInfo
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysDbmsTabsTableInfo findSingleTable(SysApplTypeTabsInfoVo vo) {
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append("select t1.uuid,t1.jdbc_uuid,t1.tabs_name,t1.tabs_desc,t1.delete_flag,t1.tabs_rows,t1.sort,t5.type_code");
		sbBuilder.append(",t1.create_time,t1.create_user,t1.update_time,t1.update_user,t1.discription,t1.dissql,t1.tabs_space ");
		sbBuilder.append(" from sys_dbms_tabs_table_info t1 ");
		sbBuilder.append("inner join sys_appl_type_tabs_info t5 on t5.tabs_uuid = t1.uuid  and t5.checkbox_type ='redio'  ");
		sbBuilder.append("inner join sys_appl_type_info t4  on  t4.checkbox_type ='redio' and t4.type_code = t5.type_code  ");
		sbBuilder.append("inner join sys_appl_info t3 on  t3.appl_code = t4.appl_code  ");
		sbBuilder.append("inner join sys_appl_data_type_info t2 on t2.appl_code = t3.appl_code  ");
		
		if ((vo != null) && (vo.getApplCode() != null)) {
			sbBuilder.append("where t2.appl_code ='" + vo.getApplCode() + "'  ");
		}
		Query query = em.createNativeQuery(sbBuilder.toString(), SysDbmsTabsTableInfo.class);
		SysDbmsTabsTableInfo result = (SysDbmsTabsTableInfo) query.getSingleResult();

		return result;
	}

	/**
	 * 方法名： findMultityTable
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param vo
	 * 参 数： @return
	 * 返 回： List<SysDbmsTabsTableInfo>
	 * 作 者 ： Administrator
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<SysApplTypeTabsInfoVo> findMultityTable(SysApplTypeTabsInfoVo vo) {
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append("select t1.uuid,t1.jdbc_uuid,t1.tabs_name,t1.tabs_desc,t1.delete_flag,t1.tabs_rows,t1.sort,t5.type_code");
		sbBuilder.append(",t5.tabs_rows_type ");
		sbBuilder.append(" from sys_dbms_tabs_table_info t1 ");
		sbBuilder.append("inner join sys_appl_type_tabs_info t5 on t5.tabs_uuid = t1.uuid  and t5.checkbox_type ='checkbox'  ");
		sbBuilder.append("inner join sys_appl_type_info t4  on  t4.checkbox_type ='checkbox' and t4.type_code = t5.type_code  ");
		sbBuilder.append("inner join sys_appl_info t3 on  t3.appl_code = t4.appl_code  ");
		sbBuilder.append("inner join sys_appl_data_type_info t2 on t2.appl_code = t3.appl_code  ");
		
		if ((vo != null) && (vo.getApplCode() != null)) {
			sbBuilder.append("where t2.appl_code ='" + vo.getApplCode() + "'  ");
		}
		sbBuilder.append("order by t4.sort,t5.sort,t1.sort  ");
		Query query = em.createNativeQuery(sbBuilder.toString());
		query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> l = query.getResultList();
		List<SysApplTypeTabsInfoVo> result = new ArrayList<>();
		for (Map<String, Object> map : l) {
			SysApplTypeTabsInfoVo info = new SysApplTypeTabsInfoVo(map);
			result.add(info);
		}
		return result;
	}

}
