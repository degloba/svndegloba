package com.degloba;

import java.util.ArrayList;
import java.util.List;


public class jboss {

	private List<tecnologia> tecnologies;

	public jboss() {
		// TODO Auto-generated constructor stub
		
		omplirTecnologies();
	}
	

	public List<tecnologia> getTecnologies() {
		return tecnologies;
	}

	public void setTecnologies(List<tecnologia> tecnologies) {
		this.tecnologies = tecnologies;
	}

	
 	
  	
  	private void omplirTecnologies() {

		tecnologies = new ArrayList<tecnologia>();
		
		try {
			
    	
/*        if (stmt.execute("SELECT * FROM arquitecturajboss")) {
            rs = stmt.getResultSet();
        }
    	
		while (rs.next())
  		{
			tecnologies.add(new tecnologia(rs.getString("tecnologia"),rs.getString("descripcio")));
  		}*/
  		
		
		} catch (Exception ex) {
		    // handle any errors

		}
		

  	}
	
	
}
