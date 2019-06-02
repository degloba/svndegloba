package com.degloba.domain.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Spring
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author degloba
 * 
 * @category Permet centralitzar la lògica de negoci a través de diversos components</br>
 * <ul>
 * <li>
 * 		Si es vol minimitzar la lògica de negoci a la capa de la façana.
 * </li>
 * <li>
 * 		Si es té una lògica de negoci que actua sobre diversos serveis o objectes de negoci.
 * </li>
 * <li>
 *      Si es vol encapsular la lògica específica d'un cas d'ús fora dels Business Objects.
 * </li>
 * </ul>
 */

@Component
@Retention(RetentionPolicy.RUNTIME)
@Transactional(propagation = Propagation.REQUIRED)
@Target(ElementType.TYPE)
public @interface ApplicationService {

}
/*@Service
@Transactional
//@Secured
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApplicationService {
    String value() default "";
}*/
