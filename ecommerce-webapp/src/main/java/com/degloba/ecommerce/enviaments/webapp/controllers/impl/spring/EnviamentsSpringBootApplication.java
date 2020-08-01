package com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author pere
 *
 * @implNote Les classes Controller, Model, Repository, Service han d'estar al mateix "package" o "package" fills que
 * 			 aquesta classe. Si no és així es pot utilitzar el @ComponentScan
 */
@SpringBootApplication
//@ComponentScan({ "com.degloba.ecommerce.enviaments.webapp.spring.config" })
public class EnviamentsSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnviamentsSpringBootApplication.class, args);
    }
}
