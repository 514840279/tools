package org.chuxue.application.dbms.echarts.service;

import java.util.ArrayList;
import java.util.List;

import org.chuxue.application.bean.manager.dbms.SysDbmsChartDimensionData;
import org.chuxue.application.common.base.BaseService;
import org.chuxue.application.common.base.BaseServiceImpl;
import org.chuxue.application.common.base.Pagination;
import org.chuxue.application.common.base.SortList;
import org.chuxue.application.dbms.echarts.dao.SysDbmsChartDimensionDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

/**
 * @文件名 SysDbmsChartDimensionDataService.java
 * @包名 org.danyuan.application.dbms.echarts.service
 * @描述 service层
 * @时间 2020年04月25日 12:15:43
 * @author test
 * @版本 V1.0
 */
@Service
public class SysDbmsChartDimensionDataService extends BaseServiceImpl<SysDbmsChartDimensionData> implements BaseService<SysDbmsChartDimensionData> {
	
	@Autowired
	SysDbmsChartDimensionDataDao sysDbmsChartDimensionDataDao;
	
	/**
	 * 方法名 ： page
	 * 功 能 ： TODO(这里用一句话描述这个方法的作用)
	 * 参 数 ： @param pageNumber
	 * 参 数 ： @param pageSize
	 * 参 数 ： @param info
	 * 参 数 ： @param map
	 * 参 数 ： @param order
	 * 参 数 ： @return
	 * 参 考 ： @see com.shumeng.application.common.base.BaseService#page(int, int, java.lang.Object, java.util.Map, org.springframework.data.domain.Sort.Order[])
	 * 作 者 ： Administrator
	 */
	
	@Override
	public Page<SysDbmsChartDimensionData> page(Pagination<SysDbmsChartDimensionData> vo) {
		Sort sort = SortList.sort(vo.getSortList());
		if (sort == null) {
			List<Order> orders = new ArrayList<>();
			Order order = new Order(Direction.ASC, "createTime");
			orders.add(order);
			sort = Sort.by(orders);
		}
		if (vo.getInfo() == null) {
			vo.setInfo(new SysDbmsChartDimensionData());
		}
		
		Example<SysDbmsChartDimensionData> example = Example.of(vo.getInfo());
		PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
		return sysDbmsChartDimensionDataDao.findAll(example, request);
	}
}
