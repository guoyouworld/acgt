package com.work23.controller;

import com.work23.entity.SysUser;
import com.work23.feign.LoginUserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.UUID;

@RestController
public class LoginController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    LoginUserFeign loginUserFeign;

    @GetMapping("login")
    public String testLogin(){
        SysUser userById = loginUserFeign.getUserById(1);
        String token = UUID.randomUUID().toString();
        //save to redis
        stringRedisTemplate.opsForValue().set(token,userById.toString(),Duration.ofSeconds(7200));
        return token;
    }
}
