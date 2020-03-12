package com.degloba.ecommerce.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.viatges.domainpersistence.rdbms.jpa.Reserves;
import com.degloba.viatges.webapp.services.ClientReservesService;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;



import javax.inject.Inject;

/**
 * @see http://zetcode.com/springboot/routerfunction/
 * @see https://www.baeldung.com/spring-5-functional-web
 * 
 * @author degloba
 *
 */
@Configuration
public class ConfigurationRoutersEcommerce {
	
	ClientReservesService  clientReservesServices;
	
	@Bean
	RouterFunction<ServerResponse> composedRoutes() {
	  return
	    route(GET("/reserves"), 
	      req -> ok().body(
	    		  clientReservesServices.buscarTotesReserves(), Reserves.class))
	         
	    .and(route(GET("/reserves/{id}"), 
	      req -> ok().body(
	    		  clientReservesServices.buscarReservaById(req.pathVariable("id")), Reserves.class)))
	         
	    .and(route(POST("/reserves/update"), 
	      req -> req.body(toMono(Reserves.class))
	        .doOnNext(clientReservesServices::updateEmployee)
	        .then(ok().build())));
	}
	
	
	// El primer argumento es un predicado de solicitud. 
	// Observe cómo usamos un método RequestPredicates.GET importado estáticamente aquí. 
	// El segundo parámetro define una función de controlador que se utilizará si se aplica el predicado.

	// El seguent codi enruta todas las solicitudes GET para /employee/{id} al método EmployeeRepository#findEmployeeById(String id).
	
	@Bean
	RouterFunction<ServerResponse> getReservaByIdRoute() {
	  return route(GET("/reserves/{id}"), 
	    req -> ok().body(
	    		clientReservesServices.buscarReservaById(req.pathVariable("id")), Reserva.class));
	}
	
	@Bean
	RouterFunction<ServerResponse> getAllReservesRoute() {
	  return route(GET("/reserves"), 
	    req -> ok().body(
	    		clientReservesServices.buscarTotesReserves(), Reserva.class));
	}

}
