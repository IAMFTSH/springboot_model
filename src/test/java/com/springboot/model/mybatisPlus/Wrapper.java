package com.springboot.model.mybatisPlus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.model.entity.User;
import com.springboot.model.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class Wrapper {
    @Autowired
    private UserMapper userMapper;


    //条件查询


    @Test
    void contextLoads() {
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.isNotNull("name").isNull("email").ge("age",12);   //name不为空  email为空  age大于12
        List<User> userList=userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test  //eq 等号
    void test1() {
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.eq("name","ls");   //name=ls
        List<User> userList=userMapper.selectList(wrapper);
//        User user=userMapper.selectOne(wrapper);   //  selectOne返回有多个数据会报错
        userList.forEach(System.out::println);
    }

    @Test
    void test2() {
        // 查询x，A<=X<=B
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.between("age",5,14);
        List<User> userList=userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    void test3() {
        // 模糊查询
        QueryWrapper<User> wrapper=new QueryWrapper();

        wrapper.notLike("name","l").likeRight("email","t").like("name","a");   //名字不包含l   邮件t开头的，like 't%'  名字中有a的
        List<User> userList=userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    void test4() {
        // 子查询
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.inSql("id","select id from user where id<8");
        //sql:SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND (id IN (select id from user where id<8))
        List<User> userList=userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    @Test
    void test5() {
        // 降序排序
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.inSql("id","select id from user where id<8").orderByDesc("age");
        List<User> userList=userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }
}
