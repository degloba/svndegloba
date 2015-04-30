package com.degloba.domain.internal.repo;

import com.degloba.domain.NamedParameters;
import com.degloba.domain.internal.repo.BetweenCriterion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class BetweenCriterionTest {

    private BetweenCriterion instance;
    
    @Before
    public void setUp() {
        instance = new BetweenCriterion("age", 18, 22);
    }

    @Test
    public void testGetPropName() {
        assertEquals("age", instance.getPropName());
    }

    @Test
    public void testGetFrom() {
        assertEquals(18, instance.getFrom());
    }

    @Test
    public void testGetTo() {
        assertEquals(22, instance.getTo());
    }

    @Test
    public void testToQueryString() {
        assertEquals("rootEntity.age between :rootEntity_age" + instance.hashCode() + 
        		"_from and :rootEntity_age" + instance.hashCode() + "_to", instance.toQueryString());
    }

    @Test
    public void testGetParameters() {
        assertEquals(NamedParameters.create().add("rootEntity_age" + instance.hashCode() + "_from", 18)
                .add("rootEntity_age" + instance.hashCode() + "_to", 22), instance.getParameters());
    }

    @Test
    public void testEquals() {
        assertFalse(instance.equals(null));
        assertFalse(instance.equals("abc"));
        assertTrue(instance.equals(instance));
        
        BetweenCriterion other = new BetweenCriterion("age", 18, 22);
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
    }

}
