package com.degloba.ioc.spring.factory;

import com.degloba.domain.ioc.IInstanceProvider;
import com.degloba.ioc.test.AbstractInstanceProviderTest;
import com.degloba.ioc.test.MyService1;
import com.degloba.ioc.test.Service;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProviderWithAnnotationTest extends AbstractInstanceProviderTest {
	
	@Override
	protected IInstanceProvider createInstanceProvider() {
		return new com.degloba.ioc.spring.factory.SpringInstanceProvider(SpringConfiguration.class);
	}

    @Test
    public void testGetInstanceByFactoryBean() {
        Service service = getProvider().getInstance(MyService1.class);
        assertEquals("I am Service 1", service.sayHello());
    }

}

