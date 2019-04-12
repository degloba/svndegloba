package com.degloba.domain.persistence.rdbms.jpa.internal;

import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;
import com.degloba.domain.persistence.rdbms.jpa.internal.SizeNotEqCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class SizeNotEqCriterionTest {
    
    private SizeNotEqCriterion instance;

    @Before
    public void setUp() {
        instance = new SizeNotEqCriterion("name", 3);
    }

    @Test
    public void testGetValue() {
        assertEquals(3, instance.getValue());
    }

    @Test
    public void testToQueryString() {
        assertEquals("size(rootEntity.name) != :rootEntity_name" + instance.hashCode(), 
                instance.toQueryString());
    }

    @Test
    public void testGetParameters() {
        assertEquals(NamedParameters.create().add("rootEntity_name" + instance.hashCode(), 3), 
                instance.getParameters());
    }

    @Test
    public void testEquals() {
        assertFalse(instance.equals(null));
        assertFalse(instance.equals("abc"));
        assertTrue(instance.equals(instance));
        
        SizeNotEqCriterion other = new SizeNotEqCriterion("name", 3);
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
        
        assertFalse(instance.equals(new SizeNotEqCriterion("name", 4)));
    }
    
}
