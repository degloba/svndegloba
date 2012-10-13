package com.insacosa.dataModels_JPA;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.appengine.api.datastore.Key;

/*
 * Classe representa una fila de la datatable un cop s'ha passat de caracteristiques
 * en una taula a una llista (com el tramita)
 */
@ManagedBean(name = "inmobleCaract")
@SessionScoped
public class InmobleCaract {
	
	private Key keyInmoble;
	private Map<Key, String> caractInmobles;
	
	
	public InmobleCaract() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Key getKeyInmoble() {
		return keyInmoble;
	}
	public void setKeyInmoble(Key keyInmoble) {
		this.keyInmoble = keyInmoble;
	}
	public Map<Key, String> getCaractInmobles() {
		return caractInmobles;
	}
	public void setCaractInmobles(Map<Key, String> caractInmobles) {
		this.caractInmobles = caractInmobles;
	}
	
}
