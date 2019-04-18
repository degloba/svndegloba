package com.degloba.domain;


import com.degloba.domain.ioc.*;
import com.degloba.domain.sharedkernel.exceptions.IocInstanceNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InstanceFactoryTest {

	private IInstanceProvider instanceProvider;
    private MyService1 service1 = new MyService1();
    private MyService2 service2 = new MyService2();
    private MyService3 service3 = new MyService3();

	@Before
	public void setUp() throws Exception {
		instanceProvider = mock(IInstanceProvider.class);
		InstanceFactory.setInstanceProvider(instanceProvider);
	}

	@After
	public void tearDown() throws Exception {
        InstanceFactory.setInstanceProvider(null);
        InstanceFactory.clear();
	}

    /**
     * Set InstanceProvider, and can be found by InstanceProvider Bean
     */
    @Test
    public void testGetInstanceByInstanceProvider() {
        InstanceFactory.setInstanceProvider(instanceProvider);
        when(instanceProvider.getInstance(Service.class)).thenReturn(service1);
        when(instanceProvider.getInstance(Service.class, "service2")).thenReturn(service2);
        when(instanceProvider.getInstance(Service.class, TheAnnotation.class)).thenReturn(service3);
        assertEquals(service1, InstanceFactory.getInstance(Service.class));
        assertEquals(service2, InstanceFactory.getInstance(Service.class, "service2"));
        assertEquals(service3, InstanceFactory.getInstance(Service.class, TheAnnotation.class));
    }

    /**
     * Set InstanceProvider, and can be found by InstanceProvider Bean
     */
    @Test
    public void testGetInstanceByServiceLoaderWithoutInstanceProvider() {
        assertNotNull(InstanceFactory.getInstance(Service2.class));
        assertEquals(service2, InstanceFactory.getInstance(Service.class, "service2"));
        assertEquals(service3, InstanceFactory.getInstance(Service.class, TheAnnotation.class));
    }

    /**
     * Set InstanceProvider, and can be found by InstanceProvider Bean
     */
    @Test
    public void testGetInstanceByServiceLoaderWithInstanceProvider() {
        InstanceFactory.setInstanceProvider(instanceProvider);
        when(instanceProvider.getInstance(Service.class)).thenReturn(null);
        when(instanceProvider.getInstance(Service.class, "service2")).thenReturn(null);
        when(instanceProvider.getInstance(Service.class, TheAnnotation.class)).thenReturn(null);
        assertNotNull(InstanceFactory.getInstance(Service2.class));
        assertEquals(service2, InstanceFactory.getInstance(Service.class, "service2"));
        assertEquals(service3, InstanceFactory.getInstance(Service.class, TheAnnotation.class));
    }


    /**
     * Register by Bind method Bean
     */
    @Test
    public void testGetInstanceByBinder() {
        MyService21 service21 = new MyService21();
        MyService22 service22 = new MyService22();
        MyService23 service23 = new MyService23();
        InstanceFactory.bind(Service2.class, service21);
        assertNotNull(InstanceFactory.getInstance(Service2.class));
        InstanceFactory.bind(Service2.class, service22, "service2");
        InstanceFactory.bind(Service2.class, service23, "service3");
        InstanceFactory.bind(Service2.class, service23, TheAnnotation.class);
        assertEquals(service22, InstanceFactory.getInstance(Service2.class, "service2"));
        assertEquals(service23, InstanceFactory.getInstance(Service2.class, "service3"));
        assertEquals(service23, InstanceFactory.getInstance(Service2.class, TheAnnotation.class));
    }

    @Test(expected = IocInstanceNotFoundException.class)
    public void testNotFound() {
        InstanceFactory.getInstance(Long.class);
    }

}
