package com.degloba.microserveis.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * @author degloba
 *
 * http://www.robertocrespo.net/kaizen/como-construir-microservicios-con-spring-boot/
 */
@SpringBootApplication
@EnableEurekaServer
public class DeglobaRegistrationEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeglobaRegistrationEurekaApplication.class, args);
	}

}
