package com.insacosa.interfaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.degloba.JPA.*;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.degloba.JPA.PersistenceService;

import entitats.*;


public class Inmoble_Impl extends UtilCriteriaBuilderJPA<Inmobles> implements Inmoble_If {

	static PersistenceService persistenceService;
	
	public Inmoble_Impl() {
		super();
		// TODO Auto-generated constructor stub
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		
		//La classe PersistenceService es "ApplicationScoped"
		persistenceService = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);
	
	}
	
	public Inmoble_Impl(EntityManager entityManager, Class<Inmobles> entityClass) {
		super(entityManager, entityClass);		
	}
	

	
	public Objecte objectePerKey(Key key) {
		
		Objecte objecte = null;
		
		EntityManager em = this.getEntityManager();
		
		try {      
				
				objecte = em.find( Objecte.class, key);
  
			} 
		catch (RuntimeException e) {
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
			
		return objecte;  
	}
	


	
	public Inmobles inmoblePerKey(String keyInmoble) {
		
		Inmobles inmoble = null;
		  
		EntityManager em = this.getEntityManager();
		
		try {      
				inmoble = em.find( Inmobles.class, keyInmoble);
			} 
		catch (RuntimeException e) {
			    throw e; // or display error message
		}
		finally {
			    //em.close();
		}   
	
		return inmoble;  
	}
	
	

	
	public Inmobles afegirInmoble(Inmobles inmoble) {
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      
			
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
 
	} catch (RuntimeException e) {
	    if ( tx != null && tx.isActive() ) tx.rollback();
	    throw e; // or display error message
	}
	finally {
	    //em.close();
	}
		
		return inmoble;
	}

	
	public void modificarInmoble(Inmobles inmoble) {
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {  
				em.persist(inmoble);
				      
				tx.commit();    
				 
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		
	}

	
	
	public void eliminarInmoble(Inmobles inmoble) {
		  
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {   
				em.remove(inmoble);
				      
				tx.commit();    
				 
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}

	}
	
	

	
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
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
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

		
	public Inmobles detallInmoble(String keyInmoble) {
		
		Inmobles inmoble = null;
		
		EntityManager em = this.getEntityManager();
		  
		try {      
				inmoble = em.find( Inmobles.class, keyInmoble);
				 
		} catch (RuntimeException e) {
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		
		return inmoble;  
		
	}

	
	public void solicitarInmobles(Solicituds solicitud) {
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      
					
				em.persist(solicitud);
				      
				tx.commit();    
		}		 
		catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
			    //em.close();
			}
	}

	
	
	public List<Inmobles> inmoblesSolicitats(Usuaris usuariVenedor) {
		
		List<Inmobles> ret = new ArrayList<Inmobles>();
		
		EntityManager em = this.getEntityManager();
		  
		try {   
	
				// JPA Criteria API	
				CriteriaBuilder cb = em.getCriteriaBuilder();
				
				CriteriaQuery<Inmobles> cq = cb.createQuery(Inmobles.class);
		        Root<Inmobles> inmobles = cq.from(Inmobles.class);
		        
		        /////ret = (List<Inmobles>) cq.select(inmobles);
		        
		        
		        //
		        TypedQuery<Inmobles> tq = em.createQuery(cq);
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

	
	public List<Inmobles> inmoblesTipus() {
		
		return null;
	}

	
	public void afegirFoto(Fotos foto) {
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {     
				tx = em.getTransaction();
				
				em.persist(foto);
				      
				tx.commit();    
				 
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}		
		
	}

	
	public void modificarFoto(Fotos foto) {
		
		
	}

	
	public void eliminarFoto(Fotos foto) {
		
		
	}

	
	public List<Fotos> fotosInmoble(Inmobles inmoble) {
		
		List<Fotos> ret = new ArrayList<Fotos>();
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		  
		try {      
	
				tx = em.getTransaction();
				/*
			Query query = session.createQuery("1");
				
			ret = session.createCriteria(Fotos.class)
				.add(Expression.eq("inmobles",inmoble))
				.list();

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




	
	public List<Usuaris> solicitantsInmoble(Inmobles inmoble) {
		
		
		List<Usuaris> nomsUsuaris = new ArrayList<Usuaris>();
		List<Usuaris> ret = new ArrayList<Usuaris>();
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
			
			tx = em.getTransaction();
			/*
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
			*/
			
			
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Usuaris> crit = cb.createQuery(Usuaris.class);
			Root<Usuaris> candidateRoot = crit.from(Usuaris.class);
			candidateRoot.alias("p");

			crit.select(candidateRoot);
			
			
			
			
			
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


		
	public void eliminarSolicitud(Solicituds solicitud) {
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = null;
		
		try {  
			try {
				
				tx = em.getTransaction();
				em.remove(solicitud);
				      
				tx.commit();    
				 
				} 		    
			catch (Exception e) {
				tx.rollback();
				}
			}
		finally {
			//em.close();
		}		
	}


	/*
	 * Retorna la llista d'Objectes
	 * Parametres : Classe , Ordre, condicio/criteri 
	 */
	public QueryResultList<Entity> llistaObjectes(Class classe, String ordre, String condicio) {
		
		QueryResultList<Entity> ret = null;
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      
			try {
				/*
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
				*/
				tx.commit();    
				}
			    catch (Exception e) {
			    	tx.rollback();
			    }
			}
		finally {
		    //em.close();
		}
		
		return ret;
		
	}


	
	public Provincies provinciaPerKey(String keyProvincia) {
		
		Provincies provincia = null;
		
		EntityManager em = this.getEntityManager();
		
		try {

				em.find(Provincies.class, keyProvincia);
 
			}
		finally {
				//em.close();
			}	
		
		return provincia;  
	}


	
	public Ciutats ciutatPerKey(Key keyCiutat) {
		
		Ciutats ciutat = null;
		
		EntityManager em = this.getEntityManager();
		
		try {      
				em.find(Ciutats.class, keyCiutat);	
			}
		finally {
			//em.close();
			}		
		
		return ciutat;  
	}


	
	public List<Ciutats> ciutatsProvincia(Provincies provincia) {
		
		List<Ciutats> ciutats = null;
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		   
		try {
				
				Book b = new Book();
				b.setTitle("JPA 4eva");
				
				Chapter c1 = new Chapter();
				c1.setTitle("Intro");
				c1.setNumPages(10);
				b.getChapters().add(c1);
				Chapter c2 = new Chapter();
				c2.setTitle("Configuration");
				c2.setNumPages(9);
				b.getChapters().add(c2);

				tx.begin();
				 try {
				    em.persist(b);
				    em.getTransaction().commit();
				 } finally {
				    if (em.getTransaction().isActive()) {
				         em.getTransaction().rollback();
				    }
				}
				
				Book bb= em.find(Book.class,b.getId());
				
				
				DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();    
				TransactionOptions options = TransactionOptions.Builder.withXG(true);    
				Transaction txn = datastore.beginTransaction(options);
								
				// Creem Book
				Entity b2 = new Entity("Book2");
				Key kb= KeyFactory.createKey("Book2", "Title");
				b2.setProperty("Id",KeyFactory.keyToString(kb));
				b2.setProperty("Title","JPA 4eva");
				b2.setProperty("Chapters",  new ArrayList<String>());
				
				// Creem Chapter
				Entity c12 = new Entity("Chapter2");
				c12.setProperty("Id",KeyFactory.createKeyString(kb,"Chapter2", "Title"));
				c12.setProperty("Title","Intro");
				c12.setProperty("NumPages",10);
				
				List<String> ff =  (List<String>) b2.getProperty("Chapters");
				((List<String>) b2.getProperty("Chapters")).add(c12.getProperty("Id").toString());
				 
				// Creem Chapter
				Entity c22 = new Entity("Chapter2");
				c22.setProperty("Id",KeyFactory.createKeyString(kb,"Chapter2", "Title2"));
				c22.setProperty("Title","Intro2");
				c22.setProperty("NumPages",210);
				
				
				List<String> ff2 =  (List<String>) b2.getProperty("Chapters");
				((List<String>) b2.getProperty("Chapters")).add(c22.getProperty("Id").toString());
				
				 try {
					 datastore.put(txn, b2);
					 datastore.put(txn, c12);
					 datastore.put(txn, c22);
					 txn.commit();
				 } finally {
				   // if (em.getTransaction().isActive()) {
				     //    em.getTransaction().rollback();
				   // }
				}
				
		
				/*
				
				Provincies p = em.find(Provincies.class, KeyFactory.stringToKey("agt3d3dpbnNhY29zYXIRCxIKUHJvdmluY2llcxigAQw"));
		List<Ciutats> c = provincia.getCiutats();
				Query q = em.createQuery("SELECT c FROM " + Ciutats.class.getSimpleName() + " c  where c.keyProv= :provincia ");
				q.setParameter(0, provincia.getId());
				
				
				ciutats = (List<Ciutats>)q.getResultList();

				
				tx.commit();    */
				 
				} 			    
			catch (Exception e) {
				tx.rollback();
				}
			
			
		return ciutats;  
	}

	
	/*
	 * Retorna el tipus de COLUMNA (VCHR,INT,DBL,DATE,,..) d'una caracteristica en concret
	 * Exemple : metres --> integer, adreça --> string, preu --> double , ...
	 */
	
	public String tipusColumnaCaract(Key keyCaract) {
		
		String ret = null;
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		  
		try {
			try {

				/*
				Criteria criteria = session.createCriteria(Caracteristiques.class)
				.add(Restrictions.eq("id",keyCaract))
				.setProjection(Projections.distinct(Projections.property("ttpbasic.ktpbasic"))); 
				
				Object r = criteria.uniqueResult();
				
				if (r !=null)
					ret = r.toString();
	*/
				tx.commit();    
				 
				} 			    
			catch (Exception e) {
				tx.rollback();
				}
			}
		finally {
			//em.close();
			}
		
		return ret;

	}


	
	public String tipusColumnaCaract(String nomCaract) {
			
		String ret = null;
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		  
		try {      
			try {
			
				/*
				Criteria criteria = session.createCriteria(Caracteristiques.class)
				.add(Restrictions.eq("nom",nomCaract))
				.setProjection(Projections.distinct(Projections.property("ttpbasic.ktpbasic"))); 
				
				Object r = criteria.uniqueResult();
				
				if (r !=null)
					ret = r.toString();
	*/
				tx.commit();    
				 
				} 			    
			catch (Exception e) {
				tx.rollback();
				}
			}
		finally {
			//em.close();
			}
		
		return ret;

	}

	

	/*
	 * Retorna el tipus de CONTROL UI (ITXT,SELT,IRAD,FILE,,..) d'una caracteristica en concret
	 * Exemple : metres --> ITXT, provincia --> SELT , ...
	 */
	
	public String tipusControlUICaract(Key keyCaract) {
		
		String ret = null;
		
		EntityManager em = this.getEntityManager();
		  
		try {
						
				/*
				Criteria criteria = session.createCriteria(Caracteristiques.class)
				.add(Restrictions.eq("id",keyCaract))
				.setProjection(Projections.distinct(Projections.property("ttpcontrol.ktpcontrol"))); 
				
				ret = criteria.uniqueResult().toString();
	*/
				
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Caracteristiques> crit = cb.createQuery(Caracteristiques.class);
				
				Root<Caracteristiques> candidateRoot = crit.from(Caracteristiques.class);
				
				Path<Tipus> nameField = candidateRoot.get("id");
				Predicate nameEquals = cb.equal(nameField, keyCaract);
				crit.where(nameEquals);
					
				TypedQuery<Caracteristiques> q = em.createQuery(crit);
				
				TypedQuery<String> q2 = em.createQuery("SELECT c.ttpcontrol.tipus FROM Caracteristiques AS c", String.class);
				
				List<String> ret2 = q2.getResultList();
				ret = q.getSingleResult().getTtpcontrol().getTipus();

			
			}
		finally {
			//em.close();
			}
		
		return ret;

	}

	
	public String tipusControlUICaract(String nomCaract) {
		
		String ret = null;
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		  
		try {
			try {

				/*
				Criteria criteria = session.createCriteria(Caracteristiques.class)
				.add(Restrictions.eq("nom",nomCaract))
				.setProjection(Projections.distinct(Projections.property("ttpcontrol.ktpcontrol"))); 
				
				ret = criteria.uniqueResult().toString();
	*/
				tx.commit();    
				} 			    
			catch (Exception e) {
		    	tx.rollback();
				}
			}
		finally {
			//em.close();
			}
		
		return ret;

	}


	
	public void afegirValorCaract(ValuesCaracteristiques valorCaracteristica) {
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {  
			try {
				
				em.persist(valorCaracteristica);
				
				tx.commit();
				
				} 		
				catch (Exception e) {
					tx.rollback();
				}
			}
		finally {
		    //em.close();
			}
	}

	
	public Caracteristiques caractPerKey(String keyCaract) {
		
		Caracteristiques caracteristica = null;
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {     
				caracteristica = em.find( Caracteristiques.class, keyCaract);
				
				tx.commit();    
		} finally {        
			//em.close();    
		}
		return caracteristica; 

	}

	
	
	public void afegirCaractInmoble(Caractinmobles caractinmoble) {
			
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
			
			try {
				try {
					
					em.persist(caractinmoble);
					
					tx.commit();
					
			} 			    
			catch (Exception e) {
		    	tx.rollback();
		    }
		}
	finally {
	    //em.close();
	}
		
	}



	
	public InmobleCaract valorsCaracteristiquesInmoble(String keyInmoble) {
		
		List<?> list = null;
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {   

		/*
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
				*/
				tx.commit();
				
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}		
	
		
	
		//----------------------------------------------------------------------
		// CUIDADO !!!!! AIXO ES ESPECIFIC DE LA IMPLEMENTACIO TENINT EN COMPTE
		// QUE CADA FILA DE LA TAULA ES UN inmobleCaract
		//----------------------------------------------------------------------
		// tipus de l'inmoble
		//Tipus tipus = tipusInmoble(keyInmoble);
		
		/*
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
		*/
		return null;
	}


	
	public List<Caracteristiques> allCaract() {
		
		List<Caracteristiques> ret = new ArrayList<Caracteristiques>();
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      

			
			Query q = em.createQuery("SELECT c FROM " + Caracteristiques.class.getName());
			
			
			ret = (List<Caracteristiques>)q.getResultList();
			
			    
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		
		return ret;

	}


	
	public void eliminarValorCaract(Key keyCaracteristica, String keyInmoble ) {
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      
		/*
				ValuesCaracteristiques vc = new ValuesCaracteristiques();
				//ValuesCaracteristiquesId vcId = new ValuesCaracteristiquesId();
				
				vcId.setKeycaracteristica(keyCaracteristica);
				vcId.setIdinmoble(keyInmoble);
				
				vc.setId(vcId);
				em.remove(vc);
				*/
				tx.commit();    
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		
		
	}


	
	public void modificarValorCaract(Key idCaracteristica, String keyInmoble, String value) {
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      
	   
			/*
				ValuesCaracteristiques vc = new ValuesCaracteristiques();
				ValuesCaracteristiquesId vcId = new ValuesCaracteristiquesId();
				
				vcId.setIdcaracteristica(idCaracteristica);
				vcId.setIdinmoble(keyInmoble);
				
				vc.setId(vcId);
				vc.setValue(value);
				em.persist(vc);
				*/
				tx.commit();    
				 
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		
	}

	public Provincies provinciaPerKey(Key idProvincia) {
		// TODO Auto-generated method stub
		return null;
	}

	public Objecte read(Objecte objecte) {
		// TODO Auto-generated method stub
		return null;
	}

	public int retId(String taula, String classe) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Objecte retDescripcio(Class entityName, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Objecte objecte) {
		// TODO Auto-generated method stub
		
	}

	public void create() {
		// TODO Auto-generated method stub
		
	}

	public void update(Objecte objecte) {
		// TODO Auto-generated method stub
		
	}

	
	

}
