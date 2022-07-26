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
		sbBuilder.append("select ");
		sbBuilder.append(" c2.uuid");
		sbBuilder.append(",'" + info.getTypeCode() + "' as type_code");
		sbBuilder.append(",c1.tabs_uuid");
		sbBuilder.append(",c1.uuid as cols_uuid");
		sbBuilder.append(",c1.cols_name");
		sbBuilder.append(",c1.cols_desc");
		sbBuilder.append(",c2.cols_type");
		sbBuilder.append(",c2.cols_type_color");
		sbBuilder.append(",if(c2.delete_flag  is null,c1.delete_flag,c2.delete_flag) as delete_flag"); // 删除不用
		sbBuilder.append(",if(c2.sort is null,c1.sort,c2.sort) as sort");
		sbBuilder.append(",c2.search_cloumn ");
		sbBuilder.append(",c2.is_union_id "); // 是否是主键，一般基本表需要配置解决页面可能出现重复值不显示的问题
		sbBuilder.append(",c2.is_relation "); // 是否是关联关系字段
		sbBuilder.append(",c2.show "); // 是否是显示字段 区别于delete_flag 新增字段，表示该字段使用但是页面不进行表述
		sbBuilder.append(",c2.span "); // 描述占几个
		sbBuilder.append(", if(c2.icon is null ,c1.user_icon,c2.icon) as icon "); // 图标

		sbBuilder.append("from sys_dbms_tabs_cols_info c1 ");
		sbBuilder.append("left join sys_appl_type_tabs_column_info c2 on c1.uuid = c2.cols_uuid and c1.tabs_uuid = c2.tabs_uuid  ");
		if (info != null && info.getTypeCode() != null) {
			sbBuilder.append(" and c2.type_code  = '" + info.getTypeCode() + "'  ");
		}

		sbBuilder.append("where (c1.delete_flag = 0 or c1.delete_flag is null)   ");
		if ((info != null) && (info.getTabsUuid() != null)) {
			sbBuilder.append(" and  c1.tabs_uuid = '" + info.getTabsUuid() + "'  ");
		}
		sbBuilder.append("order by if(c2.sort is null,999,c2.sort) asc,c2.delete_flag asc ,c2.show asc,c2.search_cloumn asc,c1.sort  ");
		
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
	
	/**
	 * 方法名： findAllTables
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： List<SysApplTypeTabsColumnInfoVo>
	 * 作 者 ： Administrator
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<SysApplTypeTabsColumnInfoVo> findAllColumns(SysApplTypeTabsColumnInfoVo info) {
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append("select c2.uuid,c2.type_code,c1.tabs_uuid,c1.uuid as cols_uuid,c1.cols_name,c1.cols_desc,c2.cols_type,c2.cols_type_color,c2.delete_flag,c2.sort,c2.search_cloumn ");
		sbBuilder.append(",c2.is_union_id "); // 是否是主键，一般基本表需要配置解决页面可能出现重复值不显示的问题
		sbBuilder.append(",c2.is_relation "); // 是否是关联关系字段
		sbBuilder.append(",c2.show "); // 是否是显示字段 区别于delete_flag 新增字段，表示该字段使用但是页面不进行表述
		sbBuilder.append(",c2.span "); // 描述占几个
		sbBuilder.append(", if(c2.icon is null ,c1.user_icon,c2.icon) as icon "); // 图标
		sbBuilder.append(",c1.cols_width,c1.cols_align,c1.cols_switchable,c1.index_code "); //
		
		sbBuilder.append("from sys_dbms_tabs_cols_info c1 ");
		sbBuilder.append("inner join sys_appl_type_tabs_column_info c2 on c1.uuid = c2.cols_uuid and c1.tabs_uuid = c2.tabs_uuid  ");
		sbBuilder.append("inner join sys_appl_type_tabs_info c3  on c3.tabs_uuid = c2.tabs_uuid and c2.type_code = c3.type_code  ");

		sbBuilder.append("where c2.delete_flag = 0   ");
		if (info != null) {
			if (info.getTabsUuid() != null) {
				sbBuilder.append(" and  c2.tabs_uuid = '" + info.getTabsUuid() + "'  ");
			}
			if (info.getTypeCode() != null) {
				sbBuilder.append(" and  c2.type_code = '" + info.getTypeCode() + "'  ");
			}
			if (info.getCheckboxType() != null) {
				sbBuilder.append(" and c3.checkbox_type ='" + info.getCheckboxType() + "'  ");
			}
		}
		sbBuilder.append("order by if(c2.sort is null,999,c2.sort) asc,c2.show asc,c1.sort  ");
		
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
