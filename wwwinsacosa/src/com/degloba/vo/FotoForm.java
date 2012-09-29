package com.degloba.vo;


// Clase Value Object de la que todas las vistas deber?an descender.
public class FotoForm implements java.io.Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private byte[] imatge;
  	private String descripcio;
  	private int idInmoble;


	// Constructor por defecto.
  	public FotoForm() 
  	{
	    super();
  	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getIdInmoble() {
		return idInmoble;
	}

	public void setIdInmoble(int idInmoble) {
		this.idInmoble = idInmoble;
	}

	

  
}
