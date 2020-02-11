package com.degloba.organitzacio.domain.persistence.rdbms.jpa;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.degloba.domain.IValueObject;

import lombok.Data;

@Embeddable
@Data
public class PersonName implements IValueObject {

	private static final long serialVersionUID = -5782631381467586227L;
	private String firstName;
	private String lastName;

	public PersonName() {
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder(31, 17).append(firstName).append(lastName)
				.toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonName)) {
			return false;
		}
		PersonName that = (PersonName) other;
		return new EqualsBuilder().append(this.firstName, that.firstName)
				.append(this.lastName, that.lastName).isEquals();
	}

	@Override
	public String toString() {
		return lastName + firstName;
	}
}
