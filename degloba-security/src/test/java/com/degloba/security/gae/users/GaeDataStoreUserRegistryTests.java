package com.degloba.security.gae.users;


import java.util.EnumSet;
import java.util.Set;

import com.degloba.security.impl.spring.authorities.AppRole;
import com.degloba.security.impl.spring.users.GaeDatastoreUserRegistry;
import com.degloba.security.spring.gae.users.GaeUser;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Luke Taylor
 */
public class GaeDataStoreUserRegistryTests {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@BeforeEach
	public void setUp() throws Exception {
		helper.setUp();
	}

	@AfterEach
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void correctDataIsRetrievedAfterInsert() {
		GaeDatastoreUserRegistry registry = new GaeDatastoreUserRegistry();

		Set<AppRole> roles = EnumSet.of(AppRole.ADMIN, AppRole.USER);
		String userId = "someUserId";

		GaeUser origUser = new GaeUser(userId, "nick", "nick@blah.com", "Forename",
				"Surname", roles, true);

		registry.registrarUsuari(origUser);

		GaeUser loadedUser = registry.buscarUsuari(userId);

		assertEquals(loadedUser.getUserId(), origUser.getUserId());
		assertEquals(true, loadedUser.isEnabled());
		assertEquals(roles, loadedUser.getAuthorities());
		assertEquals("nick", loadedUser.getNickname());
		assertEquals("nick@blah.com", loadedUser.getEmail());
		assertEquals("Forename", loadedUser.getForename());
		assertEquals("Surname", loadedUser.getSurname());
	}
}
