package org.chuxue.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService			userDetailsService;

	@Autowired
	CustomizeAuthenticationSuccessHandler	authenticationSuccessHandler;
	
	@Autowired
	CustomizeAuthenticationFailureHandler	authenticationFailureHandler;

	@Autowired
	CustomizeLogoutSuccessHandler			logoutSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}
	
	public static void main(String[] args) {
		PasswordEncoder d = passwordEncoder();
		System.out.println(d.encode("123456"));
	}

	private static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated()
		// 拦截 请求为 /user/menu的地址 权限 admin
//		        .antMatchers("/user", "/menu").access("hasRole('ADMIN')")
		// 其他的全部允许
//		        .antMatchers("/", "/**").permitAll()
		// form 登录的请求地址/login 登录成功页面/ home
//		        .and().formLogin().loginPage("/login").defaultSuccessUrl("/home")
		        // 登入
		        .and().formLogin().loginPage("/login").permitAll().// 允许所有用户
		        successHandler(authenticationSuccessHandler).// 登录成功处理逻辑
		        failureHandler(authenticationFailureHandler).// 登录失败处理逻辑
				// 登出 页面跳转到 /login
//		        .and().logout().logoutSuccessUrl("/login")
				// 登出
		        and().logout().permitAll().// 允许所有用户
		        logoutSuccessHandler(logoutSuccessHandler).// 登出成功处理逻辑
		        deleteCookies("JSESSIONID")// 登出之后删除cookie
		;
		// CSRF跨域
		http.cors().and().csrf().disable();
	}
}
