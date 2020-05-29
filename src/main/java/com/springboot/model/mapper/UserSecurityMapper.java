package com.springboot.model.mapper;

import com.springboot.model.entity.UserSecurity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 邝明山
 * @since 2020-05-29
 */
@Repository
public interface UserSecurityMapper extends BaseMapper<UserSecurity> {
    /**
     * 查找用户
     * @param username
     * @return
     */
    UserSecurity mySelectUserSecurityByName(String username);
    /**
     * 注册用户
     */
    int myInsertUserSecurity(UserSecurity userSecurity);
}
