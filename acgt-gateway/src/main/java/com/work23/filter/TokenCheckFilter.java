package com.work23.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Component
public class TokenCheckFilter implements GlobalFilter, Ordered {
    public static final List<String> ALLOW_URL = Arrays.asList("/acgt-login/login","/login");
    public static final String TOKEN_PLACEHOLDER ="bearer ";
    public static final String TOKEN_KEY_FORM_HEADER = "Authorization";

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 与前端约定好从请求头中获取token -> key：Authorization value：bearer token
     * 1 拿到url
     * 2 判断是否是登陆，如果是放行
     * 3 拿到请求头，获取到token
     * 4 校验
     * 5 放行/拦截
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if(ALLOW_URL.contains(path)){
            return chain.filter(exchange);
        }
        List<String> authorizations = request.getHeaders().get(TOKEN_KEY_FORM_HEADER);
        if(!CollectionUtils.isEmpty(authorizations)){
            String token = authorizations.get(0);
            if(StringUtils.hasText(token)){
                String realToken = token.replaceFirst(TOKEN_PLACEHOLDER,"");
                if(StringUtils.hasText(realToken) && stringRedisTemplate.hasKey(realToken)){
                    return chain.filter(exchange);
                }
            }
        }
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

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
