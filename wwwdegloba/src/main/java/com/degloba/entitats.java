package com.degloba;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;


import com.degloba.persistencia.JPA.*;
/*import com.degloba.UtilPersistenciaGAE;
import com.degloba.JPA.UtilCriteriaBuilderJPA;*/
import com.degloba.domain.Blog;
import com.degloba.domain.Document;
import com.degloba.domain.Framework;
import com.degloba.domain.Menubar;
import com.degloba.domain.Modalpanel;
import com.degloba.domain.TipusFramework;
import com.degloba.domain.Wizard;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;

import scala.actors.threadpool.Arrays;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@ManagedBean(eager = true)
@SessionScoped
@Repository
public  class entitats implements ActionListener, Serializable {
	
	DatastoreService datastore;
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;     
	public EntityManager getEntityManager() {        
		return em;    
	}   
   
	public void setEntityManager(EntityManager entityManager) {        
		this.em = entityManager;    
		}
	

	public void carrega()
	{
		
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		List lblogs = carregarCSVtoList("blog");
		persistir("blog",lblogs,null);
		
		//List lwizard = carregarCSVtoList("wizard");
		//persistir("wizard",lwizard,null);	
		
		List ldocument = carregarCSVtoList("document");
		persistir("document",ldocument,null);
		
		List lmenubar = carregarCSVtoList("menubar");
		persistir("menubar",lmenubar,null);
				
		List lmodalpanel = carregarCSVtoList("modalpanel");
		persistir("modalpanel",lmodalpanel,null);
		
				
		List lpare = carregarCSVtoList("tipusframework");
		List lfill = carregarCSVtoList("framework");	
		persistir("tipusframework",lpare,lfill);
		//persistirFills(lpare,lfill);
			
		llegirTipusFramework();
		
	}
	
	
	
	private void llegirTipusFramework()
	{
		EntityManager em = null;
		
		try
		{
			em = EMF.get().createEntityManager();
			
			TypedQuery<TipusFramework> q2 = em.createQuery("SELECT c FROM TipusFramework c WHERE c.nom = 'Web'", TipusFramework.class);
			TipusFramework e = q2.getSingleResult();
			System.out.println("Tenim : " + e.getFrameworks().size() + " frameworks d'aquest tipus");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//em.getTransaction().commit();
	
			em.close();
		}
		
		
		
		
	}
	
	
	
