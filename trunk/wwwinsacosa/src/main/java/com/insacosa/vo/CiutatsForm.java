package com.insacosa.vo;


import com.google.inject.Guice;
import com.google.inject.Injector;

import com.insacosa.interfaces.Objecte;

import java.util.ArrayList;
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
import javax.inject.Inject;

import com.insacosa.presentation.CiutatsFinder;
import com.insacosa.presentation.InmoblesFinder;
import com.insacosa.presentation.SolicitudsFinder;
import com.insacosa.presentation.TipusFinder;
import com.insacosa.presentation.UsuarisFinder;
import com.insacosa.utils.FilterBeanInmobles;
import com.insacosa.webui.CiutatItemDto;

import com.degloba.JPA.PersistenceService;

// SERVEIS CAPA APLICACIO
import com.insacosa.application.services.CaracteristiquesApplicationService;
import com.insacosa.application.services.CiutatsApplicationService;
import com.insacosa.application.services.InmoblesApplicationService;
import com.insacosa.application.services.ProvinciesApplicationService;
import com.insacosa.application.services.UsuarisAplicationService;


import guice.modules.BillingModule;


@ManagedBean(name = "ciutats")
@SessionScoped
public class CiutatsForm extends Objecte 
	implements ValueChangeListener,java.io.Serializable {
	
	
	
	// FinderS (lectura)
	//---------------------
	 
    @Inject
    private SolicitudsFinder solicitudsFinder;
    @Inject
    private TipusFinder tipusFinder;
    @Inject
    private InmoblesFinder inmoblesFinder;
    @Inject
    private CiutatsFinder ciutatsFinder;
    @Inject
    private UsuarisFinder usuarisFinder;
	
	
	private static final long serialVersionUID = 1L;

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
	
	
	// SERVEIS D'APLICACIO
	//---------------------
	
	CiutatsApplicationService ciutatsService;
	ProvinciesApplicationService provinciesService;

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
 
    		/*Injector injector = Guice.createInjector(new BillingModule()); 
    		ICiutats ciutats_app = injector.getInstance(ICiutats.class);*/
        	
        	CiutatItemDto ciutat = ciutatsService.getClasseAppByKey(newValue);   
        	
    		// Modifiquem l'String corresponent a la localitat (formulari i filtre)
    		filterBeanInmobles.setLocalitatFilter(ciutat.getCiutatKey());
    		setValorActual(ciutat.getCiutatKey());
    		
    		/*
    		inmobleForm.getFilterValues().put((long) 74, String.valueOf(newValue));
    		inmobleForm.getColumnesOperacions().put((long) 74, "ge");
    		*/
        }  
		
	}
	
		
	/*
	 * Pel combo de provincies
	 */
	public List<SelectItem> getCiutats() {
		
    	
		List<CiutatItemDto> ciutats = ciutatsFinder.findCiutats();
		
		this.setCiutats(ciutats);  
		
		
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
		
		CiutatItemDto ciutatHib = new CiutatItemDto(); 
		
		ciutatHib.setCode(this.getCode());
		ciutatHib.setName(this.getName());
				
		Provincies p = provinciesService.getClasseAppByKey(this.getId()); 
		
		ciutatHib.setKeyProv(p);
		
		
		// hem d'afegir a la llista
		CiutatsForm ciut = new CiutatsForm();
		
		ciut.setId(ciutatHib.getKey());
		ciut.setCode(ciutatHib.getCode());
		ciut.setName(ciutatHib.getName());
		ciut.setKeyProv(ciutatHib.getKeyProv().getProvinciaKey());
		
		llista.add(ciut);
		
	}
	
	
	public void remove() { 
	    
		CiutatsForm ciutat = (CiutatsForm) this.getLlista().get(currentCiutIndex);

		ciutatsService.deleteClasseAppByKey(ciutat.getId());  // esborrem de la BD
    	
    	
		// cal eliminar també de la llista
		this.llista.remove(currentCiutIndex);
	   	
	   }       
	    
	public void store() { 

		CiutatItemDto ciutat = new CiutatItemDto();  
		
		ciutat.setKey(this.getId());
		ciutat.setCode(this.getCode());
		ciutat.setName(this.getName());
		
		/*Injector injector = Guice.createInjector(new BillingModule()); 
		IProvincies provincies_app = injector.getInstance(IProvincies.class);*/
		
		Provincies p = provinciesService.getClasseAppByKey(this.getId()); 
		
		ciutat.setKeyProv(p);
		
		////	this.update(ciutatHib);
	   	
	   	// cal modificar el valor de la llista
	   	CiutatsForm ciut = (CiutatsForm) this.getLlista().get(currentCiutIndex);

		ciut.setCode(ciutat.getCode());
		ciut.setName(ciutat.getName());
		ciut.setKeyProv(ciutat.getKeyProv().getProvinciaKey());
		
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
			Iterator<CiutatItemDto> iter = this.llistaObjectes(CiutatItemDto.class, "name","").iterator();
			while (iter.hasNext())
			{
				CiutatsForm ciutatForm = new CiutatsForm();
				
				CiutatItemDto ciutat = (CiutatItemDto)(iter.next());  // objecte Hibernate
						
				//ciutatForm.setId(ciutat.getProperty("Id").toString());
				ciutatForm.setCode( ciutat.getProperty("Code").toString() );
				ciutatForm.setName( ciutat.getProperty("Name").toString() );
				ciutatForm.setKeyProv(((Provincies)ciutat.getProperty("KeyProv")).getProvinciaKey());
				
				try
				{
					// Calculem la descripcio de la provincia
					//String a = ((Ciutats)this.read(ciutatForm)).getName();
					//ciutatForm.setProvinciaDescripcio(((Provincies)retDescripcio(Provincies.class, ((Provincies)ciutat.getProperty("KeyProv")).getProvinciaKey())).getName());
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
