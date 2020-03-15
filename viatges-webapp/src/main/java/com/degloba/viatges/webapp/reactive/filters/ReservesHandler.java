package com.degloba.viatges.webapp.reactive.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
class ReservesHandler {

    Mono<ServerResponse> getId(ServerRequest request) {
        Mono<String> id = Mono.just(request.pathVariable("id"));
        return ok().body(id, String.class);
    }

}
