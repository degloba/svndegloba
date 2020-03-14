package com.degloba.viatges.webapp.reactive.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class ViatgesRouterFunction {

	   @Bean
	    public RouterFunction<ServerResponse> route(ViatgesHandler viatgesHandler) {
	        return RouterFunctions
	                .route(GET("/viatges/{name}"), viatgesHandler::getName)
	                .filter(new ViatgesHandlerFilterFunction());
	    }
}
