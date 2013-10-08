package com.degloba.webui;

import java.util.ArrayList;
import java.util.List;

import org.richfaces.event.DropEvent;

import com.degloba.framework;


import javax.faces.context.FacesContext;

public class frameworks {
	
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
		
		
		try {
			

    	
/*        if (stmt.execute("SELECT * FROM frameworks")) {

        }
    	
		while (rs.next())
  		{
		
			frameworks.add(new framework(rs.getString("nom"),rs.getString("tecnologia"), rs.getString("descripcio"),rs.getString("idtipus") ));
  		}*/
  		
  		// guardem a una variable de sessio els frameworks
  		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("cfrm", frameworks);
		
		} catch (Exception ex) {
		    // handle any errors

		}
		

  	}

    }


