package domain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Slawek
 * 
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainFactory {

}
/*@Service
public @interface DomainFactory {

}*/
