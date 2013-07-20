package domini;

// JPA
import javax.persistence.criteria.Predicate;


import com.google.appengine.api.datastore.Entity;



/**
 * @author degloba
 * Interficie Repositori (Patr� de disseny)
 * @param <T>
 */
public interface IRepository   {
	
	/// Encuentra un objeto por la expresi�n indicada       
	///       
	///       
	<E> E Find(Predicate predicate);        
	
	////// Crea un nuevo objeto en la base de datos       
	///       
	///Nuevo objeto a crear       
	<E> E Create(E entitat);        
	
	////// Borra objetos de la base de datos en base a una expresi�n de filtrado       
	///       
	///       
	int Delete(Predicate predicate);        
	
	////// Actualiza los cambios del objeto en la base de datos       
	///       
	///Objeto a actualizar       
	<E> int Update(E entitat);    

}
