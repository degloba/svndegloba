package com.degloba.domain_seedwork;

// JPA
import javax.persistence.criteria.Predicate;



/**
 * @author degloba
 * Interficie Repositori (Patr� de disseny)
 * @param <T>
 */
public interface IRepository<Entity>   {
	
	/// Encuentra un objeto por la expresi�n indicada       
	///       
	///       
	Entity Find(Predicate predicate);        
	
	////// Crea un nuevo objeto en la base de datos       
	///       
	///Nuevo objeto a crear       
	Entity Create(Entity t);        
	
	////// Borra objetos de la base de datos en base a una expresi�n de filtrado       
	///       
	///       
	int Delete(Predicate predicate);        
	
	////// Actualiza los cambios del objeto en la base de datos       
	///       
	///Objeto a actualizar       
	int Update(Entity t);    

}
