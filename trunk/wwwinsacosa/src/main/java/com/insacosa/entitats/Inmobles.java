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

import com.google.appengine.api.datastore.Key;


@Entity
public class Inmobles{

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)    
	private Key key;

	private Tipus tipus;
	private Usuaris usuaris;
	private Provincies provincies;
	private Ciutats ciutats;
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
	private Set<ValuesCaracteristiques> valuesCaracteristiqueses = new HashSet<ValuesCaracteristiques>(0);
	
	private Set<Caracteristiques> caracteristiqueses = new HashSet<Caracteristiques>(0);
	
	private Set<Solicituds> solicitudses = new HashSet<Solicituds>(0);
	
	private Set<Fotos> fotoses = new HashSet<Fotos>(0);

	public Inmobles() {
	}

	public Inmobles(Key key, long preu, boolean visitat) {
		this.key = key;
		this.preu = preu;
		this.visitat = visitat;
	}

	public Inmobles(Key key, Tipus tipus, Usuaris usuaris,
			Provincies provincies, Ciutats ciutats, String nom, String adreca,
			String smallimageurl, Short numero, Short planta, String puerta,
			Short metres, long preu, byte[] imatge, boolean visitat,
			Set<ValuesCaracteristiques> valuesCaracteristiqueses,
			Set<Caracteristiques> caracteristiqueses,
			Set<Solicituds> solicitudses, Set<Fotos> fotoses) {
		this.key = key;
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
	public Key getKey() {        
		return key;    
		}
		
	public void setKey(Key key) {
		this.key = key;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDTIPUS")
	public Tipus getTipus() {
		return this.tipus;
	}

	public void setTipus(Tipus tipus) {
		this.tipus = tipus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VENEDOR")
	public Usuaris getUsuaris() {
		return this.usuaris;
	}

	public void setUsuaris(Usuaris usuaris) {
		this.usuaris = usuaris;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPROVINCIA")
	public Provincies getProvincies() {
		return this.provincies;
	}

	public void setProvincies(Provincies provincies) {
		this.provincies = provincies;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDLOCALITAT")
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inmobles")
	public Set<ValuesCaracteristiques> getValuesCaracteristiqueses() {
		return this.valuesCaracteristiqueses;
	}

	public void setValuesCaracteristiqueses(
			Set<ValuesCaracteristiques> valuesCaracteristiqueses) {
		this.valuesCaracteristiqueses = valuesCaracteristiqueses;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CARACTINMOBLES", schema = "dbo", catalog = "INSACO", joinColumns = { @JoinColumn(name = "IDINMOBLE", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "IDCARACT", nullable = false, updatable = false) })
	public Set<Caracteristiques> getCaracteristiqueses() {
		return this.caracteristiqueses;
	}

	public void setCaracteristiqueses(Set<Caracteristiques> caracteristiqueses) {
		this.caracteristiqueses = caracteristiqueses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inmobles")
	public Set<Solicituds> getSolicitudses() {
		return this.solicitudses;
	}

	public void setSolicitudses(Set<Solicituds> solicitudses) {
		this.solicitudses = solicitudses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inmobles")
	public Set<Fotos> getFotoses() {
		return this.fotoses;
	}

	public void setFotoses(Set<Fotos> fotoses) {
		this.fotoses = fotoses;
	}

}
