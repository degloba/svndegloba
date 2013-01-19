package com.degloba.filtre;

import java.io.InputStream;
import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

public class InventoryFiltreItem implements Serializable {

	
    private static final long serialVersionUID = -5424674835711375626L;
    
    private String key;
  	private String nom;
  	private String adreca;
	private String localitat;

	private String provincia;

	private String smallImageURL;
	
	private String keyTipus;
	private String tipusStr;

	private Short numero,planta;	
	private String puerta;
	
	private Short metres;	
	private long preu;
	private InputStream imatge;	
	private String venedor;  // nomUusari 


	
	private boolean visitat = true;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdreca() {
		return adreca;
	}
	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}
	public String getLocalitat() {
		return localitat;
	}
	public void setLocalitat(String localitat) {
		this.localitat = localitat;
	}
	
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getSmallImageURL() {
		return smallImageURL;
	}
	public void setSmallImageURL(String smallImageURL) {
		this.smallImageURL = smallImageURL;
	}
	public String getKeyTipus() {
		return keyTipus;
	}
	public void setKeyTipus(String keyTipus) {
		this.keyTipus = keyTipus;
	}
	public String getTipusStr() {
		return tipusStr;
	}
	public void setTipusStr(String tipusStr) {
		this.tipusStr = tipusStr;
	}
	public Short getNumero() {
		return numero;
	}
	public void setNumero(Short numero) {
		this.numero = numero;
	}
	public Short getPlanta() {
		return planta;
	}
	public void setPlanta(Short planta) {
		this.planta = planta;
	}
	public String getPuerta() {
		return puerta;
	}
	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}
	public Short getMetres() {
		return metres;
	}
	public void setMetres(Short metres) {
		this.metres = metres;
	}
	public long getPreu() {
		return preu;
	}
	public void setPreu(long preu) {
		this.preu = preu;
	}
	public InputStream getImatge() {
		return imatge;
	}
	public void setImatge(InputStream imatge) {
		this.imatge = imatge;
	}
	public String getVenedor() {
		return venedor;
	}
	public void setVenedor(String venedor) {
		this.venedor = venedor;
	}
	
	public boolean isVisitat() {
		return visitat;
	}
	public void setVisitat(boolean visitat) {
		this.visitat = visitat;
	}
	

   
}
