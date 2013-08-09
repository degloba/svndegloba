package ddd.domain.annotations;

import org.springframework.stereotype.Service;

import ddd.domain.Entitat;
import ddd.domain.IEntityService;

/**
 * 
 * @author degloba
 * 
 * @category Defineix la Interficie del Servei de Domini
 * No lligat al model de Domini (cap entitat)
 */


@Service
//public interface DomainService <K,T extends Entitat>{
public @interface DomainService {
	

}
