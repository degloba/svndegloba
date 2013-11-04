package com.insacosa.interfaces;


import com.google.appengine.api.datastore.Key;
import com.insacosa.domain.Usuaris;

public interface Usuari_If {
	
	void afegirUsuari(Usuaris usuari);
	String modificarUsuari(Usuaris usuari);
	void eliminarUsuari(Key usuari);
	Usuaris cercarUsuari(Key usuari);
	Usuaris editPerfil(String nomUsuari);
	Usuaris usuariValid(Usuaris usuari);
	boolean emailValid(String email);
	String cambiaPassword();
	

}
