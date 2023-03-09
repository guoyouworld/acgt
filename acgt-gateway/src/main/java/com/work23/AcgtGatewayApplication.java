package com.work23;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AcgtGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcgtGatewayApplication.class,args);
    }
}