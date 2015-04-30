package com.degloba.domain.internal.repo;

import java.util.Arrays;
import com.degloba.domain.NamedParameters;
import com.degloba.domain.internal.repo.NotInCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class NotInCriterionTest {
    
    private NotInCriterion instance;
    
    @Before
    public void setUp() {
        instance = new NotInCriterion("name", new String[] {"a", "b"});
    }

    @Test
    public void testGetValue() {
        assertTrue(instance.getValue().containsAll(Arrays.asList("a", "b")));
    }

    @Test
    public void testToQueryString() {
        assertEquals("rootEntity.name not in :rootEntity_name" + instance.hashCode(), 
                instance.toQueryString());
        NotInCriterion other = new NotInCriterion("name", Arrays.asList(1, 2));
        assertEquals("rootEntity.name not in :rootEntity_name" + other.hashCode(), 
        		other.toQueryString());
    }

    @Test
    public void testGetParameters() {
        assertEquals(NamedParameters.create().add("rootEntity_name" + instance.hashCode(), Arrays.asList("a", "b")), instance.getParameters());
    }

    @Test
    public void testEquals() {
        assertFalse(instance.equals(null));
        assertFalse(instance.equals("abc"));
        assertTrue(instance.equals(instance));
        
        NotInCriterion other = new NotInCriterion("name", Arrays.asList("a", "b"));
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
        
        assertFalse(instance.equals(new NotInCriterion("name", Arrays.asList("a", "b", "c"))));
    }
}
