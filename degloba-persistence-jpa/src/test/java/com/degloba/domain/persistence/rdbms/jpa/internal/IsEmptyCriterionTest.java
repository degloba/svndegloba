package com.degloba.domain.persistence.rdbms.jpa.internal;

import com.degloba.domain.persistence.rdbms.jpa.internal.IsEmptyCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class IsEmptyCriterionTest {
    
    private IsEmptyCriterion instance;
    
    @Before
    public void setUp() {
        instance = new IsEmptyCriterion("name");
    }

    @Test
    public void testToQueryString() {
        assertEquals("rootEntity.name is empty", instance.toQueryString());
    }

    @Test
    public void testGetParameters() {
        assertTrue(instance.getParameters().getParams().isEmpty());
    }

    @Test
    public void testEquals() {
        assertFalse(instance.equals(null));
        assertFalse(instance.equals("abc"));
        assertTrue(instance.equals(instance));
        
        IsEmptyCriterion other = new IsEmptyCriterion("name");
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
        
        assertFalse(instance.equals(new IsEmptyCriterion("passwd")));
    }
        
}
