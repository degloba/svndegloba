package com.degloba.domain.specification;

import com.degloba.domain.specifications.NotSpecification;
import com.degloba.domain.specifications.Specification;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** @category valida el funcionament de l'Specification NOT
* 
* NO UTILITZA L'SPECIFICATION D'SPRING
*/ 
public class NotSpecificationTest {
    
    private Specification<String> specification;
    private String fact = "abc";
    private NotSpecification<String> instance;
    
    @BeforeEach
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
