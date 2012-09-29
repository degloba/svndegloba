package com.degloba.interfaces;


import com.degloba.HBM.*;

public interface Usuari_If {
	
	void afegirUsuari(Usuaris usuari);
	String modificarUsuari(Usuaris usuari);
	void eliminarUsuari(String usuari);
	Usuaris cercarUsuari(String usuari);
	Usuaris editPerfil(String nomUsuari);
	boolean usuariValid(Usuaris usuari);
	boolean emailValid(String email);
	String cambiaPassword();
	

}
