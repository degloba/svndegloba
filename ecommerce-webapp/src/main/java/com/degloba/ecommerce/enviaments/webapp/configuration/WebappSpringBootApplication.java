package com.degloba.ecommerce.enviaments.webapp.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({ "com.degloba.*" })
public class WebappSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebappSpringBootApplication.class, args);
    }
}
