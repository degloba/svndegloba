package com.degloba.vo;


import java.util.List;


// Clase Value Object que implementa la vista del comprador.
public class CompradorForm 
{
	// Datos del VO Comprador.
	private int id;
	private String nom;
	private String cognoms;
	private String adreca;
	private String localitat;
	private String codi;	
	private String provincia;	
	private String telefon;
	private String email;
	
	private String seleccionat; // 
	
	List<InmobleForm> llistaInmoblesBuscat;
	List<InmobleForm> llistaInmobleSolicitats;
	
	
	public CompradorForm()
	{
	    	super();
	}
 	
  	// Constructor a partir de los datos.
	public CompradorForm(String nom, String cognoms, String adreca, String localitat, String codi,String provincia, String telefon, String email) 
	{
   		super();
    	setNom(nom);
	    setCognoms(cognoms);    	
    	setAdreca(adreca);    	
    	setLocalitat(localitat);
    	setCodi(codi);
	    setProvincia(provincia);
    	setTelefon(telefon);
	    setEmail(email);	    

  	}

	
  	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalitat() 
  	{
	    return localitat;
  	}
	
  	public void setLocalitat(String newLocalitat) 
  	{
	    localitat = newLocalitat;
  	}
  	

	public String getAdreca() {
		return adreca;
	}

	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}

	public String getCodi() {
		return codi;
	}

	public void setCodi(String codi) {
		this.codi = codi;
	}

	public String getCognoms() {
		return cognoms;
	}

	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

}