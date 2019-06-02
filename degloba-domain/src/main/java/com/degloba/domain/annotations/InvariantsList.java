package com.degloba.domain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @category llista de tots els {@link Invariant}s suportat pel {@link AggregateRoot}
 * 
 * @author degloba
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InvariantsList {

	String[] value();

}