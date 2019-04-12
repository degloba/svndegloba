package com.degloba.domain.specification;

/**
 * La interfície de les normes de negoci correspon al concepte de "normes" de DDD, 
 * que s’utilitza per determinar les normes de negoci.
 * @param <T> Type parameter that indicates specification applied to the target object type
 */
public interface Specification<T> {

  /**
   * Check {code t} is satisfied by this specification.
   *
   * @param t To test object.
   * @return If {code t} meet this specification returns {code true}.
   */
  boolean isSatisfiedBy(T t);

  /**
   * The results create a new specification, 
   * the present specification {code this} and {code specification} Another specification of "and (AND)" operation
   * @param specification Another specification.
   * @return A new specification.
   */
  Specification<T> and(Specification<T> specification);

  /**
   * Create a new specification, the present specification {code this} and {code specification} Another specification of "or (OR)" Operating Results
   * @param specification Another specification.
   * @return Another new norm.
   */
  Specification<T> or(Specification<T> specification);

  /**
   * Create a new specification, the present specification, "non-(NOT)" Operating Results
   * @return A new specification.
   */
  Specification<T> not();
}
