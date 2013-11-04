package com.insacosa.interfaces;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import com.degloba.persistencia.JPA.*;

// Google
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;


import com.insacosa.domain.*;


public class Inmoble_Impl extends UtilCriteriaBuilderJPA<Inmobles> {

	
	
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
					//foto.setInmobles(inmoble);
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
				
								tx.begin();
				 try {
				    //em.persist(b);
				    em.getTransaction().commit();
				 } finally {
				    if (em.getTransaction().isActive()) {
				         em.getTransaction().rollback();
				    }
				}
											
				
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
