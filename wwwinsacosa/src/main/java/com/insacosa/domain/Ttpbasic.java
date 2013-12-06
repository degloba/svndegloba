package com.insacosa.domain;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import ddd.domain.BaseAggregateRoot;
import ddd.domain.annotations.DomainAggregateRoot;

@Entity
@DomainAggregateRoot
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Ttpbasic extends BaseAggregateRoot{

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String key;
	
	private String bdtype;
	
	@OneToMany
	private Set<Caracteristiques> caracteristiqueses = new HashSet<Caracteristiques>(
			0);

	public Ttpbasic() {
	}

	
	public Ttpbasic(String key) {
		this.key = key;
	}

	public Ttpbasic(String key, String bdtype,
			Set<Caracteristiques> caracteristiqueses) {
		this.key = key;
		this.bdtype = bdtype;
		this.caracteristiqueses = caracteristiqueses;
	}

    // Accessors for the fields. JPA doesn't use these, but your application does.    
    public String getKey() {        
    	return key;    
    	}

	@Column(name = "BDTYPE", length = 20)
	public String getBdtype() {
		return this.bdtype;
	}

	public void setBdtype(String bdtype) {
		this.bdtype = bdtype;
	}

	public Set<Caracteristiques> getCaracteristiqueses() {
		return this.caracteristiqueses;
	}

	public void setCaracteristiqueses(Set<Caracteristiques> caracteristiqueses) {
		this.caracteristiqueses = caracteristiqueses;
	}


	public void setKey(String key) {
		this.key = key;
	}

	
	
}
