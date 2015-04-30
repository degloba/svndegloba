package com.degloba.domain.internal.repo;

import com.degloba.domain.internal.repo.GtPropCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GtPropCriterionTest {
    
    private GtPropCriterion instance;
    
    @Before
    public void setUp() {
        instance = new GtPropCriterion("name", "name1");
    }

    @Test
    public void testGetOtherPropName() {
        assertEquals("name1", instance.getOtherPropName());
    }

    @Test
    public void testToQueryString() {
        assertEquals("rootEntity.name > rootEntity.name1", 
                instance.toQueryString());
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
        
        GtPropCriterion other = new GtPropCriterion("name", "name1");
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
    }
}
