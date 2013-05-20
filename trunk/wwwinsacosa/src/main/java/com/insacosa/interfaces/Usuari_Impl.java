package com.insacosa.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.richfaces.component.SortOrder;
import org.richfaces.model.FilterField;
import org.richfaces.model.SortField;

import com.insacosa.dataModels_JPA.PersistenceService;
import com.insacosa.entitats.*;

import com.degloba.Util;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;

import com.google.appengine.api.datastore.Key;

import com.google.appengine.api.datastore.Transaction;
import com.google.common.collect.Lists;



public class Usuari_Impl implements Usuari_If {

	static PersistenceService persistenceService;
	
	public Usuari_Impl() {
		super();
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		
		//La classe PersistenceService es "ApplicationScoped"
		persistenceService = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);
		
	}


	public void afegirUsuari(Usuaris usuari) {
			
		// Recuperem l'EntityManager
		
		// 		2 Opcions :
		// 			Opcio 1 --> Utilitzar classe PersistenceService
		EntityManager em = persistenceService.getEntityManager();
		// 			Opcio 2 --> Utilitzar classe EMF
		//em = EMF.get().createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		Usuaris u = new Usuaris();

		u.setNomusuari(usuari.getNomusuari());
		u.setNom(usuari.getNom());
		u.setCognoms(usuari.getCognoms());
		u.setAdreca(usuari.getAdreca());
		u.setCodi(usuari.getCodi());
		u.setProvincia(usuari.getProvincia());
		u.setTelefon(usuari.getTelefon());
		u.setEmail(usuari.getEmail());
		u.setEmail2(usuari.getEmail2());
		u.setPassword(usuari.getPassword());
		u.setAcord(usuari.getAcord());
		
		try {     
				em.persist(u);
				      
				tx.commit();  
				
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
	}


	public void eliminarUsuari(Key keyUsuari) {
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			
				Usuaris usuari = em.find(Usuaris.class, keyUsuari);
				em.remove(usuari);
     
				tx.commit();   
				
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
	}


	public Usuaris cercarUsuari(Key keyUsuari) {
		
		Usuaris usuari = null;
		
		EntityManager em = persistenceService.getEntityManager();
		
		try {     
			
				usuari = em.find(Usuaris.class, keyUsuari);
				
		} catch (RuntimeException e) {
		   
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return usuari;
	}
	
	public Usuaris cercarUsuari(String nomUsuari) {
		
		Usuaris usuari = null;
		Entity usEntity = null;
		
		EntityManager em = persistenceService.getEntityManager();
		
		try {      
			
				javax.persistence.Query q1 = em.createQuery("SELECT u FROM " + Usuaris.class.getName() + " u WHERE u.nom = :nomUsuari");
				q1.setParameter("nomUsuari", nomUsuari);
				usuari = (Usuaris)q1.getSingleResult();
				
				
				// Get the Datastore Service
				DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				Filter nomFilter =  new FilterPredicate("nom", FilterOperator.EQUAL, nomUsuari);
				Query q2 = new Query(Usuaris.class.getName()).setFilter(nomFilter);
				PreparedQuery pq = datastore.prepare(q2);
				usEntity = pq.asSingleEntity();
											
				
		} catch (RuntimeException e) {
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return usuari;
	}	


	public Usuaris editPerfil(String keyUsuari) {
		
		Usuaris usuari = null;
		
		EntityManager em = persistenceService.getEntityManager();
		
		try {   
				usuari = em.find( Usuaris.class, keyUsuari);
				
		} catch (RuntimeException e) {

		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return usuari;  
		
	}

	
	
	  protected Expression<Boolean> createFilterCriteriaForField(String propertyName, Object filterValue, Root<Usuaris> root, CriteriaBuilder criteriaBuilder) {
	    	
	    	String stringFilterValue = null;
	    	Integer integerFilterValue = null;
	    	
	    	if (filterValue !=null)
	    	{
	    		if (filterValue.getClass().toString().compareToIgnoreCase("INTEGER") == 0)
	    		{
	    			if (((String)filterValue).compareTo("") != 0)
	    			{
		    			integerFilterValue = Integer.parseInt((String)filterValue);
		    			
		    			// CONSTRUIM LA INSTRUCCIO JPA
		    			// EN EL CAS DE QUE LA COLUMNA DE LA DATATABLE SIGUI UN INTEGER PODEM APLICAR
		    	        // DIFERENTS OPERADORS (< , >=, == )
		        	    Path<Integer> expression = root.get(propertyName); ////// exemple : propertyName="metres", "habitacions",....
		    			Predicate predicate = criteriaBuilder.ge(expression,  integerFilterValue); 
		    			
		    			return predicate;
	    			}
	    		
	    		}
	    		else if (filterValue.getClass().toString().compareToIgnoreCase("STRING") == 0)
	    		{
	    			stringFilterValue = (String) filterValue;
	
		    		
		    		// CONSTRUIM LA INSTRUCCIO JPA
		    		// EN EL CAS DE QUE SIGUI UN STRING EL FILTRE ES QUE LA CADENA ESCRITA SIGUI UN SUBSTRING
		    		Path<String> expression = root.get(propertyName);////// name, metres,....
			        Expression<Integer> locator = criteriaBuilder.locate(criteriaBuilder.lower(expression), stringFilterValue, 1);
			        return criteriaBuilder.gt(locator, 0);
	    		}
	   
	    	}
	    	else
	    		return null;
	    	

	     
	     // si el valor del filtre no es de cap tipus (Integer, String,...) que controlem
	     return null;
	     
	    }
	    
	  
	  private List<Order> createOrders(CriteriaBuilder criteriaBuilder, Root<Usuaris> root, List<String> sortFields, String sortOrder) {

	        List<Order> orders = Lists.newArrayList();
	        
	        if (sortFields != null && !sortFields.isEmpty()) {
	          
	            for (String sortField: sortFields) {
	         	
	         		String propertyName = null;
	            	
	            	propertyName = (String) sortField;
	                	                	                 
	                Path<Object> expression = root.get(propertyName);
	                
	                Order jpaOrder;
	                
	                if (sortOrder == "ASC") {
	                    jpaOrder = criteriaBuilder.asc(expression);
	                } else if (sortOrder == "DESC") {
	                    jpaOrder = criteriaBuilder.desc(expression);
	                } else {
	                    throw new IllegalArgumentException(sortOrder.toString());
	                }
	            
	                orders.add(jpaOrder);
	            }
	        }
	        
	        return orders;
	    }

	  
	  
	
	    private CriteriaQuery<Usuaris> createSelectCriteriaQuery(List<String> campsFiltre,List<Object> valorsFiltre, List<String> campsOrdre, String ordre) {
	    	
	    	EntityManager em = persistenceService.getEntityManager();
	    	
	        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	        CriteriaQuery<Usuaris> criteriaQuery = criteriaBuilder.createQuery(Usuaris.class);
	        Root<Usuaris> root = criteriaQuery.from(Usuaris.class);
	        
	        List<Order> orders = createOrders(criteriaBuilder, root, campsOrdre, ordre);
	        if (!orders.isEmpty()) {
	                criteriaQuery.orderBy(orders);
	            }
	            
	        Expression<Boolean> filterCriteria = createFilterCriteria(criteriaBuilder, root, campsFiltre, valorsFiltre);
	        if (filterCriteria != null) {
	                criteriaQuery.where(filterCriteria);
	            }

	        return criteriaQuery;
	    }
	    
	
	  private Expression<Boolean> createFilterCriteria(CriteriaBuilder criteriaBuilder, Root<Usuaris> root, List<String> propertyNames, List<Object> filterValue) {
		  
	        Expression<Boolean> filterCriteria = null;
	        
	        for (String propertyName : propertyNames) {

	        	Expression<Boolean> predicate = createFilterCriteriaForField(propertyName, filterValue, root, criteriaBuilder);
                
		        if (filterCriteria == null) {
		                    filterCriteria = predicate.as(Boolean.class);
		                } else {
		                    filterCriteria = criteriaBuilder.and(filterCriteria, predicate.as(Boolean.class));
		                }
	        }
	        
	        return filterCriteria;
	    }
	
	
	
	
	
	public Usuaris usuariValid(Usuaris usuari) {
			
		EntityManager em = persistenceService.getEntityManager();
			
		
		try {    
			
			// Get the Datastore Service
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Filter nomFilter =  new Query.FilterPredicate("nomusuari", Query.FilterOperator.EQUAL, usuari.getNomusuari() );
			Query q2 = new Query("Usuaris").setFilter(nomFilter);
			PreparedQuery pq = datastore.prepare(q2);
			Entity usEntity = pq.asSingleEntity();
						
			Query query = new Query("Usuaris"); 
			query.addFilter("nom", FilterOperator.EQUAL, usuari.getNomusuari()); 
			usEntity = Util.getDatastoreServiceInstance().prepare(query).asSingleEntity(); 
			//if (!results.isEmpty()) {   
				//return (Entity)results.remove(0); 
				//} 
			//return null;
			
			
			//List<String> campsFiltre,List<Object> valorsFiltre, List<String> campsOrdre, String ordre) {
			CriteriaQuery<Usuaris> crit = createSelectCriteriaQuery(new ArrayList<String>(), null, null, null);
			
			TypedQuery<Usuaris> query2 = em.createQuery(crit);
			List<Usuaris> results = query2.getResultList();
			
			// **************************************************
			
			
			// FUNCIONA
			CriteriaBuilder cb = em.getCriteriaBuilder();  // comu
			
			crit = cb.createQuery(Usuaris.class);
			Root<Usuaris> candidateRoot = crit.from(Usuaris.class);  // FROM
			crit.select(candidateRoot);
			
			TypedQuery<Usuaris> query3 = em.createQuery(crit);
			List<Usuaris> results3 = query3.getResultList();
			
			// **************************************************			
			
			CriteriaQuery<Usuaris> crit2 = cb.createQuery(Usuaris.class);
			Root<Usuaris> c = crit2.from(Usuaris.class);			
						
			Selection<Usuaris> s = crit2.getSelection();
			Path<String> p = c.get("nom");
			
			Predicate predicate = cb.equal(p,  usuari);  // WHERE
		
			
	/*		crit2.select(predicate);
	
			
			TypedQuery<Usuaris> query2 = em.createQuery(crit);
			List<Usuaris> results = query2.getResultList();
		*/	
			
			// FUNCIONA
			Path nameField = candidateRoot.get("nom");
			Predicate nameEquals = cb.equal(nameField, "peresan");
			crit.where(nameEquals);
			
			TypedQuery<Usuaris> query4 = em.createQuery(crit);
			Usuaris u= query4.getSingleResult();
			
			
			// FUNCIONA
			javax.persistence.Query q = em.createQuery("SELECT u FROM " + Usuaris.class.getName() + " u WHERE u.nom = :nomUsuari");
			q.setParameter("nomUsuari", usuari.getNomusuari());
			
			return (Usuaris)q.getSingleResult();
			    
		} catch (NoResultException e) {
			return null;
		}
 	
	}


	public boolean emailValid(String email) {
		
		Long num = null;
		Boolean existeix = false;
		    
		EntityManager em = persistenceService.getEntityManager();
		
		try {      
			
			javax.persistence.Query q = em.createQuery("select count(*) from Usuaris as usuari where usuari.email = '" + email + "'");
				
			num = (Long)q.getSingleResult();
			if (num == 0)
				existeix = false;
			else
				existeix = true;
				 
		} catch (RuntimeException e) {

		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return existeix;

	}
	

	public String passwordEmail(String email) {
		
		String password = null;
		    
		EntityManager em = persistenceService.getEntityManager(); 
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {   
			
			 javax.persistence.Query q = em.createQuery(("select password from Usuaris as usuari where usuari.email = '" + email + "'"));
			
			 password = (String)q.getSingleResult();
			 
			 tx.commit();
			
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return password;

	}


	

	public String cambiaPassword(Usuaris usuari) {
		   
		EntityManager em = persistenceService.getEntityManager(); 
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      
				
				em.persist(usuari);
				      
				tx.commit();    
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return null;
	}
	




	public String modificarUsuari(Usuaris usuari) {
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = null;
		tx.begin();
		
		try {      
			
				em.persist(usuari);
				      
				tx.commit();    
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return null;
	}


	public String cambiaPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
