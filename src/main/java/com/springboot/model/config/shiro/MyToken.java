package com.springboot.model.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MyToken extends UsernamePasswordToken {

    private String loginType;

    public MyToken(String userName,String userPwd, String loginType) {
        super(userName,userPwd);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}