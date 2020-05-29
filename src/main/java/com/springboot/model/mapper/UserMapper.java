package com.springboot.model.mapper;

import com.springboot.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 邝明山
 * @since 2020-05-27
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 搜索所有用户
     * @return
     */
    List<User> mySelectUserList();
}
