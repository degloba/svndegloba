package com.degloba.security.spring.gae.users;

/** 
 * Servei que s’utilitza per mantenir una llista d’usuaris registrats a l’aplicació.
 *
 * @author degloba
 */
public interface UserRegistry {

	GaeUser buscarUsuari(String userId);

	void registrarUsuari(GaeUser newUser);

	void eliminarUsuari(String userId);
}
