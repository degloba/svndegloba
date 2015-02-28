package com.degloba.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// SPIRNG
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author degloba
 * 
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
