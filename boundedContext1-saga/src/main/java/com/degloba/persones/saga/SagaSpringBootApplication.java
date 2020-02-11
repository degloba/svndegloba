package com.degloba.persones.saga;

import org.axonframework.springboot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.axonframework.springboot.util.RegisterDefaultEntities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;



@SpringBootApplication(scanBasePackages = {"com.degloba.common", "com.degloba.saga"},
        exclude = {ErrorMvcAutoConfiguration.class
                , JpaEventStoreAutoConfiguration.class
                , JpaAutoConfiguration.class
                , JdbcAutoConfiguration.class
        })
@RegisterDefaultEntities(packages = {"org.axonframework.modelling.saga.repository.jpa"})
public class SagaSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SagaSpringBootApplication.class, args);
    }

}
