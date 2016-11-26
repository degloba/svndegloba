package com.degloba.organisation.domain.persistence.rdbms.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.domain.IValueObject;

@Embeddable
public class Email implements IValueObject {
	
	private static final long serialVersionUID = -734927401730610904L;
	
	@ManyToOne
	//////@JoinColumn(name = "person_id", nullable = false, referencedColumnName="aggregateId")
	private Person person_id;
	
	
	@Column(name = "email_id")
	private String address;

	protected Email() {
	}

	public Email(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(31, 17).append(address).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Email)) {
			return false;
		}
		Email that = (Email) other;
		return new EqualsBuilder().append(this.address, that.address).isEquals();
	}

	@Override
	public String toString() {
		return address;
	}

	public Person getPerson_id() {
		return person_id;
	}

	public void setPerson_id(Person person_id) {
		this.person_id = person_id;
	}

	
	
}
