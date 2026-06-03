package com.example.api_gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(
            org.springframework.web.server.ServerWebExchange exchange,
            org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        long start = System.currentTimeMillis();

        return chain.filter(exchange)
                .then(
                        Mono.fromRunnable(() -> {

                            long duration =
                                    System.currentTimeMillis() - start;

                            log.info(
                                    "{} {} -> {} ({} ms)",
                                    exchange.getRequest().getMethod(),
                                    exchange.getRequest().getURI(),
                                    exchange.getResponse().getStatusCode(),
                                    duration
                            );
                        })
                );
    }

    @Override
    public int getOrder() {
        return -1;
    }
}