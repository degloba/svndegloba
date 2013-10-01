

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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import com.degloba.domain.Blog;
import com.degloba.domain.Document;
import com.degloba.domain.Framework;
import com.degloba.domain.Menubar;
import com.degloba.domain.Modalpanel;
import com.degloba.domain.TipusFramework;
import com.degloba.domain.Wizard;

import scala.actors.threadpool.Arrays;

@ManagedBean
@SessionScoped
public  class entitats implements Serializable {
	
	public void carrega()
	{
				
		List lpare = carregarCSVtoList("tipusframework");
		List lfill = carregarCSVtoList("framework");	
		persistir("tipusframework",lpare,lfill);
		
		
		List lblogs = carregarCSVtoList("blog");
		persistir("blog",lblogs,null);
		
	}
	
	
	
	/**
	 * @param fitxer
	 */
	private void persistir(String classePare, List<HashMap<String,String>> lpare , List<HashMap<String,String>> lfill) {
		
	
		EntityManager em = null;
		
		try
		{
			
		////////em = EMF.get().createEntityManager();

		for (HashMap<String,String> linia : lpare) {
							
				switch(classePare) {
						
					case "tipusframework":
						
						TipusFramework e = new TipusFramework();
						e.setNom(linia.get("nom"));
												
						// Persistim el pare						
						////////////em.persist(e);
						
						// busquem els frameworks que tenen el tipusframework = �?
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
								////em.persist(f);
						        
						    }
							
						}
						
											
						break;
						
					case "blog":
						
						Blog b = new Blog();
						
						b.setDescripcio(linia.get("descripcio"));
						b.setTitol(linia.get("titol"));
						
						// Persistim						
						///////em.persist(b);
												
						break;
						
					case "document":
						
						Document d = new Document();
						
						d.setAbrev(linia.get("abrev"));
						d.setDescripcio(linia.get("descripcio"));
						d.setEntorn(linia.get("entorn"));
						d.setEsroot(Boolean.parseBoolean(linia.get("esroot")));
						d.setIdanterior(Integer.parseInt(linia.get("idanterior")));
						d.setIdarbre(Integer.parseInt(linia.get("idarbre")));
						d.setOrdre(Integer.parseInt(linia.get("ordre")));
						d.setTecnologia(linia.get("tecnologia"));
						
						// Persistim						
						em.persist(d);
						
						break;
						
					case "menubar":
						
						// Menubar
						Menubar m = new Menubar();
						m.setAbrev1(linia.get("abrev1"));
						m.setAbrev2(linia.get("abrev2"));
						m.setAbrev3(linia.get("abrev3"));
						//////////////m.setIdmenuitem(idmenuitem);
						
						// Persistim						
						em.persist(m);
						
						break;
						
					case "modalpanel":
						
						//ModalPanel
						Modalpanel mp = new Modalpanel();
						
						mp.setDescripcio(linia.get("descripcio"));
						//mp.setModalpanelid(modalpanelid);
						
						// Persistim						
						em.persist(mp);
						
						break;
						
					case "wizard":
						
						//Wizard
						
						Wizard w = new Wizard();
						
						w.setAbrev(linia.get("abrev"));
						w.setDescripcio(linia.get("descripcio"));
						w.setEsroot(Boolean.parseBoolean(linia.get("esroot")));
						w.setIdanterior(Integer.parseInt(linia.get("idanterior")));
						w.setIdarbre(Integer.parseInt(linia.get("idarbre")));
						w.setJsp(linia.get("jsp"));
						w.setJspgrafic(linia.get("jspgrafic"));
						
						// Persistim						
						em.persist(w);
						
						break;
		
						}

		} //for
		

		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		//////em.close();
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
	
	
}
