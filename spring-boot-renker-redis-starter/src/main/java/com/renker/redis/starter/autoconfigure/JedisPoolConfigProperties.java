package com.renker.redis.starter.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix=JedisPoolConfigProperties.JEDIS_POOL_CONFIG_PREFIX)
public class JedisPoolConfigProperties {
	public static final String JEDIS_POOL_CONFIG_PREFIX="renker.redis.poolConfig";
	
	private int minIdle;
	private int maxIdle;
	private int maxTotal;
	private int maxWaitMillis;
	private boolean testOnBorrow;
	public int getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public int getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}
	public int getMaxWaitMillis() {
		return maxWaitMillis;
	}
	public void setMaxWaitMillis(int maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}
	public boolean getTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("minIdle:").append(this.minIdle).append(",");
		sb.append("maxIdle:").append(this.maxIdle).append(",");
		sb.append("maxTotal:").append(this.maxTotal).append(",");
		sb.append("maxWaitMillis:").append(this.maxWaitMillis).append(",");
		sb.append("testOnBorrow:").append(this.testOnBorrow);
		sb.append("}");
		return sb.toString();
	}
}
