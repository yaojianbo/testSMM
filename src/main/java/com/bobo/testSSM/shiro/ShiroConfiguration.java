package com.bobo.testSSM.shiro;


import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Shiro权限列表配置
 * @author YAOJIANBO
 *
 */
@Configuration
public class ShiroConfiguration
{
	@Autowired
	SecurityManager securityManager;
	
	@Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean()
    {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/auth/login");
        shiroFilterFactoryBean.setSuccessUrl("/jsp/index.jsp");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.jsp");
        
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/login.jsp", "anon");
        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/auth/getVerifyCodeImage", "anon");
        filterChainDefinitionMap.put("/user/getUserInfo", "perms[/user/getUserInfo]");
        filterChainDefinitionMap.put("/user/showUserList", "perms[/user/showUserList]");
        filterChainDefinitionMap.put("/respBodyTest/showUserList", "perms[/respBodyTest/showUserList]");
        filterChainDefinitionMap.put("/respBodyTest/getUser", "perms[/respBodyTest/getUser]");
        filterChainDefinitionMap.put("/jsp/info.jsp", "perms[/jsp/info.jsp]");
        filterChainDefinitionMap.put("/jsp/index.jsp", "perms[/jsp/index.jsp]");
        
        filterChainDefinitionMap.put("/**", "authc");
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
