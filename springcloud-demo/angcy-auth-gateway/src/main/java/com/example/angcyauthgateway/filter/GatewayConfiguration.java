package com.example.angcyauthgateway.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;


//@Configuration
public class GatewayConfiguration {

//    @Autowired
//    private AuthFilter authFilter;
//
//    @Bean
//    @Order
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                //多个路由规则就添加多个route
//                .route(r ->
//                        r.path("/pay/payment/**")
//                                .filters(f ->
//                                        f.stripPrefix(1)
//                                                //添加自定义的filter
//                                                .filter(authFilter)
//                                )
//                                .uri("lb://cloud-payment-service")
//                ).build();
//    }
}
