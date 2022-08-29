package org.chuxue.application.po;

import org.chuxue.application.common.base.MybatisBaseEntity;

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

	private String	jiUuid;
	private Double	zhifuMoney;
	private Double	mairuFene;
	private Double	maichuFene;
	private Double	shuhuiMoney;
	private Double	danweijingzhi;
	private Double	shouxufei;

}
