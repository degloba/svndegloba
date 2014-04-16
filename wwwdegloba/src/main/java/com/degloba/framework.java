package com.degloba;

public class framework {

	
	private String nom;
	private String tecnologia;
	private String descripcio;
	private String tipus;
	
	
	public framework() {
		// TODO Auto-generated constructor stub
	}


	public framework(String nom,String tecnologia, String descripcio) {
		super();
		this.nom = nom;
		this.tecnologia = tecnologia;
		this.descripcio = descripcio;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDescripcio() {
		return descripcio;
	}


	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}


	public String getTecnologia() {
		return tecnologia;
	}


	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}


	public String getTipus() {
		return tipus;
	}


	public void setTipus(String tipus) {
		this.tipus = tipus;
	}
	
	
	
  	


}
