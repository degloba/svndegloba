package com.insacosa.vo;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.insacosa.interfaces.Inmoble_Impl;
import com.insacosa.interfaces.Objecte;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.insacosa.utils.FilterBeanInmobles;

import com.insacosa.dataModels_JPA.PersistenceService;
import com.insacosa.entitats.Ciutats;
import com.insacosa.entitats.Provincies;
import com.insacosa.entitats.Tipus;

import com.degloba.converters.KeyConverter;


@ManagedBean(name = "ciutats")
@SessionScoped
public class CiutatsForm extends Objecte 
	implements ValueChangeListener,java.io.Serializable {
	
	private List<CiutatsForm> llista;
	
	private int currentCiutIndex = 1; 
	private CiutatsForm editedCiut; 
	private int page = 1; 
	
	// Columnes de taula
	private String key;
	private String code;
	private String name;
	private String keyProv;
	
	
	// DESCRIPCIONS
	private String provinciaDescripcio;
	
	Boolean estaLlista = false;  // per saber si es pot updatar la llista per la modificacio d'una ciutat
	
	private List<SelectItem> ciutats = new ArrayList<SelectItem>();
	
	private String valorActual = "967";   // id ciutat (Manresa)
	
	

	public void processValueChange(ValueChangeEvent event)
			throws AbortProcessingException {
		// TODO Auto-generated method stub
		
		Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (oldValue != newValue)
        {
        	FacesContext context = FacesContext.getCurrentInstance(); 
        	InmobleForm inmobleForm = (InmobleForm) context.getApplication().evaluateExpressionGet(context, "#{inmobleForm}", InmobleForm.class);

        	FilterBeanInmobles filterBeanInmobles = (FilterBeanInmobles) context.getApplication().evaluateExpressionGet(context, "#{filterBeanInmobles}", FilterBeanInmobles.class);
 
        	Inmoble_Impl r = new Inmoble_Impl();
        	
        	Ciutats ciutat = r.ciutatPerKey((Key)newValue);   
        	
    		// Modifiquem l'String corresponent a la localitat (formulari i filtre)
    		filterBeanInmobles.setLocalitatFilter(ciutat.getCiutatKey());
    		setValorActual(ciutat.getCiutatKey());
    		
    		/*
    		inmobleForm.getFilterValues().put((long) 74, String.valueOf(newValue));
    		inmobleForm.getColumnesOperacions().put((long) 74, "ge");
    		*/
        }  
		
	}
	
	
	static PersistenceService persistenceService;
	//static EntityManagerFactory emfInstance =  Persistence.createEntityManagerFactory("persistenceServiceSQLServer", new Properties());
	
	/*
	 * Pel combo de provincies
	 */
	public List<SelectItem> getCiutats() {
		
		//EntityManager emHibernateSQL = emfInstance.createEntityManager();
		
		//CriteriaBuilder criteriaBuilder = emHibernateSQL.getCriteriaBuilder();
        //CriteriaQuery<Ciutats> criteriaQuery = criteriaBuilder.createQuery(Ciutats.class);
        //Root<Ciutats> from = criteriaQuery.from(Ciutats.class);
		//CriteriaQuery<Ciutats> select = criteriaQuery.select(from); 
		//TypedQuery<Ciutats> typedQuery = emHibernateSQL.createQuery(select); 
		//List<Ciutats> resultList = typedQuery.getResultList(); 
    	
		List<SelectItem> list;
		
		if (ciutats.size() == 0)
		{
		
			list = new ArrayList<SelectItem>();
			 
			SelectItem item;
			 
			Iterator<Entity> iter = this.llistaObjectes(Ciutats.class, "name", "").iterator();
			while (iter.hasNext())
				{
					Entity ciutats = (Entity)(iter.next());  
							
					item = new SelectItem(ciutats.getKey(), ciutats.getProperty("name").toString() , "", false, false);
					
					list.add(item); 
				}
				
			}
			else
				list = ciutats;
		
		
		this.setCiutats(list);  // perque no torni a carregar la segona vegada
		
		
		 return list;
	}


	
	
	public void setCiutats(List<SelectItem> ciutats) {
		this.ciutats = ciutats;
	}


	
	/*
	 * Per editar un nova ciutat netejem els camps d'entrada
	 */
	public void editarNou() {
		
		// Netejem els camps d'entrada
		
		setName("");
		setCode("");
		setProvinciaDescripcio("");
		
		estaLlista = false;
		
	}	
	
	public void insert(ActionEvent arg0) {
		
		// Construim l'objecte Hibernate
		Ciutats ciutatHib = new Ciutats();  // clase Hibernate
		
		ciutatHib.setCode(this.getCode());
		ciutatHib.setName(this.getName());
		
		Inmoble_Impl r = new Inmoble_Impl();
		Provincies p = r.provinciaPerKey(this.getKeyProv()); 
		
		ciutatHib.setKeyProv(p);
		
		
		// hem d'afegir a la llista
		CiutatsForm ciut = new CiutatsForm();
		
		ciut.setId(ciutatHib.getId());
		ciut.setCode(ciutatHib.getCode());
		ciut.setName(ciutatHib.getName());
		ciut.setKeyProv(ciutatHib.getKeyProv().getProvinciaKey());
		
		llista.add(ciut);
		
	}
	
	
	public void remove() { 
	    
		CiutatsForm ciutatForm = (CiutatsForm) this.getLlista().get(currentCiutIndex);
    	 
		// Construim l'objecte Hibernate
    	Ciutats ciutat = new Ciutats();  // clase Hibernate
		
    	ciutat.setCiutatKey(ciutatForm.getKey());
    	ciutat.setCode(ciutatForm.code);
    	ciutat.setName(ciutatForm.name);
    	//ciutat.setProvincia(ciutatForm.getKeyProv());
    	
    	ciutat.delete(ciutat);  // esborrem de la BD
    	
    	
		// cal eliminar també de la llista
		this.llista.remove(currentCiutIndex);
	   	
	   }       
	    
	public void store() { 

		// Construim l'objecte Hibernate
		Ciutats ciutatHib = new Ciutats();  // clase Hibernate
		
		ciutatHib.setId(this.getId());
		ciutatHib.setCode(this.getCode());
		ciutatHib.setName(this.getName());
		
		Inmoble_Impl r = new Inmoble_Impl();
		Provincies p = r.provinciaPerKey(this.getKeyProv()); 
		
		ciutatHib.setKeyProv(p);
		
		////	this.update(ciutatHib);
	   	
	   	// cal modificar el valor de la llista
	   	CiutatsForm ciut = (CiutatsForm) this.getLlista().get(currentCiutIndex);

		ciut.setCode(ciutatHib.getCode());
		ciut.setName(ciutatHib.getName());
		ciut.setKeyProv(ciutatHib.getKeyProv().getProvinciaKey());
		
	   } 	  
	  
	  
	// getters/setters
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	public List<CiutatsForm> getLlista() {
			
		List<CiutatsForm> ciutList = new ArrayList<CiutatsForm>();
		// Hem de construir la llista JSF a partir de la llista Hibernate
		
		if (this.llista == null) {
				
			// apliquem un criteri/condicio.En aquest cas es sobre la property "name" de l'objecte ScrCiteis
			Iterator<Entity> iter = this.llistaObjectes(Ciutats.class, "name","").iterator();
			while (iter.hasNext())
			{
				CiutatsForm ciutatForm = new CiutatsForm();
				
				Entity ciutat = (Entity)(iter.next());  // objecte Hibernate
						
				//ciutatForm.setId(ciutat.getProperty("Id").toString());
				ciutatForm.setCode( ciutat.getProperty("Code").toString() );
				ciutatForm.setName( ciutat.getProperty("Name").toString() );
				ciutatForm.setKeyProv(((Provincies)ciutat.getProperty("KeyProv")).getProvinciaKey());
				
				try
				{
					// Calculem la descripcio de la provincia
					String a = ((Ciutats)this.read(ciutatForm)).getName();
					ciutatForm.setProvinciaDescripcio(((Provincies)retDescripcio(Provincies.class, ((Provincies)ciutat.getProperty("KeyProv")).getProvinciaKey())).getName());
				}
				catch(Exception ex)  // pot ser que no trobi cap descripcio
				{
				
				}
						
				ciutList.add(ciutatForm);
								
			}
			
			this.setLlista(ciutList);  // gravem la llista en memoria
		}
		else
			{
				ciutList = llista;
			}
				
		return ciutList;
			
	}
		
	public void setLlista(List<CiutatsForm> llista) {
		this.llista = llista;
	}

		
    public int getCurrentCiutIndex() {         
	   	return currentCiutIndex;     
	   	}     
		    
	public void setCurrentCiutIndex(int currentCiutIndex) {         
	   	this.currentCiutIndex = currentCiutIndex;     
	   }
		    
	public CiutatsForm getEditedCiut() {         
	   	return editedCiut;     
	   	}   
		    
	public void setEditedCiut(CiutatsForm editedCiut) {         
	   	this.editedCiut = editedCiut;     
	   	} 
		    
	public int getPage() {         
	   	return page;     
	   }
	
	public void setPage(int page) {         
	   	this.page = page;     
	   	}


	public String getKeyProv() {
		return keyProv;
	}


	public void setKeyProv(String keyProv) {
		this.keyProv = keyProv;
	}


	public String getProvinciaDescripcio() {
		return provinciaDescripcio;
	}


	public void setProvinciaDescripcio(String provinciaDescripcio) {
		this.provinciaDescripcio = provinciaDescripcio;
	}

	public Boolean getEstaLlista() {
		return estaLlista;
	}

	public void setEstaLlista(Boolean estaLlista) {
		this.estaLlista = estaLlista;
	}


	public String getValorActual() {
		return valorActual;
	}


	public void setValorActual(String valorActual) {
		this.valorActual = valorActual;
	}


	
	
}
