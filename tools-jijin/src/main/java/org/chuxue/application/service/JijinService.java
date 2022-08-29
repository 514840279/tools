package org.chuxue.application.service;

import java.util.List;

import org.chuxue.application.common.base.MybatisBaseServiceImpl;
import org.chuxue.application.dao.JijinDao;
import org.chuxue.application.po.Flow;
import org.chuxue.application.po.Jijin;
import org.chuxue.application.vo.JijinVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文件名 ： JijinService.java
 * 包 名 ： org.chuxue.application.service
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年8月26日 下午3:31:38
 * 版 本 ： V1.0
 */
@Service
public class JijinService extends MybatisBaseServiceImpl<JijinDao, Jijin> {

	@Autowired
	JijinDao jijinDao;

	/**
	 * 方法名： search
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： JijinVo
	 * 作 者 ： Administrator
	 * @throws
	 */
	public List<JijinVo> search(Jijin info) {
		List<JijinVo> list = jijinDao.search(info);
		
		return list;
	}
	
	/**
	 * 方法名： goumai
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： JijinVo
	 * 作 者 ： Administrator
	 * @throws
	 */
	public List<JijinVo> goumai(Flow info) {
		jijinDao.goumai(info);
		List<JijinVo> list = search(null);

		return list;
	}

	/**
	 * 方法名： maichu
	 * 功 能： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param info
	 * 参 数： @return
	 * 返 回： JijinVo
	 * 作 者 ： Administrator
	 * @throws
	 */
	public List<JijinVo> maichu(Flow info) {
		jijinDao.maichu(info);
		List<JijinVo> list = search(null);
		return list;
	}

}
