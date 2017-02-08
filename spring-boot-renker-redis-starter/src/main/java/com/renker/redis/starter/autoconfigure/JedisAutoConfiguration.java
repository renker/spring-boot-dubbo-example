package com.renker.redis.starter.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableConfigurationProperties({JedisProperties.class,JedisPoolConfigProperties.class})
public class JedisAutoConfiguration {
	Logger logger = LoggerFactory.getLogger(JedisAutoConfiguration.class);
	@Autowired
	private JedisProperties prop;
	
	@Autowired
	private JedisPoolConfigProperties poolConfigProp;
	
	/**
	 * 连接池
	 * @return
	 */
	@Bean(name="jedisPoolConfig")
	public JedisPoolConfig jedisPoolConfig(){
		if(logger.isDebugEnabled()){
			logger.debug(poolConfigProp.toString());
		}
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMinIdle(poolConfigProp.getMinIdle());
		poolConfig.setMaxIdle(poolConfigProp.getMaxIdle());
		poolConfig.setMaxTotal(poolConfigProp.getMaxTotal());
		poolConfig.setMaxWaitMillis(poolConfigProp.getMaxWaitMillis());
		poolConfig.setTestOnBorrow(poolConfigProp.getTestOnBorrow());
		return poolConfig;
	}
	
	/**
	 * 连接池工厂
	 * @param jedisPoolConfig
	 * @return
	 */
	@Bean(name="jedisConnectionFactory")
	public JedisConnectionFactory jedisConnectionFactory(@Qualifier("jedisPoolConfig")JedisPoolConfig jedisPoolConfig){
		if(logger.isDebugEnabled()){
			logger.debug(prop.toString());
		}
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(prop.getHost());
		factory.setPort(prop.getPort());
		factory.setPassword(prop.getPassword());
		factory.setUsePool(prop.isUsePool());
		factory.setPoolConfig(jedisPoolConfig);
		factory.setTimeout(prop.getTimeout());
		
		return factory;
	}
	
	@Bean(name="redisTemplate")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  RedisTemplate redisTemplate(@Qualifier("jedisConnectionFactory")JedisConnectionFactory jedisConnectionFactory){
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new JdkSerializationRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new JdkSerializationRedisSerializer());
		
		return template;
	}
	
	@SuppressWarnings("rawtypes")
	@Bean(name="cacheManager")
	public CacheManager cacheManager(@Qualifier("redisTemplate")RedisTemplate redisTemplate){
		CacheManager cacheManager = new RedisCacheManager(redisTemplate);
		return cacheManager;
	}
	
	
}
