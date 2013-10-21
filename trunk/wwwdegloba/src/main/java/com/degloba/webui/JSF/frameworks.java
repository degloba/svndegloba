package com.degloba.webui.JSF;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.richfaces.event.DropEvent;

import com.degloba.framework;
import com.degloba.wizard;
import com.degloba.domain.Framework;
import com.degloba.domain.TipusFramework;
import com.degloba.persistencia.JPA.EMF;


import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class frameworks {
	
	@PersistenceContext    
	protected EntityManager em;  
	
	private List<framework> frameworks;
	private List<framework> containerNET;
	private List<framework> containerJEE;
	
	private framework dragValue; // framework agafat
	private String tecnologiaFramework;
	private String nomFramework;
	
	FacesContext context; 

	
	public frameworks() {
		omplirFrameworks();
	}
		

	public List<framework> getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(List<framework> frameworks) {
		this.frameworks = frameworks;
	}

	public List<framework> getContainerNET() {
		return containerNET;
	}

	public void setContainerNET(List<framework> containerNET) {
		this.containerNET = containerNET;
	}

	public List<framework> getContainerJEE() {
		return containerJEE;
	}

	public void setContainerJEE(List<framework> containerJEE) {
		this.containerJEE = containerJEE;
	}
	
	
    public void processDrop(DropEvent event){
    	
    	this.dragValue = (framework)event.getDragValue();
    	
    	tecnologiaFramework=dragValue.getTecnologia();
    	nomFramework=dragValue.getNom();
    	
    	if (tecnologiaFramework.substring(0, 3).equals(".NE")) {
    		
    		containerNET.add(dragValue);
    		frameworks.remove(dragValue);
    		}
    	else
    		{
    			containerJEE.add(dragValue);
    			frameworks.remove(dragValue);
    		}
    	}
    
    
 	
  	
  	public void reset() {
  		//  tornem a cargar al contenidor tots els Frameworks
  		omplirFrameworks();
  		// eliminem dels contenidor NET i JEE els frameworks
  		containerJEE.clear();
  		containerNET.clear();
  	}
  	
  	
  	private void omplirFrameworks() {

		frameworks = new ArrayList<framework>();
		
		containerNET = new ArrayList<framework>();
		containerJEE = new ArrayList<framework>();
		
		    	
			//EntityManager em = null;
			
			try
			{
				//em = EMF.get().createEntityManager();
				
				TypedQuery<Framework> q2 = em.createQuery("SELECT c FROM Framework c", Framework.class);
				List<Framework> f = q2.getResultList();
				
				Iterator iterador =f.listIterator();
				while (iterador.hasNext())
				{
					Framework actual = ((Framework)iterador.next());
					
					frameworks.add(new framework(actual.getNom(),actual.getTecnologia(), actual.getDescripcio()));
				}
  		
  		
		} catch (Exception ex) {
		    // handle any errors

		}

    }

}
