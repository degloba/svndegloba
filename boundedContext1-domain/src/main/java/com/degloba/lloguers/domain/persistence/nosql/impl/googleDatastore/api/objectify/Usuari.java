package com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

/**
 * @category Entitat (Objectify) : Usuari
 */
@Entity
public class Usuari  {
 
	@Id
	private Long id;
	
	@Unindex
	private String nom;
	
	@Unindex
	/////@Embedded
	private Address adreca;
	
	@Index
	private Key<Role> role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Address getAdreca() {
		return adreca;
	}

	public void setAdreca(Address adreca) {
		this.adreca = adreca;
	}

	public Key<Role> getRole() {
		return role;
	}

	public void setRole(Key<Role> role) {
		this.role = role;
	}



}