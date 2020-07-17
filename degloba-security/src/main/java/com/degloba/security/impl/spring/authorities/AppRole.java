package com.degloba.security.impl.spring.authorities;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

/**
 * @category Seguretat
 * 
 * @author degloba
 */
public enum AppRole implements GrantedAuthority {
	ADMIN(0), NEW_USER(1), USER(2);

	@Getter
	private final int bit;

	/**
	 * Creates an authority with a specific bit representation. It's important that this
	 * doesn't change as it will be used in the database. The enum ordinal is less
	 * reliable as the enum may be reordered or have new roles inserted which would change
	 * the ordinal values.
	 *
	 * @param bit the permission bit which will represent this authority in the datastore.
	 */
	AppRole(int bit) {
		this.bit = bit;
	}


	public String getAuthority() {
		return "ROLE_"+toString();
	}
}
