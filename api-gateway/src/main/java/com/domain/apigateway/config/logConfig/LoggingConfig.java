package com.domain.apigateway.config.logConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingConfig implements GlobalFilter {

    private final Logger log = LoggerFactory.getLogger(LoggingConfig.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Requested path is {}", exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
