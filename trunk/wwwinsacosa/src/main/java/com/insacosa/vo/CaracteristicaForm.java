package com.insacosa.vo;

import com.google.appengine.api.datastore.Key;


// 
public class CaracteristicaForm implements java.io.Serializable ,Comparable<Object>
{

	private static final long serialVersionUID = 1L;
	private Key key;
	private String nom;
	private Key keyTipus;
  	
	// Constructor por defecto.
  	public CaracteristicaForm() 
  	{
	    super();
	    
  	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Key getKeyTipus() {
		return keyTipus;
	}

	public void setKeyTipus(Key keyTipus) {
		this.keyTipus = keyTipus;
	}

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		
		// comparem camp a camp
		if (getNom().compareTo(((CaracteristicaForm)o).getNom()) == 0 
				&& getKey() == ((CaracteristicaForm)o).getKey() 
				&& getKeyTipus() == ((CaracteristicaForm)o).getKeyTipus())
		{
			return 0;
		}
		
		
		return -1;
	}



  	
  	
}
