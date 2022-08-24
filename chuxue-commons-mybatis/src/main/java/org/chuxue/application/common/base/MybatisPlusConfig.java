package org.chuxue.application.common.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

@Configuration
public class MybatisPlusConfig {

	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		try {
			MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
			interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
			return interceptor;
		} catch (Exception e) {
			return null;
		}
	}
}