	/**
	 * @param fitxer
	 */
	private void persistir(String classePare, List<HashMap<String,String>> lpare , List<HashMap<String,String>> lfill) {
		
	
		//EntityManager em = null;
		
		try
		{
			
			
			
			//em = EMF.get().createEntityManager();
			//em.getTransaction().begin();
			
	
		for (HashMap<String,String> linia : lpare) {
							
				switch(classePare) {
						
					case "tipusframework":
						
						Entity tf = new Entity("TipusFramework");
						datastore.put(tf);
										
						// Transactions on root entities
						//Transaction tx = datastore.beginTransaction();
						
						tf = datastore.get(tf.getKey());
						
					    // Construim les propietat de l'"Entitat" a partir de les columnes CSV
					    Iterator it = linia.entrySet().iterator();
					    while (it.hasNext()) {
					        Map.Entry pairs = (Map.Entry)it.next();
					        						        
					        // Hem d'excloure les columnes "idTipus" 
					        if (!pairs.getKey().toString().equals("idTipus") )
					        {
					        	tf.setProperty(pairs.getKey().toString(), linia.get(pairs.getKey().toString()));
					        }
						
					    }
						// Persistim el pare						
						datastore.put(tf);
						//tx.commit();
						
												
						// busquem els frameworks que tenen el tipusframework = 
						List<HashMap<String,String>> lhmf = cerca(lfill , "idTipus" , linia.get("idTipus"));
						
						// persistim els fills
						for (HashMap<String,String> hmf : lhmf) {
																	        						        
							//tx = datastore.beginTransaction();
						    tf = datastore.get(tf.getKey());
						        
						    // Entitat filla
						    Entity f = new Entity("Framework", tf.getKey());
						    
						    // Construim les propietat de l'"Entitat" a partir de les columnes CSV
						    Iterator it2 = hmf.entrySet().iterator();
						    while (it2.hasNext()) {
						        Map.Entry pairs2 = (Map.Entry)it2.next();
						        						        
						        // Hem d'excloure les columnes "id" i "idTipus"
						        if (!pairs2.getKey().toString().equals("id") & !pairs2.getKey().toString().equals("idTipus"))
						        {
						        	f.setProperty(pairs2.getKey().toString(), hmf.get(pairs2.getKey().toString()));
						        }

							    f.setProperty("idTipus", tf.getKey()); // lligam pare-fill
						    }
						        
							// Persistim						
							datastore.put(f);
							//tx.commit();
								
						}
						
											
						break;
						
					case "blog":
						
				/*		Blog b = new Blog();
						
						b.setDescripcio(linia.get("DESCRIPCIO"));
						b.setTitol(linia.get("TITOL"));
						
						// Persistim						
						em.persist(b);*/
						
												
						Entity b = new Entity("Blog");
						datastore.put(b);
										
					
						b = datastore.get(b.getKey());
						
					    // Construim les propietat de l'"Entitat" a partir de les columnes CSV
					    it = linia.entrySet().iterator();
					    while (it.hasNext()) {
					        Map.Entry pairs = (Map.Entry)it.next();
					        						        
					        // Hem d'excloure les columnes "idTipus" 
					        if (!pairs.getKey().toString().equals("idTipus") )
					        {
					        	b.setProperty(pairs.getKey().toString(), linia.get(pairs.getKey().toString()));
					        }
						
					    }
						// Persistim el pare						
						datastore.put(b);
																
												
						break;
						
					case "document":
						
					/*	Document d = new Document();
						
						d.setAbrev(linia.get("abrev"));
						d.setDescripcio(linia.get("descripcio"));
						d.setEntorn(linia.get("entorn"));
						d.setEsroot(Boolean.parseBoolean(linia.get("esroot")));
						d.setIdanterior(Integer.parseInt(linia.get("idanterior")));
						d.setIdarbre(Integer.parseInt(linia.get("idarbre")));
						d.setOrdre(Integer.parseInt(linia.get("ordre")));
						d.setTecnologia(linia.get("tecnologia"));
						
						// Persistim						
						em.persist(d);*/
						
						
						Entity d = new Entity("Document");
						datastore.put(d);
						
						d = datastore.get(d.getKey());
						
					    // Construim les propietat de l'"Entitat" a partir de les columnes CSV
					    it = linia.entrySet().iterator();
					    while (it.hasNext()) {
					        Map.Entry pairs = (Map.Entry)it.next();
					        						        
					        // Hem d'excloure les columnes "idTipus" 
					        if (!pairs.getKey().toString().equals("idTipus") )
					        {
					        	d.setProperty(pairs.getKey().toString(), linia.get(pairs.getKey().toString()));
					        }
						
					    }
						// Persistim el pare						
						datastore.put(d);
						
						break;
						
					case "menubar":
						
						// Menubar
					/*	Menubar m = new Menubar();
						m.setAbrev1(linia.get("ABREV1"));
						m.setAbrev2(linia.get("ABREV2"));
						m.setAbrev3(linia.get("ABREV3"));
						//////////////m.setIdmenuitem(idmenuitem);
						
						// Persistim						
						em.persist(m);*/
						
						Entity m = new Entity("Menubar");
						datastore.put(m);
						
						m = datastore.get(m.getKey());
						
					    // Construim les propietat de l'"Entitat" a partir de les columnes CSV
					    it = linia.entrySet().iterator();
					    while (it.hasNext()) {
					        Map.Entry pairs = (Map.Entry)it.next();
					        						        
					        // Hem d'excloure les columnes "idTipus" 
					        if (!pairs.getKey().toString().equals("idTipus") )
					        {
					        	m.setProperty(pairs.getKey().toString(), linia.get(pairs.getKey().toString()));
					        }
						
					    }
						// Persistim el pare						
						datastore.put(m);
						
						break;
						
					case "modalpanel":
						
						//ModalPanel
					/*	Modalpanel mp = new Modalpanel();
						
						mp.setDescripcio(linia.get("descripcio"));
						//mp.setModalpanelid(modalpanelid);
						
						// Persistim						
						em.persist(mp);*/
						
						
						Entity mp = new Entity("Modalpanel");
						datastore.put(mp);
						
						mp = datastore.get(mp.getKey());
						
					    // Construim les propietat de l'"Entitat" a partir de les columnes CSV
					    it = linia.entrySet().iterator();
					    while (it.hasNext()) {
					        Map.Entry pairs = (Map.Entry)it.next();
					        						        
					        // Hem d'excloure les columnes "idTipus" 
					        if (!pairs.getKey().toString().equals("idTipus") )
					        {
					        	mp.setProperty(pairs.getKey().toString(), linia.get(pairs.getKey().toString()));
					        }
						
					    }
						// Persistim el pare						
						datastore.put(mp);
	
						break;
						
					case "wizard":
						
						//Wizard
						
					/*	Wizard w = new Wizard();
						
						w.setAbrev(linia.get("abrev"));
						w.setDescripcio(linia.get("descripcio"));
						w.setEsroot(Boolean.parseBoolean(linia.get("esroot")));
						w.setIdanterior(Integer.parseInt(linia.get("idanterior")));
						w.setIdarbre(Integer.parseInt(linia.get("idarbre")));
						w.setJsp(linia.get("jsp"));
						w.setJspgrafic(linia.get("jspgrafic"));
						
						// Persistim						
						em.persist(w);*/
						
						Entity w = new Entity("Wizard");
						datastore.put(w);
						
						w = datastore.get(w.getKey());
						
					    // Construim les propietat de l'"Entitat" a partir de les columnes CSV
					    it = linia.entrySet().iterator();
					    while (it.hasNext()) {
					        Map.Entry pairs = (Map.Entry)it.next();
					        						        
					        // Hem d'excloure les columnes "idTipus" 
					        if (!pairs.getKey().toString().equals("idTipus") )
					        {
					        	w.setProperty(pairs.getKey().toString(), linia.get(pairs.getKey().toString()));
					        }
						
					    }
						// Persistim el pare						
						datastore.put(w);
	
						
						break;
		
						} //switch

		} //for
		

		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		//em.getTransaction().commit();

		//em.close();
	}

	}
	
	
	
