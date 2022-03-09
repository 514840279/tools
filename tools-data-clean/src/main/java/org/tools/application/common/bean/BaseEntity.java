/**
 * 文件名：BaseEntity.java 版本信息： 日期：2018年6月4日 Copyright 足下 Corporation 2018 版权所有
 */
package org.tools.application.common.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 文件名 ： BaseEntity.java
 * 包 名 ： tk.ainiyue.danyuan.application.common.base
 * 描 述 ： 通用实体类超类
 * 作 者 ： Administrator
 * 时 间 ： 2018年6月4日 上午11:11:47
 * 版 本 ： V1.0
 */

@Data
public class BaseEntity {
	
	// 主键
	protected String	uuid;
	
	// 资源功能描述
	protected String	discription;
	
	// 插入时间
	@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date		createTime;
	
	// 插入人
	protected String	createUser;
	
	// 更新时间
	@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date		updateTime;
	
	// 更新人
	protected String	updateUser;
	
	// 数据开启删除状态
	protected Integer	deleteFlag;
	
	public BaseEntity() {
		super();
	}
	
	public BaseEntity(String uuid) {
		super();
		this.uuid = uuid;
	}
	
}
