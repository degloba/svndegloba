package com.degloba.webui.JSF;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import com.degloba.modalPanel;

public class modalPanels {
	
	private  ArrayList<modalPanel> items = new ArrayList<modalPanel>();
	modalPanel mp;

	FacesContext context;
	ResourceBundle bundle;


	public modalPanels() {
		super();

		context  = FacesContext.getCurrentInstance();
		
		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
		
		
		//omplirPanelModals();
	}


	public List<modalPanel> getItems() {
		
		return this.items;
	}


	public void setItems(ArrayList<modalPanel> items) {
		this.items = items;
	}


	private void omplirPanelModals() {
		

		
		try {


/*        if (stmt.execute("SELECT MODALPANELID FROM modalpanels")) {
            rs = stmt.getResultSet();
   
        }
    	
        while (rs.next())
  		{
        	
    		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
			
	  		mp = new modalPanel();
	  		mp.setId(rs.getInt("MODALPANELID"));
	  		mp.setTitol(bundle.getString("titolPanelModal." + rs.getString("MODALPANELID")));
	  		mp.setDefinicio(bundle.getString("definicioPanelModal." + rs.getString("MODALPANELID")));
			
			this.items.add(mp);
				
  		}*/

  		
		
		}	catch (Exception ex) {
			    // handle any errors

		}
	}
	
	
}


