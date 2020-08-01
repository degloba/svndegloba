package com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring;

import java.util.Collections;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
/*import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;*/

/**
 * 
 * @author pere
 * 
 * representa realment l'aplicació web (port 8081)
 *
 */
/*
 * @SpringBootApplication
 * 
 * @ComponentScan(basePackages = {"com.degloba.ecommerce.enviaments.webapp.*"})
 */
public class WebServerApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(WebServerApplication.class).
				properties(Collections.singletonMap("server.port", "8081")).run(args);
	}
	/*
	 * @Bean public SecurityWebFilterChain
	 * corsAnnotatedSpringSecurityFilterChain(ServerHttpSecurity http) {
	 * http.csrf().disable(); return http.build(); }
	 */

	
}