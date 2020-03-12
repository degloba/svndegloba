package com.degloba.viatges.domainpersistence.rdbms.jpa;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Un hotel on els usuaris poden reservar estades.
 */
@XmlRootElement
@Entity
public class Hotel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Set<Reserva> reservations = new HashSet<Reserva>();

	private Long id;

	private String nom;

	private String adreca;

	private String ciutat;

	private String state;

	private String zip;

	private String country;

	private BigDecimal price;

	@Id
	@XmlAttribute
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlAttribute
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@XmlAttribute
	public String getAddress() {
		return adreca;
	}

	public void setAddress(String address) {
		this.adreca = address;
	}

	@XmlAttribute
	public String getCity() {
		return ciutat;
	}

	public void setCity(String city) {
		this.ciutat = city;
	}

	@XmlAttribute
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@XmlAttribute
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@XmlAttribute
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@XmlAttribute
	@Column(precision = 6, scale = 2)
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Reserva createReserva(Usuari usuari) {
		return new Reserva(this, usuari);
	}

	@Override
	public String toString() {
		return "Hotel(" + nom + "," + adreca + "," + ciutat + "," + zip + ")";
	}


	@OneToMany(mappedBy = "hotel")
	@XmlTransient
	@JsonIgnore
	public Set<Reserva> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reserva> reservations) {
		this.reservations = reservations;
	}
}
