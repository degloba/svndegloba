package com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;


/*import com.example.appengine.springboot.EnviamentDto;
import com.example.appengine.springboot.EnviamentRepository;
import com.example.appengine.springboot.SpringbootApplication;*/

import reactor.core.publisher.Flux;

/**
 * 
 * @author degloba
 *
 * @implNote Les classes Controller, Model, Repository, Service han d'estar al mateix "package" o "package" fills que
 * 			 aquesta classe. Si no és així es pot utilitzar el @ComponentScan
 */
//@ComponentScan({ "com.degloba.ecommerce.enviaments.webapp.spring.config" })
@SpringBootApplication
public class EnviamentsSpringBootApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(EnviamentsSpringBootApplication.class, args);
    }
         
  
	
}
