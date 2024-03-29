package org.chuxue.application.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.ResultCode;
import org.chuxue.application.common.base.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * @Author: Hutengfei
 * @Description: 匿名用户访问无权限资源时的异常
 * @Date Create in 2019/9/3 21:35
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		BaseResult<?> result = ResultUtil.fail(ResultCode.USER_NOT_LOGIN);
		httpServletResponse.setContentType("text/json;charset=utf-8");
		httpServletResponse.getWriter().write(JSON.toJSONString(result));
	}

}