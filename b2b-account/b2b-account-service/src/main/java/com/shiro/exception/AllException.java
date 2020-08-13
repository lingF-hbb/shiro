package com.shiro.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕捉类
 * */

@ControllerAdvice
public class AllException {
//    权限异常捕捉
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public String roleException(UnauthorizedException e){
        System.out.println("-----"+e);
        return "角色权限不够！！！";
    }

//    其他异常捕捉
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String allException(Exception e){
        System.out.println("-----"+e);
        return "系统出现异常";
    }
}
