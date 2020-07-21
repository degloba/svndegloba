package com.degloba.ecommerce.facade.impl;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @category Configuracio Spring (Beans)
 * 
 * @author pere
 * 
 * @see <a href="https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application">See</a>
 *
 */
@Configuration
public class FacadeImplConfiguration {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
