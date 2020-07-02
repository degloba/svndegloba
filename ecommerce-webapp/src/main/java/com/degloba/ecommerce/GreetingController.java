package com.degloba.ecommerce;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";

	@RequestMapping("/greeting/{name}")
    	public Greeting greeting(@PathVariable("name") String name) {
        	return new Greeting(String.format(template, name));
    	}
}
