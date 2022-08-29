package org.chuxue.application.vo;

import java.util.List;

import org.chuxue.application.po.Flow;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件名 ： JinjinVo.java
 * 包 名 ： org.chuxue.application.vo
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2022年8月26日 下午3:23:43
 * 版 本 ： V1.0
 */
@Setter
@Getter
public class JijinVo {
	// 基金名称
	private String	name;
	// 基金代码
	private String	code;
	// 标准值
	private Double	money;
	// 累计金额
	private Double	countMoney;
	// 累计份额
	private Double	countFene;
	// 标准净值
	private Double	pinjunjingzhi;

	// 标准营收
	private Double	yingshou;
	// 标准利润
	private Double	lirun;

	List<Flow>		flowList;
}
