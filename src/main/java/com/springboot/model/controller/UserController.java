package com.springboot.model.controller;


import com.springboot.model.entity.User;
import com.springboot.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 邝明山
 * @since 2020-05-27
 */
@RestController
@RequestMapping("/model/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("print")
    List<User> print(){
        List<User> userList=userMapper.selectList(null);
        return userList;
    }
}

