package com.degloba.domain.persistence.rdbms.jpa;

import javax.persistence.*;

import com.degloba.domain.NamedParameters;
import com.google.appengine.api.datastore.Key;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Abstract entity class legacy. Applies to those objects exist in the database prior to the program. It's the type of ID is unknown, but may not be the version attribute.
 */
@MappedSuperclass
public abstract class LegacyEntity extends BaseEntity {

    private static final long serialVersionUID = 8882145540383345037L;

    /**
     * The entity itself persisted to the database
     */
    public void save() {
        getRepository().save(this);
    }

    /**
     * Entity itself will be deleted from the database
     */
    public void remove() {
        getRepository().remove(this);
    }

    /**
     * According to the entity type and entity ID from the Get Warehousing
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param id ID entity
     * @return Type T or T subtypes, ID for the entity id.
     */
/*    public static <T extends Entity> T get(Class<T> clazz, Serializable id) {
        return getRepository().get(clazz, id);
    }*/
    public static <T extends com.degloba.domain.Entity> T get(Class<T> clazz, Key id) {
        return getRepository().get(clazz, id);
    }    

    /**
     * Locate an entity unmodified version in the database
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param entity  Entity
     * @return Entity Unmodified version.
     */
    public static <T extends com.degloba.domain.Entity> T getUnmodified(Class<T> clazz, T entity) {
        return getRepository().getUnmodified(clazz, entity);
    }

    /**
     * Load Entity based on Entity Type and ID from Warehousing (the difference between the get () method is that none of filling all the attribute values except id outside)
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param id ID entity
     * @return Type T or T subtypes, ID is the id Entity.
     */
    public static <T extends com.degloba.domain.Entity> T load(Class<T> clazz, Serializable id) {
        return getRepository().load(clazz, id);
    }

    /**
     * Find all Entity specified type
     * @param <T> Entity type belongs
     * @param clazz Entity Class belongs
     * @return Eligible Entity List
     */
    public static <T extends com.degloba.domain.Entity> List<T> findAll(Class<T> clazz) {
        return getRepository().createCriteriaQuery(clazz).list();
    }

    /**
     * To "attribute = attribute value" approach based on a single attribute value lookup Entity
     * @param <T> Entity Type genus
     * @param clazz Entity Class belongs
     * @param propName Property name
     * @param value Property values match
     * @return Eligible Entity List
     */
    public static <T extends com.degloba.domain.Entity> List<T> findByProperty(Class<T> clazz, String propName, Object value) {
        return getRepository().findByProperty(clazz, propName, value);
    }

    /**
     * To "attribute = attribute value" approach based on multiple attribute values Entity find, for example, look for name = "Joe Smith", age = 35 employees.
     * @param <T> Entity Type belongs
     * @param clazz Entity Class belongs
     * @param propValues Attribute value matching conditions
     * @return Eligible Entity List
     */
    public static <T extends com.degloba.domain.Entity> List<T> findByProperties(Class<T> clazz, Map<String, Object> propValues) {
        return getRepository().findByProperties(clazz, NamedParameters.create(propValues));
    }
}
