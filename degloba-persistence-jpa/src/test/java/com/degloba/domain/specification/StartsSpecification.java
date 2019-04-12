package com.degloba.domain.specification;


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
