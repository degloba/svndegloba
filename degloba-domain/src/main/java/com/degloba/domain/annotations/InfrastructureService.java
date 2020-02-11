package com.degloba.domain.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @category Representa un servei d'infrastructura. 
 * Un servei d'infrastructura proporciona funcionalitats al domini que necessita
 * adicional infrastructura únicament accessible fora del domini. 
 * The infrastructure service interface forms part of
 * the domain, the implementation is part of the infrastructure.
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface InfrastructureService {
}