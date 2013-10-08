package com.degloba;

import java.util.ArrayList;
import java.util.List;

public class frWeb {

	
	private List<framework> frameworks;
	private List<javax.faces.model.SelectItem> nomFrameworks;

	
	
	
	public frWeb() {
		// TODO Auto-generated constructor stub
		
		frWebs();
	}
	

	public List<framework> getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(List<framework> frameworks) {
		this.frameworks = frameworks;
	}
	
	public List<javax.faces.model.SelectItem> getNomFrameworks() {
		return nomFrameworks;
	}
	
	public void setNomFrameworks(List<javax.faces.model.SelectItem> nomFrameworks) {
		this.nomFrameworks = nomFrameworks;
	}

  	
  	
  	private void frWebs() {
	

		frameworks = new ArrayList<framework>();
		nomFrameworks = new ArrayList<javax.faces.model.SelectItem>();
		
		try {
			
    /*	stmt = con.createStatement();
    	
        if (stmt.execute("SELECT * FROM Frameworks")) {
            rs = stmt.getResultSet();
        }
    	
		while (rs.next())
  		{
			frameworks.add(new framework(rs.getString("nom"), rs.getString("tecnologia"),rs.getString("descripcio"), rs.getString("idTipus")));
			nomFrameworks.add(new javax.faces.model.SelectItem(rs.getString("nom")));
  		}*/
  		
  		
		} catch (Exception ex) {
		    // handle any errors
		}
		

  	}
	
	
}
