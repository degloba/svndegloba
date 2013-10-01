package com.degloba.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Wizard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key id;

	private Integer wizardid;
	private Integer idanterior;
	private String abrev;
	private String jsp;
	private String jspgrafic;
	private Boolean esroot;
	private Integer idarbre;
	private String descripcio;
	private Integer idw;
	
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public Integer getWizardid() {
		return wizardid;
	}
	public void setWizardid(Integer wizardid) {
		this.wizardid = wizardid;
	}
	public Integer getIdanterior() {
		return idanterior;
	}
	public void setIdanterior(Integer idanterior) {
		this.idanterior = idanterior;
	}
	public String getAbrev() {
		return abrev;
	}
	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}
	public String getJsp() {
		return jsp;
	}
	public void setJsp(String jsp) {
		this.jsp = jsp;
	}
	public String getJspgrafic() {
		return jspgrafic;
	}
	public void setJspgrafic(String jspgrafic) {
		this.jspgrafic = jspgrafic;
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
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public Integer getIdw() {
		return idw;
	}
	public void setIdw(Integer idw) {
		this.idw = idw;
	}
	
	
	
	
}
