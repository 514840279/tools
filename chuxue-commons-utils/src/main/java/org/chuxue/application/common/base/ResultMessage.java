package org.chuxue.application.common.base;

/**
 * 文件名 ： ResultMessage.java
 * 包 名 ： org.danyuan.application.common.base
 * 描 述 ： TODO(用一句话描述该文件做什么)
 * 机能名称：
 * 技能ID ：
 * 作 者 ： Administrator
 * 时 间 ： 2021年11月3日 上午11:03:26
 * 版 本 ： V1.0
 */
public abstract class ResultMessage {

	// 200
	public static final String	SUCCESS						= "执行成功";
	
	/* 默认失败 */
	public static final String	COMMON_FAIL					= "失败";
	
	/* 参数错误：1000～1999 */
	public static final String	PARAM_NOT_VALID				= "参数无效";
	public static final String	PARAM_IS_BLANK				= "参数为空";
	public static final String	PARAM_TYPE_ERROR			= "参数类型错误";
	public static final String	PARAM_NOT_COMPLETE			= "参数缺失";
	
	/* 用户错误 */
	public static final String	USER_NOT_LOGIN				= "用户未登录";
	public static final String	USER_ACCOUNT_EXPIRED		= "账号已过期";
	public static final String	USER_CREDENTIALS_ERROR		= "密码错误";
	public static final String	USER_CREDENTIALS_EXPIRED	= "密码过期";
	public static final String	USER_ACCOUNT_DISABLE		= "账号不可用";
	public static final String	USER_ACCOUNT_LOCKED			= "账号被锁定";
	public static final String	USER_ACCOUNT_NOT_EXIST		= "账号不存在";
	public static final String	USER_ACCOUNT_ALREADY_EXIST	= "账号已存在";
	public static final String	USER_ACCOUNT_USE_BY_OTHERS	= "账号下线";
	
	/* 业务错误 */
	public static final String	NO_PERMISSION				= "没有权限";
	
	// 没有数据
	public static final String	NO_DATA						= "没有数据";

	/* 异常抛出 */
	// 指针
	public static final String	NULL_EXCEPTION				= "空指针异常";
	// 数据，文件，类型 未找到
	public static final String	NOT_FIND_EXCEPTION			= "未找到异常";
	// 类型转化
	public static final String	DATA_TRANC_EXCEPTION		= "数据类型转化异常";
	// 字段异常
	public static final String	NO_SUCH_FIELD_EXCEPTION		= "没有字段异常";
	// 字段安全异常
	public static final String	SECURITY_EXCEPTION			= "安全异常";

}
