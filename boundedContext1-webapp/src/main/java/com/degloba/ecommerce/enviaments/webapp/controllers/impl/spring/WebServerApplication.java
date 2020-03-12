package com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring;

import java.util.Collections;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.degloba.*"})
public class WebServerApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(WebServerApplication.class).
				properties(Collections.singletonMap("server.port", "8081")).run(args);
	}

	
}