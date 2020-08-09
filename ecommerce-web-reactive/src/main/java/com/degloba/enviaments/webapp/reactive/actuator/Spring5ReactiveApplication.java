package com.degloba.enviaments.webapp.reactive.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:com/degloba/enviaments/webapp/reactive/actuator/application.properties")
public class Spring5ReactiveApplication{

    public static void main(String[] args) {
        SpringApplication.run(Spring5ReactiveApplication.class, args);
    }

}
