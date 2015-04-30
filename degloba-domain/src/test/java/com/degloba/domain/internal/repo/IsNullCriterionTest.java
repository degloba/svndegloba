package com.degloba.domain.internal.repo;

import com.degloba.domain.internal.repo.IsNullCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class IsNullCriterionTest {
    
    private IsNullCriterion instance;
    
    @Before
    public void setUp() {
        instance = new IsNullCriterion("name");
    }

    @Test
    public void testToQueryString() {
        assertEquals("rootEntity.name is null", instance.toQueryString());
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
        
        IsNullCriterion other = new IsNullCriterion("name");
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
        
        assertFalse(instance.equals(new IsNullCriterion("passwd")));
    }
        
}
