package com.work23.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();


        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("content-type","application/json;charset-utf-8");
        HashMap<String,Object> map = new HashMap<>(4);
        map.put("code", HttpStatus.UNAUTHORIZED.value());
        map.put("msg","未授权");
        ObjectMapper objMapper = new ObjectMapper();
        byte[] bytes=new byte[0];
        try{
            bytes = objMapper.writeValueAsBytes(map);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        //拦截
        return response.writeWith(Mono.just(wrap));
        //放行
        //return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
