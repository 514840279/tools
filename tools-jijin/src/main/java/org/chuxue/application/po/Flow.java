package org.chuxue.application.po;

import java.util.Date;
import java.util.UUID;

import org.chuxue.application.common.base.MybatisBaseEntity;
import org.chuxue.application.vo.JijinVo;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件名 ： Flow.java
 * 包 名 ： org.chuxue.application.po
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年8月26日 下午3:17:19
 * 版 本 ： V1.0
 */
@Setter
@Getter
public class Flow extends MybatisBaseEntity {
	
	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数：
	 * 作 者 ： Administrator
	 * @throws
	 */
	public Flow() {
	}
	
	/**
	 * @param ji
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param vo
	 * 作 者 ： Administrator
	 * @throws
	 */
	public Flow(JijinVo vo, Jijin ji) {
		this.uuid = UUID.randomUUID().toString();
		this.createTime = new Date();
		this.deleteFlag = 0;
		this.jiUuid = ji.getUuid();
		this.zhifuMoney = vo.getMoney();
//		this.mairuFene = (vo.getMoney() - vo.getShouxufei()) / vo.getPinjunjingzhi();
		this.danweijingzhi = vo.getPinjunjingzhi();
		this.shouxufei = vo.getShouxufei();
		
	}
	
	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param uuid
	 * 作 者 ： Administrator
	 * @throws
	 */
	public Flow(String uuid) {
		this.jiUuid = uuid;
	}
	
	private String	jiUuid;
	private Double	zhifuMoney;
	private Double	mairuFene;
	private Double	maichuFene;
	private Double	shuhuiMoney;
	private Double	danweijingzhi;
	private Double	shouxufei;
	
}
