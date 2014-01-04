package com.degloba.webui.JSF;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

// JSF
import javax.faces.context.FacesContext;
import javax.inject.Inject;


import com.degloba.modalPanel;

// Entitat Domini
import com.degloba.domain.Modalpanel;

// Finder (CQRS)
import com.degloba.presentacio.ModalpanelsFinder;


public class modalPanels {
	
	
    //@Inject
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
		omplirPanelModals();
		return this.items;
	}


	public void setItems(ArrayList<modalPanel> items) {
		this.items = items;
	}


	private void omplirPanelModals() {
		
		try {
			
			List<Modalpanel> rs = modalpanelsFinder.findAll();
    	
			Iterator<Modalpanel> imp=rs.iterator();
	        while (imp.hasNext())
	  		{
	        	Modalpanel mp1 = (Modalpanel) imp.next();
	        	
	    		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
				
		  		mp = new modalPanel();
		  /*		mp.setId(rs.getInt("MODALPANELID"));
		  		mp.setTitol(bundle.getString("titolPanelModal." + rs.getString("MODALPANELID")));
		  		mp.setDefinicio(bundle.getString("definicioPanelModal." + rs.getString("MODALPANELID")));*/
				
				//this.items.add(mp);
	  		}
                
		}	catch (Exception ex) {
			    // handle any errors
		}
	}


	public ModalpanelsFinder getModalpanelsFinder() {
		return modalpanelsFinder;
	}


	public void setModalpanelsFinder(ModalpanelsFinder modalpanelsFinder) {
		this.modalpanelsFinder = modalpanelsFinder;
	}
	
	
}


