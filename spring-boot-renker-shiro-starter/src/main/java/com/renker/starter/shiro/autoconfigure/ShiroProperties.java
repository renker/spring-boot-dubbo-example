package com.renker.starter.shiro.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix=ShiroProperties.SHIRO_CONFIG_PREFIX)
public class ShiroProperties {
	public  static final String  SHIRO_CONFIG_PREFIX="renker.shiro";
	
	private String loginUrl;
	
	private String successUrl;
	
	private String unauthorizedUrl;
	
	private Object filters;
	
	private String authorizingRealm;
	
	private String filterChainDefinitions;

	public String getFilterChainDefinitions() {
		return filterChainDefinitions;
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getUnauthorizedUrl() {
		return unauthorizedUrl;
	}

	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}

	public Object getFilters() {
		return filters;
	}

	public void setFilters(Object filters) {
		this.filters = filters;
	}

	public String getAuthorizingRealm() {
		return authorizingRealm;
	}

	public void setAuthorizingRealm(String authorizingRealm) {
		this.authorizingRealm = authorizingRealm;
	}
}
