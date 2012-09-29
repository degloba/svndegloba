package com.degloba.HBM;

// Generated 27/01/2012 12:51:10 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Fotos generated by hbm2java
 */
@Entity
@Table(name = "FOTOS", schema = "dbo", catalog = "INSACO")
@TableGenerator(name="TABLE_GEN", schema = "dbo", catalog = "INSACO", table="COMPTADOR", pkColumnName="TAULAID",
	        valueColumnName="COMPTADOR", pkColumnValue="FOTOS", allocationSize=1)
public class Fotos implements java.io.Serializable {

	private int id;
	private Inmobles inmobles;
	private byte[] imatge;
	private String descripcio;

	public Fotos() {
	}

	public Fotos(int id) {
		this.id = id;
	}

	public Fotos(int id, Inmobles inmobles, byte[] imatge, String descripcio) {
		this.id = id;
		this.inmobles = inmobles;
		this.imatge = imatge;
		this.descripcio = descripcio;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDINMOBLE")
	public Inmobles getInmobles() {
		return this.inmobles;
	}

	public void setInmobles(Inmobles inmobles) {
		this.inmobles = inmobles;
	}

	@Column(name = "IMATGE")
	public byte[] getImatge() {
		return this.imatge;
	}

	public void setImatge(byte[] imatge) {
		this.imatge = imatge;
	}

	@Column(name = "DESCRIPCIO", length = 50)
	public String getDescripcio() {
		return this.descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

}
