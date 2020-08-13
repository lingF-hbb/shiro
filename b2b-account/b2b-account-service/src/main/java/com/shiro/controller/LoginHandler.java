package com.shiro.controller;

import com.shiro.configBean.passwordHelper;
import com.shiro.entity.mysql.MUserTable;
import com.shiro.service.mysql.IMUserTableMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginHandler {

    @Autowired
    private IMUserTableMapper imUserTableMapper;

    @RequestMapping("/login.action")
    public String login(MUserTable mUserTable, HttpSession session){
        System.out.println(mUserTable.getUsername()+"--------"+mUserTable.getPassword());
//        获取当前的subject
        Subject subject = SecurityUtils.getSubject();
//        验证用户是否登录，如果登录了返回true，没有登录返回false
        if(!subject.isAuthenticated()){
            String msg="";
//            将用户名和密码封装到usernamepasswordToken对象
            UsernamePasswordToken Token = new UsernamePasswordToken(mUserTable.getUsername(), mUserTable.getPassword());
//            remeberMe(),记住密码
            Token.setRememberMe(true);
            try{
                subject.login(Token);
//                登录成功
                return "redirect:/LoginSuccess.action";
            }catch (IncorrectCredentialsException e){
                msg="登录密码错误";
                System.out.println(msg+e);
            }catch(ExcessiveAttemptsException e){
                msg="登录次数太多";
                System.out.println(msg+e);
            }catch (LockedAccountException e){
                msg="账号已被锁定";
                System.out.println(msg+e);
            }catch (DisabledAccountException e){
                msg="账号已经被禁用";
                System.out.println(msg+e);
            }catch (ExpiredCredentialsException e){
                msg="账号已经过期了";
                System.out.println(msg+e);
            }catch (UnknownAccountException e){
                msg="账号不存在";
                System.out.println(msg+e);
            }catch (UnauthorizedException e){
                msg="您还没有的到相应的授权";
                System.out.println(msg+e);
            }catch (Exception e){
                System.out.println("出错！！"+e);
            }
            return  "/index";
        }
//        登录成功，重定向到LoginSuccess.action
        return "redirect:/LoginSuccess.action";
    }

//    注册
    @RequestMapping("/insert.action")
    public String insert(MUserTable mUserTable){
        System.out.println("进入方法");
//      密码加密
        MUserTable mUserTable1 = passwordHelper.encryptPassword(mUserTable);
        int i = imUserTableMapper.insertNewAuth(mUserTable1);
        String s="";
        if(i>0){
            s="index";
        }
        System.out.println("已经注册");
        System.out.println(s);
        return s;
    }

    //退出系统
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }
}
