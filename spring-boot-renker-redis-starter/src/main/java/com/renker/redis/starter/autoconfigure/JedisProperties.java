package com.renker.redis.starter.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix=JedisProperties.JEDIS_PREFIX)
public class JedisProperties {
	public static final String JEDIS_PREFIX="renker.redis";
	
	private String host;
	private int port;
	private int timeout;
	private String password;
	private boolean usePool;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUsePool() {
		return usePool;
	}
	public void setUsePool(boolean usePool) {
		this.usePool = usePool;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("host:").append(this.host).append(",");
		sb.append("port:").append(this.port).append(",");
		sb.append("timeout:").append(this.timeout).append(",");
		sb.append("password:").append(this.password).append(",");
		sb.append("usePool:").append(this.usePool);
		sb.append("}");
		return sb.toString();
	}
}
