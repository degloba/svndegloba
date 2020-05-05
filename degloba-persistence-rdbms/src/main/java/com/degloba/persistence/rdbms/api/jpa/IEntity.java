package com.degloba.persistence.rdbms.jpa;

import java.io.Serializable;


/**
 * Totes les classes d'entitat han d'implementar aquesta interfície sigui directa o indirectament.</br>
 * It is mainly from the tag role, in order to unify the processing system Entity like.
 */
public interface IEntity extends Serializable {

	/**
	 * Obté l'ID de l'entitat. Cada instància de la classe Entity ha de tenir un identificador únic.
	 * EntityId Ha de ser {@link Serializable}.
	 * @return Id Instància de l'entitat.
	 */
	Serializable getId();
	
	/**
	 * Si ja existeix a la base de dades
	 * @return Otherwise, it returns false if the Entity to exist in the database, returns true,
	 */
	boolean existed();
	
	/**
	 * Si no existeix a la base de dades
     * @return Otherwise, it returns {@code true} if the Entity to exist in the database, it returns false,
	 */
	boolean notExisted();
}
