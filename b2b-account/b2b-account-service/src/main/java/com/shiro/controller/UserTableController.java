package com.shiro.controller;

import com.shiro.entity.mysql.MUserTable;
import com.shiro.service.mysql.impl.MUserTableMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserTableController {

    @Autowired
    private MUserTableMapperImpl mUserTableMapper;

    @RequestMapping("/muser")
    @ResponseBody
    public void mselectAll(HttpServletRequest httpRequest){
        List<MUserTable> mUserTables = mUserTableMapper.selectAll();
        HttpSession session = httpRequest.getSession();
        session.setAttribute("username","sjdfs");
        System.out.println(mUserTables);
    }

    @RequestMapping("/admin")
    @ResponseBody
    public void setsfd(HttpServletRequest httprdsf){
        HttpSession session = httprdsf.getSession();
        Object username = session.getAttribute("username");
        System.out.println(username);
    }
}
