package com.degloba.hotels.webapp.reactive.functional;

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
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.WebFilterChainProxy;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.degloba.hotels.webapp.webflux.Hotel;
import com.degloba.hotels.webapp.webflux.HotelRepository;



@Configuration
@ConditionalOnClass({ EnableWebFluxSecurity.class, WebFilterChainProxy.class })
@ConditionalOnMissingBean(WebFilterChainProxy.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
////////@Order(1000)
public class HotelFunctionalConfig {

    @Bean
    HotelRepository employeeRepository() {
        return new HotelRepository();
    }

    @Bean
    RouterFunction<ServerResponse> getAllEmployeesRoute() {
      return route(GET("/employees"), 
        req -> ok().body(
          employeeRepository().findAllHotels(), Hotel.class));
    }

    @Bean
    RouterFunction<ServerResponse> getEmployeeByIdRoute() {
      return route(GET("/employees/{id}"), 
        req -> ok().body(
          employeeRepository().findHotelById(req.pathVariable("id")), Hotel.class));
    }

    @Bean
    RouterFunction<ServerResponse> updateEmployeeRoute() {
      return route(POST("/employees/update"), 
        req -> req.body(toMono(Hotel.class))
                  .doOnNext(employeeRepository()::updateEmployee)
                  .then(ok().build()));
    }

    @Bean
    RouterFunction<ServerResponse> composedRoutes() {
      return 
          route(GET("/hotels"), 
            req -> ok().body(
              employeeRepository().findAllHotels(), Hotel.class))
            
          .and(route(GET("/hotels/{id}"), 
            req -> ok().body(
              employeeRepository().findHotelById(req.pathVariable("id")), Hotel.class)))
            
          .and(route(POST("/hotels/update"), 
            req -> req.body(toMono(Hotel.class))
                      .doOnNext(employeeRepository()::updateEmployee)
                      .then(ok().build())));
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf()
            .disable()
            .authorizeExchange()
            .anyExchange()
            .permitAll();
        return http.build();
    }
}
