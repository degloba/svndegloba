package domini;

import com.google.appengine.api.datastore.Entity;

import entitats.Usuaris;


public interface IInsacosaClasseService<E> extends IEntityService{
	
	public <E> IEntityService CreateService();
	
	Entity usuariValid(Usuaris usuari);

}
