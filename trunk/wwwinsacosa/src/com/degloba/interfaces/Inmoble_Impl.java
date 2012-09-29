package com.degloba.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//import org.hibernate.Criteria;
//import org.hibernate.HibernateException;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.criterion.Criterion;
//import org.hibernate.criterion.Example;
//import org.hibernate.criterion.Expression;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;

import com.google.common.collect.Maps;

import com.degloba.dataModels_JPA.InmobleCaract;
import com.degloba.HBM.*;
import com.degloba.utils.SessionFactoryUtil;


public class Inmoble_Impl extends Objecte implements Inmoble_If {


	public Inmoble_Impl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public Tipus tipusInmoble(Integer idInmoble) {
		// TODO Auto-generated method stub
		
		Tipus ret = null;
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			
			Criteria criteria = session.createCriteria(Inmobles.class)
			.add(Expression.eq("id",idInmoble))
			.setProjection(Projections.property("tipus"));
			
			ret = (Tipus) criteria.uniqueResult();
		
			
			tx.commit();  
			
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;
		
	}
	
	
	@Override
	public Objecte objectePerId(int id) {
		// TODO Auto-generated method stub
		
		Objecte objecte = null;
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				objecte = (Objecte) session.get(Inmobles.class , id);
				
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}
		return objecte;  
	}
	
	
	
	

	@Override
	public Inmobles inmoblePerId(int idInmoble) {
		// TODO Auto-generated method stub
		
		Inmobles inmoble = null;
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				inmoble = (Inmobles) session.get(Inmobles.class , idInmoble);
				
				inmoble.getFotoses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getSolicitudses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getCiutats().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getProvincies().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getTipus().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getCaracteristiqueses().size();  // will make hibernate initialize the collection for you instead of the proxy
				
				
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}
		return inmoble;  
	}
	
	

	@Override
	public Inmobles afegirInmoble(Inmobles inmoble) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;  
		
		SessionFactory sf = SessionFactoryUtil.getInstance();
		Session session = sf.getCurrentSession();
		
		try {      
				tx = session.beginTransaction();   
				
				
				// 1. Afegim abans totes les coleccions (que conté l'inmoble : caracteristiques,fotos)
				// -----------------------------------------------------------------------------------
				// caracteristiques
				
				/*Iterator it = inmoble.getCaracteristiqueses().iterator();
				
				while (it.hasNext())
				{
					
					 Caractinmobles c3 = (Caracteristiques) it.next();  // caracteristica inmoble
					 
					 CaractinmoblesId cid = new CaractinmoblesId();  
					 
					 //Caracteristiques cc3 = new Caracteristiques();
					 //cc3.setId(c3.getId_1()); // id de la caracteristica
					///// c3.setCaracteristiques(cc3);
					 
					 // PK es composta
					 cid.setIdcaract(c3.getId().getIdcaract());
					 cid.setIdinmoble(inmoble.getId());
					 
					 c3.setId(cid);
					 
					 Caracteristiques cc3 = new Caracteristiques();
					 cc3.setId(c3.getId().getIdcaract());
					 inmoble.getCaracteristiqueses().add(cc3);
					
				}
				*/
				
				// fotos
				
				Iterator it = inmoble.getFotoses().iterator();
				
				while (it.hasNext())
				{
				
					Fotos foto = (Fotos) it.next();
					foto.setInmobles(inmoble);
					session.save(foto);
				}
				
				
				// 2. Salvem a la BD l'inmoble
				// ---------------------------
				session.save(inmoble); 	
				
				
				tx.commit();
				
			} 
		catch (RuntimeException e) 
			{     
			
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
						catch (HibernateException e1) 
						{       
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
		
		return inmoble;
	}

	@Override
	public void modificarInmoble(Inmobles inmoble) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;  
		
		SessionFactory sf = SessionFactoryUtil.getInstance();
		Session session = sf.getCurrentSession();
		
		try {      
				tx = session.beginTransaction();   


				session.update(inmoble);      
				tx.commit();    
				} 
		catch (RuntimeException e) 
			{     
			
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
						catch (HibernateException e1) 
						{       
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
		
	}

	
	@Override
	public void eliminarInmoble(Inmobles inmoble) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				session.delete(inmoble);      
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well    
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
		
		
	}
	
	
	
	

	@Override
	public List<Inmobles> buscarInmobles(Inmobles condicioInmoble) {
		// TODO Auto-generated method stub
		
		Inmobles inmoble = new Inmobles();
		Example example = Example.create(inmoble)
		    .excludeZeroes()           //exclude zero valued properties
		    //.excludeProperty("color")  //exclude the property named "color"
		    .ignoreCase()              //perform case insensitive string comparisons
		    .enableLike();             //use like for string comparisons
		
		
		List<Inmobles> ret = new ArrayList<Inmobles>();
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();
				
	
				Criteria criteria;
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
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;
	}

	
	@Override
	public Caracteristiques caracteristicaCaractInmoble(Caractinmobles caractinmoble) {
	
	Caracteristiques c = null;
	Transaction tx = null;    
	Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
	try {      
			tx = session.beginTransaction();      
	
			c = (Caracteristiques) session.get(Caracteristiques.class , caractinmoble.getId().getIdcaract());
			
			tx.commit(); 
	}
	catch (RuntimeException e) 
	{      
		if (tx != null && tx.isActive()) 
		{        
			try {
				// Second try catch as the rollback could fail as well          
				tx.rollback();        
				} 
			catch (HibernateException e1) 
				{          
					System.out.println("Error rolling back transaction");
					//logger.debug("Error rolling back transaction");        
				}
				// throw again the first exception        
				throw e;      
		}    
	}
	
	return c;
	}
	

	@Override
	public Inmobles detallInmoble(int idInmoble) {
		// TODO Auto-generated method stub
		
		Inmobles inmoble = null;
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				inmoble = (Inmobles) session.get(Inmobles.class , idInmoble);
				
				inmoble.getFotoses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getSolicitudses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getCiutats().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getProvincies().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getTipus().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getCaracteristiqueses().size();  // will make hibernate initialize the collection for you instead of the proxy
				
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}
		return inmoble;  
		
	}

	@Override
	public void solicitarInmobles(Solicituds solicitud) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;  
		
		SessionFactory sf = SessionFactoryUtil.getInstance();
		Session session = sf.getCurrentSession();
		
		try {      
				tx = session.beginTransaction();      
				session.save(solicitud);      
				tx.commit();    
				} 
		catch (RuntimeException e) 
			{     
			
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
						catch (HibernateException e1) 
						{       
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
	}

	@Override
	public List<Inmobles> inmoblesSolicitats(Usuaris usuariVenedor) {
		// TODO Auto-generated method stub
		
		
		List<Inmobles> ret = new ArrayList<Inmobles>();
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			
				Criteria criteria;
				criteria = session.createCriteria(Inmobles.class)
					.add(Expression.eq("usuaris",usuariVenedor));
				
				ret = criteria.list();
		
			
			tx.commit();  
			
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;
		
	}
	
	
	@Override
	public List<Inmobles> inmoblesSolicitatsPerUsuari(Usuaris usuariComprador) {
		// TODO Auto-generated method stub
		
		
		List<Inmobles> ret = new ArrayList<Inmobles>();
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			
			Criteria criteria = session.createCriteria(Solicituds.class)
				.setProjection(Projections.property("inmobles"))
				.add(Expression.eq("usuaris",usuariComprador))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			ret = criteria.list();
			
			Iterator<Inmobles> it = ret.iterator();
			
			while (it.hasNext())
			{
				Inmobles inmoble = it.next();
				
				inmoble.getFotoses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getSolicitudses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getCiutats().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getProvincies().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getTipus().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
				inmoble.getCaracteristiqueses().size();  // will make hibernate initialize the collection for you instead of the proxy
			}
			
		
			
			tx.commit();    
			
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;
		
	}
	
	
	
	@Override
	public List<Inmobles> inmoblesVenedorRang(Usuaris usuari, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		List<Inmobles> ret = new ArrayList<Inmobles>();
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			
				Criteria criteria;
				criteria = session.createCriteria(Inmobles.class)
					.add(Expression.eq("usuaris",usuari));
				
				criteria.setFirstResult(firstResult);
				criteria.setMaxResults(maxResults);
				
				ret = criteria.list();
				
				Iterator<Inmobles> it = ret.iterator();
				
				while (it.hasNext())
				{
					Inmobles inmoble = it.next();
					
					inmoble.getFotoses().size(); // will make hibernate initialize the collection for you instead of the proxy
					inmoble.getSolicitudses().size(); // will make hibernate initialize the collection for you instead of the proxy
					inmoble.getCiutats().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
					inmoble.getProvincies().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
					inmoble.getTipus().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
					inmoble.getCaracteristiqueses().size();  // will make hibernate initialize the collection for you instead of the proxy
					
				}
				
			
			tx.commit();    
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;
		
	}
	
	

	@Override
	public List<Inmobles> inmoblesVenedor(Usuaris usuari) {
		// TODO Auto-generated method stub
		List<Inmobles> ret = new ArrayList<Inmobles>();
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			
				Criteria criteria;
				criteria = session.createCriteria(Inmobles.class)
					.add(Expression.eq("usuaris",usuari));
				//////.addOrder( Order.asc(ordre));  // class interfaces.scrObject
				
				ret = criteria.list();
				
				Iterator<Inmobles> it = ret.iterator();
				
				while (it.hasNext())
				{
					Inmobles inmoble = it.next();
					
					inmoble.getFotoses().size(); // will make hibernate initialize the collection for you instead of the proxy
					inmoble.getSolicitudses().size(); // will make hibernate initialize the collection for you instead of the proxy
					inmoble.getCiutats().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
					inmoble.getProvincies().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
					inmoble.getTipus().getInmobleses().size(); // will make hibernate initialize the collection for you instead of the proxy
					inmoble.getCaracteristiqueses().size();  // will make hibernate initialize the collection for you instead of the proxy
					
				}
				
			
			tx.commit();    
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;
		
	}

	@Override
	public List<Inmobles> inmoblesTipus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afegirFoto(Fotos foto) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;  
		
		SessionFactory sf = SessionFactoryUtil.getInstance();
		Session session = sf.getCurrentSession();
		
		try {      
				tx = session.beginTransaction();      
				session.save(foto);      
				tx.commit();    
				} 
		catch (RuntimeException e) 
			{     
			
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
						catch (HibernateException e1) 
						{       
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
		
		
	}

	@Override
	public void modificarFoto(Fotos foto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarFoto(Fotos foto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Fotos> fotosInmoble(Inmobles inmoble) {
		// TODO Auto-generated method stub
		
		
		List<Fotos> ret = new ArrayList<Fotos>();
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			Query query = session.createQuery("1");
				
			ret = session.createCriteria(Fotos.class)
				.add(Expression.eq("inmobles",inmoble))
				.list();

						
			tx.commit();    
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;
	}




	@Override
	public List<Usuaris> solicitantsInmoble(Inmobles inmoble) {
		// TODO Auto-generated method stub
		
		List<Usuaris> nomsUsuaris = new ArrayList<Usuaris>();
		List<Usuaris> ret = new ArrayList<Usuaris>();
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
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
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;
		
	}


	@Override
	public List<Caracteristiques> caractTipus(Tipus tipus) {
		// TODO Auto-generated method stub
		
		List<Caracteristiques> ret = new ArrayList<Caracteristiques>();
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			Criteria criteria;
			criteria = session.createCriteria(Caracteristiques.class)
			.add(Expression.eq("tipus",tipus));
			
			
			ret = criteria.list();
			
			tx.commit();    
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;

	}






	@Override
	public List<Caracteristiques> caractTipus(Tipus tipus, Integer control, Boolean inclouCaractComu) {
		// TODO Auto-generated method stub
		
		List<Caracteristiques> ret = new ArrayList<Caracteristiques>();
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			Criteria criteria;
			
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
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;

	}




	@Override
	public void eliminarSolicitud(Solicituds solicitud) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				session.delete(solicitud);      
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well    
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
		
	}


	/*
	 * Retorna la llista d'Objectes
	 * Parametres : Classe , Ordre, condicio/criteri 
	 */
	public List<Objecte> llistaObjectes(Class classe, String ordre, String condicio) {
		// TODO Auto-generated method stub
		
		List<Objecte> ret = null;
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			if (condicio != "")
			{
				
				Query criteria = session.createQuery(condicio);
				
				ret = criteria.list();
				
				
			}
			else
			{
				Criteria criteria;
				criteria = session.createCriteria(classe)
				.addOrder( Order.asc(ordre));  // class interfaces.Objecte
				
				ret = criteria.list();
			}
			
			tx.commit();    
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
					
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;
		
	}


	@Override
	public Provincies provinciaPerId(int idProvincia) {
		// TODO Auto-generated method stub
		
		Provincies provincia = null;
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				provincia = (Provincies) session.get(Provincies.class , idProvincia);
				
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}
		return provincia;  
	}


	@Override
	public Ciutats ciutatPerId(int idCiutat) {
		// TODO Auto-generated method stub
		Ciutats ciutat = null;
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				ciutat = (Ciutats) session.get(Ciutats.class , idCiutat);
				
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}
		return ciutat;  
	}


	@Override
	public List<Ciutats> ciutatsProvincia(Provincies provincia) {
		// TODO Auto-generated method stub
		
		List<Ciutats> ciutats = null;
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();  
				
				Criteria criteria = session.createCriteria(Ciutats.class)
				.add(Expression.eq("idProv", provincia.getId()));
				
				ciutats = criteria.list();
				
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}
		return ciutats;  
	}


	
	
	
	
	/*
	 * Retorna el tipus de COLUMNA (VCHR,INT,DBL,DATE,,..) d'una caracteristica en concret
	 * Exemple : metres --> integer, adreça --> string, preu --> double , ...
	 */
	@Override
	public String tipusColumnaCaract(Long idCaract) {
		// TODO Auto-generated method stub
		
		String ret = null;
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Caracteristiques.class)
			.add(Restrictions.eq("id",idCaract))
			.setProjection(Projections.distinct(Projections.property("ttpbasic.ktpbasic"))); 
			
			Object r = criteria.uniqueResult();
			
			if (r !=null)
				ret = r.toString();

			tx.commit();    
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					///////////throw e;      
					}    
			}  
		
		return ret;

	}


	@Override
	public String tipusColumnaCaract(String nomCaract) {
		// TODO Auto-generated method stub
		
		String ret = null;
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Caracteristiques.class)
			.add(Restrictions.eq("nom",nomCaract))
			.setProjection(Projections.distinct(Projections.property("ttpbasic.ktpbasic"))); 
			
			Object r = criteria.uniqueResult();
			
			if (r !=null)
				ret = r.toString();

			tx.commit();    
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					///////////throw e;      
					}    
			}  
		
		return ret;

	}

	

	/*
	 * Retorna el tipus de CONTROL UI (ITXT,SELT,IRAD,FILE,,..) d'una caracteristica en concret
	 * Exemple : metres --> ITXT, provincia --> SELT , ...
	 */
	@Override
	public String tipusControlUICaract(int idCaract) {
		// TODO Auto-generated method stub
		
		String ret = null;
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Caracteristiques.class)
			.add(Restrictions.eq("id",idCaract))
			.setProjection(Projections.distinct(Projections.property("ttpcontrol.ktpcontrol"))); 
			
			ret = criteria.uniqueResult().toString();

			tx.commit();    
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;

	}

	@Override
	public String tipusControlUICaract(String nomCaract) {
		// TODO Auto-generated method stub
		
		String ret = null;
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Caracteristiques.class)
			.add(Restrictions.eq("nom",nomCaract))
			.setProjection(Projections.distinct(Projections.property("ttpcontrol.ktpcontrol"))); 
			
			ret = criteria.uniqueResult().toString();

			tx.commit();    
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;

	}


	@Override
	public void afegirValorCaract(
			ValuesCaracteristiques valorCaracteristica) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;  
		
		SessionFactory sf = SessionFactoryUtil.getInstance();
		Session session = sf.getCurrentSession();
		
		try {      
				tx = session.beginTransaction();   
				
				
				// 2. Salvem a la BD l'inmoble
				// ---------------------------
				session.save(valorCaracteristica); 	
				
				
				tx.commit();
				
			} 
		catch (RuntimeException e) 
			{     
			
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
						catch (HibernateException e1) 
						{       
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
	}


	
	@Override
	public Caracteristiques caractPerId(Long idCaract) {
		// TODO Auto-generated method stub
		
		
		Caracteristiques caracteristica = null;
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				caracteristica = (Caracteristiques) session.get(Caracteristiques.class , idCaract);
				
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}
		return caracteristica; 

	}

	
	@Override
	public void afegirCaractInmoble(Caractinmobles caractinmoble) {
		// TODO Auto-generated method stub
			
			Transaction tx = null;  
			
			SessionFactory sf = SessionFactoryUtil.getInstance();
			Session session = sf.getCurrentSession();
			
			try {      
					tx = session.beginTransaction();   
					
					
					// 2. Salvem a la BD l'inmoble
					// ---------------------------
					session.save(caractinmoble); 	
					
					
					tx.commit();
					
				} 
			catch (RuntimeException e) 
				{     
				
					if (tx != null && tx.isActive()) 
					{        
						try {
							// Second try catch as the rollback could fail as well          
							tx.rollback();        
							} 
							catch (HibernateException e1) 
							{       
								System.out.println("Error rolling back transaction");
								//logger.debug("Error rolling back transaction");        
							}
							// throw again the first exception        
							throw e;      
					}    
				}  
		
	}



	@Override
	public InmobleCaract valorsCaracteristiquesInmoble(
			Integer idInmoble) {
		// TODO Auto-generated method stub
		
		
		
		List<?> list = null;
		Transaction tx = null;  
		
		SessionFactory sf = SessionFactoryUtil.getInstance();
		Session session = sf.getCurrentSession();
		
		try {      
				tx = session.beginTransaction();   

				Inmobles inmoble = new Inmobles();
				inmoble.setId(idInmoble);
				
				Criteria criteria = session.createCriteria(ValuesCaracteristiques.class)
				.add(Expression.eq("inmobles",inmoble))
				.setProjection(
					Projections.projectionList()
				     	.add(Projections.property("caracteristiques.id"))
				     	.add(Projections.property("value"))
				     );
				
				
				list = criteria.list();
				
				tx.commit();
				
			} 
		catch (RuntimeException e) 
			{     
			
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
						catch (HibernateException e1) 
						{       
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}
		
	
		
	
		//----------------------------------------------------------------------
		// CUIDADO !!!!! AIXO ES ESPECIFIC DE LA IMPLEMENTACIO TENINT EN COMPTE
		// QUE CADA FILA DE LA TAULA ES UN inmobleCaract
		//----------------------------------------------------------------------
		// tipus de l'inmoble
		Tipus tipus = tipusInmoble(idInmoble);
		
		
		// construim la row amb totes les caracteristiques segons l'inmoble
		HashMap<Long, String> rowInmoble = Maps.newHashMap();
		
		// caracteristiques pel tipus
		Iterator it = caractTipus(tipus).iterator();
		while (it.hasNext())
		{
			Caracteristiques c = (Caracteristiques) it.next();
			rowInmoble.put(c.getId(), "");  // <idCaract,valor> . Inicialitzem
			
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
		rowInmobleMesId.setIdInmoble(idInmoble);
		rowInmobleMesId.setCaractInmobles(rowInmoble);
		
		return rowInmobleMesId;
	}


	@Override
	public List<Caracteristiques> allCaract() {
		// TODO Auto-generated method stub
		
		List<Caracteristiques> ret = new ArrayList<Caracteristiques>();
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			Criteria criteria;
			criteria = session.createCriteria(Caracteristiques.class);
			
			ret = criteria.list();
			
			tx.commit();    
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;

	}


	@Override
	public void eliminarValorCaract(Long idCaracteristica, int idInmoble ) {
		// TODO Auto-generated method stub
		
		
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();   
	
				ValuesCaracteristiques vc = new ValuesCaracteristiques();
				ValuesCaracteristiquesId vcId = new ValuesCaracteristiquesId();
				
				vcId.setIdcaracteristica(idCaracteristica);
				vcId.setIdinmoble(idInmoble);
				
				vc.setId(vcId);
				
				session.delete(vc);
				
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well    
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
		
		
	}


	@Override
	public void modificarValorCaract(Long idCaracteristica, int idInmoble, String value) {
		// TODO Auto-generated method stub
		
		
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();   

				ValuesCaracteristiques vc = new ValuesCaracteristiques();
				ValuesCaracteristiquesId vcId = new ValuesCaracteristiquesId();
				
				vcId.setIdcaracteristica(idCaracteristica);
				vcId.setIdinmoble(idInmoble);
				
				vc.setId(vcId);
				vc.setValue(value);
				
				session.update(vc);
				
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well    
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
		
		
	}






	

}
