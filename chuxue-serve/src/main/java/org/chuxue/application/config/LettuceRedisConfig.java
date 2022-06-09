package org.chuxue.application.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 田治功
 * @description Redis配置类：Lettuce客户端
 * @date 2021-10-31 1:36
 */
@Configuration
@EnableCaching // 开启缓存
@Order(50)
public class LettuceRedisConfig {
	
	@Autowired
	UserRedisConfig userRedisConfig;
	
	@Bean
	public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		redisCacheConfiguration = redisCacheConfiguration
		        // 当value为null时不进行缓存
		        .disableCachingNullValues()
		        // 设置 key 序列化
		        .serializeKeysWith(keyPair())
		        // 设置 value 序列化
		        .serializeValuesWith(valuePair())
		        // 配置缓存空间名称的前缀
		        .prefixCacheNameWith("demo:")
		        // 全局配置缓存过期时间【可以不配置】
		        .entryTtl(Duration.ofSeconds(30L));
		Map<String, RedisCacheConfiguration> map = new HashMap<>();
		for (String key : userRedisConfig.getTtl().keySet()) {
			// 指定特定缓存空间对应的过期时间
			map.put(key, redisCacheConfiguration.entryTtl(Duration.ofSeconds(userRedisConfig.getTtl().get(key))));
		}
		// 专门指定某些缓存空间的配置，如果过期时间【主要这里的key为缓存空间名称】
		return RedisCacheManager.builder(redisConnectionFactory)
		        // 默认配置
		        .cacheDefaults(redisCacheConfiguration)
		        //
		        .initialCacheNames(userRedisConfig.getTtl().keySet())
		        // 某些缓存空间的特定配置
		        .withInitialCacheConfigurations(map)
		        //
		        .build();
	}

	/**
	 * 配置键序列化
	 *
	 * @return StringRedisSerializer
	 */
	
	private RedisSerializationContext.SerializationPair<String> keyPair() {
		return RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer.UTF_8);
	}
	
	/**
	 * 配置值序列化，使用 GenericJackson2JsonRedisSerializer 替换默认序列化
	 *
	 * @return GenericJackson2JsonRedisSerializer
	 */
	
	private RedisSerializationContext.SerializationPair<Object> valuePair() {
		return RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());// StringRedisSerializer：序列化为String
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());// GenericJackson2JsonRedisSerializer：序列化为JSON,同时在json中加入@class属性，类的全路径包名，方便反系列化
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setConnectionFactory(lettuceConnectionFactory);// 设置连接工厂
		return redisTemplate;
	}
}
