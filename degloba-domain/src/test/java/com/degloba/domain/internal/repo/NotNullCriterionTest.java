package com.degloba.domain.internal.repo;

import com.degloba.domain.internal.repo.NotNullCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class NotNullCriterionTest {
    
    private NotNullCriterion instance;
    
    @Before
    public void setUp() {
        instance = new NotNullCriterion("name");
    }

    @Test
    public void testToQueryString() {
        assertEquals("rootEntity.name is not null", instance.toQueryString());
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
        
        NotNullCriterion other = new NotNullCriterion("name");
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
        
        assertFalse(instance.equals(new NotNullCriterion("passwd")));
    }
        
}
