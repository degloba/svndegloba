package com.degloba.domain.specification;

import org.junit.jupiter.api.Test;

import com.degloba.domain.specifications.Specification;

import static org.junit.jupiter.api.Assertions.*;

/** @category valida el funcionament de qualsevol Specification 
* 
* NO UTILITZA L'SPECIFICATION D'SPRING
*/ 
public class AbstractSpecificationTest {

    String data = "Ha! Ha! Ha!";

    @Test
    public void testAnd() {
        assertTrue(new StartsSpecification("Ha! ")
                .and(new LengthSpecification(2, 20))
                .isSatisfiedBy(data));
       assertFalse(new StartsSpecification("Ha! ")
                .and(new LengthSpecification(5))
                .isSatisfiedBy(data));
       assertFalse(new StartsSpecification("Ha ")
                .and(new LengthSpecification(2, 20))
                .isSatisfiedBy(data));     
    }

    @Test
    public void testOr() {
       assertTrue(new StartsSpecification("Ha! ")
                .or(new LengthSpecification(5))
                .isSatisfiedBy(data));
       assertTrue(new StartsSpecification("Ha ")
                .or(new LengthSpecification(2, 20))
                .isSatisfiedBy(data));     
       assertFalse(new StartsSpecification("Ha ")
                .or(new LengthSpecification(5))
                .isSatisfiedBy(data));
    }

    @Test
    public void testNot() {
        Specification<String> specification = new LengthSpecification(5);
       // assertTrue(specification.isSatisfiedBy(data));
        //assertFalse(specification.not().isSatisfiedBy(data));
    }
    
}
