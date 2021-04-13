package com.example.angcyauthgateway.filter;

import com.example.apicommon.util.JwtUtils;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

@Slf4j
@Component //需要注意的是这个类需要加上@Component，否则会无效。
public class AuthFilter implements GlobalFilter, Ordered {
    private static final String AUTH = "Authorization";

    private static final String KEY = "changeIt";
    private static final String ISSUE = "zhangcy";

    //过期时间
    private static final long TOKEN_EXPIRE_TIME = 60000;

    private static final String USER_NAME = "username";

    private static JwtUtils jwtUtils = new JwtUtils();


    /**
     * 自定义过滤器
     * 实现 GlobalFilter 和 Ordered 接口
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //todo 获取请求体中的参数
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        for (Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            System.out.println("key-----"+key);
            System.out.println("value------"+value);
        }

        //访问http://localhost:9527/provider/getInfo?name=1，访问成功：
        String name = queryParams.getFirst("username");
        if (name == null){
            // 不放行
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        }

//
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        HttpHeaders headers = request.getHeaders();
//        String token = headers.getFirst(AUTH);
//        //1、校验token是否存在
//        if (StringUtils.isBlank(token)) {
//            log.error("token not found");
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return response.setComplete();
//        }
//        //2、校验token是否合理，比如是否超时等
//        boolean vertify = jwtUtils.vertify(USER_NAME, token);
//        if(!vertify){
//            log.error("valid token");
//            response.setStatusCode(HttpStatus.FORBIDDEN);
//            return response.setComplete();
//        }
        return chain.filter(exchange);
    }

    /**
     * 根据业务需求，自定义过滤器的执行顺序
     * 值越小，代表其优先级别越高。
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
