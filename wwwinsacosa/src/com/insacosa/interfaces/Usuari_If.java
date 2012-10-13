package com.insacosa.interfaces;


import com.google.appengine.api.datastore.Key;
import com.insacosa.entitats.*;

public interface Usuari_If {
	
	void afegirUsuari(Usuaris usuari);
	String modificarUsuari(Usuaris usuari);
	void eliminarUsuari(Key usuari);
	Usuaris cercarUsuari(Key usuari);
	Usuaris editPerfil(Key nomUsuari);
	boolean usuariValid(Usuaris usuari);
	boolean emailValid(String email);
	String cambiaPassword();
	

}
