package com.degloba.domain;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

/**
 * Entity interface areas. All Entity class must implement this interface either directly or indirectly. It is mainly from the tag role, in order to unify the processing system Entity like.
 */
public interface Entity extends Serializable {

	/**
	 * Obtain ID entity. Each instance of the Entity class must have a unique Id to identify themselves.
	 * EntityId Must be serialized.
	 * @return Id Entity instance.
	 */
	Key getId();
	
	/**
	 * Are already exists in the database
	 * @return Otherwise, it returns false if the Entity to exist in the database, returns true,
	 */
	boolean existed();
	
	/**
	 * Are not exist in the database
     * @return Otherwise, it returns true if the Entity to exist in the database, it returns false,
	 */
	boolean notExisted();
}
