package org.tools.rollcall.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "user")
@Data
public class UserConfig {
	
	// 总开关
	private Boolean	start				= false;

	private String	fileUploadPath		= "doc/upload";

	private String	fileDownloadPath	= "doc/download";
	
}
