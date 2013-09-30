package com.degloba.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Document {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key id;
	   
	private String abrev;
	private String entorn;
	private String descripcio;
	private String tecnologia;
	private Integer ordre;
	private Integer idanterior;
	private Boolean esroot;
	private Integer idarbre;
	
	
	public Key getId() {
		return id;
	}

	public void setId(Key id) {	
		this.id = id;
	}
	
	public String getAbrev() {
		return abrev;
	}
	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}
	public String getEntorn() {
		return entorn;
	}
	public void setEntorn(String entorn) {
		this.entorn = entorn;
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public String getTecnologia() {
		return tecnologia;
	}
	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}
	public Integer getOrdre() {
		return ordre;
	}
	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}
	public Integer getIdanterior() {
		return idanterior;
	}
	public void setIdanterior(Integer idanterior) {
		this.idanterior = idanterior;
	}
	public Boolean getEsroot() {
		return esroot;
	}
	public void setEsroot(Boolean esroot) {
		this.esroot = esroot;
	}
	public Integer getIdarbre() {
		return idarbre;
	}
	public void setIdarbre(Integer idarbre) {
		this.idarbre = idarbre;
	}
	
	
	
	

}
