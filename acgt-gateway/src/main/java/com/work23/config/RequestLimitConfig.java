package com.work23.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

@Configuration
public class RequestLimitConfig
{
    //针对某一个ip限流 每5秒钟只能访问3次
    @Bean
    @Primary
    public KeyResolver ipKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getHeaders().getHost().getHostString());
    }
    //针对某一个路径做限流
    @Bean
    public KeyResolver apiKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }
}
