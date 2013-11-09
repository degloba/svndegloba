package com.insacosa.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import ddd.domain.BaseAggregateRoot;
import ddd.domain.annotations.DomainAggregateRoot;

@Entity
@DomainAggregateRoot
public class Caracteristiques extends BaseAggregateRoot{

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private String id;
	
	private String caracteristicaKey;
	
	
	@Transient
	private Ttpcontrol ttpcontrol;
	
	// Unowned relationship
    private String ttpcontrolKey;
	
	@Transient
	private Ttpbasic ttpbasic;
	
	// Unowned relationship
    private String ttpbasicKey;

		
	private String nom;
	private Integer control;
	private Integer tamanyControl;
	private Boolean obligatori;
	private boolean modificable;
	private String icono;
	

	@Transient
	private Set<Inmobles> inmobles;
	
	@Basic(fetch = FetchType.EAGER)
	private Set<String> inmoblesKeys = new HashSet<String>();

	@Transient
	private Set<ValuesCaracteristiques> valuesCaracteristiques;
	
	@Basic(fetch = FetchType.EAGER)
	private Set<String> valuesCaracteristiquesKeys = new HashSet<String>();
	
	
	public Caracteristiques() {
	}

	public Caracteristiques(String caracteristicaKey, boolean modificable) {
		this.caracteristicaKey = caracteristicaKey;
		this.modificable = modificable;
	}

	public Caracteristiques(String caracteristicaKey, Ttpcontrol ttpcontrol,
			Ttpbasic ttpbasic, String nom, Integer control,
			Integer tamanyControl, Boolean obligatori, boolean modificable,
			String icono, Set<Inmobles> inmobles,
			Set<ValuesCaracteristiques> valuesCaracteristiqueses) {
		this.caracteristicaKey = caracteristicaKey;
		this.ttpcontrol = ttpcontrol;
		this.ttpbasic = ttpbasic;
		this.nom = nom;
		this.control = control;
		this.tamanyControl = tamanyControl;
		this.obligatori = obligatori;
		this.modificable = modificable;
		this.icono = icono;
		this.inmobles = inmobles;
		this.valuesCaracteristiques = valuesCaracteristiqueses;
	}

    // Accessors for the fields. JPA doesn't use these, but your application does.    
    public String getCaracteristicaKey() {        
    	return caracteristicaKey;    
    	}
    
	public void setCaracteristicaKey(String caracteristicaKey) {
		this.caracteristicaKey = caracteristicaKey;
	}    

	

	public Ttpcontrol getTtpcontrol() {
		return this.ttpcontrol;
	}

	public void setTtpcontrol(Ttpcontrol ttpcontrol) {
		this.ttpcontrol = ttpcontrol;
	}

	public Ttpbasic getTtpbasic() {
		return this.ttpbasic;
	}

	public void setTtpbasic(Ttpbasic ttpbasic) {
		this.ttpbasic = ttpbasic;
	}

	@Column(name = "NOM", length = 50)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "CONTROL")
	public Integer getControl() {
		return this.control;
	}

	public void setControl(Integer control) {
		this.control = control;
	}

	@Column(name = "TAMANY_CONTROL")
	public Integer getTamanyControl() {
		return this.tamanyControl;
	}

	public void setTamanyControl(Integer tamanyControl) {
		this.tamanyControl = tamanyControl;
	}

	@Column(name = "OBLIGATORI")
	public Boolean getObligatori() {
		return this.obligatori;
	}

	public void setObligatori(Boolean obligatori) {
		this.obligatori = obligatori;
	}

	@Column(name = "MODIFICABLE", nullable = false)
	public boolean isModificable() {
		return this.modificable;
	}

	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}

	@Column(name = "ICONO", length = 50)
	public String getIcono() {
		return this.icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}



	public Set<Inmobles> getInmobles() {
		return inmobles;
	}

	public void setInmobles(Set<Inmobles> inmobles) {
		this.inmobles = inmobles;
	}

	public Set<String> getInmoblesKeys() {
		return inmoblesKeys;
	}

	public void setInmoblesKeys(Set<String> inmoblesKeys) {
		this.inmoblesKeys = inmoblesKeys;
	}

	public Set<ValuesCaracteristiques> getValuesCaracteristiques() {
		return valuesCaracteristiques;
	}

	public void setValuesCaracteristiques(
			Set<ValuesCaracteristiques> valuesCaracteristiques) {
		this.valuesCaracteristiques = valuesCaracteristiques;
	}

	public Set<String> getValuesCaracteristiquesKeys() {
		return valuesCaracteristiquesKeys;
	}

	public void setValuesCaracteristiquesKeys(Set<String> valuesCaracteristiquesKeys) {
		this.valuesCaracteristiquesKeys = valuesCaracteristiquesKeys;
	}


	public String getTtpcontrolKey() {
		return ttpcontrolKey;
	}

	public void setTtpcontrolKey(String ttpcontrolKey) {
		this.ttpcontrolKey = ttpcontrolKey;
	}

	public String getTtpbasicKey() {
		return ttpbasicKey;
	}

	public void setTtpbasicKey(String ttpbasicKey) {
		this.ttpbasicKey = ttpbasicKey;
	}

	
	
}
