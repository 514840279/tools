package com.tools.jijin.po;

import java.util.Date;

import javax.persistence.Entity;

import org.chuxue.application.common.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Jijin extends BaseEntity {
	
//	id
//	名称
	private String name;
//	日期
	private Date date;
//	购买金额
	private Double money;
//	当天股价
	private Double gujia;
//	购买股份
	private Double gufen;
//	最终盈利
	private Double leijiyingli;
//	操作值
	private String caozuo;
//	预警线min
	private Double yujingMin;
//	预警线max
	private Double yujingMax;
//	排序
	
//	创建时间
//	修改时间
//	状态
	
}
