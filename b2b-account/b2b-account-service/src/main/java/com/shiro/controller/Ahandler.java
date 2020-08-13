package com.shiro.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Ahandler {
    //登录的url
    @RequestMapping({"/login","/"})
    public String indexHtml(){
        return "/index";
    }

    @RequestMapping("/showUserHtml.action")
    @RequiresRoles(value = {"admin","user"},logical = Logical.OR)
    @RequiresPermissions("user:query")
    public String userHtml(){
        return "/user";
    }

    @RequestMapping("/showAdminHtml.action")
    @RequiresRoles("admin")
    @RequiresPermissions("admin:query")
    public String AdminHtml(){
        return "/admin";
    }

    @RequestMapping("/unauthorized.action")
    public String unauthorized(){
        return "/abc";
    }

    @RequestMapping("/LoginSuccess.action")
    public String listHtml(){
        return "/list";
    }

    @RequestMapping("/error.action")
    public String error(){
        int a=1/0;
        return "/abc";
    }
}
