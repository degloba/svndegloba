package com.degloba.domain.internal.repo;

import com.degloba.domain.NamedParameters;
import com.degloba.domain.internal.repo.ContainsTextCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ContainsTextCriterionTest {
    
    private ContainsTextCriterion instance;

    @Before
    public void setUp() {
        instance = new ContainsTextCriterion("name", "abc");
    }

    @Test
    public void testGetValue() {
        assertEquals("abc", instance.getValue());
    }

    @Test
    public void testToQueryString() {
        assertEquals("rootEntity.name like :rootEntity_name" + instance.hashCode(), 
                instance.toQueryString());
    }

    @Test
    public void testGetParameters() {
        assertEquals(NamedParameters.create().add("rootEntity_name" + instance.hashCode(), "%abc%"), 
                instance.getParameters());
    }

    @Test
    public void testEquals() {
        assertFalse(instance.equals(null));
        assertFalse(instance.equals("abc"));
        assertTrue(instance.equals(instance));
        
        ContainsTextCriterion other = new ContainsTextCriterion("name", "abc");
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
    }
    
}
