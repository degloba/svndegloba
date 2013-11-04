package com.insacosa.presentation.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import query.annotations.Finder;


import com.degloba.persistencia.JPA.UtilCriteriaBuilderJPA;
import com.google.appengine.api.datastore.Key;
import com.insacosa.domain.InmobleCaract;
import com.insacosa.domain.Inmobles;
import com.insacosa.domain.Solicituds;
import com.insacosa.domain.Usuaris;
import com.insacosa.presentation.CiutatsFinder;
import com.insacosa.presentation.InmoblesFinder;
import com.insacosa.presentation.SolicitudsFinder;
import com.insacosa.presentation.SolicitudsListItemDto;
import com.insacosa.presentation.TipusFinder;
import com.insacosa.presentation.UsuarisFinder;

import com.insacosa.vo.FotoForm;
import com.insacosa.webui.InmobleCaractItemDto;
import com.insacosa.webui.InmobleItemDto;
import com.insacosa.webui.UsuariItemDto;

/**
 * @author Rafał Jamróz
 */
@Finder
public class JpaInmoblesFinder extends UtilCriteriaBuilderJPA<Inmobles> implements InmoblesFinder {

    @PersistenceContext
    private EntityManager entityManager;
    
    // FinderS (lectura)
 	//---------------------
 	 
     @Inject
     private SolicitudsFinder solicitudsFinder;
     @Inject
     private TipusFinder tipusFinder;
     @Inject
     private InmoblesFinder inmoblesFinder;
     @Inject
     private CiutatsFinder ciutatsFinder;
     @Inject
     private UsuarisFinder usuarisFinder;

  
    private SolicitudsListItemDto toSolicitudsListItemDto(Solicituds solicitud) {
    	SolicitudsListItemDto dto = new SolicitudsListItemDto();
        dto.setOrderId(solicitud.getEntityId());
/*        dto.setTotalCost(solicitud.getTotalCost());
        dto.setOrderedProducts(solicitud.getOrderedProducts());
        dto.setSubmitDate(solicitud.getSubmitDate());
        dto.setOrderStatus(solicitud.getStatus());*/
        return dto;
    }

    
    
