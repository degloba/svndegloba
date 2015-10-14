/**
 * 
 */
package query.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author degloba
 * 
 */
@Service
@Transactional(readOnly=true)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Finder {

}
