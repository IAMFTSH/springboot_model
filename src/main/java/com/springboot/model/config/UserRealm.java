package com.springboot.model.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.model.entity.User;
import com.springboot.model.entity.UserSecurity;
import com.springboot.model.mapper.UserSecurityMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ftsh
 */
public class UserRealm extends AuthorizingRealm {
    UserSecurityMapper userSecurityMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();

        //SimpleAuthenticationInfo(userSecurity,userSecurity.getPassword(),credentialsSalt,realmName)下面认证记得加第一个参数
        Subject subject= SecurityUtils.getSubject();
        UserSecurity userSecurity=(UserSecurity)subject.getPrincipal();

        info.addStringPermission(userSecurity.getAuthority());
        //info的Permission是set属性（set值不会重复），所以如果是多个权限，可以使用new一个set  然后赋值
        System.out.println("授权"+info.getStringPermissions());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");

        //token携带了用户信息
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        //根据用户名查询数据库中对应的记录
        QueryWrapper<UserSecurity> wrapper=new QueryWrapper();
        wrapper.eq("username",token.getUsername());
        UserSecurity userSecurity=userSecurityMapper.selectOne(wrapper);
        System.out.println("密码："+token.getPassword());
        //当前realm对象的name
        String realmName = getName();
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(userSecurity.getCredentialsSalt());
        if(userSecurity== null) {
            return null;
        }
        //封装用户信息，构建AuthenticationInfo对象并返回
        return new SimpleAuthenticationInfo(userSecurity,userSecurity.getPassword(),credentialsSalt,realmName);
    }

    @Autowired
    public void setUserSecurityMapper(UserSecurityMapper userSecurityMapper) {
        this.userSecurityMapper = userSecurityMapper;
    }
}
