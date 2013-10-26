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
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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
	
	List<HashMap<String,String>> lblogs;
	List<HashMap<String,String>> ltipusframework;
	List<HashMap<String,String>> lframeworks;

	public void carrega()
	{
		
		datastore = DatastoreServiceFactory.getDatastoreService();
		
/*		lblogs = carregarCSVtoList("blog");
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
			
		llegirTipusFramework();*/
		
		ltipusframework = carregarCSVtoList("tipusframework");
		lframeworks = carregarCSVtoList("framework");
		persistir("tipusframework", null, null, ltipusframework,null);
		
		List<String> relacionsPare = new ArrayList<String>();
		relacionsPare.add("idTipus"); // llista de FKs
		persistirFilla(lframeworks, "framework", null , relacionsPare ); 
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
	private void persistir(String classePare, String classeFilla, String relacio, List<HashMap<String,String>> lpares , List<HashMap<String,String>> lfill) {
		
		
		try
		{

	
		for (HashMap<String,String> lpare : lpares) {
							

				Entity pare = new Entity(classePare);
				datastore.put(pare);
				
				Key clauPare = pare.getKey();
				
				// Afegim la Key de l'Entitat a la 
				lpare.put("clauGoogle", KeyFactory.keyToString(clauPare));
						
				pare = datastore.get(clauPare);
						
				// Construim les propietat de l'"Entitat" a partir de les columnes CSV
				Iterator it = lpare.entrySet().iterator();
				while (it.hasNext()) {
				   Map.Entry pairs = (Map.Entry)it.next();
					        						        
				   // Hem d'excloure les columnes "idXXX" 
				   if (!pairs.getKey().toString().equals(relacio) & !pairs.getKey().toString().equals("clauGoogle"))
				       {
					   pare.setProperty(pairs.getKey().toString(), lpare.get(pairs.getKey().toString()));
				       }
						
				    }
					// Persistim el pare						
					datastore.put(pare);
								
					// Tractem les entitats filles si en té
					if (classeFilla != null) 
						{
							
							// busquem els "fills" que tenen el "pareId = "
							List<HashMap<String,String>> lhmf = cerca(lfill , relacio , lpare.get(relacio));
							
							// persistim els fills
							for (HashMap<String,String> hmf : lhmf) {
																		        						        
								pare = datastore.get(pare.getKey());
							        
							    // Entitat filla
							    Entity filla = new Entity(classeFilla, pare.getKey());
							    
							    // Construim les propietat de l'"Entitat" a partir de les columnes CSV
							    Iterator it2 = hmf.entrySet().iterator();
							    while (it2.hasNext()) {
							        Map.Entry pairs2 = (Map.Entry)it2.next();
							        						        
							        // Hem d'excloure les columnes "id" i "<relacio>"
							        if (!pairs2.getKey().toString().equals("id") & !pairs2.getKey().toString().equals(relacio))
							        {
							        	filla.setProperty(pairs2.getKey().toString(), hmf.get(pairs2.getKey().toString()));
							        }

							        filla.setProperty(relacio, pare.getKey()); // lligam pare-fill
							    }
							        
								// Persistim						
								datastore.put(filla);
									
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
	
	
	
	private void persistirFilla(List<HashMap<String,String>> lfills,String classeFilla, List<String> classesPare ,List<String> relacionsPare ) {
		
		
		try
		{			
			// 
			for (HashMap<String,String> lfilla : lfills) {
				
				Entity filla = null;
				Boolean teTotElsPares = true;
				
				// per cada fill busquem els pares
				for (String relacioPare : relacionsPare)
				{
							
					// Recuperem l'entitat Pare a partir de la "Key" guardada en la List<HashMap<>>
					HashMap<String,String> p = cercaPare(ltipusframework,relacioPare,lfilla.get(relacioPare));
					if (p.size() != 0) {  //té pare
						
						Key clauPare = KeyFactory.stringToKey(p.get("clauGoogle").toString());

						
						// Entitat filla
						filla = new Entity(classeFilla);
									    
						// Construim les propietat de l'"Entitat" a partir de les columnes CSV
						Iterator it2 = lfilla.entrySet().iterator();
						while (it2.hasNext()) {
							Map.Entry pairs2 = (Map.Entry)it2.next();
									        						        
							// Hem d'excloure les columnes "id" i "<relacio>"
							if (!pairs2.getKey().toString().equals("id") & !pairs2.getKey().toString().equals(relacioPare) & !pairs2.getKey().toString().equals("clauGoogle"))
								{
								   	filla.setProperty(pairs2.getKey().toString(), lfilla.get(pairs2.getKey().toString()));
								}

							// FK
							filla.setProperty(relacioPare, clauPare); // lligam pare-fill
							}
						
					}
					else {  // no té pare
						teTotElsPares = false;
					}
					
				} //for	
					
				if (teTotElsPares)
				{
					// Persistim						
					datastore.put(filla);
				}
							
			}
							
					

		
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
	private List<HashMap<String,String>> cerca(List<HashMap<String,String>> lfills , String col , String valor)
	{
		
		List<HashMap<String,String>> lret = new ArrayList<HashMap<String,String>>();
		
		for (HashMap<String,String> filla : lfills) {
			
			if (filla.get(col).equals(valor)) lret.add(filla);
		}
		 
		return lret;
		
	}
	
	
	/**
	 * Retorna el "pare" del fill d'una llista de pares
	 * @param lpare
	 * @param relacioPare = columna que representa la clau primaria
	 * @param valor
	 * @return
	 */
	private HashMap<String,String> cercaPare(List<HashMap<String,String>> lpares, String relacioPare , String valorRelacio)
	{
		
		HashMap<String,String> ret = new HashMap<String,String>();
		
		for (HashMap<String,String> pare : lpares) {
			
			if (pare.get(relacioPare).equals(valorRelacio)) ret = pare;
		}
		 
		return ret;
		
	}
	
	
	
	private List<HashMap<String,String>> carregarCSVtoList(String fitxer) {
		
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
