package com.degloba.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// SPRING
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;


/**
 * @author degloba
 * @category Defineix el patro Repository
 */

@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainRepositoryImpl {

}

/*@Repository
public @interface DomainRepositoryImpl {

}*/
