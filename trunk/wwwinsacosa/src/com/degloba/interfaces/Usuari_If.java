package com.degloba.interfaces;


import com.degloba.HBM.*;
import com.google.appengine.api.datastore.Entity;

public interface Usuari_If {
	
	void afegirUsuari(Usuaris usuari);
	String modificarUsuari(Usuaris usuari);
	void eliminarUsuari(Usuaris usuari);
	Entity cercarUsuari(String usuari);
	Entity editPerfil(String nomUsuari);
	boolean usuariValid(Usuaris usuari);
	boolean emailValid(String email);
	String cambiaPassword();
	

}
