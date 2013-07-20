package Application.usuaris;

import com.google.appengine.api.datastore.Key;

import entitats.Usuaris;
import Application.IInsacosaClasseApp;

public interface IUsuaris extends IInsacosaClasseApp {
	
	void afegirUsuari(Usuaris usuari);
	String modificarUsuari(Usuaris usuari);
	void eliminarUsuari(Key usuari);
	Usuaris cercarUsuari(Key usuari);
	Usuaris editPerfil(String nomUsuari);
	Usuaris usuariValid(Usuaris usuari);
	boolean emailValid(String email);
	String cambiaPassword();
}
