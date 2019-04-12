package com.degloba.security.gae.security;

import static org.junit.Assert.*;



import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;

import com.degloba.security.spring.gae.security.AppRole;

/**
 * @author Luke Taylor
 */
public class AppRoleTests {

	@Test
	public void getAuthorityReturnsRoleName() {
		GrantedAuthority admin = AppRole.ADMIN;

		assertEquals("ROLE_ADMIN", admin.getAuthority());
	}

	@Test
	public void bitsAreCorrect() throws Exception {
		// If this fails, someone has modified the Enum and the Datastore is probably
		// corrupt!
		assertEquals(0, AppRole.ADMIN.getBit());
		assertEquals(1, AppRole.NEW_USER.getBit());
		assertEquals(2, AppRole.USER.getBit());
	}
}
