package com.degloba.webui.JSF;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

// JSF
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.degloba.modalPanel;

// Entitat Domini
import com.degloba.boundedContext.domain.Modalpanel;


// Finder (CQRS)
import com.degloba.readmodel.ModalpanelsFinder;


public class modalPanels {
	
	private static final Logger log = Logger.getLogger(modalPanels.class.getName());
	
    @Inject
    private ModalpanelsFinder modalpanelsFinder;
    
	
	private  ArrayList<modalPanel> items = new ArrayList<modalPanel>();
	modalPanel mp;

	FacesContext context;
	ResourceBundle bundle;


	public modalPanels() {
		super();

		context  = FacesContext.getCurrentInstance();
		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
		
	}


	public List<modalPanel> getItems() {
		return omplirPanelModals();		
	}


	public void setItems(ArrayList<modalPanel> items) {
		this.items = items;
	}


	private List<modalPanel> omplirPanelModals() {
		
		List<modalPanel> panells = new ArrayList<modalPanel>(); 
		
		try {
			
			List<Modalpanel> rs = modalpanelsFinder.findAll();
			
			log.info("Modalpanel count : " + rs.size());
    	
			Iterator<Modalpanel> imp=rs.iterator();
	        while (imp.hasNext())
	  		{
	        	Modalpanel mp1 = (Modalpanel) imp.next();
	        	
	    		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
				
		  		mp = new modalPanel();
		  		mp.setId(mp1.getModalpanelid());
		  		mp.setTitol(bundle.getString("titolPanelModal." + mp1.getModalpanelid().toString()));
		  		mp.setDefinicio(bundle.getString("definicioPanelModal." + mp1.getModalpanelid().toString()));
				
				panells.add(mp);
	  		}
                
		}	catch (Exception ex) {
			    // handle any errors
		}
		
		return panells;
	}


	public ModalpanelsFinder getModalpanelsFinder() {
		return modalpanelsFinder;
	}


	public void setModalpanelsFinder(ModalpanelsFinder modalpanelsFinder) {
		this.modalpanelsFinder = modalpanelsFinder;
	}
	
	
}


