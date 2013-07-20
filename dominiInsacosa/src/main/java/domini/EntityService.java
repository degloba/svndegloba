package domini;

import javax.persistence.criteria.Predicate;
import com.google.appengine.api.datastore.Entity;

public class EntityService<E extends Entity> implements IEntityService {

	private IRepository _repositori;

	public EntityService(IRepository _repositori) {
		super();
		this._repositori = _repositori;
	}



	public <E> void Update(E entitat) {
		_repositori.Update(entitat);
		
	}

	public void Delete(Predicate predicate) {
		_repositori.Delete(predicate);
		
	}

	public <E> void Delete(E entitat) {
		// TODO Auto-generated method stub
		
	}

	public <E> void Add(E entitat) {
		// TODO Auto-generated method stub
		
	}




	

	
	
}
