package com.work23.controller;

import com.work23.entity.SysUser;
import com.work23.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzheng
 * @since 2023-03-07
 */
@RestController
public class SysUserController {
    @Autowired
    ISysUserService sysUserServiceImpl;
    @GetMapping("getUserById")
    public SysUser getUserById(@RequestParam Integer id){
        // http://localhost:8113/getUserById?id=1
        SysUser byId = sysUserServiceImpl.getById(id);
        return byId;
    }
    @GetMapping("test")
    public String test(){
        return "test";
    }
}
