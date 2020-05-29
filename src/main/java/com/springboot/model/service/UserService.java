package com.springboot.model.service;

import com.springboot.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 邝明山
 * @since 2020-05-27
 */
public interface UserService extends IService<User> {

}
