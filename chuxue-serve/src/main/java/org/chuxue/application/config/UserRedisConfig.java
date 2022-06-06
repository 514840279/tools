package org.chuxue.application.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "user.redis")
@Data
public class UserRedisConfig {
	
	// 总开关
	private Map<String, Long> ttl;
	
}
