package org.chuxue.application.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.ResultCode;
import org.chuxue.application.common.base.ResultUtil;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * @Author: Hutengfei
 * @Description: 登录失败处理逻辑
 * @Date Create in 2019/9/3 15:52
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		// 返回json数据
		BaseResult<?> result = ResultUtil.success();
		if (e instanceof AccountExpiredException) {
			// 账号过期
			result = ResultUtil.fail(ResultCode.USER_ACCOUNT_EXPIRED);
		} else if (e instanceof BadCredentialsException) {
			// 密码错误
			result = ResultUtil.fail(ResultCode.USER_CREDENTIALS_ERROR);
		} else if (e instanceof CredentialsExpiredException) {
			// 密码过期
			result = ResultUtil.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
		} else if (e instanceof DisabledException) {
			// 账号不可用
			result = ResultUtil.fail(ResultCode.USER_ACCOUNT_DISABLE);
		} else if (e instanceof LockedException) {
			// 账号锁定
			result = ResultUtil.fail(ResultCode.USER_ACCOUNT_LOCKED);
		} else if (e instanceof InternalAuthenticationServiceException) {
			// 用户不存在
			result = ResultUtil.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
		} else {
			// 其他错误
			result = ResultUtil.fail(ResultCode.COMMON_FAIL);
		}
		// 处理编码方式，防止中文乱码的情况
		httpServletResponse.setContentType("text/json;charset=utf-8");
		// 塞到HttpServletResponse中返回给前台
		httpServletResponse.getWriter().write(JSON.toJSONString(result));
	}
}