package com.degloba.ioc.spring.factory;

import com.degloba.domain.InstanceProvider;
import com.degloba.ioc.test.AbstractInstanceProviderTest;
import com.degloba.ioc.test.MyService1;
import com.degloba.ioc.test.Service;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by yyang on 14-6-11.
 */
public class ProviderWithXMLTest extends AbstractInstanceProviderTest {

    @Override
    protected InstanceProvider createInstanceProvider() {
        return new SpringInstanceProvider("applicationContext.xml");
    }

    @Test
    public void testGetInstanceByFactoryBean() {
        Service service = getProvider().getInstance(MyService1.class);
        assertEquals("I am Service 1", service.sayHello());
    }
}
