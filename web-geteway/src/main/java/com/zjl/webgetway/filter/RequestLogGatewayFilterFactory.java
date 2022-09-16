package com.zjl.webgetway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
@Slf4j
public class RequestLogGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {


    @Override
    public GatewayFilter apply(NameValueConfig config) {

        return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
            log.info("请求网关:{} -> {}", config.getName(), config.getValue());
            exchange.getRequest().getQueryParams().forEach((k, v) -> {
                log.info("param : {}", k);
                v.forEach(val -> {
                    log.info("value : {}", val);
                });
            });
            return chain.filter(exchange);
        };
    }
}
