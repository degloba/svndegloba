package com.degloba.domain.internal.repo;

import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;
import com.degloba.domain.persistence.rdbms.jpa.QueryCriterion;
import com.degloba.domain.internal.repo.NotCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class NotCriterionTest {
    
    private QueryCriterion other;
    
    private NotCriterion instance;
    
    @Before
    public void setUp() {
        other = mock(QueryCriterion.class);
        when(other.toQueryString()).thenReturn("a = 1");
        when(other.getParameters()).thenReturn(
                NamedParameters.create().add("name", "abc"));
        instance = new NotCriterion(other);
    }

    @Test
    public void testToQueryString() {
        assertEquals("not (a = 1)", instance.toQueryString());
    }

    @Test
    public void testGetParameters() {
        assertEquals(other.getParameters(), instance.getParameters());
    }

    @Test
    public void testEquals() {
        assertFalse(instance.equals(null));
        assertFalse(instance.equals(other));
        assertTrue(instance.equals(instance));
        
        NotCriterion another = new NotCriterion(other);
        assertTrue(instance.equals(another));
        assertTrue(another.equals(instance));
    }
    
}
