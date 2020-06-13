package com.springboot.model.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.model.entity.UserSecurity;
import com.springboot.model.mapper.UserSecurityMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author FTSH
 * @date 2020/6/13 - 19:46
 */
public class UserRealm2 extends AuthorizingRealm {

    UserSecurityMapper userSecurityMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();

        //授权（赋予角色addRole 详情看源码）
        QueryWrapper<UserSecurity> wrapper=new QueryWrapper();
        // SimpleAuthenticationInfo(userSecurity,userSecurity.getPassword(),credentialsSalt,realmName)下面认证记得加第一个参数是username字符串,缺点，权限信息登陆才会更新一次

        info.addStringPermission("VIP1");
        //info的Permission是set属性（set值不会重复），所以如果是多个权限，可以使用new一个set  然后赋值
        System.out.println("不管通过了哪个认证，都会执行授权，UserRealm2授权"+info.getStringPermissions());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("UserRealm2认证");
        //token携带了用户信息
        MyToken token=(MyToken) authenticationToken;
        //根据用户名查询数据库中对应的记录
        //当前realm对象的name
        String realmName = getName();
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes("766319321");
        //封装用户信息，构建AuthenticationInfo对象并返回
        return new SimpleAuthenticationInfo(token.getUsername(),"f30a9893f08a93c8e683cac5a6432ce9",credentialsSalt,realmName);
    }

    @Autowired
    public void setUserSecurityMapper(UserSecurityMapper userSecurityMapper) {
        this.userSecurityMapper = userSecurityMapper;
    }
}
