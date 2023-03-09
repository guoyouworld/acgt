package com.work23.controller;

import com.work23.entity.SysUser;
import com.work23.feign.LoginUserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LoginController {
    @Autowired
    LoginUserFeign loginUserFeign;

    @GetMapping("login")
    public String testLogin(){
        SysUser userById = loginUserFeign.getUserById(1);
        String token = UUID.randomUUID().toString();
        return "ok:"+token+":"+userById;
    }
}
