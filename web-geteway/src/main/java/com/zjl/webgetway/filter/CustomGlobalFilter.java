package com.zjl.webgetway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("全局过滤器 。。。");
        ServerHttpRequest request = exchange.getRequest();
        HttpMethod method = request.getMethod();

        if (method.equals(HttpMethod.GET)) {
            if (exchange.getRequest().getQueryParams().size() > 0) {
                MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
                queryParams.forEach((k, v) -> {
                    log.info("param:{}", k);
                    v.forEach(e -> {
                        log.info("value:{}", e);
                    });
                });
            }


        } else if (method.equals(HttpMethod.POST)) {
            log.info(exchange.getRequest().getBody().toString());
        }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
