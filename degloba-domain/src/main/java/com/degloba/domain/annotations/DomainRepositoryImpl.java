package com.degloba.domain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// SPRING
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;


/**
 * @author degloba
 * 
 * @category Defineix el patró Repository
 * 
 * @see <a href="https://barradevblog.wordpress.com/2013/04/23/el-patron-repositorio-repository-pattern-implementacion-practica-con-entity-framework/">Patró repositori</a>
 * 
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainRepositoryImpl {

}

/*@Repository
public @interface DomainRepositoryImpl {

}*/
