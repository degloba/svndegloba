package com.insacosa.entitats;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.google.appengine.api.datastore.Key;


@Entity
public class Caracteristiques{

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Key key;
	
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Tipus tipus;
	
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
	
	
	private Set<Inmobles> inmobleses = new HashSet<Inmobles>(0);
	private Set<ValuesCaracteristiques> valuesCaracteristiqueses = new HashSet<ValuesCaracteristiques>(
			0);

	
	
	
	public Caracteristiques() {
	}

	public Caracteristiques(Key key, boolean modificable) {
		this.key = key;
		this.modificable = modificable;
	}

	public Caracteristiques(Key key, Tipus tipus, Ttpcontrol ttpcontrol,
			Ttpbasic ttpbasic, String nom, Integer control,
			Integer tamanyControl, Boolean obligatori, boolean modificable,
			String icono, Set<Inmobles> inmobleses,
			Set<ValuesCaracteristiques> valuesCaracteristiqueses) {
		this.key = key;
		this.tipus = tipus;
		this.ttpcontrol = ttpcontrol;
		this.ttpbasic = ttpbasic;
		this.nom = nom;
		this.control = control;
		this.tamanyControl = tamanyControl;
		this.obligatori = obligatori;
		this.modificable = modificable;
		this.icono = icono;
		this.inmobleses = inmobleses;
		this.valuesCaracteristiqueses = valuesCaracteristiqueses;
	}

    // Accessors for the fields. JPA doesn't use these, but your application does.    
    public Key getKey() {        
    	return key;    
    	}
    
	public void setKey(Key key) {
		this.key = key;
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

	public Set<Inmobles> getInmobleses() {
		return this.inmobleses;
	}

	public void setInmobleses(Set<Inmobles> inmobleses) {
		this.inmobleses = inmobleses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caracteristiques")
	public Set<ValuesCaracteristiques> getValuesCaracteristiqueses() {
		return this.valuesCaracteristiqueses;
	}

	public void setValuesCaracteristiqueses(
			Set<ValuesCaracteristiques> valuesCaracteristiqueses) {
		this.valuesCaracteristiqueses = valuesCaracteristiqueses;
	}

	public Tipus getTipus() {
		return tipus;
	}

	public void setTipus(Tipus tipus) {
		this.tipus = tipus;
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
