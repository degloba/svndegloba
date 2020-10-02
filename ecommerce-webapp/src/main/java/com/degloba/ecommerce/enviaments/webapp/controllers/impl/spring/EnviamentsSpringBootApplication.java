package com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


/**
 * 
 * @author degloba
 *
 * @implNote Les classes Controller, Model, Repository, Service han d'estar al mateix "package" o "package" fills que
 * 			 aquesta classe. Si no és així es pot utilitzar el @ComponentScan
 */
//@ComponentScan({ "com.degloba.ecommerce.enviaments.webapp.spring.config" })
//https://stackoverflow.com/questions/51221777/failed-to-configure-a-datasource-url-attribute-is-not-specified-and-no-embedd/54892724
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })  
public class EnviamentsSpringBootApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(EnviamentsSpringBootApplication.class, args);
    }
         
  
	
}
