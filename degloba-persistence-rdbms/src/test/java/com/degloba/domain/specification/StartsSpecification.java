package com.degloba.domain.specification;

import com.degloba.domain.specifications.AbstractSpecification;

/** @category valida el funcionament de l'Specification STARTWITH
* 
* NO UTILITZA L'SPECIFICATION D'SPRING
*/ 
public class StartsSpecification extends AbstractSpecification<String>{
    
    private final String startsWith;

    public StartsSpecification(String startsWith) {
        this.startsWith = startsWith;
    }

    @Override
    public boolean isSatisfiedBy(String t) {
        return t != null && t.startsWith(startsWith);
    }
    
}
