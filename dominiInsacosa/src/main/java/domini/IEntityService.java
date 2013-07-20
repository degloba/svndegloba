package domini;

import com.google.appengine.api.datastore.Entity;

public interface IEntityService {

	public <E> void Add(E entitat);
	public <E> void Update(E entitat);
	public <E> void Delete(E entitat);
	

}
