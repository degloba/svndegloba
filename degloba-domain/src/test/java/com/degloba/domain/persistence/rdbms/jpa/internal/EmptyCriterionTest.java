package com.degloba.domain.persistence.rdbms.jpa.internal;

import com.degloba.domain.persistence.rdbms.jpa.QueryCriterion;
import com.degloba.domain.persistence.rdbms.jpa.internal.EmptyCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class EmptyCriterionTest {
    
    private EmptyCriterion instance;
    
    private QueryCriterion other;
    
    @Before
    public void setUp() {
        instance = new EmptyCriterion();
        other = mock(QueryCriterion.class);
    }

    @Test
    public void testAnd() {
        assertEquals(other, instance.and(other));
    }

    @Test
    public void testOr() {
        assertEquals(other, instance.or(other));
    }

    @Test
    public void testNot() {
        assertEquals(instance, instance.not());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testToQueryString() {
        assertTrue(instance.toQueryString().isEmpty());
    }

    @Test
    public void testGetParameters() {
        assertTrue(instance.getParameters().getParams().isEmpty());
    }
    
}
