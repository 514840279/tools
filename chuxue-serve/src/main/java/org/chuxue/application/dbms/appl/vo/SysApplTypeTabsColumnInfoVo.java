package org.chuxue.application.dbms.appl.vo;

import java.util.Map;
import java.util.UUID;

import org.chuxue.application.bean.manager.appl.SysApplTypeTabsColumnInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件名 ： SysApplTypeTabsColumnInfoVo.java
 * 包 名 ： org.chuxue.application.dbms.appl.vo
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年7月20日 下午2:26:03
 * 版 本 ： V1.0
 */
@Setter
@Getter
public class SysApplTypeTabsColumnInfoVo extends SysApplTypeTabsColumnInfo {

	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param map
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysApplTypeTabsColumnInfoVo(Map<String, Object> map) {
		this.uuid = map.get("uuid") != null ? map.get("uuid").toString() : UUID.randomUUID().toString();
		this.typeCode = map.get("type_code") != null ? map.get("type_code").toString() : null;
		this.tabsUuid = map.get("tabs_uuid") != null ? map.get("tabs_uuid").toString() : null;
		this.colsUuid = map.get("cols_uuid") != null ? map.get("cols_uuid").toString() : null;
		this.colsName = map.get("cols_name") != null ? map.get("cols_name").toString() : null;
		this.colsDesc = map.get("cols_desc") != null ? map.get("cols_desc").toString() : null;
		this.colsType = map.get("cols_type") != null ? map.get("cols_type").toString() : null;
		this.searchCloumn = map.get("search_cloumn") != null ? map.get("search_cloumn").toString() : null;
		this.colsTypeColor = map.get("cols_type_color") != null ? map.get("cols_type_color").toString() : null;
		this.deleteFlag = map.get("delete_flag") != null ? Integer.parseInt(map.get("delete_flag").toString()) : 0;
		this.isUnionId = map.get("is_union_id") != null ? (Boolean) map.get("is_union_id") : false;
		this.isRelation = map.get("is_relation") != null ? (Boolean) map.get("is_relation") : false;
		this.show = map.get("show") != null ? (Boolean) map.get("show") : true;
		this.span = map.get("span") != null ? Integer.parseInt(map.get("span").toString()) : null;
		this.icon = map.get("icon") != null ? map.get("icon").toString() : null;
		this.sort = map.get("sort") != null ? Integer.parseInt(map.get("sort").toString()) : null;
		this.indexCode = map.get("index_code") != null ? map.get("index_code").toString() : null;
		this.colsSwitchable = map.get("cols_switchable") != null ? Boolean.parseBoolean(map.get("cols_switchable").toString()) : null;
		this.colsWidth = map.get("cols_width") != null ? Integer.parseInt(map.get("cols_width").toString()) : null;
		this.colsAlign = map.get("cols_align") != null ? map.get("cols_align").toString() : null;
		
	}

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long	serialVersionUID	= 1L;

	private String				colsName;
	private String				colsDesc;
	private String				checkboxType;
	private String				indexCode;
	private Boolean				colsSwitchable;
	private Integer				colsWidth;
	private String				colsAlign;

	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数：
	 * 作 者 ： Administrator
	 * @throws
	 */
	public SysApplTypeTabsColumnInfoVo() {
	}

}
