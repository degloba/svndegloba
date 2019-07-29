package com.degloba.axon_multi.address;

import org.axonframework.springboot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaAutoConfiguration;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;


import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(scanBasePackages = {"com.github.axonmulti.common", "com.github.axonmulti.address"},
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
