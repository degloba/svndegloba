package com.degloba;

public class tecnologia {

	
	private String nom;
	private String descripcio;
	
	
	public tecnologia() {
		// TODO Auto-generated constructor stub
	}


	public tecnologia(String nom, String descripcio) {
		super();
		this.nom = nom;
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
	
}
