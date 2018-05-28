package com.basic.core.admin.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: wangzw
 * @date: 2018/5/28 16:33
 * @version: 1.0
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        Map<String,String> filterMap=new LinkedHashMap<>();
        filterMap.put("/static/**","anon");
        filterMap.put("/login_toLogin*","anon");
        filterMap.put("/login_confirmLogin*","anon");
        filterMap.put("/verifyCode*","anon");
        filterMap.put("/**","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/login_toLogin.do");
        bean.setSuccessUrl("/systemIndex.do");
        bean.setUnauthorizedUrl("/login_toLogin.do");
        return bean;
    }

    @Bean
    public ShiroRealm myShiroRealm(){
        ShiroRealm myShiroRealm = new ShiroRealm();
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }
}
