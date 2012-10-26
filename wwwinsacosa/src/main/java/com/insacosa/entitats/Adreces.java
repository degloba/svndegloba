package com.insacosa.entitats;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "adreces", schema = "dbo", catalog = "INSACO")
public class Adreces implements java.io.Serializable {

	private int idAdreca;
	private PersonesAdreces personesAdreces;

	public Adreces() {
	}

	public Adreces(int idAdreca) {
		this.idAdreca = idAdreca;
	}

	public Adreces(int idAdreca, PersonesAdreces personesAdreces) {
		this.idAdreca = idAdreca;
		this.personesAdreces = personesAdreces;
	}

	@Id
	@Column(name = "id_adreca", unique = true, nullable = false)
	public int getIdAdreca() {
		return this.idAdreca;
	}

	public void setIdAdreca(int idAdreca) {
		this.idAdreca = idAdreca;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "adreces")
	public PersonesAdreces getPersonesAdreces() {
		return this.personesAdreces;
	}

	public void setPersonesAdreces(PersonesAdreces personesAdreces) {
		this.personesAdreces = personesAdreces;
	}

}
