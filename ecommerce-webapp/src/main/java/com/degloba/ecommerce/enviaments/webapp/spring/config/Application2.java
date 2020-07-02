package com.degloba.ecommerce.enviaments.webapp.spring.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextListener;

/**
 * https://github.com/eugenp/tutorials/tree/master/spring-rest-query-language
 * 
 * Main Application Class - uses Spring Boot. Just run this as a normal Java
 * class to run up a Jetty Server (on http://localhost:8082/spring-rest-query-language)
 *
 */
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan("com.baeldung")
@SpringBootApplication
public class Application2 extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application2.class);
    }

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        // Manages the lifecycle of the root application context
        sc.addListener(new RequestContextListener());
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application2.class, args);
    }
}