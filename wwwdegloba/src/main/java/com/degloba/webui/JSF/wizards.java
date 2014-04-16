package com.degloba.webui.JSF;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpSession;

import com.degloba.jsp;
import com.degloba.jspgrafic;
import com.degloba.visiblePanellDegloba;
import com.degloba.wizard;

public class wizards implements ActionListener{
	
	private String descripcioWizardActiu;  // texte qua anira al titol del panel de togglepanel.jspx
	private List<Boolean> estatWizards=new ArrayList<Boolean>();  // per poder utilitar el mateix fitxer JSPX (togglePAnel.jspx)
										// per tots els Wizards de l'aplicacio
	
	private  ArrayList<wizard> items = new ArrayList<wizard>();
		
	FacesContext context;
	ResourceBundle bundle;
	wizard wz;
	
	public wizards() {
		// TODO Auto-generated constructor stub
		super();
		context  = FacesContext.getCurrentInstance();
		
		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
		omplirWizards();
	}
	
	
	// Mètode que capturarà un parametre  de l'Action per saber quin Wizard hem d'activar
	public String activarWizard ()
	{
		int quinWizard=Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("quinWizard"));
				
		Iterator<wizard> iterador =items.listIterator();
		
		while (iterador.hasNext())
		{
			
			wizard actual = ((wizard)iterador.next());
			
			if (quinWizard==actual.getId())
			{
				estatWizards.set(actual.getId() - 1 ,true);				
				descripcioWizardActiu=bundle.getString("titolwizard." + actual.getAbrev());
			}
			else
				estatWizards.set(actual.getId() - 1 ,false);
		}

		return "wizard";
	}


	public void processAction(ActionEvent event) throws AbortProcessingException {
		// TODO Auto-generated method stub
		
		/*** Visibilitzem el panell dels wizards i invisibilitzem el panell de l"esquema degloba" ***/
		FacesContext context;
		context  = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		visiblePanellDegloba visiblePanellDegloba = (visiblePanellDegloba)session.getAttribute("visiblePanellDegloba");
		
		//Nom�s si el panell de l'esquema degloba esta visible cal modificar la visibilitat
		if (visiblePanellDegloba.getVisible())
			visiblePanellDegloba.cambiaVisible();
		/******************************************************************************************/
		
				
		
				
		// Calculem el wizard que hem de visualitzar.
		String quinWizard = event.getComponent().getAttributes().get("quinWizard").toString();
		
		Iterator<wizard> iterador =items.listIterator();
		
		while (iterador.hasNext())
		{
			
			wizard actual = ((wizard)iterador.next());
			
			if (quinWizard.compareTo(Integer.toString(actual.getId())) == 0)
			{
				estatWizards.set(actual.getId() - 1 ,true);
				descripcioWizardActiu=bundle.getString("titolwizard." + actual.getAbrev());
			}
			else
				estatWizards.set(actual.getId() - 1 ,false); 
		}
		
	}

	public List<Boolean> getEstatWizards() {
		return estatWizards;
	}


	public void setEstatWizards(List<Boolean> estatWizards) {
		this.estatWizards = estatWizards;
	}


	public String getDescripcioWizardActiu() {
		return descripcioWizardActiu;
	}


	public void setDescripcioWizardActiu(String descripcioWizardActiu) {
		this.descripcioWizardActiu = descripcioWizardActiu;
	}
		
	
	public ArrayList<wizard> getItems() {
		return this.items;
	}

	public void setItems(ArrayList<wizard> items) {
		this.items = items;
	}

	
	private void omplirWizards() {
		
		/*Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
		
    	
        if (stmt.execute("SELECT DISTINCT WIZARDID , ABREV, DESCRIPCIO FROM wizards order by wizardid")) {
            rs = stmt.getResultSet();
        }
    	
        
        while (rs.next())
  		{
        	
    		bundle= ResourceBundle.getBundle("idioma", context.getViewRoot().getLocale()); 
			
	  		wz = new wizard();
	  		
	  		wz.setId(rs.getInt("WIZARDID"));
	  		wz.setAbrev(rs.getString("ABREV"));
	  		wz.setDescripcio(rs.getString("DESCRIPCIO"));
	  					
			carregaJSPS(wz);
			carregaJSPGRAFIC(wz);
			
			this.items.add(wz);
			
			estatWizards.add(false);
  		}
        	
		
		}	catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
		}*/
		
		this.setItems(this.items);
	}
	
	
	
	private ArrayList<jsp> carregaJSPS(wizard wz)
	{
		
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<jsp> jsps = new ArrayList<jsp>();
		
		
		try {
			
	    	
	        if (stmt.execute("SELECT JSP FROM wizards WHERE WIZARDID=" + wz.getId())) {
	            rs = stmt.getResultSet();
	        }
	        
	        
	        while (rs.next())
	  			{
		        	jsp js = new jsp();
		        	js.setUrl(rs.getString("JSP"));
		        			        	
	        		jsps.add(js);	        	
	  			}
	
			}	
			catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
			
			wz.setJsps(jsps);
	
	return jsps;
		
	}
	
	
	private ArrayList<jspgrafic> carregaJSPGRAFIC(wizard wz)
	{
		
	
		ArrayList<jspgrafic> jspgrafics = new ArrayList<jspgrafic>();
		return jspgrafics;
		
		/*try {
			
	    	
	        if (stmt.execute("SELECT JSPGRAFIC FROM wizards WHERE WIZARDID=" + wz.getId())) {
	            rs = stmt.getResultSet();
	        }
	        	        
	        while (rs.next())
	  			{
	        		jspgrafic jspgrafic = new jspgrafic();      	
	        		jspgrafic.setUrl(rs.getString("JSPGRAFIC"));		        			        	
	        		jspgrafics.add(jspgrafic);	        		
	  			}
			}	
			catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
			
			wz.setJspsgrafics(jspgrafics);
	
	return jspgrafics;*/
		
	}
	
}
