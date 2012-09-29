package com.degloba.dataModels_JPA;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 * Classe representa una fila de la datatable un cop s'ha passat de caracteristiques
 * en una taula a una llista (com el tramita)
 */
@ManagedBean(name = "inmobleCaract")
@SessionScoped
public class InmobleCaract {
	
	private int idInmoble;
	private Map<Long, String> caractInmobles;
	
	
	public InmobleCaract() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getIdInmoble() {
		return idInmoble;
	}
	public void setIdInmoble(int idInmoble) {
		this.idInmoble = idInmoble;
	}
	public Map<Long, String> getCaractInmobles() {
		return caractInmobles;
	}
	public void setCaractInmobles(Map<Long, String> caractInmobles) {
		this.caractInmobles = caractInmobles;
	}
	
}
