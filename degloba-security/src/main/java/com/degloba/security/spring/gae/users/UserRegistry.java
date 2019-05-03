package com.degloba.security.spring.gae.users;

/**
 *
 * Servei que s’utilitza per mantenir una llista d’usuaris registrats a l’aplicació.
 *
 * @author Luke Taylor
 */
public interface UserRegistry {

	GaeUser findUser(String userId);

	void registerUser(GaeUser newUser);

	void removeUser(String userId);
}
