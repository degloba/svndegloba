/**
 * 
 */
package infrastructure.events.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * @author Slawek
 * 
 */
@Component
@Target(ElementType.TYPE)
public @interface EventListeners {

}