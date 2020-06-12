package com.springboot.model.handle;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理拦截会一切异常，WARN形式显示，被捕获的不算（是不是一切待定，test里面不会，应该只连接web的）
 * @author FTSH
 * @date 2020/6/12 - 21:32
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String doException(Exception e){
        if(e instanceof UnauthorizedException){
            System.out.println("异常拦截会一切异常，WARN形式显示，被捕获的不算");
            return "noAuth";
        }
        return null;
    }
}
