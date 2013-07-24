package com.insacosa.vo;

import javax.persistence.Transient;

import com.google.appengine.api.datastore.Key;

import com.insacosa.domain.*;


// Clase Value Object de la que todas las vistas deber?an descender.
public class FotoForm implements java.io.Serializable 
{

	private static final long serialVersionUID = 1L;
	private Key key;
	private byte[] imatge;
  	private String descripcio;
  	

  	@Transient
    private Inmobles inmoble;

    // Unowned relationship
  	private String keyInmoble;


	// Constructor por defecto.
  	public FotoForm() 
  	{
	    super();
  	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public byte[] getImatge() {
		return imatge;
	}

	public void setImatge(byte[] imatge) {
		this.imatge = imatge;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String getKeyInmoble() {
		return keyInmoble;
	}

	public void setKeyInmoble(String keyInmoble) {
		this.keyInmoble = keyInmoble;
	}

	public Inmobles getInmoble() {
		return inmoble;
	}

	public void setInmoble(Inmobles inmoble) {
		this.inmoble = inmoble;
	}

	

  
}
