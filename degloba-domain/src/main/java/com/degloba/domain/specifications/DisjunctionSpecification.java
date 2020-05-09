package com.degloba.domain.specifications;

/**
 * 
 * @author degloba
 * 
 * See <a href="https://www.google.com/search?q=disyunci%C3%B3n+definici%C3%B3n&oq=disjunci%C3%B3&aqs=chrome.1.69i57j0l7.2396j0j8&sourceid=chrome&ie=UTF-8">definició</a>
 *
 * @param <T>
 */
public class DisjunctionSpecification<T> extends CompositeSpecification<T>{
    private Specification<T>[] disjunction;

    public DisjunctionSpecification(Specification<T>... disjunction){
        this.disjunction = disjunction;
    }

    public boolean isSatisfiedBy(T candidate){
        for (Specification<T> spec : disjunction){
        	if (spec.isSatisfiedBy(candidate))
        		return true;
        }
    	
    	return false;
    }
}
