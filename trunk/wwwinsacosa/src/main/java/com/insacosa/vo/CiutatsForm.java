package com.insacosa.vo;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// JSF
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.model.SelectItem;

import javax.inject.Inject;


// FINDERS
import com.insacosa.presentation.CiutatsFinder;
import com.insacosa.presentation.InmoblesFinder;
import com.insacosa.presentation.SolicitudsFinder;
import com.insacosa.presentation.TipusFinder;
import com.insacosa.presentation.UsuarisFinder;


import com.insacosa.utils.FilterBeanInmobles;

// DTOs
import com.insacosa.Inmobles.webui.CiutatItemDto;



// SERVEIS CAPA APLICACIO
import com.insacosa.Inmobles.application.services.CiutatsApplicationService;
import com.insacosa.Inmobles.application.services.ProvinciesApplicationService;
import com.insacosa.Inmobles.domain.Ciutats;



@ManagedBean(name = "ciutats")
@SessionScoped
public class CiutatsForm 
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
    
    
	// SERVEIS D'APLICACIO
	//---------------------
	
	CiutatsApplicationService<?> ciutatsService;
	ProvinciesApplicationService<?> provinciesService;
	
	
	private static final long serialVersionUID = 1L;

	private List<CiutatsForm> llista;
	
	private int currentCiutIndex = 1; 
	private CiutatsForm editedCiut; 
	private int page = 1; 
	
	// Columnes de taula
	private String guid;
	private String code;
	private String name;
	private String guidProv;
	
	
	// DESCRIPCIONS
	private String provinciaDescripcio;
	
	Boolean estaLlista = false;  // per saber si es pot updatar la llista per la modificacio d'una ciutat
	
	private List<SelectItem> ciutats = new ArrayList<SelectItem>();
	
	private String valorActual = "967";   // id ciutat (Manresa)
	
	


	public void processValueChange(ValueChangeEvent event)
			throws AbortProcessingException {
		
		Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (oldValue != newValue)
        {
        	FacesContext context = FacesContext.getCurrentInstance(); 
        	InmobleForm inmobleForm = (InmobleForm) context.getApplication().evaluateExpressionGet(context, "#{inmobleForm}", InmobleForm.class);

        	FilterBeanInmobles filterBeanInmobles = (FilterBeanInmobles) context.getApplication().evaluateExpressionGet(context, "#{filterBeanInmobles}", FilterBeanInmobles.class);
       	
        	//Ciutats ciutat = (Ciutats) ciutatsService.getClasseAppByGuid((String) newValue);   
        	
    		// Modifiquem l'String corresponent a la localitat (formulari i filtre)
    		/*filterBeanInmobles.setLocalitatFilter(ciutat.getCiutatKey());
    		setValorActual(ciutat.getCiutatKey());*/
    		
    		/*
    		inmobleForm.getFilterValues().put((long) 74, String.valueOf(newValue));
    		inmobleForm.getColumnesOperacions().put((long) 74, "ge");
    		*/
        }  
		
	}
	
		
	/*
	 * Combo de provincies
	 */
	public List<SelectItem> getCiutats() {
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		
		List<Ciutats> ciutats = ciutatsFinder.findCiutats();
		
		//this.setCiutats(ciutats);  
		
		
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
		
		Ciutats ciutat = new Ciutats(); 
		
		ciutat.setCode(this.getCode());
		ciutat.setName(this.getName());
				
/*		Provincies p = (Provincies) provinciesService.getClasseAppByGuid(this.guidProv); 
		
		ciutat.setGuidProv(p);
		

		
		llista.add(ciut);
		*/
	}
	
	
	public void remove() { 
	    
		CiutatsForm ciutat = (CiutatsForm) this.getLlista().get(currentCiutIndex);

		//ciutatsService.deleteClasseAppByGuid(ciutat.getGuid());  // esborrem de la BD
    	
    	
		// cal eliminar també de la llista
		this.llista.remove(currentCiutIndex);
	   	
	   }       
	    
	public void store() { 

		Ciutats ciutat = new Ciutats();  
						
		ciutat.setCode(this.getCode());
		ciutat.setName(this.getName());
		
		
	/*	BaseEntity p = provinciesService.getClasseAppByGuid(this.getGuid()); 
		
		
		ciutat.setGuidProv(p);
		
		ciutatsService.createClasseApp();
	   	
	   	// cal modificar el valor de la llista
	   	CiutatsForm ciut = (CiutatsForm) this.getLlista().get(currentCiutIndex);

		ciut.setCode(ciutat.getCode());
		ciut.setName(ciutat.getName());
		ciut.setGuidProv(ciutat.getEntityId().getProvinciaGuid());*/
		
	   } 	  
	  
	  
	// getters/setters
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
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
			Iterator<CiutatItemDto> iter = null;  //this.llistaObjectes(CiutatItemDto.class, "name","").iterator();
			while (iter.hasNext())
			{
				CiutatsForm ciutatForm = new CiutatsForm();
				
				CiutatItemDto ciutat = (CiutatItemDto)(iter.next());  // objecte Hibernate
						
				//ciutatForm.setId(ciutat.getProperty("Id").toString());
				ciutatForm.setCode( ciutat.getProperty("Code").toString() );
				ciutatForm.setName( ciutat.getProperty("Name").toString() );
				//ciutatForm.setguidProv(((Provincies)ciutat.getProperty("guidProv")).getProvinciaGuid());
				
				try
				{
					// Calculem la descripcio de la provincia
					//String a = ((Ciutats)this.read(ciutatForm)).getName();
					//ciutatForm.setProvinciaDescripcio(((Provincies)retDescripcio(Provincies.class, ((Provincies)ciutat.getProperty("guidProv")).getProvinciaGuid())).getName());
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


	public String getGuidProv() {
		return guidProv;
	}


	public void setguidProv(String guidProv) {
		this.guidProv = guidProv;
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
