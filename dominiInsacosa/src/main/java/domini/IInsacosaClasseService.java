package domini;

import com.google.appengine.api.datastore.Entity;

public interface IInsacosaClasseService<Entity> {
	
	public IEntityService<Entity> CreateService();

}
