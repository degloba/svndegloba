package com.degloba.domain.specification;


public class LengthSpecification extends AbstractSpecification<String> {

    private final int min;
    
    private final int max;

    public LengthSpecification(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public LengthSpecification(int max) {
        this(0, max);
    }
    
    @Override
    public boolean isSatisfiedBy(String t) {
        return t != null && t.length() >= min && t.length() <= max;
    }
    
}
