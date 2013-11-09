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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


import ddd.domain.BaseAggregateRoot;
import ddd.domain.annotations.DomainAggregateRoot;

@Entity
@DomainAggregateRoot
public class Inmobles extends BaseAggregateRoot{

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String inmobleKey;

	@ManyToOne(fetch = FetchType.EAGER)
	private Tipus tipus;
	
	@Transient
	private Usuaris usuaris;

	 // Unowned relationship
    private String usuariKey;
	
	@Transient
	private Provincies provincies;
	
	 // Unowned relationship
    private String provinciaKey;

	
	@Transient
	private Ciutats ciutats;
	
	 // Unowned relationship
    private String ciutatKey;

	
	private String nom;
	private String adreca;
	private String smallimageurl;
	private Short numero;
	private Short planta;
	private String puerta;
	private Short metres;
	private long preu;
	private byte[] imatge;
	private boolean visitat;
	
	

   @Transient
   private Set<ValuesCaracteristiques> valuesCaracteristiqueses;
   
   @Basic(fetch = FetchType.EAGER)
   private Set<String> valuesCaracteristiquesesKeys = new HashSet<String>();

   
   @Transient
   @ManyToMany
   private Set<Caracteristiques> caracteristiqueses;
   
   @Basic(fetch = FetchType.EAGER)
   private Set<String> caracteristiquesesKeys = new HashSet<String>();

	
   @Transient
   private Set<Solicituds> solicitudses;
   
   @Basic(fetch = FetchType.EAGER)
   private Set<String> solicitudsesKeys = new HashSet<String>();
	
   
   @Transient
   private Set<Fotos> fotoses;
   
   @Basic(fetch = FetchType.EAGER)
   private Set<String> fotosesKeys = new HashSet<String>();
	
 	
	
	public Inmobles() {
	}

	public Inmobles(String inmobleKey, long preu, boolean visitat) {
		this.inmobleKey = inmobleKey;
		this.preu = preu;
		this.visitat = visitat;
	}

	public Inmobles(String inmobleKey, Tipus tipus, Usuaris usuaris,
			Provincies provincies, Ciutats ciutats, String nom, String adreca,
			String smallimageurl, Short numero, Short planta, String puerta,
			Short metres, long preu, byte[] imatge, boolean visitat,
			Set<ValuesCaracteristiques> valuesCaracteristiqueses,
			Set<Caracteristiques> caracteristiqueses,
			Set<Solicituds> solicitudses, Set<Fotos> fotoses) {
		this.inmobleKey = inmobleKey;
		this.tipus = tipus;
		this.usuaris = usuaris;
		this.provincies = provincies;
		this.ciutats = ciutats;
		this.nom = nom;
		this.adreca = adreca;
		this.smallimageurl = smallimageurl;
		this.numero = numero;
		this.planta = planta;
		this.puerta = puerta;
		this.metres = metres;
		this.preu = preu;
		this.imatge = imatge;
		this.visitat = visitat;
		this.valuesCaracteristiqueses = valuesCaracteristiqueses;
		this.caracteristiqueses = caracteristiqueses;
		this.solicitudses = solicitudses;
		this.fotoses = fotoses;
	}

	 // Accessors for the fields. JPA doesn't use these, but your application does.    
	public String getInmobleKey() {        
		return inmobleKey;    
		}
		
	public void setInmobleKey(String inmobleKey) {
		this.inmobleKey = inmobleKey;
	}


	public Tipus getTipus() {
		return this.tipus;
	}

	public void setTipus(Tipus tipus) {
		this.tipus = tipus;
	}


	public Usuaris getUsuaris() {
		return this.usuaris;
	}

	public void setUsuaris(Usuaris usuaris) {
		this.usuaris = usuaris;
	}


	public Provincies getProvincies() {
		return this.provincies;
	}

	public void setProvincies(Provincies provincies) {
		this.provincies = provincies;
	}


	public Ciutats getCiutats() {
		return this.ciutats;
	}

	public void setCiutats(Ciutats ciutats) {
		this.ciutats = ciutats;
	}

