package com.degloba.domain.specification;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class AndSpecificationTest {
    
    private Specification<String> specification1;
    
    private Specification<String> specification2;
    
    private String fact = "abc";
    
    private Specification<String> instance;
    
    @Before
    public void setUp() {
        specification1 = mock(Specification.class);
        specification2 = mock(Specification.class);
        instance = new AndSpecification<String>(specification1, specification2);
    }

    @Test
    public void testIsSatisfiedByTrueTrue() {
        when(specification1.isSatisfiedBy(fact)).thenReturn(true);
        when(specification2.isSatisfiedBy(fact)).thenReturn(true);
        assertTrue(instance.isSatisfiedBy(fact));
    }

    @Test
    public void testIsSatisfiedByTrueFalse() {
        when(specification1.isSatisfiedBy(fact)).thenReturn(true);
        when(specification2.isSatisfiedBy(fact)).thenReturn(false);
        assertFalse(instance.isSatisfiedBy(fact));
    }

    @Test
    public void testIsSatisfiedByFalseTrue() {
        when(specification1.isSatisfiedBy(fact)).thenReturn(false);
        when(specification2.isSatisfiedBy(fact)).thenReturn(true);
        assertFalse(instance.isSatisfiedBy(fact));
    }

    @Test
    public void testIsSatisfiedByFalseFalse() {
        when(specification1.isSatisfiedBy(fact)).thenReturn(false);
        when(specification2.isSatisfiedBy(fact)).thenReturn(false);
        assertFalse(instance.isSatisfiedBy(fact));
    }
    
}
