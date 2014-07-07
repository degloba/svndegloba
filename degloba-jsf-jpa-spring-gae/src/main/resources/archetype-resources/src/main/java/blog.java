package com.degloba;

import java.util.ArrayList;


/**
 * @author root
 *
 */
public  class blog  {
	
	private int id;
	private String titol;
	private int ordre;
	private int body;
	private String descripcio;
	
	private  ArrayList<jsp> jsps = new ArrayList<jsp>();

	
	public blog() {
		
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


	public int getOrdre() {
		return ordre;
	}


	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}


	public ArrayList<jsp> getJsps() {
		return jsps;
	}


	public void setJsps(ArrayList<jsp> jsps) {
		this.jsps = jsps;
	}


	public int getBody() {
		return body;
	}


	public void setBody(int body) {
		this.body = body;
	}


	public String getDescripcio() {
		return descripcio;
	}


	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	
	
}
