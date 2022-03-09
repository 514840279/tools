package org.tools.application.common.string;

import lombok.Data;

@Data
public class IdInfo {

	private String	id;				// 序号
	private String	deleteFlag;		// 是否正确
	private String	sfzh18;			// 18位身份证号
	private String	province;		// 省份
	private String	city;			// 市
	private String	area;			// 地区
	private String	birthday;		// 生日
	private String	gender;			// 性别
	private String	zodiac;			// 生肖
	private String	constellation;	// 星座
	
}
