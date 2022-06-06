package org.tools.application.po;

import java.util.Date;

import lombok.Data;

@Data
public class SysTaskInfo {
	
	private String	uuid;
	private String	procedureName;
	private String	tableName;
	private Long	tableCount;
	private Long	currentId;
	private Date	createTime;
	private Date	updateTime;

}
