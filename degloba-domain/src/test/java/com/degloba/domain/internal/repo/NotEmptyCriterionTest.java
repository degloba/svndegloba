package com.degloba.domain.internal.repo;

import com.degloba.domain.internal.repo.NotEmptyCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class NotEmptyCriterionTest {
    
    private NotEmptyCriterion instance;
    
    @Before
    public void setUp() {
        instance = new NotEmptyCriterion("name");
    }

    @Test
    public void testToQueryString() {
        assertEquals("rootEntity.name is not empty", instance.toQueryString());
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
        
        NotEmptyCriterion other = new NotEmptyCriterion("name");
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
        
        assertFalse(instance.equals(new NotEmptyCriterion("passwd")));
    }
        
}
