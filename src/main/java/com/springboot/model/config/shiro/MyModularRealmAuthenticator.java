package com.springboot.model.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;


import java.util.ArrayList;
import java.util.Collection;

public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {


    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("------------------------------MyModularRealmAuthenticator");

        this.assertRealmsConfigured();
        Collection<Realm> realms = this.getRealms();

        MyToken token = (MyToken) authenticationToken;
        // User
        String loginType = token.getLoginType();
        System.out.println("------------------------------loginType:"+loginType);

        Collection<Realm> typeRealms = new ArrayList<>();
        for(Realm realm:realms){
            //UserRealm
            System.out.println("-------------"+realm.getClass().getName());
            if(realm instanceof UserRealm){
                typeRealms.add(realm);
            }
        }
        System.out.println(typeRealms);
       if(typeRealms.size()==1){
           return this.doSingleRealmAuthentication((Realm)typeRealms.iterator().next(), authenticationToken);
       }else{
           return this.doMultiRealmAuthentication(typeRealms, authenticationToken);
       }

    }

}