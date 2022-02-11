package org.tools.application.po;

import java.util.Date;

import lombok.Data;

@Data
public class SysTaskErrorMessage {
	
	private Long	id;
	private String	taskId;
	private String	tableId;
	private Integer	code;
	private String	message;
	private Date	createTime;
	
}
