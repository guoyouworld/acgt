package com.work23;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//@MapperScan("com.work23.mapper")
public class AcgtApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcgtApiApplication.class,args);
    }
}