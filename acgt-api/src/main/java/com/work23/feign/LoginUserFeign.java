package com.work23.feign;

import com.work23.entity.SysUser;
import com.work23.feign.hystrix.LoginUserFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="acgt-user",fallback = LoginUserFeignHystrix.class)
public interface LoginUserFeign {
    @GetMapping("getUserById")
    SysUser getUserById(@RequestParam Integer id);
}
