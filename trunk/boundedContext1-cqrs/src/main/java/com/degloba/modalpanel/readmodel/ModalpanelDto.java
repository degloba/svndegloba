package com.degloba.modalpanel.readmodel;

import java.io.Serializable;

public  class ModalpanelDto  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String titol;
	private String definicio;

	
	public ModalpanelDto() {
		
		super();
   
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitol() {
		return titol;
	}


	public void setTitol(String titol) {
		this.titol = titol;
	}


	public String getDefinicio() {
		return definicio;
	}


	public void setDefinicio(String definicio) {
		this.definicio = definicio;
	}

	
	
	
		
	
}
