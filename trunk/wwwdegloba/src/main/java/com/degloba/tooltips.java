package com.degloba;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class tooltips {
	
	private  ArrayList<tooltip> items = new ArrayList<tooltip>();
	
	FacesContext context;
	ResourceBundle bundle;
	tooltip tooltip;
	

	public tooltips() {
		// TODO Auto-generated constructor stub
		super();
		context  = FacesContext.getCurrentInstance();
		
		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
		omplirtooltips();
	}
	
	public ArrayList<tooltip> getItems() {
		return this.items;
	}

	public void setItems(ArrayList<tooltip> items) {
		this.items = items;
	}
		
	private void omplirtooltips() {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
	    	
	        if (stmt.execute("SELECT ABREV ,PER FROM tooltips")) {
	            rs = stmt.getResultSet();
        }
    	
        
	    bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
        while (rs.next())
  		{
        	
        	try
        	{
	    		
		  		tooltip = new tooltip();
		  		
		  		tooltip.setPer(rs.getString("PER"));
		  		tooltip.setTexte(bundle.getString("texteTooltip." + rs.getString("ABREV")));
			
				this.items.add(tooltip);
	        }
        	catch (Exception ex)  // pot ser que no hi hagi el corresponent "bundle" associat al tooltip
        	{
        	
        	}
  		}
        
   
		
		}	catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		this.setItems(this.items);
	}

}
