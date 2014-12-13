package com.degloba;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;



// JSF
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.ServletContext;

// Entitat domini



import com.degloba.boundedContext.modalpanel.domain.Modalpanel;
// GAE
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


// DDD
import domain.canonicalmodel.publishedlanguage.AggregateId;
import scala.actors.threadpool.Arrays;


@ManagedBean
@SessionScoped
public  class entitats implements ActionListener, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	List<HashMap<String,String>> lblogs;
	List<HashMap<String,String>> ltipusframework;
	List<HashMap<String,String>> lframeworks;
	
	private static final Logger log = Logger.getLogger(entitats.class.getName());

	public void carrega()
	{
				
		/*List<HashMap<String, String>> lblogs = carregarCSVtoList("Blog");
		persistir("Blog",null,null, lblogs,null);*7
		
		//List lwizard = carregarCSVtoList("Wizard");
		//persistir("Wizard",null,null, lwizard,null);	
		
	/*	List ldocument = carregarCSVtoList("Document");
		persistir("Document",null, null, ldocument,null);
		
		List lmenubar = carregarCSVtoList("Menubar");
		persistir("Menubar",null, null, lmenubar,null);*/
				
		ServletContext ctx =(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
		/* List<HashMap<String, String>> lblog = carregarCSVtoList(ctx.getRealPath("/") + "/dades/Blog");
		persistir("Blog",null, null, lblog,null); */
		
		List<HashMap<String, String>> lmodalpanel = carregarCSVtoList(ctx.getRealPath("/") + "/dades/Modalpanel");
		persistir("Modalpanel",null, null, lmodalpanel,null);
		
				
		/*List lpare = carregarCSVtoList("Tipusframework");
		List lfill = carregarCSVtoList("Framework");	
		persistir("Tipusframework", "Framework", "idTipus", lpare,lfill);
		//persistirFills(lpare,lfill);
			
		//llegirTipusFramework();
		
		ltipusframework = carregarCSVtoList("Tipusframework");
		lframeworks = carregarCSVtoList("Framework");
		persistir("Tipusframework", "idTipus", null, ltipusframework,null);
		
		List<String> relacionsPare = new ArrayList<String>();
		relacionsPare.add("idTipus"); // llista de FKs
		persistirFilla(lframeworks, "Framework", null , relacionsPare ); */
	}
		
	
		
	/**
	 * @param fitxer
	 */
	private void persistir(String classePare, String classeFilla, String relacio, List<HashMap<String,String>> lpares , List<HashMap<String,String>> lfilles) {
				
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		log.severe("Info log message --> DataStoreService!!!!!!!");
		
		try
		{
	
			for (HashMap<String,String> lpare : lpares) {
			
				Entity pare = new Entity(classePare);
				datastore.put(pare);
				log.warning("Persistim pare.!!!!!!!");
				
				Key clauPare = pare.getKey();
				
				// Afegim la Key de l'Entitat a la 
				lpare.put("clauGoogle", KeyFactory.keyToString(clauPare));
									
				pare = datastore.get(clauPare);
						
				// Construim les propietat de l'"Entitat" a partir de les columnes CSV
				Iterator<?> it = lpare.entrySet().iterator();
				while (it.hasNext()) {
				   Map.Entry pairs = (Map.Entry)it.next();
					        						        
				   // Hem d'excloure les columnes "idXXX" 
				   if (!pairs.getKey().toString().equals(relacio) & !pairs.getKey().toString().equals("clauGoogle"))
				       {
					   		Class<?> c = Class.forName("com.degloba.boundedContext.domain." + classePare);
					   		Field f = c.getDeclaredField(pairs.getKey().toString().toLowerCase());
					   		Class<?> outputType = f.getType();
					   		String value = lpare.get(pairs.getKey().toString());
					   		Object valorConvertit = value;
				   			
					   			if(Byte.class.equals(outputType)) {
					   				valorConvertit =  Byte.parseByte(value);
							    }
							    if(Short.class.equals(outputType)) { 
							    	valorConvertit = Short.parseShort(value);
							    }
							    if(Integer.class.equals(outputType)) {	
							    	valorConvertit = Integer.parseInt(value);
							    }
							    if(Long.class.equals(outputType)) { 
							    	valorConvertit = Long.parseLong(value);
							    }
							    if(Float.class.equals(outputType)) {
							    	valorConvertit = Float.parseFloat(value);
							    }
							    if(Double.class.equals(outputType)) {
							    	valorConvertit = Double.parseDouble(value);
							    }
					   						   		
					   		pare.setProperty(pairs.getKey().toString(), valorConvertit);
				       }
				   
					// Entity employee = /*...*/;
				   /*EmbeddedEntity embeddedContactInfo = new EmbeddedEntity();
			   		embeddedContactInfo.setProperty("aggregateId", UUID.randomUUID().toString());			   	
			   		pare.setProperty("aggregateId", embeddedContactInfo);*/
				   
				    Modalpanel m = new Modalpanel();
			   		//m.setAggregateId(AggregateId.generate());
					
			   		
				    }
					// Persistim el pare	
					log.warning("Persistim pare.!!!!!!!");
					datastore.put(pare);
								
					// Tractem les entitats filles si en t�
					if (classeFilla != null) 
						{
							
							// busquem els "fills" que tenen el "pareId = "
							List<HashMap<String,String>> lhmf = cerca(lfilles , relacio , lpare.get(relacio));
							
							// persistim els fills
							for (HashMap<String,String> hmf : lhmf) {
																		        						        
								pare = datastore.get(pare.getKey());
							        
							    // Entitat filla
							    Entity filla = new Entity(classeFilla, pare.getKey());
							    
							    // Construim les propietat de l'"Entitat" a partir de les columnes CSV
							    Iterator<?> it2 = hmf.entrySet().iterator();
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
		log.warning("Error");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {

	}

}
	
	
	
	@SuppressWarnings("unused")
	private void persistirFilla(List<HashMap<String,String>> lfills,String classeFilla, List<String> classesPare ,List<String> relacionsPare ) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		log.severe("Info log message --> DataStoreService!!!!!!!");
		
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
					if (p.size() != 0) {  //t� pare
						
						Key clauPare = KeyFactory.stringToKey(p.get("clauGoogle").toString());

						
						// Entitat filla
						filla = new Entity(classeFilla);
									    
						// Construim les propietat de l'"Entitat" a partir de les columnes CSV
						Iterator<?> it2 = lfilla.entrySet().iterator();
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
					else {  // no t� pare
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
	 * Retorna una llista de HashMaps que cumpleixi que la "col" t� el "valor"
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
		BufferedReader breader;
		String         line;
		Integer		   numlin = 0;
		List<String>	   columnes = null;
		List<String>	   valors = null;
		
		List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
		
		try
		{
			log.info("Obrim fitxer CSV " + fitxer + ".csv");
			fis = new FileInputStream(fitxer + ".csv");
			
			breader = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			while ((line = breader.readLine()) != null) {
			    // Deal with the line
				
				if (numlin == 0) {
						columnes = Arrays.asList(line.split(";"));
						numlin = 1;
				}
				else {
					
					numlin = numlin + 1;
					valors = Arrays.asList(line.split(";"));
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
			
			breader.close();
			
			} catch (Exception e) {
				
				log.severe("Error carrega fitxer CSV " + fitxer + ".csv");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				
			}
		
		
		return list;
		
	}
	
	
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		// TODO Auto-generated method stub
		
	}
	
	
}
