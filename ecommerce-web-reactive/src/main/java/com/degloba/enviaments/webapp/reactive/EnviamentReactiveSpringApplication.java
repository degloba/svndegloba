package com.degloba.enviaments.webapp.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author degloba
 * 
 * @category test per provar un {@link org.springframework.web.reactive.function.client.WebClient}
 *
 */
@SpringBootApplication
public class EnviamentReactiveSpringApplication {

	   public static void main(String[] args) {
	        
	        SpringApplication.run(EnviamentReactiveSpringApplication.class, args);

			/*
			 * EnviamentWebClient enviamentWebClient = new EnviamentWebClient();
			 * enviamentWebClient.consume();
			 */
	    }
}
