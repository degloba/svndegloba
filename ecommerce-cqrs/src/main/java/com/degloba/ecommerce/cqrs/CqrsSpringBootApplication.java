package com.degloba.ecommerce.cqrs;


import lombok.extern.slf4j.Slf4j;


import org.springframework.boot.SpringApplication;

/*@SpringBootApplication(scanBasePackages = {"com.degloba.ecommerce.cqrs"},
        exclude = {ErrorMvcAutoConfiguration.class
        		, JpaEventStoreAutoConfiguration.class
        		, JpaAutoConfiguration.class
        		, JdbcAutoConfiguration.class
          //      , EventProcessingAutoConfiguration.class
        })*/
@Slf4j
public class CqrsSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CqrsSpringBootApplication.class, args);
        
        log.debug("[CqrsSpringBootApplication] Run");
    }

}
