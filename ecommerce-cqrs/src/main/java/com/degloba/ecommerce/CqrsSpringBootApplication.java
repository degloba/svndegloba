package com.degloba.ecommerce;


import lombok.extern.slf4j.Slf4j;

import org.axonframework.springboot.autoconfig.EventProcessingAutoConfiguration;
import org.axonframework.springboot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.degloba.cqrs"},
        exclude = {ErrorMvcAutoConfiguration.class
        		, JpaEventStoreAutoConfiguration.class
        		, JpaAutoConfiguration.class
        		, JdbcAutoConfiguration.class
          //      , EventProcessingAutoConfiguration.class
        })
@Slf4j
public class CqrsSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CqrsSpringBootApplication.class, args);
        
        log.debug("[CqrsSpringBootApplication] Run");
    }

}
