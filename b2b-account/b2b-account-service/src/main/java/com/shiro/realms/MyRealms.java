package com.shiro.realms;

import com.shiro.entity.mysql.MUserTable;
import com.shiro.mapper.mysql.MUserTableMapper;
import com.shiro.service.mysql.IMUserTableMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

/**
 * 用于实现具体的验证和授权方法
 * */
public class MyRealms extends AuthorizingRealm {
    @Autowired
    private IMUserTableMapper iUserTableMapper;

    @Autowired
    private MUserTableMapper mUserTableMapper;

//  授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//       principalCollection:通过认证的用户的信息，SimpleAuthenticationInfo（）的第一个参数
        System.out.println("MyRealms的dogetauthorizationInfo授权方法的执行");

//       从PrincipalCollection中获取用户信息
//        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
//        System.out.println("ShiroRealm AuthorizationInfo"+primaryPrincipal.toString());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

//      获取通过验证的用户名
        String username=(String) principalCollection.getPrimaryPrincipal();

//      根据用户名查找角色，并传递给simpleAuthorizationInfo,这是给用户设置角色
        simpleAuthorizationInfo.setRoles(mUserTableMapper.selectRoleByName(username));
//      查找权限，并给用户设置权限
        simpleAuthorizationInfo.setStringPermissions(mUserTableMapper.selectPermissionByName(username));

        return simpleAuthorizationInfo;
    }

//  验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("dogetAuthenticationinfo"+authenticationToken);

//      将AuthenticationToken转化为UsernamePasswordToken
//        UsernamePasswordToken up=(UsernamePasswordToken)authenticationToken;

//      获取表单传递来的用户名
        Object principal = authenticationToken.getPrincipal();
        System.out.println("传递的身份"+principal);

        String username=String.valueOf(principal);

//      从数据库查看是否存在用户
        MUserTable userTable = iUserTableMapper.selectAllByUsername(username);

        System.out.println("从数据库查询出来的用户信息"+userTable);
//      如果用户不存在，抛出异常
        if(userTable.equals(null)){
            throw new UnknownAccountException("无此用户");
        }

//      获取查询出来的用户密码
        String password = userTable.getPassword();
//        Object credentials = authenticationToken.getCredentials();
//        String password=String.valueOf(credentials);

//      盐值，密码加密时用的，此处使用用户名
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

//      获取当前realm对象的名称
        String realName = this.getName();

//      创建simpleAuthenticationInfo对象，该对象作用是对密码进行比对认证
        SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(username,password,credentialsSalt,realName);
        return info;
    }
}
