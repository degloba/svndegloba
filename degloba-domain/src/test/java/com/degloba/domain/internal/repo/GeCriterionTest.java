package com.degloba.domain.internal.repo;

import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;
import com.degloba.domain.internal.repo.GeCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GeCriterionTest {
    
    private GeCriterion instance;

    @Before
    public void setUp() {
        instance = new GeCriterion("name", "abc");
    }

    @Test
    public void testGetValue() {
        assertEquals("abc", instance.getValue());
    }

    @Test
    public void testToQueryString() {
        assertEquals("rootEntity.name >= :rootEntity_name" + instance.hashCode(), 
                instance.toQueryString());
    }

    @Test
    public void testGetParameters() {
        assertEquals(NamedParameters.create().add("rootEntity_name" + instance.hashCode(), "abc"), 
                instance.getParameters());
    }

    @Test
    public void testEquals() {
        assertFalse(instance.equals(null));
        assertFalse(instance.equals("abc"));
        assertTrue(instance.equals(instance));
        
        GeCriterion other = new GeCriterion("name", "abc");
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
    }
    
}
