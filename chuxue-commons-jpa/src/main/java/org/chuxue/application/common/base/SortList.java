package org.chuxue.application.common.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

/**
 * 文件名 ： SortList.java
 * 包 名 ： org.chuxue.application.common.base
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年7月6日 下午3:47:30
 * 版 本 ： V1.0
 */
public class SortList {

	/**
	 * 方法名： sort
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param sortList
	 * 参 数： @return
	 * 返 回： Sort
	 * 作 者 ： Administrator
	 * @throws
	 */
	public static Sort sort(List<SortParameters> sortList) {
		
		Collections.sort(sortList, (o1, o2) -> o1.getSortIndex() - o2.getSortIndex());
		Sort sort = null;
		List<Order> orders = new ArrayList<>();
		for (SortParameters sortParameters : sortList) {
			Order order = Order.asc(sortParameters.getSortName());
			switch (sortParameters.getSortOrder()) {
				case "asc":
					order = Order.asc(sortParameters.getSortName());
					break;
				case "desc":
					order = Order.desc(sortParameters.getSortName());
					break;
				default:
					order = Order.asc(sortParameters.getSortName());
					break;
			}
			orders.add(order);
		}
		if (orders.size() > 0) {
			sort = Sort.by(orders);
		}
		return sort;
	}
	
}
