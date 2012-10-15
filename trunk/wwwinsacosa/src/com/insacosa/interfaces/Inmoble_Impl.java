package com.insacosa.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import com.degloba.EMF;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.common.collect.Maps;

import com.insacosa.dataModels_JPA.InmobleCaract;
import com.insacosa.entitats.*;



public class Inmoble_Impl extends Objecte implements Inmoble_If {


	public Inmoble_Impl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public Tipus tipusInmoble(Key keyInmoble) {
		
		Tipus ret = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      

			tx.begin();
			
			Criteria criteria = session.createCriteria(Inmobles.class)
			.add(Expression.eq("id",keyInmoble))
			.setProjection(Projections.property("tipus"));
			
			ret = (Tipus) criteria.uniqueResult();
		
			
			tx.commit();  
			 
			} finally {        
				em.close();    
			}
		
		return ret;
		
	}
	
	
	@Override
	public Objecte objectePerKey(Key key) {
		
		Objecte objecte = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
				
				objecte = em.find( Objecte.class, key);
				
				tx.commit();    
			} 
		finally {        
			em.close();    
		}   
			
		return objecte;  
	}
	


	@Override
	public Inmobles inmoblePerKey(Key keyInmoble) {
		
		Inmobles inmoble = null;
		  
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
				
				inmoble = em.find( Inmobles.class, keyInmoble);
				
				tx.commit();    
			 
			} finally {        
				em.close();    
		}
		return inmoble;  
	}
	
	

	@Override
	public Inmobles afegirInmoble(Inmobles inmoble) {
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
				
				// fotos
				
				Iterator it = inmoble.getFotoses().iterator();
				
				while (it.hasNext())
				{
				
					Fotos foto = (Fotos) it.next();
					foto.setInmobles(inmoble);
					em.persist(foto);
				}
				
				
				em.persist(inmoble);
				
				tx.commit();
 
	} finally {        
		em.close();    
	} 
		
		return inmoble;
	}

	@Override
	public void modificarInmoble(Inmobles inmoble) {
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
				
				em.persist(inmoble);
				      
				tx.commit();    
				 
		} finally {        
			em.close();    
		}
		
	}

	
	@Override
	public void eliminarInmoble(Inmobles inmoble) {
		  
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
				
				em.remove(inmoble);
				      
				tx.commit();    
				 
		} finally {        
			em.close();    
		}

	}
	
	
	
	

	@Override
	public List<Inmobles> buscarInmobles(Inmobles condicioInmoble) {
		
		
		Inmobles inmoble = new Inmobles();
		Example example = Example.create(inmoble)
		    .excludeZeroes()           //exclude zero valued properties
		    //.excludeProperty("color")  //exclude the property named "color"
		    .ignoreCase()              //perform case insensitive string comparisons
		    .enableLike();             //use like for string comparisons
		
		
		List<Inmobles> ret = new ArrayList<Inmobles>();
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		  
		try {      
	
				tx.begin();
	
				
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
					
				tx.commit();    
				 
		} finally {        
			em.close();    
		}
		 
			  
		
		return ret;
	}

	
	@Override
	public Caracteristiques caracteristicaCaractInmoble(Caractinmobles caractinmoble) {
	
	Caracteristiques c = null;
	
	EntityManager em = EMF.get().createEntityManager();
	EntityTransaction tx = em.getTransaction();
	  
	try {      
 
			tx.begin();
			
			c = em.find(Caracteristiques.class, caractinmoble.getKey());
			
			tx.commit(); 
			 
	} finally {        
		em.close();    
	}
	
	return c;
	}
	

	@Override
	public Inmobles detallInmoble(Key keyInmoble) {
		
		Inmobles inmoble = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		  
		try {      
				inmoble = em.find( Inmobles.class, keyInmoble);
				
				tx.commit();    
				 
		} finally {        
			em.close();    
		}
		return inmoble;  
		
	}

	@Override
	public void solicitarInmobles(Solicituds solicitud) {
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
				
				em.persist(solicitud);
				      
				tx.commit();    
				 
		} finally {        
			em.close();    
		}
	}

	
	@Override
	public List<Inmobles> inmoblesSolicitats(Usuaris usuariVenedor) {
		
		List<Inmobles> ret = new ArrayList<Inmobles>();
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		  
		try {   
				tx.begin();
				
				CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
				CriteriaQuery<Inmobles> criteriaQuery = criteriaBuilder.createQuery(Inmobles.class);
		        Root<Inmobles> inmobles = criteriaQuery.from(Inmobles.class);
		        
		        ret = (List<Inmobles>) criteriaQuery.gefrom(Inmobles.class).;
		
			
			tx.commit();  
			
			 
		} finally {        
			em.close();    
		}
		
		return ret;
		
	}
	
	
	@Override
	public List<Inmobles> inmoblesSolicitatsPerUsuari(Usuaris usuariComprador) {
		
		List<Inmobles> ret = new ArrayList<Inmobles>();
		  
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
			
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
			
		
			
			tx.commit();    
			
			 
		} finally {        
			em.close();    
		}	
		return ret;
		
	}
	
	
	
	@Override
	public List<Inmobles> inmoblesVenedorRang(Usuaris usuari, int firstResult, int maxResults) {

		List<Inmobles> ret = new ArrayList<Inmobles>();
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		  
		try {      

				tx.begin();
			
				
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
				
			
			tx.commit();    
			 
		} finally {        
			em.close();    
		}
		return ret;
		
	}
	
	

	@Override
	public List<Inmobles> inmoblesVenedor(Usuaris usuari) {

		List<Inmobles> ret = new ArrayList<Inmobles>();
		  
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
			
				
				criteria = session.createCriteria(Inmobles.class)
					.add(Expression.eq("usuaris",usuari));
				
				ret = criteria.list();
				
				Iterator<Inmobles> it = ret.iterator();
				
				while (it.hasNext())
				{
					Inmobles inmoble = it.next();
						
				}
				
			
			tx.commit();    
			 
		} finally {        
			em.close();    
		}		
		return ret;
		
	}

	@Override
	public List<Inmobles> inmoblesTipus() {
		
		return null;
	}

	@Override
	public void afegirFoto(Fotos foto) {
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {     
				tx.begin();
				
				em.persist(foto);
				      
				tx.commit();    
				 
		} finally {        
			em.close();    
		}		
		
	}

	@Override
	public void modificarFoto(Fotos foto) {
		
		
	}

	@Override
	public void eliminarFoto(Fotos foto) {
		
		
	}

	@Override
	public List<Fotos> fotosInmoble(Inmobles inmoble) {
		
		List<Fotos> ret = new ArrayList<Fotos>();
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		  
		try {      
	
				tx.begin();
				
			Query query = session.createQuery("1");
				
			ret = session.createCriteria(Fotos.class)
				.add(Expression.eq("inmobles",inmoble))
				.list();

						
			tx.commit();    
			 
		} finally {        
			em.close();    
		}		
		return ret;
	}




	@Override
	public List<Usuaris> solicitantsInmoble(Inmobles inmoble) {
		
		
		List<Usuaris> nomsUsuaris = new ArrayList<Usuaris>();
		List<Usuaris> ret = new ArrayList<Usuaris>();
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
			
			tx.begin();
			
			//Noms Usuaris solicitants d'un determinat inmoble
			String hql = "select usuaris.nomusuari from Solicituds where inmobles = :inmoble";
			Query query = session.createQuery(hql);
			query.setEntity("inmoble", inmoble); 
			
			nomsUsuaris = query.list();
			
			if (!nomsUsuaris.isEmpty())
			{
				hql = "from Usuaris where nomusuari in (:names)";
				query = session.createQuery(hql); 
				query.setParameterList("names", nomsUsuaris);
				ret = query.list();
			}
			
			tx.commit();    
			 
		} finally {        
			em.close();    
		}		
		return ret;
		
	}


	@Override
	public List<Caracteristiques> caractTipus(Tipus tipus) {
		
		List<Caracteristiques> ret = new ArrayList<Caracteristiques>();
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		  
		try {      
			
			tx.begin();
			
			criteria = session.createCriteria(Caracteristiques.class)
			.add(Expression.eq("tipus",tipus));
			
			
			ret = criteria.list();
			
			tx.commit();    
			 
		} finally {        
			em.close();    
		}		
		return ret;

	}






	@Override
	public List<Caracteristiques> caractTipus(Tipus tipus, Integer control, Boolean inclouCaractComu) {
		
		List<Caracteristiques> ret = new ArrayList<Caracteristiques>();
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
			
			tx.begin();
			
			if (inclouCaractComu)
			{
				Tipus tipus99 = new Tipus();
				tipus99.setId(99);
				criteria = session.createCriteria(Caracteristiques.class)
				.add(Restrictions.or(Expression.eq("tipus",tipus), Expression.eq("tipus",tipus99)))
				.add(Restrictions.eq("control", control));
			}
			else
			{
				criteria = session.createCriteria(Caracteristiques.class)
				.add(Expression.eq("tipus",tipus))
				.add(Restrictions.eq("control", control));
			}
			
			
			ret = criteria.list();
			
			tx.commit();    
			 
		} finally {        
			em.close();    
		}		
		return ret;

	}




	@Override
	public void eliminarSolicitud(Solicituds solicitud) {
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
				em.remove(solicitud);
				      
				tx.commit();    
				 
		} finally {        
			em.close();    
		}		
	}


	/*
	 * Retorna la llista d'Objectes
	 * Parametres : Classe , Ordre, condicio/criteri 
	 */
	public List<Objecte> llistaObjectes(Class classe, String ordre, String condicio) {
		
		List<Objecte> ret = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
			tx.begin();
			if (condicio != "")
			{
				ret = em.createQuery(condicio).getResultList();
				
			}
			else
			{
				
				criteria = session.createCriteria(classe)
				.addOrder( Order.asc(ordre));  // class interfaces.Objecte
				
				ret = criteria.list();
			}
			
			tx.commit();    
			 
		} finally {        
			em.close();    
		}		
		return ret;
		
	}


	@Override
	public Provincies provinciaPerKey(Key keyProvincia) {
		
		Provincies provincia = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
				em.find(Provincies.class, keyProvincia);
				
				tx.commit();    
				 
		} finally {        
			em.close();    
		}		return provincia;  
	}


	@Override
	public Ciutats ciutatPerKey(Key keyCiutat) {
		
		Ciutats ciutat = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
				em.find(Ciutats.class, keyCiutat);
				
				tx.commit();    
				 
		} finally {        
			em.close();    
		}		return ciutat;  
	}


	@Override
	public List<Ciutats> ciutatsProvincia(Provincies provincia) {
		
		
		List<Ciutats> ciutats = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
				
				Criteria criteria = session.createCriteria(Ciutats.class)
				.add(Expression.eq("idProv", provincia.getId()));
				
				ciutats = criteria.list();
				
				tx.commit();    
				 
		} finally {        
			em.close();    
		}		return ciutats;  
	}

	
	/*
	 * Retorna el tipus de COLUMNA (VCHR,INT,DBL,DATE,,..) d'una caracteristica en concret
	 * Exemple : metres --> integer, adreça --> string, preu --> double , ...
	 */
	@Override
	public String tipusColumnaCaract(Key keyCaract) {
		
		String ret = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		  
		try {      
			tx.begin();
			Criteria criteria = session.createCriteria(Caracteristiques.class)
			.add(Restrictions.eq("id",keyCaract))
			.setProjection(Projections.distinct(Projections.property("ttpbasic.ktpbasic"))); 
			
			Object r = criteria.uniqueResult();
			
			if (r !=null)
				ret = r.toString();

			tx.commit();    
			 
		} finally {        
			em.close();    
		}		
		return ret;

	}


	@Override
	public String tipusColumnaCaract(String nomCaract) {
			
		String ret = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		  
		try {      
			tx.begin();
			Criteria criteria = session.createCriteria(Caracteristiques.class)
			.add(Restrictions.eq("nom",nomCaract))
			.setProjection(Projections.distinct(Projections.property("ttpbasic.ktpbasic"))); 
			
			Object r = criteria.uniqueResult();
			
			if (r !=null)
				ret = r.toString();

			tx.commit();    
			 
		} finally {        
			em.close();    
		}		
		return ret;

	}

	

	/*
	 * Retorna el tipus de CONTROL UI (ITXT,SELT,IRAD,FILE,,..) d'una caracteristica en concret
	 * Exemple : metres --> ITXT, provincia --> SELT , ...
	 */
	@Override
	public String tipusControlUICaract(Key keyCaract) {
		
		String ret = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		  
		try {      
			tx.begin();
			Criteria criteria = session.createCriteria(Caracteristiques.class)
			.add(Restrictions.eq("id",keyCaract))
			.setProjection(Projections.distinct(Projections.property("ttpcontrol.ktpcontrol"))); 
			
			ret = criteria.uniqueResult().toString();

			tx.commit();    
			 
		} finally {        
			em.close();    
		}		
		return ret;

	}

	@Override
	public String tipusControlUICaract(String nomCaract) {
		
		String ret = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		  
		try {      

			tx.begin();
			Criteria criteria = session.createCriteria(Caracteristiques.class)
			.add(Restrictions.eq("nom",nomCaract))
			.setProjection(Projections.distinct(Projections.property("ttpcontrol.ktpcontrol"))); 
			
			ret = criteria.uniqueResult().toString();

			tx.commit();    
		} finally {        
			em.close();    
		}
		
		return ret;

	}


	@Override
	public void afegirValorCaract(ValuesCaracteristiques valorCaracteristica) {
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {    tx.begin();  
				em.persist(valorCaracteristica);
				
				tx.commit();
				
		} finally {        
			em.close();    
		}
	}


	
	@Override
	public Caracteristiques caractPerKey(Key keyCaract) {
		
		Caracteristiques caracteristica = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {    tx.begin();  
				caracteristica = em.find( Caracteristiques.class, keyCaract);
				
				tx.commit();    
		} finally {        
			em.close();    
		}
		return caracteristica; 

	}

	
	@Override
	public void afegirCaractInmoble(Caractinmobles caractinmoble) {
			
			EntityManager em = EMF.get().createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			try {    tx.begin();  
					em.persist(caractinmoble);
					
					tx.commit();
					
			} finally {        
				em.close();    
			}
		
	}



	@Override
	public InmobleCaract valorsCaracteristiquesInmoble(Key keyInmoble) {
		
		List<?> list = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {   tx.begin();
				Inmobles inmoble = new Inmobles();
				inmoble.setId(keyInmoble);
				
				Criteria criteria = session.createCriteria(ValuesCaracteristiques.class)
				.add(Expression.eq("inmobles",inmoble))
				.setProjection(
					Projections.projectionList()
				     	.add(Projections.property("caracteristiques.id"))
				     	.add(Projections.property("value"))
				     );
				
				
				list = criteria.list();
				
				tx.commit();
				
		} finally {        
			em.close();    
		}		
	
		
	
		//----------------------------------------------------------------------
		// CUIDADO !!!!! AIXO ES ESPECIFIC DE LA IMPLEMENTACIO TENINT EN COMPTE
		// QUE CADA FILA DE LA TAULA ES UN inmobleCaract
		//----------------------------------------------------------------------
		// tipus de l'inmoble
		Tipus tipus = tipusInmoble(keyInmoble);
		
		
		// construim la row amb totes les caracteristiques segons l'inmoble
		HashMap<Long, String> rowInmoble = Maps.newHashMap();
		
		// caracteristiques pel tipus
		Iterator it = caractTipus(tipus).iterator();
		while (it.hasNext())
		{
			Caracteristiques c = (Caracteristiques) it.next();
			rowInmoble.put(c.getKey(), "");  // <keyCaract,valor> . Inicialitzem
			
		}
		
		
		it = list.iterator();  // valors de caracteristiques
		if(!it.hasNext()){
			   System.out.println("No any data!");
		   }
		else{
		   while(it.hasNext()){
			   Object[] row = (Object[])it.next();
					   
			   rowInmoble.put((Long)row[0], (String)row[1]);
				
		   }
		 }
		 	
		InmobleCaract rowInmobleMesId = new InmobleCaract();
		rowInmobleMesId.setKeyInmoble(keyInmoble);
		rowInmobleMesId.setCaractInmobles(rowInmoble);
		
		return rowInmobleMesId;
	}


	@Override
	public List<Caracteristiques> allCaract() {
		
		List<Caracteristiques> ret = new ArrayList<Caracteristiques>();
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
			tx.begin();
			
			criteria = session.createCriteria(Caracteristiques.class);
			
			ret = criteria.list();
			
			tx.commit();    
		} finally {        
			em.close();    
		}
		
		return ret;

	}


	@Override
	public void eliminarValorCaract(Key keyCaracteristica, Key keyInmoble ) {
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      tx.begin();
				ValuesCaracteristiques vc = new ValuesCaracteristiques();
				ValuesCaracteristiquesId vcId = new ValuesCaracteristiquesId();
				
				vcId.setKeycaracteristica(keyCaracteristica);
				vcId.setIdinmoble(keyInmoble);
				
				vc.setId(vcId);
				em.remove(vc);
				
				tx.commit();    
		} finally {        
			em.close();    
		}
		
		
	}


	@Override
	public void modificarValorCaract(Key idCaracteristica, Key keyInmoble, String value) {
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
	   
			tx.begin();
				ValuesCaracteristiques vc = new ValuesCaracteristiques();
				ValuesCaracteristiquesId vcId = new ValuesCaracteristiquesId();
				
				vcId.setIdcaracteristica(idCaracteristica);
				vcId.setIdinmoble(keyInmoble);
				
				vc.setId(vcId);
				vc.setValue(value);
				em.persist(vc);
				
				tx.commit();    
				 
		} finally {        
			em.close();    
		}
		
	}

	

}
