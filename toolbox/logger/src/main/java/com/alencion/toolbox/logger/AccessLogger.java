package com.alencion.toolbox.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;


public class AccessLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessLogger.class);


    private AccessLogger() {
        throw new AssertionError("Can't be instantiate this.");
    }

    public static final WebFilter ACCESS_LOGGER_WEB_FILTER = ((exchange, chain) -> {
        final ServerHttpRequest request = exchange.getRequest();
        final ServerHttpResponse response = exchange.getResponse();
        final AccessLog accessLog = AccessLog.of(request, response);

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> accessLog.log(LOGGER)));
    });
}
