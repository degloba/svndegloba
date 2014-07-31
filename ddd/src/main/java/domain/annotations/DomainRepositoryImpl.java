package domain.annotations;

import org.springframework.stereotype.Repository;

/**
 * @author degloba
 * @category Defineix el patro Repository
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainRepositoryImpl {

}

/*@Repository
public @interface DomainRepositoryImpl {

}*/
