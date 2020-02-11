package com.degloba.domain.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * @category Representa una excepció de negoci. 
 * Business exceptions signal attempts to invalidly change business invariants.
 */
@Target(TYPE)
@Documented
public @interface BusinessException {

}