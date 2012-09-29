package com.degloba.vo;

import com.degloba.interfaces.Inmoble_Impl;
import com.degloba.interfaces.Objecte;

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

import com.degloba.utils.FilterBeanInmobles;

import com.degloba.HBM.Ciutats;
import com.degloba.HBM.Provincies;

@ManagedBean(name = "ciutats")
@SessionScoped
public class CiutatsForm extends Objecte 
	implements ValueChangeListener {
	
	private List<CiutatsForm> llista;
	
	private int currentCiutIndex = 1; 
	private CiutatsForm editedCiut; 
	private int page = 1; 
	
	// Columnes de taula
	private int id;
	private Date tmstamp;
	private String code;
	private String name;
	private int id_prov;
	
	
	// DESCRIPCIONS
	private String provinciaDescripcio;
	
	Boolean estaLlista = false;  // per saber si es pot updatar la llista per la modificacio d'una ciutat
	
	private List<SelectItem> ciutats = new ArrayList<SelectItem>();
	
	private int valorActual = 967;   // id ciutat (Manresa)
	
	
	@Override
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
        	
        	Ciutats ciutat = r.ciutatPerId((Integer)newValue);   
        	
    		// Modifiquem l'String corresponent a la localitat (formulari i filtre)
    		filterBeanInmobles.setLocalitatFilter(ciutat.getId());
    		setValorActual(ciutat.getId());
    		
    		
    		inmobleForm.getFilterValues().put((long) 74, String.valueOf(newValue));
    		inmobleForm.getColumnesOperacions().put((long) 74, "ge");
        }  
		
	}
	
	
	/*
	 * Pel combo de provincies
	 */
	public List<SelectItem> getCiutats() {
		
		List<SelectItem> list;
		
		if (ciutats.size() == 0)
		{
		
			list = new ArrayList<SelectItem>();
			 
			SelectItem item;
			 
			Iterator<Objecte> iter = this.llistaObjectes(Ciutats.class, "name", "").iterator();
			while (iter.hasNext())
				{
					Ciutats ciutats = (Ciutats)(iter.next());  
							
					item = new SelectItem(ciutats.getId(), ciutats.getName() , "", false, false);
					
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
		setTmstamp(new Date());
		setProvinciaDescripcio("");
		
		estaLlista = false;
		
	}	
	
	public void insert(ActionEvent arg0) {
		
		// Construim l'objecte Hibernate
		Ciutats ciutatHib = new Ciutats();  // clase Hibernate
		
		ciutatHib.setCode(this.getCode());
		ciutatHib.setName(this.getName());
		ciutatHib.setTmstamp(this.getTmstamp());
		ciutatHib.setIdProv(this.getId_prov());
		
		
		// hem d'afegir a la llista
		CiutatsForm ciut = new CiutatsForm();
		
		ciut.setId(ciutatHib.getId());
		ciut.setCode(ciutatHib.getCode());
		ciut.setName(ciutatHib.getName());
		ciut.setId_prov(ciutatHib.getIdProv());
		ciut.setTmstamp(ciutatHib.getTmstamp());
		
		llista.add(ciut);
		
	}
	
	
	public void remove() { 
	    
		CiutatsForm ciutat = (CiutatsForm) this.getLlista().get(currentCiutIndex);
    	 
		// Construim l'objecte Hibernate
    	Ciutats ciutatHib = new Ciutats();  // clase Hibernate
		
    	ciutatHib.setId(ciutat.id);
    	ciutatHib.setCode(ciutat.code);
    	ciutatHib.setName(ciutat.name);
    	ciutatHib.setTmstamp(ciutat.tmstamp);
    	ciutatHib.setIdProv(ciutat.id_prov);
    	
    	////ciutatHib.delete(ciutatHib);  // esborrem de la BD
    	
    	
		// cal eliminar també de la llista
		this.llista.remove(currentCiutIndex);
	   	
	   }       
	    
	public void store() { 

		// Construim l'objecte Hibernate
		Ciutats ciutatHib = new Ciutats();  // clase Hibernate
		
		ciutatHib.setId(this.getId());
		ciutatHib.setCode(this.getCode());
		ciutatHib.setName(this.getName());
		ciutatHib.setTmstamp(this.getTmstamp());
		ciutatHib.setIdProv(this.getId_prov());
		
		////	this.update(ciutatHib);
	   	
	   	// cal modificar el valor de la llista
	   	CiutatsForm ciut = (CiutatsForm) this.getLlista().get(currentCiutIndex);

		ciut.setCode(ciutatHib.getCode());
		ciut.setName(ciutatHib.getName());
		ciut.setId_prov(ciutatHib.getIdProv());
		ciut.setTmstamp(ciutatHib.getTmstamp());
		
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
		
	public List<CiutatsForm> getLlista() {
			
		List<CiutatsForm> ciutList = new ArrayList<CiutatsForm>();
		// Hem de construir la llista JSF a partir de la llista Hibernate
		
		if (this.llista == null) {
				
			// apliquem un criteri/condicio.En aquest cas es sobre la property "name" de l'objecte ScrCiteis
			Iterator<Objecte> iter = this.llistaObjectes(Ciutats.class, "name","").iterator();
			while (iter.hasNext())
			{
				CiutatsForm ciutat = new CiutatsForm();
				
				Ciutats ciutatHib = (Ciutats)(iter.next());  // objecte Hibernate
						
				ciutat.setId(ciutatHib.getId());
				ciutat.setCode( ciutatHib.getCode() );
				ciutat.setName( ciutatHib.getName() );
				ciutat.setTmstamp( ciutatHib.getTmstamp() );
				ciutat.setId_prov(ciutatHib.getIdProv());
				
				try
				{
					// Calculem la descripcio de la provincia
					String a = ((Ciutats)this.read(ciutatHib)).getName();
					ciutat.setProvinciaDescripcio(((Provincies)retDescripcio(Provincies.class, ciutatHib.getIdProv())).getName());
				}
				catch(Exception ex)  // pot ser que no trobi cap descripcio
				{
				
				}
						
				ciutList.add(ciutat);
								
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


	public int getId_prov() {
		return id_prov;
	}


	public void setId_prov(int id_prov) {
		this.id_prov = id_prov;
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


	public int getValorActual() {
		return valorActual;
	}


	public void setValorActual(int valorActual) {
		this.valorActual = valorActual;
	}


	
	
}
