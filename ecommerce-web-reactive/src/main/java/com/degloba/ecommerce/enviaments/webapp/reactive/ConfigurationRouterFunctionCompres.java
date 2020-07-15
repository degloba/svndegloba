package com.degloba.ecommerce.enviaments.webapp.reactive;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.degloba.ecommerce.enviaments.webapp.reactive.service.ClientCompresService;
import com.degloba.ecommerce.vendes.compres.facade.dtos.CompraDto;

/**
 * @category Configura rutes mapejant urlS en @Service
 * 
 * @RouterFunction serves as an alternative to the @RequestMapping annotation
 * 
 * https://github.com/eugenp/tutorials/blob/master/spring-5-reactive-security/src/main/java/com/baeldung/reactive/functional/EmployeeFunctionalConfig.java
 * 
 * @author degloba
 *
 */
//////@Configuration
public class ConfigurationRouterFunctionCompres {
	
	/**
	 * En aquest punt podriem utilitzar directament una classe Repositori
	 * 
	 * @return
	 */
    @Bean
    public
    ClientCompresService clientCompresService() {
        return new ClientCompresService();
    }

    @Bean
	public
    RouterFunction<ServerResponse> getTotesCompresRoute() {
      return route(GET("/compres"), 
        req -> ok().body(
        		clientCompresService().buscarTotesCompres(), CompraDto.class));
    }

    @Bean
	public
    RouterFunction<ServerResponse> getCompraByIdRoute() {
      return route(GET("/compres/{id}"), 
        req -> ok().body(
        		clientCompresService().buscarCompraById(req.pathVariable("id")), CompraDto.class));
    }

    @Bean
    RouterFunction<ServerResponse> updateCompraRoute() {
      return route(POST("/compres/update"), 
        req -> req.body(toMono(CompraDto.class))
                  .doOnNext(clientCompresService()::updateCompra)
                  .then(ok().build()));
    }

    @Bean
    RouterFunction<ServerResponse> composedRoutes() {
      return 
          route(GET("/compres"), 
            req -> ok().body(
            		clientCompresService().buscarTotesCompres(), CompraDto.class))
            
          .and(route(GET("/employees/{id}"), 
            req -> ok().body(
            		clientCompresService().buscarCompraById(req.pathVariable("id")), ClientCompresService.class)))
            
          .and(route(POST("/compres/update"), 
            req -> req.body(toMono(ClientCompresService.class))
                      //////.doOnNext(ClientCompresService::updateCompra)
                      .then(ok().build())));
    }

	/*
	 * @Bean public SecurityWebFilterChain
	 * springSecurityFilterChain(ServerHttpSecurity http) { http.csrf() .disable()
	 * .authorizeExchange() .anyExchange() .permitAll(); return http.build(); }
	 */
}
