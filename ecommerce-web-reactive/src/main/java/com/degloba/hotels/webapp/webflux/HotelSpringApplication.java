package com.degloba.hotels.webapp.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelSpringApplication {

	   public static void main(String[] args) {
	        
	        SpringApplication.run(HotelSpringApplication.class, args);

	        HotelWebClient employeeWebClient = new HotelWebClient();
	        employeeWebClient.consume();
	    }
}
