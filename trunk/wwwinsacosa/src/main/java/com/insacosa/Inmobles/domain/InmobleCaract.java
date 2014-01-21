package com.insacosa.Inmobles.domain;

import java.util.Map;


import com.google.appengine.api.datastore.Key;


/*
 * Classe representa una fila de la datatable un cop s'ha passat de caracteristiques
 * en una taula a una llista (com el tramita)
 */

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
	public void setKeyInmoble(Key key) {
		this.keyInmoble = key;
	}
	public Map<Key, String> getCaractInmobles() {
		return caractInmobles;
	}
	public void setCaractInmobles(Map<Key, String> caractInmobles) {
		this.caractInmobles = caractInmobles;
	}
	
}
