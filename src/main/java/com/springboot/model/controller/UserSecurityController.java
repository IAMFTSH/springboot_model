package com.springboot.model.controller;


import com.springboot.model.entity.UserSecurity;
import com.springboot.model.mapper.UserSecurityMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

import static org.apache.shiro.authz.annotation.Logical.OR;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 邝明山
 * @since 2020-05-29
 */
@Controller
public class UserSecurityController {
    UserSecurityMapper userSecurityMapper;
    @RequestMapping(value = {"/user/", "/user/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello");
        return "user/index";
    }

    @RequestMapping("/user/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping(value = {"/toLogin", "/login","/"})
    public String toLogin() {
        return "login";
    }
    @RequestMapping("/noAuth")
    public String noAuth() {
        return "noAuth";
    }

    @RequestMapping("/logout")
    public String login() {
        System.out.printf("注销了");
        return "login";
    }

    /**
     * 注释测试
     * @return
     */
    @RequestMapping("/user/annotation")
    @RequiresPermissions(value = {"VIP1","VIP2"},logical=OR)
    //@RequiresPermissions("VIP1")  默认是AND
    //@RequiresRoles({"teacher"})   角色和权限都要满足才可以

    public String annotation() {
        return "user/annotation";
    }

    /**
     * 登陆，随机盐，这里可以考虑丢给service层
     * @param username
     * @param password
     * @param remember
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String login(String username, String password, String remember,Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if(remember == "1") {
            token.setRememberMe(true);
        }
        try {
            subject.login(token);
            return "user/index";
        } catch (UnknownAccountException uae) {
            System.out.println("返回用户不存在");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("返回密码错误");
        } catch (LockedAccountException lae) {
            System.out.println("返回用户被锁定");
        }
        // ... catch more exceptions here (maybe custom ones specific to your application?
        catch (AuthenticationException ae) {
            System.out.println("返回身份验证异常");

            //unexpected condition?  error?
        }
        return "/login";
    }

    @PostMapping("/register")
    public String register(String username,String password){
        Random random=new Random();
        String salt=random.nextInt(900000)+100000+username;
        UserSecurity userSecurity=new UserSecurity();
        userSecurity.setUsername(username);
        userSecurity.setCredentialsSalt(salt);
        SimpleHash hash=new SimpleHash("MD5",password,salt,1);
        userSecurity.setPassword(hash.toString());
        userSecurity.setAuthority("VIP2");
        userSecurityMapper.myInsertUserSecurity(userSecurity);
        System.out.println("注册"+userSecurity);
        return "login";
    }

    @Autowired
    public void setUserSecurityMapper(UserSecurityMapper userSecurityMapper) {
        this.userSecurityMapper = userSecurityMapper;
    }
}

