package com.work23.feign.hystrix;

import com.work23.entity.SysUser;
import com.work23.feign.LoginUserFeign;
import org.springframework.stereotype.Component;

@Component
public class LoginUserFeignHystrix implements LoginUserFeign {
    @Override
    public SysUser getUserById(Integer id) {
        return null;
    }
}
