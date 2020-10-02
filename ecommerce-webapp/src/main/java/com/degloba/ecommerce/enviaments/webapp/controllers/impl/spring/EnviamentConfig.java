package com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.degloba.ecommerce.enviaments.domain.persistence.nosql.mongo.EnviamentTemplateOperations;
import com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring.EnviamentRepository2;

/**
 * 
 * @author pere
 * 
 * @category defineix les rutes
 *
 */

@Configuration
public class EnviamentConfig {

  
    @Bean
    EnviamentRepository2 enviamentRepository() {
        return new EnviamentRepository2();
    }
    
    @Bean
    EnviamentTemplateOperations enviamentTemplateOperations() {
        return new EnviamentTemplateOperations();
    }
    


}
