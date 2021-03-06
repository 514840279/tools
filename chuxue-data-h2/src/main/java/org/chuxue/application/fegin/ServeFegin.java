package org.chuxue.application.fegin;

import org.chuxue.application.bean.manager.dbms.SysDbmsTabsJdbcInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 文件名 ： ServeFegin.java
 * 包 名 ： org.danyuan.application.fegin
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： wth
 * 时 间 ： 2021年9月7日 下午9:00:11
 * 版 本 ： V1.0
 */
@FeignClient(name = "chuxue-serve")
public interface ServeFegin {
	
	/**
	 * 方法名： save
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 返 回： void
	 * 作 者 ： wth @throws
	 */
	@PostMapping("/serve/sysDbmsTabsJdbcInfo/save")
	void save(SysDbmsTabsJdbcInfo info);
	
}
