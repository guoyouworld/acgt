package com.work23;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.work23.mapper")
public class AcgtUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcgtUserApplication.class,args);
    }
}