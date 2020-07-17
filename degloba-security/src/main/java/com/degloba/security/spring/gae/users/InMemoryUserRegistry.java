package com.degloba.security.spring.gae.users;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

/**
 * @author degloba
 */
@Slf4j
public class InMemoryUserRegistry  implements UserRegistry {
	
			
	private final Map<String, GaeUser> users = Collections.synchronizedMap(new HashMap<String, GaeUser>());

	public GaeUser buscarUsuari(String userId) {
		return users.get(userId);
	}

	public void registrarUsuari(GaeUser newUser) {
				
		
		log.debug("Attempting to create new user " + newUser);

		Assert.state(!users.containsKey(newUser.getUserId()));

		users.put(newUser.getUserId(), newUser);
	}

	public void eliminarUsuari(String userId) {
		users.remove(userId);
	}
}
