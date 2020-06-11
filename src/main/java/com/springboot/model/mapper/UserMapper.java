package com.springboot.model.mapper;

import com.springboot.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 邝明山
 * @since 2020-05-27
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询全部用户信息
     * @return
     */
    List<User> mySelectUserList();
}
