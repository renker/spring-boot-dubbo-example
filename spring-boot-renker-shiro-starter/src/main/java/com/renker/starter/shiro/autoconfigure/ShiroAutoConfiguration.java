package com.renker.starter.shiro.autoconfigure;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.renker.starter.shiro.DefalutChainDefinitionSectionMetaSource;

@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
public class ShiroAutoConfiguration {
	@Autowired
	private ShiroProperties prop;
	
	
	@Bean(name="lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean(name="defalutChainDefinitionSectionMetaSource")
	public DefalutChainDefinitionSectionMetaSource defalutChainDefinitionSectionMetaSource(){
		DefalutChainDefinitionSectionMetaSource defalut = new DefalutChainDefinitionSectionMetaSource();
		defalut.setFilterChainDefinitions(prop.getFilterChainDefinitions());
		return defalut;
	}
	
	@SuppressWarnings("rawtypes")
	@Bean(name="defaultWebSecurityManager")
	public  DefaultWebSecurityManager defaultWebSecurityManager() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		DefaultWebSecurityManager manage = new DefaultWebSecurityManager();
		Class realm = Class.forName(prop.getAuthorizingRealm());
		manage.setRealm((Realm) realm.newInstance());
		return manage;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(
			@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
		
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(defaultWebSecurityManager);
		bean.setLoginUrl(prop.getLoginUrl());
		bean.setSuccessUrl(prop.getSuccessUrl());
		bean.setUnauthorizedUrl(prop.getUnauthorizedUrl());
		return bean;
	}
}
