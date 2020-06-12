package com.springboot.model.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ftsh
 */
@Configuration
public class ShiroConfig{

    /**
     * 密码校验规则HashedCredentialsMatcher
     * 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1);
        // 存储散列后的密码是否为16进制
      /*  credentialsMatcher.setStoredCredentialsHexEncoded(false);*/
        return credentialsMatcher;
    }

    /**
     * 开启shiro注解支持第一步
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * 开启shiro注解支持第二步
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    //开启shiro注解支持第三步  配置全局异常处理器

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*
        * anon:无需认证就可以访问
        * authc：必须认证才能访问
        * user：必须拥有 记住我功能才能访问
        * perms：必须拥有某个资源的权限才能用
        * role：拥有某个角色的权限才能用
        * */
        //添加shiro过滤器
        Map<String,String> map=new LinkedHashMap<String,String>();
        map.put("/user/update","perms[user:VIP2]");
        map.put("/user/*","authc");
        map.put("/user/add","perms[user:VIP1]");
        //logout如果不需要其他操作，可以不写接口
        map.put("/logout","logout");
        //必须是user和add才能访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //这里是页面不是接口
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher){
        UserRealm userRealm=new UserRealm();
        userRealm.setAuthorizationCachingEnabled(false);
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }
}
