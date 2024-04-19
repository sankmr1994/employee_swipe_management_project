package com.domain.apigateway.config.routeConfig;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class apiRouteConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        Function<PredicateSpec, Buildable<Route>> employeeRoute = predicateSpec ->
                predicateSpec.path("/api/v1/employees/**")
                        .uri("lb://employee-swipe-tracker");
        Function<PredicateSpec, Buildable<Route>> employeeSwipeRoute = predicateSpec ->
                predicateSpec.path("/api/v1/swipe/**")
                        .uri("lb://employee-swipe-tracker");
        return routeLocatorBuilder.routes().route(employeeRoute).route(employeeSwipeRoute).build();
    }

}
