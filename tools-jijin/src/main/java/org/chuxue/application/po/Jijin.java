package org.chuxue.application.po;

import java.util.Date;
import java.util.UUID;

import org.chuxue.application.common.base.MybatisBaseEntity;
import org.chuxue.application.vo.JijinVo;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件名 ： Jijin.java
 * 包 名 ： org.chuxue.application.po
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年8月26日 下午3:15:12
 * 版 本 ： V1.0
 */
@Setter
@Getter
public class Jijin extends MybatisBaseEntity {

	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数：
	 * 作 者 ： Administrator
	 * @throws
	 */
	public Jijin() {
	}

	/**
	 * 构造方法：
	 * 描 述： TODO(这里用一句话描述这个方法的作用)
	 * 参 数： @param vo
	 * 作 者 ： Administrator
	 * @throws
	 */
	public Jijin(JijinVo vo) {
		this.uuid = UUID.randomUUID().toString();
		this.name = vo.getName();
		this.deleteFlag = 0;
		this.createTime = new Date();
		this.code = vo.getCode();
		this.money = vo.getMoney();
	}

	// 基金名称
	private String	name;
	// 基金代码
	private String	code;
	// 标准值
	private Double	money;

}
