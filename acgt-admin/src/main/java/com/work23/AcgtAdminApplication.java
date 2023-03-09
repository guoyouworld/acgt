package com.work23;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
public class AcgtAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcgtAdminApplication.class, args);
    }

}
