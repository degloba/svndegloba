package com.insacosa.vo;

// IOC - GUICE
import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.modules.BillingModule;


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

import javax.inject.Inject;

import com.insacosa.presentation.CiutatsFinder;
import com.insacosa.presentation.InmoblesFinder;
import com.insacosa.presentation.ProvinciesFinder;
import com.insacosa.presentation.SolicitudsFinder;
import com.insacosa.presentation.TipusFinder;
import com.insacosa.presentation.UsuarisFinder;
import com.insacosa.utils.FilterBeanInmobles;
import com.insacosa.webui.CiutatItemDto;
import com.insacosa.webui.ProvinciaItemDto;


// SERVEIS APLICACIO
import com.insacosa.application.services.CaracteristiquesApplicationService;
import com.insacosa.application.services.InmoblesApplicationService;
import com.insacosa.application.services.ProvinciesApplicationService;
import com.insacosa.application.services.UsuarisAplicationService;
import com.insacosa.domain.Ciutats;
import com.insacosa.domain.Provincies;


@ManagedBean(name = "provincies")
@SessionScoped
public class ProvinciesForm 
		implements ValueChangeListener,java.io.Serializable  {
	
	
	
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
    private ProvinciesFinder provinciesFinder;
    @Inject
    private UsuarisFinder usuarisFinder;
    
    
	
	private List<ProvinciaItemDto> llista;
	
	private int currentProvIndex = 1; 
	private ProvinciesForm editedProv; 
	private int page = 1; 
	
	// Columnes de taula
	private String idProvincia;
	private String code;
	private String name;
	
	private List<SelectItem> provincies = new ArrayList<SelectItem>();
	//private List<SelectItem> ciutats = new ArrayList<SelectItem>(); 
	
	private String valorActual = "8";   // id provincia (Barcelona)
	
	
	Boolean estaLlista = false;  // per saber si es pot updatar la llista per la modificacio d'una provincia
	
	
	// SERVEIS D'APLICACIO
	//---------------------
	
	ProvinciesApplicationService provinciesService;
	InmoblesApplicationService inmoblesService;
		
	
	//@Override
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
        	        	
        	List<SelectItem> novesCiutats = new ArrayList<SelectItem>();
       	
        	// CAL ACTUALITZAR EL COMBO DE CIUTATS EN FUNCIO DE LA PROVINCIA SELECCIONADA
			
    		Provincies provincia = new Provincies();
  		
    		    		
    		List<Ciutats> ciutats = ciutatsFinder.ciutatsProvincia(provincia);
    		
    		 
    		// Modifiquem l'String corresponent a la provincia (formulari i filtre)
    		  
    		FilterBeanInmobles filterBeanInmobles = (FilterBeanInmobles) context.getApplication().evaluateExpressionGet(context, "#{filterBeanInmobles}", FilterBeanInmobles.class);

    		filterBeanInmobles.setProvinciaFilter(provincia.getProvinciaKey());
    		
    		
    		setValorActual(provincia.getProvinciaKey());
    		//inmobleForm.setProvincia(provincia.getId());
    		
    		ciutatsForm.setCiutats(novesCiutats);
    		
    		
    		/*
    		inmobleForm.getFilterValues().put((long) 75, String.valueOf(newValue));
    		inmobleForm.getColumnesOperacions().put((long) 75, "ge");
    		*/
        	
        }  
		
	}

	
	/*
	 * Pel combo de provincies
	 */
	public List<SelectItem> getProvincies() {
		
		List<SelectItem> list;
			
		List<Provincies> provincies = provinciesFinder.findProvincies();
		
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
		
		this.estaLlista = false;
		
	}

	public void insert() {
		
		Provincies provincia = new Provincies();  
		
		provincia.setCode(this.getCode());
		provincia.setName(this.getName());
		
		provinciesService.createClasseApp();
				
		// hem d'afegir a la llista
		ProvinciaItemDto prov = new ProvinciaItemDto();
		
		prov.setId(provincia.getKey());
		prov.setName(provincia.getName());
		prov.setCode(provincia.getCode());

		
		this.llista.add(prov);
		
	}
	
	
	public void remove() { 
	
		provinciesService.deleteClasseAppByGuid(getGuid());  // esborrem de la BD
	   	
		// cal eliminar també de la llista
		llista.remove(currentProvIndex);
   
	   }       
	    

	private String getGuid() {
		// TODO Auto-generated method stub
		return null;
	}


	public void store() { 

		Provincies provincia = new Provincies();  // clase del domini
		
		provincia.setProvinciaKey(this.getIdProvincia());
		provincia.setCode(this.getCode());
		provincia.setName(this.getName());
			
		provinciesService.updateClasseApp(provincia);
  	
  
	   } 	  
	  
	  
	// getters/setters
	public String getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
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



	public String getValorActual() {
		return valorActual;
	}



	public void setValorActual(String valorActual) {
		this.valorActual = valorActual;
	} 

	
}
