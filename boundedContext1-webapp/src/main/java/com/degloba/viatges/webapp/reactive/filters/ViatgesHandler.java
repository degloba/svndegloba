package com.degloba.viatges.webapp.reactive.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class ViatgesHandler {

    Mono<ServerResponse> getName(ServerRequest request) {
        Mono<String> name = Mono.just(request.pathVariable("name"));
        return ok().body(name, String.class);
    }
}
