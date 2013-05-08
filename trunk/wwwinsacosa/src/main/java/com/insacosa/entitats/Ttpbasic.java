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

import org.datanucleus.api.jpa.annotations.Extension;

@Entity
public class Ttpbasic {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk",value="true") 
	private String id;
	
	private String key;
	
	private String bdtype;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ttpbasic")
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
