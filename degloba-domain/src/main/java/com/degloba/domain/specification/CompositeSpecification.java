package com.degloba.domain.specification;

/**
 * 
 * @author degloba
 * 
 * @category  La classe Composite Specification té una funció anomenada IsSatisfiedBy que retorna un valor booleà.</br>
 * Després de la instanciació, l'especificació està "encadenada" amb altres especificacions, cosa que fa que les noves especificacions es puguin mantenir fàcilment, 
 * però que segueixin una lògica de negoci altament personalitzable.A més, a l'instància, la lògica de negoci pot, mitjançant la invocació del mètode o mitjançant IOC, 
 * alterar el seu estat per convertir-se en un delegat d'altres classes, com ara un repositori de persistència.
 *
 * @param <T> Entitat de domini
 */
public abstract class CompositeSpecification<T> implements Specification<T>{
	
 
    public Specification<T> and(Specification<T> other){
		return new AndSpecification<T>(this, other);
    }
    public Specification<T> or(Specification<T> other){
		return new OrSpecification<T>(this, other);
    }
	public Specification<T> not(){
		return new NotSpecification<T>(this);
    }
}
