package com.degloba.ecommerce.enviaments.webapp.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 
 * @author degloba
 * 
 * @category test per provar un {@link org.springframework.web.reactive.function.client.WebClient}
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }) 
public class EnviamentReactiveSpringApplication {

	   public static void main(String[] args) {
	        
	        SpringApplication.run(EnviamentReactiveSpringApplication.class, args);

			/*
			 * EnviamentWebClient enviamentWebClient = new EnviamentWebClient();
			 * enviamentWebClient.consume();
			 */
	    }
}
