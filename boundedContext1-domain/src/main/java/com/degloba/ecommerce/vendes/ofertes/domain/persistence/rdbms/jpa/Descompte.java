package com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.persistence.domain.sharedkernel.Money;

/**
 * @category 
 * 
 * @author degloba
 *
 */
@ValueObject
public class Descompte {

	private String causa;
	
	private Money valor;

	public Descompte(String causa, Money valor) {
		this.causa = causa;
		this.valor = valor;
	}
	
	public String getCause() {
		return causa;
	}
	
	public Money getValue() {
		return valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((causa == null) ? 0 : causa.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Descompte other = (Descompte) obj;
		if (causa == null) {
			if (other.causa != null)
				return false;
		} else if (!causa.equals(other.causa))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	
	
}
