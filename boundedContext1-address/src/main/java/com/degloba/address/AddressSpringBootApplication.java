package com.degloba.address;


import lombok.extern.slf4j.Slf4j;
import org.axonframework.springboot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.degloba.common", "com.degloba.address"},
        exclude = {ErrorMvcAutoConfiguration.class
                , JpaEventStoreAutoConfiguration.class
                , JpaAutoConfiguration.class
                , JdbcAutoConfiguration.class
        })
@Slf4j
public class AddressSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressSpringBootApplication.class, args);
    }

}

