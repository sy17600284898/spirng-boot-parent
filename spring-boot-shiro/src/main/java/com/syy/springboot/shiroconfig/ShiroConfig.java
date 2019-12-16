package com.syy.springboot.shiroconfig;


import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig
 *
 * @author: shiyan
 * @version: 2019-12-11 15:52
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //Custom interceptor
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        //Limit the number of simultaneous online accounts。
        filtersMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        // Access control map.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // Public request
        filterChainDefinitionMap.put("/common/**", "anon");
        // Login method, which means anonymous access
        filterChainDefinitionMap.put("/login/**", "anon");
        //You need to add a kickout here for the custom interceptor added above to take effect
        filterChainDefinitionMap.put("/**", "authc,kickout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * Restrict the number of users who log in to the same account at the same time
     *
     * @return
     */
    @Bean
    public SessionControlFilter kickoutSessionControlFilter() {
        SessionControlFilter kickoutSessionControlFilter = new SessionControlFilter();
        //使Use cacheManager to get the corresponding cache to cache user login sessions; used to save the relationship between users and sessions；
        //Here we still use the cacheManager () cache management implemented by redisManager () used by Shiro
        //You can also write another one to reconfigure custom cache properties such as cache time.
        kickoutSessionControlFilter.setCache(cacheManager());
        //Used to get the session for kick-out operation based on the session ID
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        //Whether to log out and log in later, the default is false; that is, the latter logged in user kicks out the former logged in user; the kick out order
        kickoutSessionControlFilter.setKickoutAfter(false);
        //The maximum number of sessions for the same user, the default is 1; for example, 2 means that the same user allows up to two people to log in simultaneously
        kickoutSessionControlFilter.setMaxSession(1);
        kickoutSessionControlFilter.setKickoutUrl("/login/logout");
        return kickoutSessionControlFilter;
    }

    /**
     * RImplementation of edisSessionDAO shiro sessionDao layer via redis
     * Using shiro-redis open source plugin
     */
    @Bean
    public RedisSessionDao redisSessionDAO() {
        RedisSessionDao redisSessionDAO = new RedisSessionDao();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setKeyPrefix("SPRINGBOOT_SESSION:");
        return redisSessionDAO;
    }

    /**
     * Session Manager
     * Using shiro-redis open source plugin
     */
    @Bean
    public SessionManager sessionManager() {
        SimpleCookie simpleCookie = new SimpleCookie("Token");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(false);

        SessionManager sessionManager = new SessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdCookieEnabled(false);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }

    /**
     * Configure shiro redisManager
     * Using shiro-redis open source plugin
     * Need password to add password
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost);
        redisManager.setPort(redisPort);
        redisManager.setTimeout(1800);
        return redisManager;
    }

    /**
     * cacheManager cache redis implementation
     * Using shiro-redis open source plugin
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //Set prefix
        redisCacheManager.setKeyPrefix("SPRINGBOOT_CACHE:");
        return redisCacheManager;
    }


    @Bean
    public ShiroRealm myShiroRealm() {
        ShiroRealm myShiroRealm = new ShiroRealm();
        myShiroRealm.setCredentialsMatcher(credentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }


    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // Setting up realm.
        securityManager.setRealm(myShiroRealm());
        // Custom cache implementation using redis
        securityManager.setCacheManager(cacheManager());
        // Custom session management using redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * Enable shiro aop annotation support.
     * Use proxy mode; so need to enable code support;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Shiro Lifecycle Processor
     * This method needs to use static as a modifier, otherwise the value of the configuration file cannot be obtained through the @Value () annotation.
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /***
     * Configuration used for authorization
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * Custom Realm management, mainly for multiple realm
     */
    @Bean("myModularRealmAuthenticator")
    public MyModularRealmAuthenticator modularRealmAuthenticator() {
        MyModularRealmAuthenticator customizedModularRealmAuthenticator = new MyModularRealmAuthenticator();
        // Set realm judgment conditions
        customizedModularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return customizedModularRealmAuthenticator;
    }
}
