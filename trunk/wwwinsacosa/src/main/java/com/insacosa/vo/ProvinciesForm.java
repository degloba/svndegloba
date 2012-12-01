package com.insacosa.vo;

import com.insacosa.interfaces.Inmoble_Impl;
import com.insacosa.interfaces.Objecte;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.insacosa.utils.FilterBeanInmobles;
import com.insacosa.dataModels_JPA.PersistenceService;
import com.insacosa.entitats.Ciutats;
import com.insacosa.entitats.Provincies;

@ManagedBean(name = "provincies")
@SessionScoped
public class ProvinciesForm extends Objecte 
		implements ValueChangeListener,java.io.Serializable  {
	
	private List<ProvinciesForm> llista;
	
	private int currentProvIndex = 1; 
	private ProvinciesForm editedProv; 
	private int page = 1; 
	
	// Columnes de taula
	private int id;
	private Date tmstamp;
	private String code;
	private String name;
	
	private List<SelectItem> provincies = new ArrayList<SelectItem>();
	//private List<SelectItem> ciutats = new ArrayList<SelectItem>(); 
	
	private int valorActual = 8;   // id provincia (Barcelona)
	
	
	Boolean estaLlista = false;  // per saber si es pot updatar la llista per la modificacio d'una provincia
		
	
	public void processValueChange(ValueChangeEvent event)
			throws AbortProcessingException {
		// TODO Auto-generated method stub
		
		Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (oldValue != newValue)
        {
        	FacesContext context = FacesContext.getCurrentInstance(); 
        	InmobleForm inmobleForm = (InmobleForm) context.getApplication().evaluateExpressionGet(context, "#{inmobleForm}", InmobleForm.class);
        	
        	CiutatsForm ciutatsForm = (CiutatsForm) context.getApplication().evaluateExpressionGet(context, "#{ciutats}", CiutatsForm.class);
        	
        	
        	List<SelectItem> novesCiutats = ciutatsForm.getCiutats();
        	novesCiutats.clear();
        	
            // Get the country name and populate the pin value.
        	Inmoble_Impl r = new Inmoble_Impl();
        	
        	// CAL ACTUALITZAR EL COMBO DE CIUTATS EN FUNCIO DE LA PROVINCIA SELECCIONADA
			
    		Provincies provincia = new Provincies();
    		provincia.setId(new Integer(event.getNewValue().toString()));
    		
    		Iterator<Ciutats> iter = r.ciutatsProvincia(provincia).iterator();
    		while (iter.hasNext())
    		{
    			Ciutats ciutatHBM = (Ciutats)(iter.next());  
    			
    			SelectItem item = new SelectItem(ciutatHBM.getId(), ciutatHBM.getName() , "", false, false);
    			                   
    			novesCiutats.add(item);
    			
    		}
    		 
    		// Modifiquem l'String corresponent a la provincia (formulari i filtre)
    		  
    		FilterBeanInmobles filterBeanInmobles = (FilterBeanInmobles) context.getApplication().evaluateExpressionGet(context, "#{filterBeanInmobles}", FilterBeanInmobles.class);

    		filterBeanInmobles.setProvinciaFilter(provincia.getId());
    		
    		
    		setValorActual(provincia.getId());
    		//inmobleForm.setProvincia(provincia.getId());
    		
    		ciutatsForm.setCiutats(novesCiutats);
    		
    		
    		/*
    		inmobleForm.getFilterValues().put((long) 75, String.valueOf(newValue));
    		inmobleForm.getColumnesOperacions().put((long) 75, "ge");
    		*/
        	
        }  
		
	}

	static PersistenceService persistenceService;
	
	/*
	 * Pel combo de provincies
	 */
	public List<SelectItem> getProvincies() {
		
		/*		
		// Crear Entitats
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		//La classe PersistenceService es "ApplicationScoped"
		persistenceService = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Provincies t = new Provincies();
		t.setName("Barcelona");
		
		t.setCode("9");
		em.persist(t);
		
		tx.commit();
		*/
		
		
		List<SelectItem> list;
		
		if (provincies.size() == 0)
		{
		
			list = new ArrayList<SelectItem>();
			 
			SelectItem item;
			 
			Iterator<Objecte> iter = this.llistaObjectes(Provincies.class, "name", "").iterator();
			while (iter.hasNext())
				{
					Provincies provincies = (Provincies)(iter.next());  
							
					item = new SelectItem(provincies.getId(), provincies.getName() , "", false, false);
					
					list.add(item); 
				}
				
			}
			else
				list = provincies;
		
		
		this.setProvincies(list);  // perque no torni a carregar la segona vegada
		
		
		 return list;
	}


	public void setProvincies(List<SelectItem> provincies) {
		this.provincies = provincies;
	}


	
	/*
	 * Per editar una nova provincia netejem els camps d'entrada
	 */
	public void editarNou() {
		
		// Netejem els camps d'entrada
		
		this.setName("");
		this.setCode("");
		this.setTmstamp(new Date());
		
		this.estaLlista = false;
		
	}

	public void insert(ActionEvent arg0) {
		
		// Construim l'objecte Hibernate
		Provincies provincies = new Provincies();  // clase Hibernate
		
		provincies.setCode(this.getCode());
		provincies.setName(this.getName());

		provincies.create();
				
		// hem d'afegir a la llista
		ProvinciesForm prov = new ProvinciesForm();
		
		prov.setId(provincies.getId());
		prov.setName(provincies.getName());
		prov.setCode(provincies.getCode());

		
		this.llista.add(prov);
		
	}
	
	
	public void remove() { 
	    
		ProvinciesForm provincia = (ProvinciesForm) this.getLlista().get(currentProvIndex);
    	 
		// Construim l'objecte Hibernate
		Provincies provincies = new Provincies();  // clase Hibernate
		
		provincies.setId(provincia.id);
		provincies.setCode(provincia.code);
		provincies.setName(provincia.name);

	   	provincies.delete(provincies);  // esborrem de la BD
	   	
		// cal eliminar també de la llista
		this.llista.remove(currentProvIndex);
   
	   }       
	    
	public void store() { 

		// Construim l'objecte Hibernate
		Provincies provincies = new Provincies();  // clase Hibernate
		
		provincies.setId(this.getId());
		provincies.setCode(this.getCode());
		provincies.setName(this.getName());
		
	   	this.update(provincies);
	   	
	   	// cal modificar el valor de la llista
	   	ProvinciesForm prov = (ProvinciesForm) this.getLlista().get(currentProvIndex);

		prov.setName(provincies.getName());
		prov.setCode(provincies.getCode());
  
	   } 	  
	  
	  
	// getters/setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTmstamp() {
		return tmstamp;
	}

	public void setTmstamp(Date tmstamp) {
		this.tmstamp = tmstamp;
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
		
	public List<ProvinciesForm> getLlista() {
			
		List<ProvinciesForm> provList = new ArrayList<ProvinciesForm>();
		// Hem de construir la llista JSF a partir de la llista Hibernate
				
		if (this.llista == null) {
			
			// apliquem un criteri/condicio.En aquest cas es sobre la property "name" de l'objecte Provincies
			Iterator<Objecte> iter = this.llistaObjectes(Provincies.class, "name", "").iterator();
			while (iter.hasNext())
			{
				ProvinciesForm provincia = new ProvinciesForm();
				
				Provincies provincies = (Provincies)(iter.next());  // objecte Hibernate
					
				provincia.setId(provincies.getId());
				provincia.setCode( provincies.getCode() );
				provincia.setId( provincies.getId() );
				provincia.setName( provincies.getName() );
						
				provList.add(provincia);
				
			}
			
			this.setLlista(provList);  // gravem la llista en memoria
		}
		else
		{
			provList = llista;
		}
				
		return provList;
			
	}
		
	public void setLlista(List<ProvinciesForm> llista) {
		this.llista = llista;
	}

		
    public int getCurrentProvIndex() {         
	   	return currentProvIndex;     
	   	}     
		    
	public void setCurrentProvIndex(int currentProvIndex) {         
	   	this.currentProvIndex = currentProvIndex;     
	   }
		    
	public ProvinciesForm getEditedProv() {         
	   	return editedProv;     
	   	}   
		    
	public void setEditedProv(ProvinciesForm editedProv) {         
	   	this.editedProv = editedProv;     
	   	} 
		    
	public int getPage() {         
	   	return page;     
	   }
	
	public void setPage(int page) {         
	   	this.page = page;     
	   	}


	public Boolean getEstaLlista() {
		return estaLlista;
	}


	public void setEstaLlista(Boolean estaLlista) {
		this.estaLlista = estaLlista;
	}



	public int getValorActual() {
		return valorActual;
	}



	public void setValorActual(int valorActual) {
		this.valorActual = valorActual;
	} 

	
}
