package domini;

// JPA
import javax.persistence.criteria.Predicate;


import com.google.appengine.api.datastore.Entity;



/**
 * @author degloba
 * Interficie Repositori (Patró de disseny)
 * @param <T>
 */
public interface IRepository<Entity>   {
	
	/// Encuentra un objeto por la expresión indicada       
	///       
	///       
	Entity Find(Predicate predicate);        
	
	////// Crea un nuevo objeto en la base de datos       
	///       
	///Nuevo objeto a crear       
	Entity Create(Entity t);        
	
	////// Borra objetos de la base de datos en base a una expresión de filtrado       
	///       
	///       
	int Delete(Predicate predicate);        
	
	////// Actualiza los cambios del objeto en la base de datos       
	///       
	///Objeto a actualizar       
	int Update(Entity t);    

}