    public List<Inmobles> inmoblesVenedorRang(Usuaris usuari, int firstResult, int maxResults) {

		List<Inmobles> ret = new ArrayList<Inmobles>();
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		  
		try {      
			
				/*
				criteria = session.createCriteria(Inmobles.class)
					.add(Expression.eq("usuaris",usuari));
				
				criteria.setFirstResult(firstResult);
				criteria.setMaxResults(maxResults);
				
				ret = criteria.list();
				
				Iterator<Inmobles> it = ret.iterator();
				
				while (it.hasNext())
				{
					Inmobles inmoble = it.next();
					
						
				}
				
			*/
			tx.commit();    
			 
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		return ret;
		
	}
	
	

    @Override
	public List<Inmobles> inmoblesVenedor(Usuaris usuari) {

		List<Inmobles> ret = new ArrayList<Inmobles>();
		  
		EntityManager em = this.getEntityManager();
		
		try {      
				
			// JPA Criteria API	
			CriteriaBuilder cb = em.getCriteriaBuilder();
			
			CriteriaQuery<Inmobles> cq = cb.createQuery(Inmobles.class);
	        Root<Inmobles> inmobles = cq.from(Inmobles.class);
	        ParameterExpression<Usuaris> p = cb.parameter(Usuaris.class);
	  
	        TypedQuery<Inmobles> tq = em.createQuery(cq);
	        tq.setParameter(p, usuari);
	        ret = tq.getResultList();
			
			 
		} catch (RuntimeException e) {
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		
		return ret;
		
	}

    
    @Override
	public List<Inmobles> inmoblesTipus() {
		
		return null;
	}
    
    
    
    /*
	 * Construim les files de la datatable que son una llista d'objectes InmobleCaract
	 */
    @Override
	public List<InmobleCaract> getInmoblesVenedorCaract()
    {
		return null;

/*		synchronized (this) { 
		
			if (inmoblesVenedorCaract.isEmpty() || cambiDades)  // ES CAMBIA LES DADES SI CAMBIEM EL TIPUS
			{
				cambiDades = false;
								
		    	List<InmobleCaractItemDto> listDataRow = new ArrayList<InmobleCaractItemDto>();
						    	
	    			    	
		    	UsuariItemDto usuari = usuarisFinder.cercarUsuari("peresan");
		    	
		    	List<InmobleItemDto> inmoblesVenedor = inmoblesFinder.inmoblesVenedorRang(usuari, 0, 10);
		        
				Iterator<InmobleItemDto> inmoblesVenedorIt = inmoblesVenedor.iterator();
				while (inmoblesVenedorIt.hasNext())
				    {
						InmobleItemDto inmoble = inmoblesVenedorIt.next();
							
						InmobleCaract valorsCaract = inmoblesFinder.valorsCaracteristiquesInmoble(inmoble.getInmobleKey());
						
						valorsCaract.setKeyInmoble(inmoble.getInmobleKey());
						
						listDataRow.add(valorsCaract);
				    }
		    	
		    	
				inmoblesVenedorCaract = listDataRow;
				
			}
		}
		
		return inmoblesVenedorCaract;*/
    	 
    }
	
    
    

	@Override
	public List<InmobleItemDto> findInmobles() {
		Query query = entityManager
                .createQuery("select new com.insacosa.presentation.InmobleItemDto("
                        + "o.id, o.totalCost, o.submitDate, o.status) from Inmobles o");
        return query.getResultList();
	}

	@Override
	public Inmobles inmoblePerKey(String keyInmoble) {
		
		Inmobles inmoble = null;
		  	
		try {      
				inmoble = entityManager.find( Inmobles.class, keyInmoble);
			} 
		catch (RuntimeException e) {
			    throw e; // or display error message
		}
		finally {
			    //em.close();
		}   
	
		return inmoble;  
	}
	
	
	@Override
	public List<Inmobles> inmoblesSolicitatsPerUsuari(Long usuariId)
	{
	return null;
	
	}

	@Override
	public List<Inmobles> buscarInmobles(Inmobles condicioInmoble) {
		
		/*
		Inmobles inmoble = new Inmobles();
		Example example = Example.create(inmoble)
		    .excludeZeroes()           //exclude zero valued properties
		    //.excludeProperty("color")  //exclude the property named "color"
		    .ignoreCase()              //perform case insensitive string comparisons
		    .enableLike();             //use like for string comparisons
		
		*/
		List<Inmobles> ret = new ArrayList<Inmobles>();
		
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		  
		try {      

	
			/*	
				ret = session.createCriteria(Inmobles.class)
					.add(Restrictions.like("nom", "%" + condicioInmoble.getNom() + "%"))
					.add(Restrictions.like("adreca", "%" + condicioInmoble.getAdreca() + "%"))
					///////.add(Restrictions.like("puerta", "%" + condicioInmoble.getPuerta() + "%"))
					.add(Expression.eq("tipus",condicioInmoble.getTipus()))
					.add(example)
					.createCriteria("ciutats","ciutat")
					.add(Expression.eq("ciutat.id",condicioInmoble.getCiutats().getId()))
					.add(Expression.eq("ciutat.idProv",condicioInmoble.getCiutats().getIdProv()))	
					.list();
					
				Iterator<Inmobles> it = ret.iterator();
					
				while (it.hasNext())
					{
						inmoble = it.next();
						
						inmoble.getFotoses().size(); // will make hibernate initialize the collection for you instead of the proxy
						inmoble.getSolicitudses().size(); // will make hibernate initialize the collection for you instead of the proxy
						inmoble.getCiutats().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
						inmoble.getProvincies().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
						inmoble.getTipus().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
						inmoble.getCaracteristiqueses().size();  // will make hibernate initialize the collection for you instead of the proxy
					}
				*/	
				tx.commit();    
				 
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		 
			  
		
		return ret;
	}


	@Override
	public Inmobles detallInmoble(String keyInmoble) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<InmobleItemDto> inmoblesVenedorRang(UsuariItemDto usuari,
			int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public InmobleCaract valorsCaracteristiquesInmoble(Object inmobleKey) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Inmobles> inmoblesSolicitats(Usuaris usuariVenedor) {
		
		List<Inmobles> ret = new ArrayList<Inmobles>();
		
		  
		try {   
	
				// JPA Criteria API	
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				
				CriteriaQuery<Inmobles> cq = cb.createQuery(Inmobles.class);
		        Root<Inmobles> inmobles = cq.from(Inmobles.class);
		        
		        /////ret = (List<Inmobles>) cq.select(inmobles);
		        
		        
		        //
		        TypedQuery<Inmobles> tq = entityManager.createQuery(cq);
		        ret = tq.getResultList();

			 
		} catch (RuntimeException e) {

		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		
		return ret;
		
	}
	
	
	
	public List<Inmobles> inmoblesSolicitatsPerUsuari(Usuaris usuariComprador) {
		
		List<Inmobles> ret = new ArrayList<Inmobles>();
		  
		EntityManager em = this.getEntityManager();

		try {      

			/*
			Criteria criteria = session.createCriteria(Solicituds.class)
				.setProjection(Projections.property("inmobles"))
				.add(Expression.eq("usuaris",usuariComprador))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			ret = criteria.list();
			
			Iterator<Inmobles> it = ret.iterator();
			
			while (it.hasNext())
			{
				Inmobles inmoble = it.next();
				
				}
			
		*/
			
	   
			
			 
		} catch (RuntimeException e) {
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}	
		return ret;
		
	}



	@Override
	public String tipusColumnaCaract(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String tipusColumnaCaract(Key idCaract) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Inmobles> inmoblesVenedor(UsuariItemDto usuari) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
