package com.degloba.domain.internal.repo;

import java.util.Arrays;
import com.degloba.domain.NamedParameters;
import com.degloba.domain.internal.repo.InCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class InCriterionTest {
    
    private InCriterion instance;
    
    @Before
    public void setUp() {
        instance = new InCriterion("name", new String[] {"a", "b"});
    }

    @Test
    public void testGetValue() {
        assertTrue(instance.getValue().containsAll(Arrays.asList("a", "b")));
    }

    @Test
    public void testToQueryString() {
        assertEquals("rootEntity.name in :rootEntity_name" + instance.hashCode(), 
                instance.toQueryString());
        InCriterion other =  new InCriterion("name", Arrays.asList(1, 2));
        assertEquals("rootEntity.name in :rootEntity_name" + other.hashCode(), 
               other.toQueryString());
    }

    @Test
    public void testGetParameters() {
        assertEquals(NamedParameters.create()
        		.add("rootEntity_name" + instance.hashCode(), Arrays.asList("a", "b")), 
        		instance.getParameters());
    }

    @Test
    public void testEquals() {
        assertFalse(instance.equals(null));
        assertFalse(instance.equals("abc"));
        assertTrue(instance.equals(instance));
        
        InCriterion other = new InCriterion("name", Arrays.asList("a", "b"));
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
        
        assertFalse(instance.equals(new InCriterion("name", Arrays.asList("a", "b", "c"))));
    }
}
