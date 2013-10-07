package com.degloba;

import java.util.ArrayList;
import java.util.List;

import org.richfaces.event.DropEvent;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;

import javax.faces.context.FacesContext;

public class frameworks {
	
	private List<framework> frameworks;
	private List<framework> containerNET;
	private List<framework> containerJEE;
	
	private framework dragValue; // framework agafat
	private String tecnologiaFramework;
	private String nomFramework;
	private Connection con = null;
	
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
    
    

  	private void cargarDriver()
  	{
  		
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.gjt.mm.mysql.Driver");  //funciona el de enredados
			//con = DriverManager.getConnection("jdbc:mysql://localhost/degloba_carrito?user=degloba_root&password=tivoli00");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/degloba_carrito","degloba_root","tivoli00");
    	}
		catch (SQLException se)
		{
			System.err.println("Se ha producido un error al abrir la conexi?n de BD.");  
            System.err.println(se.getMessage());  
		}
		catch (java.lang.ClassNotFoundException s) 
		{ 
			System.out.println("No se encuentra la clase "+ s.toString());
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
		Statement stmt = null;
		ResultSet rs = null;


		frameworks = new ArrayList<framework>();
		
		containerNET = new ArrayList<framework>();
		containerJEE = new ArrayList<framework>();
		
		cargarDriver();
		
		try {
			
    	stmt = con.createStatement();
    	
        if (stmt.execute("SELECT * FROM frameworks")) {
            rs = stmt.getResultSet();
        }
    	
		while (rs.next())
  		{
		
			frameworks.add(new framework(rs.getString("nom"),rs.getString("tecnologia"), rs.getString("descripcio"),rs.getString("idtipus") ));
  		}
  		
  		// guardem a una variable de sessio els frameworks
  		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("cfrm", frameworks);
		
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		

  	}

    }


