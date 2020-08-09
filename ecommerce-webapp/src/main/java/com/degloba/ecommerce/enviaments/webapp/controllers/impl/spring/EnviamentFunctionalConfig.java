package com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.web.server.WebFilterChainProxy;
/*import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.WebFilterChainProxy;*/
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;



/**
 * 
 * @author pere
 * 
 * @category defineix les rutes
 *
 */
@Configuration
  @ConditionalOnClass({ EnableWebFluxSecurity.class, WebFilterChainProxy.class })  
  @ConditionalOnMissingBean(WebFilterChainProxy.class)  
  @ConditionalOnWebApplication(type =
  ConditionalOnWebApplication.Type.REACTIVE) 
////////@Order(1000)
public class EnviamentFunctionalConfig {

	/**
	 * @category configura la ruta /hello enviant la petició a enviamentHandler:hello
	 * @param enviamentHandler
	 * @return
	 */
	  @Bean
	  public RouterFunction<ServerResponse> route(EnviamentWebFluxHandler enviamentHandler) {

	    return RouterFunctions
	      .route(RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), enviamentHandler::hello);
	  }
	  
    @Bean
    EnviamentRepository enviamentRepository() {
        return new EnviamentRepository();
    }

    @Bean
    RouterFunction<ServerResponse> getAllEnviamentsRoute() {
      return RouterFunctions.route(GET("/enviaments"), 
        req -> ok().body(
        		enviamentRepository().findAllEnviaments(), EnviamentDto.class));
    }

    @Bean
    RouterFunction<ServerResponse> getEnviamentByIdRoute() {
      return RouterFunctions.route(GET("/enviaments/{id}"), 
        req -> ok().body(
        		enviamentRepository().findEnviamentById(req.pathVariable("id")), EnviamentDto.class));
    }

    @Bean
    RouterFunction<ServerResponse> updateEnviamentRoute() {
      return RouterFunctions.route(POST("/enviaments/update"), 
        req -> req.body(toMono(EnviamentDto.class))
                  .doOnNext(enviamentRepository()::updateEnviament)
                  .then(ok().build()));
    }

    @Bean
    RouterFunction<ServerResponse> composedRoutes() {
      return 
    		  RouterFunctions.route(GET("/enviaments"), 
            req -> ok().body(
            		enviamentRepository().findAllEnviaments(), EnviamentDto.class))
            
          .and(RouterFunctions.route(GET("/enviaments/{id}"), 
            req -> ok().body(
            		enviamentRepository().findEnviamentById(req.pathVariable("id")), EnviamentDto.class)))
            
          .and(RouterFunctions.route(POST("/enviaments/update"), 
            req -> req.body(toMono(EnviamentDto.class))
                      .doOnNext(enviamentRepository()::updateEnviament)
                      .then(ok().build())));
    }

	/*
	 * @Bean public SecurityWebFilterChain
	 * springSecurityFilterChain(ServerHttpSecurity http) { http.csrf() .disable()
	 * .authorizeExchange() .anyExchange() .permitAll(); return http.build(); }
	 */
}
