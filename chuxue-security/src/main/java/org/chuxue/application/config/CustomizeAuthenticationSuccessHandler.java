package org.chuxue.application.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chuxue.application.common.base.BaseResult;
import org.chuxue.application.common.base.ResultUtil;
import org.chuxue.application.po.User;
import org.chuxue.application.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * @Author: Hutengfei
 * @Description: 登录成功处理逻辑
 * @Date Create in 2019/9/3 15:52
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	SysUserService sysUserService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		// 更新用户表上次登录时间、更新人、更新时间等字段
		User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails sysUser = sysUserService.loadUserByUsername(userDetails.getUsername());
		sysUserService.updateLastLoginTime(sysUser);
		
		// 此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
		// 进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展
		
		// 返回json数据
		BaseResult<UserDetails> result = ResultUtil.success(sysUser);
		// 处理编码方式，防止中文乱码的情况
		httpServletResponse.setContentType("text/json;charset=utf-8");
		// 塞到HttpServletResponse中返回给前台
		httpServletResponse.getWriter().write(JSON.toJSONString(result));
	}
}