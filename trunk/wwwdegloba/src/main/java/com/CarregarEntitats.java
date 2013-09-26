package com;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import com.degloba.EMF;
import com.degloba.domain.Blog;
import com.degloba.domain.Document;
import com.degloba.domain.Framework;
import com.degloba.domain.Menubar;
import com.degloba.domain.Modalpanel;
import com.degloba.domain.TipusFramework;
import com.degloba.domain.Wizard;
import com.google.appengine.api.datastore.Entity;

import scala.actors.threadpool.Arrays;

public class CarregarEntitats {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		c("tipusframework");
		c("framework");			
		
	}
	
	
	
	/**
	 * @param fitxer
	 */
	private static void c(String fitxer) {
		
		InputStream    fis;
		BufferedReader br;
		String         line;
		Integer		   numlin = 0;
		List<String>	   columnes = null;
		List<String>	   valors = null;
		
		EntityManager em = null;
		
		try
		{
			
		em = EMF.get().createEntityManager();
		
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
				
				switch(fitxer) {
				
					case "framework":
						
						// Creem entitat Google DataStore
						Framework f = new Framework();
						
						f.setDescripcio(hm.get("descripcio"));
						f.setIcon(hm.get("icon"));
						f.setNom(hm.get("nom"));
						f.setTecnologia(hm.get("tecnologia"));
						f.setUrl(hm.get("url"));
						
						////////TipusFramework tipusframework = em.find(TipusFramework.class, arg1)
						/////////f.setTipusframework(tipusframework);
						
						// Persistim						
						em.persist(f);
					    
						break;
						
					case "tipusframework":
						
						TipusFramework e = new TipusFramework();
						e.setNom(hm.get("nom"));
						
						// Persistim						
						em.persist(e);
												
						break;
						
					case "blog":
						
						Blog b = new Blog();
						
						b.setDescripcio(hm.get("descripcio"));
						b.setTitol(hm.get("titol"));
						
						// Persistim						
						em.persist(b);
												
						break;
						
					case "document":
						
						Document d = new Document();
						
						d.setAbrev(hm.get("abrev"));
						d.setDescripcio(hm.get("descripcio"));
						d.setEntorn(hm.get("entorn"));
						d.setEsroot(Boolean.parseBoolean(hm.get("esroot")));
						d.setIdanterior(Integer.parseInt(hm.get("idanterior")));
						d.setIdarbre(Integer.parseInt(hm.get("idarbre")));
						d.setOrdre(Integer.parseInt(hm.get("ordre")));
						d.setTecnologia(hm.get("tecnologia"));
						
						// Persistim						
						em.persist(d);
						
						break;
						
					case "menubar":
						
						// Menubar
						Menubar m = new Menubar();
						m.setAbrev1(hm.get("abrev1"));
						m.setAbrev2(hm.get("abrev2"));
						m.setAbrev3(hm.get("abrev3"));
						//////////////m.setIdmenuitem(idmenuitem);
						
						// Persistim						
						em.persist(m);
						
						break;
						
					case "modalpanel":
						
						//ModalPanel
						Modalpanel mp = new Modalpanel();
						
						mp.setDescripcio(hm.get("descripcio"));
						//mp.setModalpanelid(modalpanelid);
						
						// Persistim						
						em.persist(mp);
						
						break;
						
					case "wizard":
						
						//Wizard
						
						Wizard w = new Wizard();
						
						w.setAbrev(hm.get("abrev"));
						w.setDescripcio(hm.get("descripcio"));
						w.setEsroot(Boolean.parseBoolean(hm.get("esroot")));
						w.setIdanterior(Integer.parseInt(hm.get("idanterior")));
						w.setIdarbre(Integer.parseInt(hm.get("idarbre")));
						w.setJsp(hm.get("jsp"));
						w.setJspgrafic(hm.get("jspgrafic"));
						
						// Persistim						
						em.persist(w);
						
						break;
		
						}
			

		
				} // if

		} //while
		
		// Done with the file
		br.close();
		br = null;
		fis = null;
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		em.close();
	}

	}
}
