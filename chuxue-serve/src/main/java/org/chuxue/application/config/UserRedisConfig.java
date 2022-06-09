package org.chuxue.application.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "user.redis")
@Data
@Order(value = 10)
public class UserRedisConfig {
	
	// 总开关
	private Map<String, Long> ttl;
	
}
