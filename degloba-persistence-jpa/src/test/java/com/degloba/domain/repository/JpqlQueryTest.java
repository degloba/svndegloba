package com.degloba.domain.repository;


import com.degloba.domain.persistence.rdbms.jpa.PositionalParameters;
import com.degloba.domain.persistence.rdbms.jpa.IEntityRepository;
import com.degloba.domain.persistence.rdbms.jpa.JpqlQuery;
import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class JpqlQueryTest {
    
    private JpqlQuery instance;
    private IEntityRepository repository;
    private String jpql = "select o from Employee o";
    
    @Before
    public void setUp() {
        repository = mock(IEntityRepository.class);
        instance = new JpqlQuery(repository, jpql);
    }
 
    /**
     * Test of getSql method, of class JpqlQuery.
     */
    @Test
    public void testGetJpql() {
        assertEquals(jpql, instance.getJpql());
    }

    /**
     * Test of setParameters method, of class JpqlQuery.
     */
    @Test
    public void testSetParameters_ObjectArr() {
        Object[] params = new Object[] {"Hello", 1234};
        instance.setParameters(params);
        assertEquals(PositionalParameters.create(params), instance.getParameters());
    }

    /**
     * Test of setParameters method, of class JpqlQuery.
     */
    @Test
    public void testSetParameters_List() {
        List<? extends Object> params = Arrays.asList("Hello", 1234);
        instance.setParameters(params);
        assertEquals(PositionalParameters.create(params), instance.getParameters());
    }

    /**
     * Test of setParameters method, of class JpqlQuery.
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
     * Test of addParameter method, of class JpqlQuery.
     */
    @Test
    public void testAddParameter() {
        Date createdDate = new Date();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "abc");
        params.put("created", createdDate);
        instance.setParameters(params);
        assertEquals(NamedParameters.create(params), 
                instance.addParameter("name", "abc")
                        .addParameter("created", createdDate)
                        .getParameters());
    }

    /**
     * Test of getFirstResult method, of class JpqlQuery.
     */
    @Test
    public void testFirstResult() {
        assertEquals(3, instance.setFirstResult(3).getFirstResult());
    }

    /**
     * Test of getMaxResults method, of class JpqlQuery.
     */
    @Test
    public void testMaxResults() {
        assertEquals(10, instance.setMaxResults(10).getMaxResults());
    }

    /**
     * Test of list method, of class JpqlQuery.
     */
    @Test
    public void testList() {
        List<Object> results = Arrays.asList(new Object[] {1, 3, 5});
        when(repository.find(instance)).thenReturn(results);
        assertEquals(results, instance.list());
    }

    /**
     * Test of singleResult method, of class JpqlQuery.
     */
    @Test
    public void testSingleResult() {
        Object result = "abc";
        when(repository.getSingleResult(instance)).thenReturn(result);
        assertEquals(result, instance.singleResult());
    }

    /**
     * Test of executeUpdate method, of class JpqlQuery.
     */
    @Test
    public void testExecuteUpdate() {
        when(repository.executeUpdate(instance)).thenReturn(8);
        assertEquals(8, instance.executeUpdate());
    }
    
}
