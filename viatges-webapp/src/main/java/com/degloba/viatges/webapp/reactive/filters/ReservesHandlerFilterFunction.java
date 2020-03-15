package com.degloba.viatges.webapp.reactive.filters;

import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * @category Filtre/s que apliquem a nivell de request http
 * 
 * @author degloba
 *
 */
public class ReservesHandlerFilterFunction implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    @Override
    public Mono<ServerResponse> filter(ServerRequest serverRequest, HandlerFunction<ServerResponse> handlerFunction) {
        if (serverRequest.pathVariable("id").equalsIgnoreCase("test")) {
            return ServerResponse.status(FORBIDDEN).build();
        }
        return handlerFunction.handle(serverRequest);
    }
}
