package com.degloba.domain.repository;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.degloba.domain.PositionalParameters;
import com.degloba.domain.IEntityRepository;
import com.degloba.domain.NamedParameters;
import com.degloba.domain.NamedQuery;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class NamedQueryTest {
    
    private NamedQuery instance;
    private IEntityRepository repository;
    private String queryName = "Employee.findByName";
    
    @Before
    public void setUp() {
        repository = mock(IEntityRepository.class);
        instance = new NamedQuery(repository, queryName);
    }

    /**
     * Test of getQueryName method, of class NamedQuery.
     */
    @Test
    public void testGetQueryName() {
        assertEquals(queryName, instance.getQueryName());
    }

    /**
     * Test of setParameters method, of class NamedQuery.
     */
    @Test
    public void testSetParameters_ObjectArr() {
        Object[] params = new Object[] {"Hello", 1234};
        instance.setParameters(params);
        assertEquals(PositionalParameters.create(params), instance.getParameters());
    }

    /**
     * Test of setParameters method, of class NamedQuery.
     */
    @Test
    public void testSetParameters_List() {
        List<? extends Object> params = Arrays.asList("Hello", 1234);
        instance.setParameters(params);
        assertEquals(PositionalParameters.create(params), instance.getParameters());
    }

    /**
     * Test of setParameters method, of class NamedQuery.
     */
    @Test
    public void testSetParameters_Map() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "abc");
        params.put("created", new Date());
        instance.setParameters(params);
        assertEquals(NamedParameters.create(params), instance.getParameters());
    }

    /**
     * Test of addParameter method, of class NamedQuery.
     */
    @Test
    public void testAddParameter() {
        Map<String, Object> params = new HashMap<String, Object>();
        Date created = new Date();
        params.put("name", "abc");
        params.put("created", created);
        instance.setParameters(params);
        assertEquals(NamedParameters.create(params), 
                instance.addParameter("name", "abc")
                        .addParameter("created", created)
                        .getParameters());
    }

    /**
     * Test of getFirstResult method, of class NamedQuery.
     */
    @Test
    public void testFirstResult() {
        assertEquals(3, instance.setFirstResult(3).getFirstResult());
    }

    /**
     * Test of getMaxResults method, of class NamedQuery.
     */
    @Test
    public void testMaxResults() {
        assertEquals(10, instance.setMaxResults(10).getMaxResults());
    }

    /**
     * Test of list method, of class NamedQuery.
     */
    @Test
    public void testList() {
        List<Object> results = Arrays.asList(new Object[] {1, 3, 5});
        when(repository.find(instance)).thenReturn(results);
        assertEquals(results, instance.list());
    }

    /**
     * Test of singleResult method, of class NamedQuery.
     */
    @Test
    public void testSingleResult() {
        Object result = "abc";
        when(repository.getSingleResult(instance)).thenReturn(result);
        assertEquals(result, instance.singleResult());
    }

    /**
     * Test of executeUpdate method, of class NamedQuery.
     */
    @Test
    public void testExecuteUpdate() {
        when(repository.executeUpdate(instance)).thenReturn(8);
        assertEquals(8, instance.executeUpdate());
    }
    
}
