package com.degloba.domain.internal.repo;

import com.degloba.domain.NamedParameters;
import com.degloba.domain.QueryCriterion;
import com.degloba.domain.internal.repo.OrCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class OrCriterionTest {
    
    private QueryCriterion criterion1;
    
    private QueryCriterion criterion2;
    
    private QueryCriterion criterion3;
    
    private QueryCriterion criterion4;
    
    private OrCriterion instance;
    
    @Before
    public void setUp() {
        criterion1 = mock(QueryCriterion.class);
        when(criterion1.toQueryString()).thenReturn("a = 1");
        when(criterion1.getParameters()).thenReturn(
                NamedParameters.create().add("name", "abc"));
        
        criterion2 = mock(QueryCriterion.class);
        when(criterion2.toQueryString()).thenReturn("b = 2");
        when(criterion2.getParameters()).thenReturn(
                NamedParameters.create().add("age", 15));
        
        criterion3 = null;
        
        criterion4 = mock(QueryCriterion.class);
        when(criterion4.isEmpty()).thenReturn(Boolean.TRUE);
        
        instance = new OrCriterion(criterion1, criterion2, criterion3, criterion4);
    }

    @Test
    public void testToQueryString() {
        assertEquals("(a = 1 or b = 2)", instance.toQueryString());
    }

    @Test
    public void testGetParameters() {
        assertEquals(NamedParameters.create().add("name", "abc").add("age", 15),
                instance.getParameters());
    }

    @Test
    public void testEquals() {
        assertFalse(instance.equals(null));
        assertFalse(instance.equals(criterion1));
        assertTrue(instance.equals(instance));
        
        OrCriterion other = new OrCriterion(criterion1, criterion2);
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
    }
}
