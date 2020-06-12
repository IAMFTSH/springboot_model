package com.springboot.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * <p>
 *
 * </p>
 *
 * @author 邝明山
 * @since 2020-05-27
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment){
        //dev环境和test环境
        Profiles profiles=Profiles.of("dev","test");
        //如果配置了相应的application.yaml环境
        //1.application.yaml中加入
/*        spring:
            profiles:
                active: dev
          这样会使用application-dev.yaml的配置        shiroFilterFactoryBean.setUnauthorizedUrl("noAuth");比如dev端口设置为8888   application-pro.yaml 端口设置为   9999   上面值为dev的话，启动端口是8888
          2.
        建立新的配置文档    application-dev.yaml
        建立新的配置文档    application-pro.yaml
        3.
                把flag=environment.acceptsProfiles(profiles);替换调下面flag=true；

 */
        boolean flag=true;

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("分组A")
                //是否启用Swagger  默认启用
                .enable(flag)
                .select()
                //basePackage需要扫描的包，默认还会会扫描错误页面的controller和容器内的Controller
                //any全部，   没实验
                // none不扫描，   没实验
                // withClassAnnotation扫描类上的注解（arg是射对象）   没实验
                //withMethodAnnotation扫描方法的注解  懒得管了
                .apis(RequestHandlerSelectors.basePackage("com.springboot.model.controller"))
                //过滤  只扫描请求路径中符合的
/*                .paths(PathSelectors.ant("/user/**"))*/
                .build();
    }

    @Bean
    public Docket docket2(Environment environment){
        //dev环境和test环境
        Profiles profiles=Profiles.of("dev","test");
        //如果当前环境符合上面两个值，flag=true
        boolean flag=true;

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("分组B")
                //是否启用Swagger  默认启用
                .enable(flag)
                .select()
                //basePackage需要扫描的包，默认还会会扫描错误页面的controller和容器内的Controller
                //any全部，   没实验
                // none不扫描，   没实验
                // withClassAnnotation扫描类上的注解（arg是射对象）   没实验
                //withMethodAnnotation扫描方法的注解  懒得管了
                .apis(RequestHandlerSelectors.basePackage("com.springboot.model.controller"))
                //过滤  只扫描请求路径中符合的
/*                .paths(PathSelectors.ant("/user/**"))*/
                .build();
    }
    @Bean
    public Docket docket3(Environment environment){
        //dev环境和test环境
        Profiles profiles=Profiles.of("dev","test");
        //如果当前环境符合上面两个值，flag=true
        boolean flag=true;

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("分组C")
                //是否启用Swagger  默认启用
                .enable(flag)
                .select()
                //basePackage需要扫描的包，默认还会会扫描错误页面的controller和容器内的Controller
                //any全部，   没实验
                // none不扫描，   没实验
                // withClassAnnotation扫描类上的注解（arg是射对象）   没实验
                //withMethodAnnotation扫描方法的注解  懒得管了
                .apis(RequestHandlerSelectors.basePackage("com.springboot.model.controller"))
                //过滤  只扫描请求路径中符合的
/*                .paths(PathSelectors.ant("/user/**"))*/
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact=new Contact("FTSH","https://www.hao123.com/","2244456@qq.com");
        return  new ApiInfo(
                "title",
                "description",
                "version",
                "https://www.baidu.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
