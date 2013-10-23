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
public  class entitats implements ActionListener, Serializable {
	
	DatastoreService datastore;

	private static final long serialVersionUID = 1L;

	public void carrega()
	{
		
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		List lblogs = carregarCSVtoList("blog");
		persistir("blog",null,null, lblogs,null);
		
		//List lwizard = carregarCSVtoList("wizard");
		//persistir("wizard",null,null, lwizard,null);	
		
		List ldocument = carregarCSVtoList("document");
		persistir("document",null, null, ldocument,null);
		
		List lmenubar = carregarCSVtoList("menubar");
		persistir("menubar",null, null, lmenubar,null);
				
		List lmodalpanel = carregarCSVtoList("modalpanel");
		persistir("modalpanel",null, null, lmodalpanel,null);
		
				
		List lpare = carregarCSVtoList("tipusframework");
		List lfill = carregarCSVtoList("framework");	
		persistir("tipusframework", "framework", "idTipus", lpare,lfill);
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
	private void persistir(String classePare, String classeFilla, String relacio, List<HashMap<String,String>> lpare , List<HashMap<String,String>> lfill) {
		
		
		try
		{

	
		for (HashMap<String,String> linia : lpare) {
							

				Entity tf = new Entity(classePare);
				datastore.put(tf);
						
				tf = datastore.get(tf.getKey());
						
				// Construim les propietat de l'"Entitat" a partir de les columnes CSV
				Iterator it = linia.entrySet().iterator();
				while (it.hasNext()) {
				   Map.Entry pairs = (Map.Entry)it.next();
					        						        
				   // Hem d'excloure les columnes "idXXX" 
				   if (!pairs.getKey().toString().equals(relacio) )
				       {
				        	tf.setProperty(pairs.getKey().toString(), linia.get(pairs.getKey().toString()));
				       }
						
				    }
					// Persistim el pare						
					datastore.put(tf);
								
					// Tractem les entitats filles si en té
					if (classeFilla != null) 
						{
							
							// busquem els "fills" que tenen el "pareId = "
							List<HashMap<String,String>> lhmf = cerca(lfill , relacio , linia.get(relacio));
							
							// persistim els fills
							for (HashMap<String,String> hmf : lhmf) {
																		        						        
							    tf = datastore.get(tf.getKey());
							        
							    // Entitat filla
							    Entity f = new Entity(classeFilla, tf.getKey());
							    
							    // Construim les propietat de l'"Entitat" a partir de les columnes CSV
							    Iterator it2 = hmf.entrySet().iterator();
							    while (it2.hasNext()) {
							        Map.Entry pairs2 = (Map.Entry)it2.next();
							        						        
							        // Hem d'excloure les columnes "id" i "<relacio>"
							        if (!pairs2.getKey().toString().equals("id") & !pairs2.getKey().toString().equals(relacio))
							        {
							        	f.setProperty(pairs2.getKey().toString(), hmf.get(pairs2.getKey().toString()));
							        }

								    f.setProperty(relacio, tf.getKey()); // lligam pare-fill
							    }
							        
								// Persistim						
								datastore.put(f);
									
							}
							
						}
						

		} //for
		

		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {

	}

	}
	
	
	
	
	/**
	 * Retorna una llista de HashMaps que cumpleixi que la "col" tï¿½ el "valor"
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
