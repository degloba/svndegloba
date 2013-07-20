package com.insacosa.interfaces;


import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.degloba.JPA.*;

import entitats.*;


public class Tipus_Impl extends UtilCriteriaBuilderJPA<Tipus> implements Tipus_If {

	static PersistenceService persistenceService;
	
	public Tipus_Impl() {
		super();
		// TODO Auto-generated constructor stub
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		
		//La classe PersistenceService es "ApplicationScoped"
		persistenceService = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);
	
	}
	
	public Tipus_Impl(EntityManager entityManager, Class<Tipus> entityClass) {
		super(entityManager, entityClass);		
	}
	

	public Tipus tipusInmoble(String keyInmoble) {
		
		Tipus ret = null;
		
		EntityManager em = this.getEntityManager();
		
		try {  
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Tipus> q = cb.createQuery(Tipus.class);
	        Root<Inmobles> root = q.from(Inmobles.class);
	        
	        Path<String> path = root.get("id"); 
	        Predicate p = cb.equal(path, keyInmoble);
	        q.where(p);
	        
	        Selection<Tipus> s = root.get("Tipus");
	        q.select(s);
					
			TypedQuery<Tipus> tq = em.createQuery(q);
			ret = tq.getSingleResult();
			
			}
		finally {
		    //em.close();
		}
		
		return ret;
		
	}
	
	
}
