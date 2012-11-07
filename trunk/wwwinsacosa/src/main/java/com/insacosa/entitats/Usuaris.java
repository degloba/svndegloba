package com.insacosa.entitats;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;


@Entity
public class Usuaris implements java.io.Serializable {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)    
	private Key key;
	
	private String nomusuari;
	private String nom;
	private String cognoms;
	private String adreca;
	private String localitat;
	private String codi;
	private String provincia;
	private String telefon;
	private String email;
	private String email2;
	private String password;
	private Boolean acord;
	private Set<Solicituds> solicitudses = new HashSet<Solicituds>(0);
	//private Set<Inmobles> inmobleses = new HashSet<Inmobles>(0);

	
	 // Accessors for the fields. JPA doesn't use these, but your application does.    
	public Key getKey() {        
		return key;    
		}
	
	
	public void setKey(Key key) {
		this.key = key;
	}

	public Usuaris() {
	}

	public Usuaris(String nomusuari) {
		this.nomusuari = nomusuari;
	}
	public Usuaris(Key key, String nom, String cognoms, String adreca,
			String localitat, String codi, String provincia, String telefon,
			String email, String email2, String password, Boolean acord,
			Set<Solicituds> solicitudses, Set<Inmobles> inmobleses) {
		this.key = key;
		this.nom = nom;
		this.cognoms = cognoms;
		this.adreca = adreca;
		this.localitat = localitat;
		this.codi = codi;
		this.provincia = provincia;
		this.telefon = telefon;
		this.email = email;
		this.email2 = email2;
		this.password = password;
		this.acord = acord;
		this.solicitudses = solicitudses;
		//this.inmobleses = inmobleses;
	}

	@Column(name = "NOMUSUARI", length = 20)
	public String getNomusuari() {
		return this.nomusuari;
	}

	public void setNomusuari(String nomusuari) {
		this.nomusuari = nomusuari;
	}

	@Column(name = "NOM", length = 20)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "COGNOMS", length = 50)
	public String getCognoms() {
		return this.cognoms;
	}

	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}

	@Column(name = "ADRECA", length = 50)
	public String getAdreca() {
		return this.adreca;
	}

	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}

	@Column(name = "LOCALITAT", length = 30)
	public String getLocalitat() {
		return this.localitat;
	}

	public void setLocalitat(String localitat) {
		this.localitat = localitat;
	}

	@Column(name = "CODI", length = 8)
	public String getCodi() {
		return this.codi;
	}

	public void setCodi(String codi) {
		this.codi = codi;
	}

	@Column(name = "PROVINCIA", length = 30)
	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Column(name = "TELEFON", length = 10)
	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "EMAIL2", length = 50)
	public String getEmail2() {
		return this.email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	@Column(name = "PASSWORD", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ACORD")
	public Boolean getAcord() {
		return this.acord;
	}

	public void setAcord(Boolean acord) {
		this.acord = acord;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuaris")
	public Set<Solicituds> getSolicitudses() {
		return this.solicitudses;
	}

	public void setSolicitudses(Set<Solicituds> solicitudses) {
		this.solicitudses = solicitudses;
	}

/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuaris")
	public Set<Inmobles> getInmobleses() {
		return this.inmobleses;
	}

	public void setInmobleses(Set<Inmobles> inmobleses) {
		this.inmobleses = inmobleses;
	}*/

}