	@Column(name = "NOM", length = 30)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "ADRECA", length = 30)
	public String getAdreca() {
		return this.adreca;
	}

	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}

	@Column(name = "SMALLIMAGEURL")
	public String getSmallimageurl() {
		return this.smallimageurl;
	}

	public void setSmallimageurl(String smallimageurl) {
		this.smallimageurl = smallimageurl;
	}

	@Column(name = "NUMERO")
	public Short getNumero() {
		return this.numero;
	}

	public void setNumero(Short numero) {
		this.numero = numero;
	}

	@Column(name = "PLANTA")
	public Short getPlanta() {
		return this.planta;
	}

	public void setPlanta(Short planta) {
		this.planta = planta;
	}

	@Column(name = "PUERTA", length = 2)
	public String getPuerta() {
		return this.puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	@Column(name = "METRES")
	public Short getMetres() {
		return this.metres;
	}

	public void setMetres(Short metres) {
		this.metres = metres;
	}

	@Column(name = "PREU", nullable = false, precision = 18, scale = 0)
	public long getPreu() {
		return this.preu;
	}

	public void setPreu(long preu) {
		this.preu = preu;
	}

	@Column(name = "IMATGE")
	public byte[] getImatge() {
		return this.imatge;
	}

	public void setImatge(byte[] imatge) {
		this.imatge = imatge;
	}

	@Column(name = "VISITAT", nullable = false)
	public boolean isVisitat() {
		return this.visitat;
	}

	public void setVisitat(boolean visitat) {
		this.visitat = visitat;
	}


	public Set<Caracteristiques> getCaracteristiqueses() {
		return this.caracteristiqueses;
	}

	public void setCaracteristiqueses(Set<Caracteristiques> caracteristiqueses) {
		this.caracteristiqueses = caracteristiqueses;
	}

	

	public String getUsuariKey() {
		return usuariKey;
	}

	public void setUsuariKey(String usuariKey) {
		this.usuariKey = usuariKey;
	}

	public String getProvinciaKey() {
		return provinciaKey;
	}

	public void setProvinciaKey(String provinciaKey) {
		this.provinciaKey = provinciaKey;
	}

	public String getCiutatKey() {
		return ciutatKey;
	}

	public void setCiutatKey(String ciutatKey) {
		this.ciutatKey = ciutatKey;
	}

	public Set<ValuesCaracteristiques> getValuesCaracteristiqueses() {
		return valuesCaracteristiqueses;
	}

	public void setValuesCaracteristiqueses(
			Set<ValuesCaracteristiques> valuesCaracteristiqueses) {
		this.valuesCaracteristiqueses = valuesCaracteristiqueses;
	}

	public Set<String> getValuesCaracteristiquesesKeys() {
		return valuesCaracteristiquesesKeys;
	}

	public void setValuesCaracteristiquesesKeys(
			Set<String> valuesCaracteristiquesesKeys) {
		this.valuesCaracteristiquesesKeys = valuesCaracteristiquesesKeys;
	}

	public Set<String> getCaracteristiquesesKeys() {
		return caracteristiquesesKeys;
	}

	public void setCaracteristiquesesKeys(Set<String> caracteristiquesesKeys) {
		this.caracteristiquesesKeys = caracteristiquesesKeys;
	}

	public Set<Solicituds> getSolicitudses() {
		return solicitudses;
	}

	public void setSolicitudses(Set<Solicituds> solicitudses) {
		this.solicitudses = solicitudses;
	}

	public Set<String> getSolicitudsesKeys() {
		return solicitudsesKeys;
	}

	public void setSolicitudsesKeys(Set<String> solicitudsesKeys) {
		this.solicitudsesKeys = solicitudsesKeys;
	}

	public Set<Fotos> getFotoses() {
		return fotoses;
	}

	public void setFotoses(Set<Fotos> fotoses) {
		this.fotoses = fotoses;
	}

	public Set<String> getFotosesKeys() {
		return fotosesKeys;
	}

	public void setFotosesKeys(Set<String> fotosesKeys) {
		this.fotosesKeys = fotosesKeys;
	}

	
	
	
	
}