	private void persistirFills(List<HashMap<String,String>> lpare , List<HashMap<String,String>> lfill)
	{
		
		EntityManager em = null;
		
		try
		{
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			//em = EMF.get().createEntityManager();
			em.getTransaction().begin();
			
		
			for (HashMap<String,String> linia : lpare) {
				
			
				
				/*UtilCriteriaBuilderJPA<TipusFramework> u = new UtilCriteriaBuilderJPA<TipusFramework>();
				u.createFilterCriteriaForField(propertyName, filterValue, root, criteriaBuilder);
				u.createFilterCriteria(criteriaBuilder, root, propertyNames, filterValue);
				u.createSelectCriteriaQuery(campsFiltre, valorsFiltre, campsOrdre, ordre);*/
				
				TypedQuery<TipusFramework> q2 = em.createQuery("SELECT c FROM TipusFramework c WHERE c.idTipus = '" + linia.get("idTipus") + "'", TipusFramework.class);
				TipusFramework e = q2.getSingleResult();
																	
				// busquem els fills que del pare en questio
				List<HashMap<String,String>> lhmf = cerca(lfill , "idTipus" , linia.get("idTipus"));
						
				// persistim els fills
						
				for (HashMap<String,String> hmf : lhmf) {
							
					Iterator it = hmf.entrySet().iterator();
				    while (it.hasNext()) {
				        Map.Entry pairs = (Map.Entry)it.next();
				        System.out.println(pairs.getKey() + " = " + pairs.getValue());
						        
						// Entitat filla
						Framework f = new Framework();
								
						f.setDescripcio(linia.get("descripcio"));
						f.setIcon(linia.get("icon"));
						f.setNom(linia.get("nom"));
						f.setTecnologia(linia.get("tecnologia"));
						f.setUrl(linia.get("url"));
						f.setTipusframework(e);  // referencia
								
							
						// Persistim						
						em.persist(f);
						        
				    }
				}
			}
		}
		catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			em.getTransaction().commit();
			em.close();
		}
					
	}
	
	
	/**
	 * Retorna una llista de HashMaps que cumpleixi que la "col" t� el "valor"
	 * @param l
	 * @param col
	 * @param valor
	 * @return
	 */
	private List<HashMap<String,String>> cerca(List<HashMap<String,String>> lfill , String col , String valor)
	{
		
		List<HashMap<String,String>> lret = new ArrayList<HashMap<String,String>>();
		
		for (HashMap<String,String> linia : lfill) {
			
			if (linia.get(col).equals(valor)) lret.add(linia);
		}
		 
		return lret;
		
	}
	
	
	private List carregarCSVtoList(String fitxer) {
		
		InputStream    fis;
		BufferedReader br;
		String         line;
		Integer		   numlin = 0;
		List<String>	   columnes = null;
		List<String>	   valors = null;
		
		List list = new ArrayList();
		
		try
		{
					
			fis = new FileInputStream(fitxer + ".csv");
			
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			while ((line = br.readLine()) != null) {
			    // Deal with the line
				
				if (numlin == 0) {
						columnes = Arrays.asList(line.split(","));
						numlin = 1;
				}
				else {
					
					numlin = numlin + 1;
					valors = Arrays.asList(line.split(","));
					HashMap<String,String> hm = new HashMap<String,String>();
									
					for (String columna : columnes) {
						
						Integer index = columnes.indexOf(columna);
						String valor = valors.get(index);
					    
					    hm.put(columna,valor);
	
					}
					
					// afegim linia
					list.add(hm);
	
				}
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
			}
		
		
		return list;
		
	}
	
	
	
	
	/**
	 * 
	 */
	private void uuuu()
	{
		/*UtilPersistenciaGAE.persistEntity(null);
		Util.listChildKeys(kind, ancestor);
		Util.listChildren(kind, ancestor);
		Util.listEntities(kind, searchBy, searchFor);
		*/
		
		
	}


	@Override
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		// TODO Auto-generated method stub
		
	}
	
	
}
