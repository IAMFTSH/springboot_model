package com.springboot.model.controller;


import com.springboot.model.entity.User;
import com.springboot.model.entity.UserSecurity;
import com.springboot.model.mapper.UserSecurityMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

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
    @RequestMapping(value = {"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/login";
    }
    @RequestMapping("/noAuth")
    public String noAuth() {
        return "/noAuth";
    }


    @PostMapping("/login")
    public String login(String username, String password, String remember,Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        if(remember == "1") {
            token.setRememberMe(true);
        }

        try {
            subject.login(token);
            return "index";
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
        userSecurity.setAuthority("VIP1");
        userSecurityMapper.myInsertUserSecurity(userSecurity);
        System.out.println("注册"+userSecurity);
        return "login";
    }

    @Autowired
    public void setUserSecurityMapper(UserSecurityMapper userSecurityMapper) {
        this.userSecurityMapper = userSecurityMapper;
    }
}

