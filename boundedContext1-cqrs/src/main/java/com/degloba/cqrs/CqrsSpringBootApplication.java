package com.degloba.cqrs;


import lombok.extern.slf4j.Slf4j;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.degloba.cqrs"},
        exclude = {ErrorMvcAutoConfiguration.class
        		//, JpaEventStoreAutoConfiguration.class
        		//, JpaAutoConfiguration.class
        		//, JdbcAutoConfiguration.class
                //, EventProcessingAutoConfiguration.class
        })
@Slf4j
public class CqrsSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CqrsSpringBootApplication.class, args);
    }

}
