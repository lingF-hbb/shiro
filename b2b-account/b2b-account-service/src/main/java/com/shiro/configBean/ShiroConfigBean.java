package com.shiro.configBean;

import com.shiro.realms.MyRealms;

import org.apache.log4j.spi.LoggerFactory;

import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置bean
 * */
@Configuration
public class ShiroConfigBean {
//    private static final Logger logger=LoggerFactory.getLogger(ShiroConfigBean.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        System.out.println("ShiroConfiguration.shiroFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

//      设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        设置login URL
        shiroFilterFactoryBean.setLoginUrl("/login");
//        登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/loginSuccess.action");
//        没有授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unanthorized.action");
//        构建需要拦截的路径的集合
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/jquery/*","anon");
//        设置登录的url为匿名访问，因为一开始没有用户验证
        filterChainDefinitionMap.put("/login.action","anon");
        filterChainDefinitionMap.put("/Exception.class","anon");
//      设置注册是url是匿名访问
        filterChainDefinitionMap.put("/insert.action","anon");
       filterChainDefinitionMap.put("/*.action","authc");
//        退出系统的过滤器
        filterChainDefinitionMap.put("/logout","logout");
//        给路径设置登录的角色
        filterChainDefinitionMap.put("/admin.html","roles[admin]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

//   身份适配器
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

//注入自定义的realm
    @Bean
    public MyRealms myAuthRealm(){
        MyRealms myRealms = new MyRealms();
        return myRealms;
    }

//    注入安全管理器
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(myAuthRealm());
//        defaultWebSecurityManager.setCacheManager(ehCacheManager());
        return defaultWebSecurityManager;
    }

    /**
     * shiro缓存管理器
     *并将缓存管理注入到安全管理器中
     * */
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }

}
