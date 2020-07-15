package com.degloba.hotels.webapp.reactive;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


import com.degloba.hotels.HotelDto;
//import com.degloba.hotels.webapp.reactive.controllers.AppErrorController;
import com.degloba.hotels.webapp.reactive.service.ClientHotelsService;

/**
 * @category Configura rutes mapejant urlS en @Service
 * 
 * RouterFunction serves as an alternative to the @RequestMapping annotation. 
 * We can use it to route requests to the handler functions
 * 
 * https://github.com/eugenp/tutorials/blob/master/spring-5-reactive-security/src/main/java/com/baeldung/reactive/functional/EmployeeFunctionalConfig.java
 * 
 * @author degloba
 *
 */
@Configuration
@PropertySource("classpath:/com/degloba/hotels/webapp/reactive/application.properties")
@EnableWebFlux
public class ConfigurationRouterFunctionHotels {
	
	/**
	 * En aquest punt podriem utilitzar directament una classe Repositori
	 * 
	 * @return
	 */
    @Bean
    public
    ClientHotelsService clientHotelsService() {
        return new ClientHotelsService();
    }

    @Bean
	public
    RouterFunction<ServerResponse> getTotsHotelsRoute() {
      return route(GET("/hotels"), 
        req -> ok().body(
        		clientHotelsService().buscarTotsHotels(), HotelDto.class));
    }

    @Bean
	public
    RouterFunction<ServerResponse> getHotelByIdRoute() {
      return route(GET("/hotels/{id}"), 
        req -> ok().body(
        		clientHotelsService().buscarHotelById(req.pathVariable("id")), HotelDto.class));
    }

    @Bean
    RouterFunction<ServerResponse> updateHotelRoute() {
      return route(POST("/hotels/update"), 
        req -> req.body(toMono(HotelDto.class))
                  .doOnNext(clientHotelsService()::updateHotel)
                  .then(ok().build()));
    }

    @Bean
    RouterFunction<ServerResponse> composedRoutes() {
      return 
          route(GET("/hotels"), 
            req -> ok().body(
            		clientHotelsService().buscarTotsHotels(), HotelDto.class))
            
          .and(route(GET("/employees/{id}"), 
            req -> ok().body(
            		clientHotelsService().buscarHotelById(req.pathVariable("id")), ClientHotelsService.class)))
            
          .and(route(POST("/hotels/update"), 
            req -> req.body(toMono(ClientHotelsService.class))
                      //////.doOnNext(ClientCompresService::updateCompra)
                      .then(ok().build())));
    }

	
	/*
	 * @Bean public SecurityWebFilterChain
	 * springSecurityFilterChain(ServerHttpSecurity http) { http.csrf() .disable()
	 * .authorizeExchange() .anyExchange() .permitAll(); return http.build(); }
	 */
	 
    
		/*
		 * @Autowired private ErrorAttributes errorAttributes;
		 * 
		 * 
		 * @Bean public AppErrorController appErrorController(){ return new
		 * AppErrorController(errorAttributes);}
		 */
}
