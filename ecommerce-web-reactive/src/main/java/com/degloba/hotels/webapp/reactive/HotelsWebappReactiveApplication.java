package com.degloba.hotels.webapp.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class })   // necessari segons https://stackoverflow.com/questions/57472586/application-failed-to-start-due-to-same-bean 
public class HotelsWebappReactiveApplication {

	public static void main(String[] args) {
        SpringApplication.run(HotelsWebappReactiveApplication.class, args);
    }
}
