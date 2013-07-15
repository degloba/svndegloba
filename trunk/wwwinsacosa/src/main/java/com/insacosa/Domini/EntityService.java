package com.insacosa.Domini;

import com.google.appengine.api.datastore.Entity;



public class EntityService<TEntity extends InsacosaClasse> implements IEntityService {

	private IRepository<Entity> _repositori; 
}
