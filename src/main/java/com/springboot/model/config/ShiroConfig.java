package com.springboot.model.config;

import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.*;

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
     * 缓存设置
     * 1。添加3个jar
     * 2.添加ehcache.xml文件
     * 3.搞这个类
     * 4.设置DefaultWebSecurityManager
     * 5.设置userRealm.setAuthorizationCachingEnabled(true);   这个没有默认值，如果没有缓存那就设为false
     * 6.管理缓存 解决热部署 Ehcache重复创建CacheManager问题，配置这个类  每次热部署后缓存会刷新
     * @return
     */
    @Bean
    public EhCacheManager getEhCacheManager(){
        EhCacheManager ehCacheManager=new EhCacheManager();
        ehCacheManager.setCacheManager(ehCacheManagerFactoryBean());
        return ehCacheManager;
    }
    /**
     * 管理缓存 解决热部署 Ehcache重复创建CacheManager问题
     * @return
     */
    @Bean(name = "ehcacheManager")
    public CacheManager ehCacheManagerFactoryBean() {
        CacheManager cacheManager = CacheManager.getCacheManager("shiro");
        if(cacheManager == null){
            try {
                cacheManager = CacheManager.create(ResourceUtils.getInputStreamForPath("classpath:ehcache.xml"));
            } catch (IOException e) {
                throw new RuntimeException("initialize cacheManager failed.....");
            }
        }
        return cacheManager;
    }


    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
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

    /**
     * 记住功能实现
     * 1.配置这个类
     * 2.DefaultWebSecurityManager  securityManager.setRememberMeManager(getCookieRememberMeManager());
     * @return
     */
    @Bean
    public CookieRememberMeManager getCookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager=new CookieRememberMeManager();
        SimpleCookie cookie=new SimpleCookie("rememberMe");
        cookie.setMaxAge(30*24*60*60);
        cookieRememberMeManager.setCookie(cookie);

        return cookieRememberMeManager;
    }
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*
        * anon:无需认证就可以访问
        * authc：必须认证才能访问   付款操作这些
        * user： 记住我就能访问
        * perms：必须拥有某个资源的权限才能用
        * role：拥有某个角色才能用
        * */
        //添加shiro过滤器
        Map<String,String> map=new LinkedHashMap<String,String>();

        //问题1  如何实现或条件
        //问题2  记住我的一大问题地方，        map.put("/user/**","authc");这里authc会当成user处理不会让你重新登陆  注解会让你重新登陆
        //问题3  Unable to execute 'doFinal' with cipher instance [Cipher.AES/GCM/NoPadding, mode: decryption, algorithm from: SunJCE].  bug无解
        map.put("/user/update","perms[VIP2]");
        map.put("/user/add","perms[VIP1]");
        //map.put("/user","perms[VIP1]");
        map.put("/user/remember","user");
        map.put("/user/removeCache","user");
        map.put("/user/index","user");
        map.put("/user","user");
        //这个要放最后authc
        map.put("/user/**","authc");
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
        //缓存
        securityManager.setCacheManager(getEhCacheManager());
        securityManager.setRememberMeManager(getCookieRememberMeManager());
        return securityManager;
    }

    @Bean
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher){
        UserRealm userRealm=new UserRealm();
        //这个没有默认值，如果没有缓存那就设为false
        userRealm.setAuthorizationCachingEnabled(true);
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }
}
