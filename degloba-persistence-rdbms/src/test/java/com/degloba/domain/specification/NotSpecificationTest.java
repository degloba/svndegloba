package com.degloba.domain.specification;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class NotSpecificationTest {
    
    private Specification<String> specification;
    private String fact = "abc";
    private NotSpecification<String> instance;
    
    @Before
    public void setUp() {
        specification = mock(Specification.class);
        instance = new NotSpecification<String>(specification);
    }

    @Test
    public void testIsSatisfiedByTrue() {
        when(specification.isSatisfiedBy(fact)).thenReturn(true);
        assertFalse(instance.isSatisfiedBy(fact));
    }

    @Test
    public void testIsSatisfiedByFalse() {
        when(specification.isSatisfiedBy(fact)).thenReturn(false);
        assertTrue(instance.isSatisfiedBy(fact));
    }
    
}
