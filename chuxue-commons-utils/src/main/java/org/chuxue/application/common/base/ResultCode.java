package org.chuxue.application.common.base;

/**
 * @Author: Hutengfei
 * @Description: 返回码定义
 *               规定:
 *               #1表示成功
 *               #1001～1999 区间表示参数错误
 *               #2001～2999 区间表示用户错误
 *               #3001～3999 区间表示接口异常
 * @Date Create in 2019/7/22 19:28
 */
public enum ResultCode {
    /* 成功 */
	SUCCESS(200, ResultMessage.SUCCESS),
	
    /* 默认失败 */
	COMMON_FAIL(-1, ResultMessage.COMMON_FAIL),
	
    /* 参数错误：1000～1999 */
	PARAM_NOT_VALID(1001, ResultMessage.PARAM_NOT_VALID), PARAM_IS_BLANK(1002, ResultMessage.PARAM_IS_BLANK), PARAM_TYPE_ERROR(1003, ResultMessage.PARAM_TYPE_ERROR), PARAM_NOT_COMPLETE(1004, ResultMessage.PARAM_NOT_COMPLETE),
	
    /* 用户错误 */
	USER_NOT_LOGIN(2001, ResultMessage.USER_NOT_LOGIN), USER_ACCOUNT_EXPIRED(2002, ResultMessage.USER_ACCOUNT_EXPIRED), USER_CREDENTIALS_ERROR(2003, ResultMessage.USER_CREDENTIALS_ERROR), USER_CREDENTIALS_EXPIRED(2004, ResultMessage.USER_CREDENTIALS_EXPIRED), USER_ACCOUNT_DISABLE(2005, ResultMessage.USER_ACCOUNT_DISABLE), USER_ACCOUNT_LOCKED(2006, ResultMessage.USER_ACCOUNT_LOCKED), USER_ACCOUNT_NOT_EXIST(2007, ResultMessage.USER_ACCOUNT_NOT_EXIST), USER_ACCOUNT_ALREADY_EXIST(2008, ResultMessage.USER_ACCOUNT_ALREADY_EXIST), USER_ACCOUNT_USE_BY_OTHERS(2009, ResultMessage.USER_ACCOUNT_USE_BY_OTHERS),
	
    /* 业务错误 */
	NO_PERMISSION(3001, ResultMessage.NO_PERMISSION), NO_DATA(3002, ResultMessage.NO_DATA),

    /* 异常抛出 */
	NULL_EXCEPTION(4002, ResultMessage.NULL_EXCEPTION), NOT_FIND_EXCEPTION(4003, ResultMessage.NOT_FIND_EXCEPTION), DATA_TRANC_EXCEPTION(4004, ResultMessage.DATA_TRANC_EXCEPTION), NO_SUCH_FIELD_EXCEPTION(4005, ResultMessage.NO_SUCH_FIELD_EXCEPTION), SECURITY_EXCEPTION(4006, ResultMessage.SECURITY_EXCEPTION)
	//
	;
	
	private Integer	code;
	private String	message;
	
	ResultCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	/**
	 * 根据code获取message
	 *
	 * @param code
	 * @return
	 */
	public static String getMessageByCode(Integer code) {
		for (ResultCode ele : values()) {
			if (ele.getCode().equals(code)) {
				return ele.getMessage();
			}
		}
		return null;
	}
}