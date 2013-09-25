package com.degloba.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;


@Entity
public class Framework {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key id;
	
	private String nom;
	private String tecnologia;
	private String icon;
	private String descripcio;
	private String url;
	private Integer idtipus;
	private Integer idf;
	
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTecnologia() {
		return tecnologia;
	}
	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIdtipus() {
		return idtipus;
	}
	public void setIdtipus(Integer idtipus) {
		this.idtipus = idtipus;
	}
	public Integer getIdf() {
		return idf;
	}
	public void setIdf(Integer idf) {
		this.idf = idf;
	}
	
	
	
	
	


}
