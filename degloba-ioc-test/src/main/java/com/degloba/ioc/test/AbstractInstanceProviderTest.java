package com.degloba.ioc.test;

import com.degloba.domain.InstanceProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Public InstanceProvider test
 */
public abstract class AbstractInstanceProviderTest {

	private InstanceProvider provider;
	abstract protected InstanceProvider createInstanceProvider();

	@Before
	public void setUp() {
		provider = createInstanceProvider();
	}
	
	@Test
	public void testGetInstance() {
		assertNotNull(provider.getInstance(Service2.class));
	}

	@Test
	public void testGetInstanceWithName() {
		Service service = provider.getInstance(Service.class, "service2");
		assertEquals("I am Service 2", service.sayHello());
	}

    @Test
    public void testGetInstanceWithAnnotation() {
        Service service = provider.getInstance(Service.class, TheAnnotation.class);
        assertEquals("I am Service 3", service.sayHello());
    }

    @Test
    public void testNotFound() {
        assertNull(provider.getInstance(Long.class));
    }

	public InstanceProvider getProvider() {
		return provider;
	}
}
