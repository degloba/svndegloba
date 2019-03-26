package com.degloba.ioc.spring.factory;

import com.degloba.ioc.spring.beans.ServiceFactory;
import com.degloba.ioc.test.MyService2;
import com.degloba.ioc.test.MyService21;
import com.degloba.ioc.test.MyService3;
import com.degloba.ioc.test.Service;
import com.degloba.ioc.test.Service2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
	
	@Bean(name = "service1")
	public Service service1() {
        try {
            return new ServiceFactory().getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	@Bean(name = "service2")
	public Service service2() {
		return new MyService2();
	}
	
	@Bean(name = "service3")
	public Service service3() {
		return new MyService3();
	}

    @Bean
    public Service2 service2_2() {
         return new MyService21();
    }
}
