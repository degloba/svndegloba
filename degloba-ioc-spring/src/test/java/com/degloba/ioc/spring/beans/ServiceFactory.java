package com.degloba.ioc.spring.beans;

import com.degloba.ioc.test.MyService1;
import org.springframework.beans.factory.FactoryBean;

public class ServiceFactory implements FactoryBean<MyService1> {

    public MyService1 getObject() throws Exception {
        return new MyService1();
    }

    public Class<?> getObjectType() {
        return MyService1.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
